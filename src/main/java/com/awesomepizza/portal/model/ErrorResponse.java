package com.awesomepizza.portal.model;

import lombok.Data;

@Data
public class ErrorResponse {

	private String errorCode;
	private String errorMessage;
}
