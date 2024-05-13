package com.awesomepizza.portal.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class NewOrderStatus {
	@NotNull
	private String newOrderStatus;
	
}
