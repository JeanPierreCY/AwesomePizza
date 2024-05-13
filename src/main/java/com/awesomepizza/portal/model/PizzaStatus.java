package com.awesomepizza.portal.model;

public enum PizzaStatus {
	ORDERED("ORDERED"),
    READY("READY"),
    DELIVERED("DELIVERED"),
	COMPLETED("COMPLETED"),
	CANCELED("CANCELED");
	
	
	PizzaStatus(String value) {
		this.value = value;
	}

	private String value;
	
	public String getValue() {
		return value;
	}
	
	
	
}
