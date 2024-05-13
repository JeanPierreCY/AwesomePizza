package com.awesomepizza.portal.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awesomepizza.portal.model.AwesomePizzaOrder;
import com.awesomepizza.portal.model.NewOrderStatus;
import com.awesomepizza.portal.model.OrderUpd;
import com.awesomepizza.portal.model.Pizza;
import com.awesomepizza.portal.model.PizzaStatus;
import com.awesomepizza.portal.model.exception.OrderNotFoundException;
import com.awesomepizza.portal.model.exception.PizzaStatusException;
import com.awesomepizza.portal.repository.PortalRepository;
import com.awesomepizza.portal.transformation.DataTransformation;

@Service
public class PortalServiceImpl implements PortalService{
	
	@Autowired
	PortalRepository repository;
	
	@Autowired
	DataTransformation dtTransformation;
	
	@Override
	public AwesomePizzaOrder createOrder(Pizza pizza) {
		AwesomePizzaOrder order = dtTransformation.mapCreateOrder(pizza);
		return repository.save(order);
	}
	
	@Override
	public Optional<AwesomePizzaOrder> getOrderById(BigInteger id) {
		return repository.findById(id);
	}
	
	@Override
	public List<AwesomePizzaOrder> getAllOrders() {
		return repository.findAll();
	}

	@Override
	public AwesomePizzaOrder updateOrder(OrderUpd orderUpd) throws PizzaStatusException, OrderNotFoundException {
		Optional<AwesomePizzaOrder> order = repository.findById(orderUpd.getOrderId());
		if(order.isEmpty()) {
			throw new OrderNotFoundException("Order not found");
		}
		try {
			order.get().setStatus(PizzaStatus.valueOf(orderUpd.getNewOrderStatus()).getValue());
		}catch(IllegalArgumentException e) {
			throw new PizzaStatusException("Status not valid");
		}
		return repository.save(order.get());
	}

	@Override
	public List<AwesomePizzaOrder> updateAllNotCompletedOrders(NewOrderStatus newOrderState) throws PizzaStatusException {
		ArrayList<AwesomePizzaOrder> notCompletedOrders = repository.findAllNotCompletedOrders();
		try {
			notCompletedOrders.stream().forEach(o -> o.setStatus(PizzaStatus.valueOf(newOrderState.getNewOrderStatus()).getValue()));
		}catch(IllegalArgumentException e) {
			throw new PizzaStatusException("Status not valid");
		}
		return repository.saveAll(notCompletedOrders);
	}
	
}
