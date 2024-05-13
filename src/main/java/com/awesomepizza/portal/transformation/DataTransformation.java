package com.awesomepizza.portal.transformation;

import com.awesomepizza.portal.model.AwesomePizzaOrder;
import com.awesomepizza.portal.model.Pizza;
import com.awesomepizza.portal.model.PizzaStatus;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class DataTransformation {
	public AwesomePizzaOrder mapCreateOrder(Pizza pizza){
		AwesomePizzaOrder order = new AwesomePizzaOrder();
		order.setPizza(pizza);
		order.setStatus(PizzaStatus.ORDERED.getValue());
		order.setTimestamp(String.valueOf(LocalDateTime.now()));
		return order;
	}
}
