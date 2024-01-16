package com.school.sba.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("serial")
@Getter
@AllArgsConstructor
public class AdminException extends RuntimeException 
{
	private String message;
}
