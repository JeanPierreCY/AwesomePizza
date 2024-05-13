package com.awesomepizza.portal.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.awesomepizza.portal.model.AwesomePizzaOrder;
import com.awesomepizza.portal.model.NewOrderStatus;
import com.awesomepizza.portal.model.OrderUpd;
import com.awesomepizza.portal.model.Pizza;
import com.awesomepizza.portal.model.exception.OrderNotFoundException;
import com.awesomepizza.portal.model.exception.PizzaStatusException;

public interface PortalService {
	public AwesomePizzaOrder createOrder(Pizza pizza);
	public Optional<AwesomePizzaOrder> getOrderById(BigInteger id);
	public List<AwesomePizzaOrder> getAllOrders();
	public AwesomePizzaOrder updateOrder(OrderUpd orderUpd) throws PizzaStatusException, OrderNotFoundException;
	public List<AwesomePizzaOrder> updateAllNotCompletedOrders(NewOrderStatus newOrderState)  throws PizzaStatusException;
}
