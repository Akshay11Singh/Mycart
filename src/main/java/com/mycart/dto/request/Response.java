package com.mycart.dto.request;

import org.springframework.stereotype.Component;

@Component
public class Response {
	String responseType;

	protected Response() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the responseType
	 */
	public String getResponseType() {
		return responseType;
	}

	/**
	 * @param responseType the responseType to set
	 */
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Response [responseType=" + responseType + "]";
	}
	
	
}
