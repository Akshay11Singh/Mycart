package com.mycart.controller.login;

import java.text.SimpleDateFormat;
import java.util.Locale;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycart.dto.request.Header;
import com.mycart.dto.request.Request;
import com.mycart.dto.request.RequestDetails;
import com.mycart.dto.request.Response;
import com.mycart.dto.request.ResponseDetails;
import com.mycart.entity.Registration;
import com.mycart.service.MyRegistrationService;

@RestController
@RequestMapping("/mycartreg")
public class MyRegistrationController {
	@Autowired
	Header headerObject;
	@Autowired
	Request request;
	@Autowired
	RequestDetails requestDetails;
	@Autowired
	RegistrationMapper registrationMapper;
	
	@Autowired
	MyRegistrationService myRegistrationService;
	
	@Autowired
	Response response;
	@Autowired
	ResponseDetails responseDetails;

	@PostMapping(value = "/registration", consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registration(@RequestBody String header) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		// mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		JSONObject jsonObject = new JSONObject(header);
		if (!header.equals("") && header != null) {
			try {
				headerObject = mapper.readValue(jsonObject.get("Header").toString(), Header.class);
				request = mapper.readValue(jsonObject.get("Request").toString(), Request.class);
				requestDetails = mapper.readValue(jsonObject.get("RequestDetails").toString(), RequestDetails.class);
			} catch (Exception e) {
				 throw new Exception(e.toString()) ;
			}
		} else {
			throw new Exception("Request Body is Empty!!");
		}
		System.out.println(" cid:::  " + headerObject);
		System.out.println(" cid::: "+request);
		System.out.println(" cid::: "+requestDetails);
		
		Registration registration = Registration.getRegfactory();
		
		registration.setChannelId(headerObject.getChannelId());
		registration.setTimeStamp(headerObject.getTimeStamp());
		registration.setServiceProvider(headerObject.getServiceProvider());
		registration.setRequestType(request.getRequestType());
		registration.setUserType(request.getUserType());	
		registration.setBirthDate(requestDetails.getBirthDate());
		registration.setEmailId(requestDetails.getEmailId());
		registration.setFirstName(requestDetails.getFirstName());
		registration.setLastName(requestDetails.getLastName());
		registration.setMobileNumber(requestDetails.getMobileNumber());
		
		if(myRegistrationService.save(registration).isPresent()) {
			response.setResponseType("Success");
			responseDetails.setMessage("User Registration Sucessfull..!!!");
			registrationMapper.setHeader(headerObject);
			registrationMapper.setRequest(request);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			requestDetails.setBirthDate(formatter.parse(registration.getBirthDate()));
			registrationMapper.setRequestDetails(requestDetails);
			registrationMapper.setResponse(response);
			registrationMapper.setResponseDetails(responseDetails);
			
		}
		// service call to save in the db return 0 or 1 and create response object and assign it to registration mapper and return.
		
		System.out.println(jsonObject);
		
		return ResponseEntity.ok(registrationMapper);

	}

	@GetMapping("/registration")
	public String registration() {

		return "my registraion is called!!";

	}
}

@Component
class RegistrationMapper{
	
	Header header;
	Request request;
	RequestDetails requestDetails;
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
	public RequestDetails getRequestDetails() {
		return requestDetails;
	}
	/**
	 * @param requestDetails the requestDetails to set
	 */
	public void setRequestDetails(RequestDetails requestDetails) {
		this.requestDetails = requestDetails;
	}
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