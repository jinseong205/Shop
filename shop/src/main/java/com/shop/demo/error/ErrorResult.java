package com.shop.demo.error;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ErrorResult {
	private String errorMessage;

	public ErrorResult(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
