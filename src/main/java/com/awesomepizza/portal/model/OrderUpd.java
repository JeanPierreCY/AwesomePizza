package com.awesomepizza.portal.model;

import java.math.BigInteger;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderUpd {
	@NotNull
	private BigInteger orderId;
	@NotNull
	private String newOrderStatus;
	
}
