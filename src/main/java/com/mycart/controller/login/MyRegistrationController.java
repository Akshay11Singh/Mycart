package com.mycart.controller.login;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycart.dto.request.Header;
import com.mycart.dto.request.RegistrationMapper;
import com.mycart.dto.request.Request;
import com.mycart.dto.request.RequestDetails;
import com.mycart.dto.request.Response;
import com.mycart.dto.request.ResponseDetails;
import com.mycart.entity.Registration;
import com.mycart.service.MyLoginControllerService;
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
	@Autowired
	MyLoginControllerService myLoginControllerService;

	@PostMapping(value = "/registration", consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registration(@RequestBody String header) throws Exception {

		try {
		ObjectMapper mapper = new ObjectMapper();
		// mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		JSONObject jsonObject = new JSONObject(header);
		if (!header.equals("") && header != null) {
			try {
				headerObject = mapper.readValue(jsonObject.get("header").toString(), Header.class);
				request = mapper.readValue(jsonObject.get("request").toString(), Request.class);
				requestDetails = mapper.readValue(jsonObject.get("requestDetails").toString(), RequestDetails.class);
			} catch (Exception e) {

				response.setResponseType("Fail");
				responseDetails.setMessage(e.getMessage());
				registrationMapper.setResponse(response);
				registrationMapper.setResponseDetails(responseDetails);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(registrationMapper);
			}
		} else {
			response.setResponseType("Fail");
			responseDetails.setMessage("Request Body is empty!!!");
			registrationMapper.setResponse(response);
			registrationMapper.setResponseDetails(responseDetails);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(registrationMapper);
		}

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
		
		// to check if user already existed with email or mobile
		
		if (myLoginControllerService.findByEmailId(requestDetails.getEmailId()).isPresent() || 
				myLoginControllerService.findByMobileNumber(requestDetails.getMobileNumber()).isPresent()) {
			response.setResponseType("User Already Registered");
			responseDetails.setMessage("User Already Registered..!!!");
			registrationMapper.setHeader(headerObject);
			registrationMapper.setRequest(request);
			registrationMapper.setResponse(response);
			registrationMapper.setResponseDetails(responseDetails);
			return ResponseEntity.status(HttpStatus.OK).body(registrationMapper);
		}
		
		if (myRegistrationService.save(registration).isPresent()) {
			response.setResponseType("Success");
			responseDetails.setMessage("User Registration Sucessfull..!!!");
			registrationMapper.setHeader(headerObject);
			registrationMapper.setRequest(request);
			/*
			 * SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd",
			 * Locale.ENGLISH);
			 * requestDetails.setBirthDate(formatter.parse(registration.getBirthDate()));
			 * registrationMapper.setRequestDetails(requestDetails);
			 */
			registrationMapper.setResponse(response);
			registrationMapper.setResponseDetails(responseDetails);

		} else {
			response.setResponseType("Fail");
			responseDetails.setMessage("User Registration Failed!!!");
			registrationMapper.setResponse(response);
			registrationMapper.setResponseDetails(responseDetails);

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(registrationMapper);
		}
		// service call to save in the db return 0 or 1 and create response object and
		// assign it to registration mapper and return.

		System.out.println(jsonObject);
		}
		catch (Exception e) {
			response.setResponseType("Fail");
			responseDetails.setMessage(e.getMessage().toString());
			registrationMapper.setResponse(response);
			registrationMapper.setResponseDetails(responseDetails);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(registrationMapper);
		}
		return ResponseEntity.status(HttpStatus.OK).body(registrationMapper);

	}

	@GetMapping("/registration")
	public String registration() {
		return "my registraion is called!!";
	}
}
