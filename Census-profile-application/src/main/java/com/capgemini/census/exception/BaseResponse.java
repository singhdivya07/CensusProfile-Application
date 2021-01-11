package com.capgemini.census.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseResponse {
	private Object response;
    private int statusCode;
   
}