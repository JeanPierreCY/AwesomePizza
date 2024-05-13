package com.awesomepizza.portal.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.awesomepizza.portal.model.ErrorResponse;
import com.awesomepizza.portal.model.exception.OrderNotFoundException;
import com.awesomepizza.portal.model.exception.PizzaStatusException;


@ControllerAdvice
@Component
public class GlobalExceptionHandler {

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handle(OrderNotFoundException ex) {
		return buildDefaultErrorResponse(ex, "404", ex.getMessage());
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handle(PizzaStatusException ex) {
		return buildDefaultErrorResponse(ex, "400", ex.getMessage());
	}
	
	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handle(MethodArgumentNotValidException ex) {
		return buildDefaultErrorResponse(ex, "400", "Bad Request");
	}

	/**
	 * Generic Exception
	 **/
	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handle(Exception ex) {
		return buildDefaultErrorResponse(ex, "500", "Internal Error");
	}

	// Build default ResponseBase
	private static ErrorResponse buildDefaultErrorResponse(Exception ex, String code, String message) {
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode(code);
		response.setErrorMessage(message);
		return response;
	}

}
