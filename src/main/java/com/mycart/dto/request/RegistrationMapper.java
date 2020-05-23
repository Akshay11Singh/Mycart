package com.mycart.dto.request;

import org.springframework.stereotype.Component;

@Component
public class RegistrationMapper{
	
	Header header;
	Request request;
	//RequestDetails requestDetails;
	Response response;
	ResponseDetails responseDetails;
	/**
	 * @return the header
	 */
	public Header getHeader() {
		return header;
	}
	/**
	 * @param header the header to set
	 */
	public void setHeader(Header header) {
		this.header = header;
	}
	/**
	 * @return the request
	 */
	public Request getRequest() {
		return request;
	}
	/**
	 * @param request the request to set
	 */
	public void setRequest(Request request) {
		this.request = request;
	}
	/**
	 * @return the requestDetails
	 */
	
	/**
	 * @return the response
	 */
	public Response getResponse() {
		return response;
	}
	/**
	 * @param response the response to set
	 */
	public void setResponse(Response response) {
		this.response = response;
	}
	/**
	 * @return the responseDetails
	 */
	public ResponseDetails getResponseDetails() {
		return responseDetails;
	}
	/**
	 * @param responseDetails the responseDetails to set
	 */
	public void setResponseDetails(ResponseDetails responseDetails) {
		this.responseDetails = responseDetails;
	}
	
}