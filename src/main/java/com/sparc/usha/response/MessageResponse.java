package com.sparc.usha.response;

import lombok.Data;

@Data
public class MessageResponse {
	String message;

	public MessageResponse(String message) {
		super();
		this.message = message;
	}

}
