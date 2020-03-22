package com.altran.roche.model;

public class ResultCode {
	

	private String statusCode ; 

	public String getRESULT_STATUS() {
		return statusCode;
	}

	public void setRESULT_STATUS(String rESULT_STATUS) {
		statusCode = rESULT_STATUS;
	}
	
	public ResultCode(String statusCode) {
		super();
		this.statusCode = statusCode;
	}
	
}