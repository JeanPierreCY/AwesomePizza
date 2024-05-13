package com.awesomepizza.portal.controller;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.awesomepizza.portal.model.AwesomePizzaOrder;
import com.awesomepizza.portal.model.NewOrderStatus;
import com.awesomepizza.portal.model.OrderUpd;
import com.awesomepizza.portal.model.Pizza;
import com.awesomepizza.portal.model.exception.OrderNotFoundException;
import com.awesomepizza.portal.model.exception.PizzaStatusException;
import com.awesomepizza.portal.service.PortalService;

@RestController
public class PortalController {
	
	@Autowired
	PortalService service;
	
	/*
	 * create a pizza order
	 * @param pizza - object (Pizza) to save into the order
	 * @return object saved or throw an exception if pizza has one or more constraint violations
	 */
	@RequestMapping(value="/createOrder", method=RequestMethod.POST)
	public AwesomePizzaOrder createOrder(@Valid @RequestBody Pizza pizza){
		return service.createOrder(pizza);
	}
	
	/*
	 * get a single AwesomePizzaOrder by its ID
	 * @param id - id of the order to get
	 * @return object searched or throw an exception if id doesn't exist 
	 */
	@RequestMapping(value="/orders/{id}", method=RequestMethod.GET)
	public AwesomePizzaOrder getOrderById(@PathVariable BigInteger id) throws OrderNotFoundException{
		Optional<AwesomePizzaOrder> order = service.getOrderById(id);
		if(order.isEmpty()) {
			throw new OrderNotFoundException("Order not found");
		}
		return order.get();
	}
	
	/*
	 * get all AwesomePizzaOrders
	 * @return all objects (List<AwesomePizzaOrder>)
	 */
	@RequestMapping(value="/orders", method=RequestMethod.GET)
	public List<AwesomePizzaOrder>  getAllOrders() {
		return service.getAllOrders();
	}
	
	/*
	 * update a pizza order
	 * @param orderUpd - object (OrderUpd) used to contains data useful to the update
	 * @return object updated or throw an exception if the order or the status are incorrect
	 */
	@RequestMapping(value="/updateOrder", method=RequestMethod.PUT)
	public AwesomePizzaOrder updateOrder(@Valid @RequestBody OrderUpd orderUpd) throws PizzaStatusException, OrderNotFoundException{
		return service.updateOrder(orderUpd);
	}
	
	
	/*
	 * update all not COMPLETED pizza orders
	 * @param newOrderState - object (NewOrderState) used to contains data useful to the update
	 * @return objects updated or throw an exception if the status is incorrect
	 */
	@RequestMapping(value="/updateAllNotCompletedOrders", method=RequestMethod.PUT)
	public List<AwesomePizzaOrder> updateAllNotCompletedOrders(@Valid @RequestBody NewOrderStatus newOrderState) throws PizzaStatusException{
		return service.updateAllNotCompletedOrders(newOrderState);
	}
}
