package com.shop.demo.error;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ErrorResult {
	private String message;

	public ErrorResult(String message) {
		this.message = message;
	}
	
	
}
