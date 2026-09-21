package com.carepulse.exception;

public class HospitalNotFoundException extends RuntimeException {
	public HospitalNotFoundException(String message) {
		super(message);
	}

}
