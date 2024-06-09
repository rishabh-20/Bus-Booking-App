package org.jsp.reservationapp.dto;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	
	
		private String message;
		private T data;
		private int statusCode;
	

}
