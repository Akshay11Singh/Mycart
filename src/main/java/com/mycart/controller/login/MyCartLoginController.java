package com.mycart.controller.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycart.dto.request.Header;
import com.mycart.dto.request.RegistrationMapper;
import com.mycart.dto.request.Request;
import com.mycart.dto.request.RequestDetails;
import com.mycart.dto.request.Response;
import com.mycart.dto.request.ResponseDetails;
import com.mycart.service.MyLoginControllerService;

@RestController
public class MyCartLoginController {
	@Autowired
	Header headerObject;
	@Autowired
	Request request;
	@Autowired
	RequestDetails requestDetails;
	@Autowired
	RegistrationMapper registrationMapper;

	@Autowired
	MyLoginControllerService myLoginControllerService;

	@Autowired
	Response response;
	@Autowired
	ResponseDetails responseDetails;
	boolean flag =false;
	@PostMapping("/login")
	public ResponseEntity<RegistrationMapper> getLogin(HttpServletRequest request, @RequestParam("email") String email,@RequestParam("mobileNo") String mobileNo) {
		
		try {
			if (!email.equals("") && email != null || (!mobileNo.equals("") && mobileNo != null)) {
				myLoginControllerService.findByEmailId(email).ifPresent((temp)->{
						
					if(!temp.isSaveVerificationFlag()) {
						response.setResponseType("Fail");
						responseDetails.setMessage(" User is not Verified Ask for otp!!");
						registrationMapper.setResponse(response);
						registrationMapper.setResponseDetails(responseDetails);
						flag=true;
					}
					
				});
				
			/*	myLoginControllerService.findByMobileNumber(mobileNo).ifPresent((temp)->{
					if(!temp.isSaveVerificationFlag()) {
						response.setResponseType("Fail");
						responseDetails.setMessage(" User is not Verified Ask for otp!!");
						registrationMapper.setResponse(response);
						registrationMapper.setResponseDetails(responseDetails);
						flag=true;
					}
				}); */
				
				if(!myLoginControllerService.findByEmailId(email).isPresent() && !myLoginControllerService.findByMobileNumber(mobileNo).isPresent()) {
					response.setResponseType("Fail");
					responseDetails.setMessage("Email Id or mobile not found.. User is not registered");
					registrationMapper.setResponse(response);
					registrationMapper.setResponseDetails(responseDetails);
					return ResponseEntity.status(HttpStatus.OK).body(registrationMapper);
				}
				if(!flag) {
					response.setResponseType("Success");
					responseDetails.setMessage("Login successfull.. Ask for OtpS");
					registrationMapper.setResponse(response);
					registrationMapper.setResponseDetails(responseDetails);
				}
				
			} else {
				response.setResponseType("Fail");
				responseDetails.setMessage(" Email or Mobile Number Cannot Be Empty");
				registrationMapper.setResponse(response);
				registrationMapper.setResponseDetails(responseDetails);
				return ResponseEntity.status(HttpStatus.OK).body(registrationMapper);
			}

		} catch (Exception e) {
			response.setResponseType("Fail");
			responseDetails.setMessage(e.getMessage().toString());
			registrationMapper.setResponse(response);
			registrationMapper.setResponseDetails(responseDetails);
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(registrationMapper);
		}
		return ResponseEntity.status(HttpStatus.OK).body(registrationMapper);
	}
}
