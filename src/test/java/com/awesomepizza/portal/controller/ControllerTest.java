package com.awesomepizza.portal.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.awesomepizza.portal.model.AwesomePizzaOrder;
import com.awesomepizza.portal.model.NewOrderStatus;
import com.awesomepizza.portal.model.OrderUpd;
import com.awesomepizza.portal.model.Pizza;
import com.awesomepizza.portal.model.PizzaStatus;
import com.awesomepizza.portal.model.exception.OrderNotFoundException;
import com.awesomepizza.portal.model.exception.PizzaStatusException;
import com.awesomepizza.portal.repository.PortalRepository;

@SpringBootTest
class ControllerTest {
	
	@Autowired
    private PortalController controller;
	
	@Autowired
    private PortalRepository repo;
	
	Pizza mapPizza() {
    	Pizza pizza = new Pizza();
    	pizza.setName("Margherita");
    	pizza.setSize("S");
        
       return pizza;
    }
	
	AwesomePizzaOrder createOrder() {
        AwesomePizzaOrder order = controller.createOrder(mapPizza());
        return order;
    }

	@Test
    void testCreateOrder() {
        AwesomePizzaOrder order = createOrder();
        
        AwesomePizzaOrder orderF = new AwesomePizzaOrder();
        orderF.setId(order.getId());
        orderF.setPizza(mapPizza());
        orderF.setStatus(PizzaStatus.ORDERED.getValue());
        orderF.setTimestamp(order.getTimestamp());
   
        assertEquals(order, orderF);
    }
    
	@Test
    void testGetOrderById() throws OrderNotFoundException {
    	AwesomePizzaOrder order = createOrder();
        AwesomePizzaOrder order2 = controller.getOrderById(order.getId());     
        assertEquals(order, order2);
        
    }
	
	@Test
    void testGetOrderById404() {
		Exception exception = assertThrows(OrderNotFoundException.class, () -> {
			controller.getOrderById(new BigInteger("500"));
	    });
		assertEquals("Order not found", exception.getMessage());
    }
    
	@Test
    void testUpdateOrder() throws PizzaStatusException, OrderNotFoundException {
		AwesomePizzaOrder order = createOrder();
		OrderUpd oupd = new OrderUpd();
		oupd.setOrderId(order.getId());
		oupd.setNewOrderStatus(PizzaStatus.READY.getValue());
		
        AwesomePizzaOrder orderUpd = controller.updateOrder(oupd);     
        assertEquals(orderUpd.getStatus(), PizzaStatus.READY.getValue());
    }
	
	@Test
    void testUpdateOrder404(){
        OrderUpd oupd = new OrderUpd();
		oupd.setOrderId(new BigInteger("500"));
		oupd.setNewOrderStatus(PizzaStatus.READY.getValue());
        
        Exception exception = assertThrows(OrderNotFoundException.class, () -> {
			controller.updateOrder(oupd);
	    });
		assertEquals("Order not found", exception.getMessage());
    }
	
	@Test
    void testUpdateOrder400(){
		AwesomePizzaOrder order = createOrder();
        OrderUpd oupd = new OrderUpd();
		oupd.setOrderId(order.getId());
		oupd.setNewOrderStatus("NOT READY");
        
        Exception exception = assertThrows(PizzaStatusException.class, () -> {
			controller.updateOrder(oupd);
	    });
		assertEquals("Status not valid", exception.getMessage());
    }
	
	@Test
    void testUpdateAllNotCompletedOrders() throws PizzaStatusException{
		repo.deleteAll();
		AwesomePizzaOrder order = createOrder();
		NewOrderStatus nos = new NewOrderStatus();
		nos.setNewOrderStatus(PizzaStatus.CANCELED.getValue());
        
        List<AwesomePizzaOrder> order2 = controller.updateAllNotCompletedOrders(nos);
        
        assertEquals(
        		order2.stream().allMatch(y -> y.getStatus().equals(PizzaStatus.CANCELED.getValue())),
        		true);
    }
	
	@Test
    void testUpdateAllNotCompletedOrders400(){
		repo.deleteAll();
		AwesomePizzaOrder order = createOrder();
		NewOrderStatus nos = new NewOrderStatus();
		nos.setNewOrderStatus("NOT READY");
        
		
		Exception exception = assertThrows(PizzaStatusException.class, () -> {
			controller.updateAllNotCompletedOrders(nos);
	    });
		assertEquals("Status not valid", exception.getMessage());
    }
	
}
