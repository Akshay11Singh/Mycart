package com.mycart.controller.login;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycart.dto.request.RegistrationMapper;
import com.mycart.dto.request.RequestDetails;
import com.mycart.dto.request.Response;
import com.mycart.dto.request.ResponseDetails;
import com.mycart.repository.MyRegistrationRepo;
import com.mycart.service.MyCartOtp;
import com.mycart.utils.MailUtility;

@Controller
public class MyCartOtpGeneration {

	@Autowired
	MyCartOtp myCartOtp;
	@Autowired
	Response response;
	@Autowired
	ResponseDetails responseDetails;
	@Autowired
	RegistrationMapper registrationMapper;
	@Autowired
	RequestDetails requestDetails;
	JSONObject json;
	ObjectMapper mapper;
	@Autowired
	MyRegistrationRepo myRegistrationRepo;
	
	@PostMapping("/otp")
	public ResponseEntity<RegistrationMapper> getOtp(HttpServletRequest request, @RequestParam("otp") String otp) {
	//public String getOtp(HttpServletRequest request, @RequestParam("otp") String otp) {
		String same = "";
		/*
		  json = new JSONObject(otp); mapper = new ObjectMapper(); otp =
		  json.getString("otp");
		 */
		String otpToCheck = (String) request.getSession().getAttribute("sessionotp");
		System.out.println(" sessionotp." +otpToCheck);
		System.out.println(" otp" +otp);
		if (otp.trim().equals(otpToCheck.trim())) {
			same = otpToCheck;
			response.setResponseType("Success");
			responseDetails.setMessage("OTP verified successfully!!");
			registrationMapper.setResponse(response);
			registrationMapper.setResponseDetails(responseDetails);
			myRegistrationRepo.saveVerificationFlag(true,(String)request.getSession().getAttribute("sessionemail"));
			//request.setAttribute("same", same + " " + response.getResponseType() + " " + responseDetails.getMessage());

		} else {
			response.setResponseType("Failed");
			responseDetails.setMessage("Otp Verification failed!!");
			registrationMapper.setResponse(response);
			registrationMapper.setResponseDetails(responseDetails);
		}

		return ResponseEntity.status(HttpStatus.OK).body(registrationMapper);
		//return "otp";
	}

	@GetMapping("/otp")
	public ResponseEntity<RegistrationMapper> generateotp(HttpServletRequest request,@RequestParam(name = "email") String email, @RequestParam(name = "mobileno") String mobileNo) {
	// public String generateotp(HttpServletRequest request, @RequestParam(name="email") String email, @RequestParam(name="mobileno") String mobileNo) {
		String otp = "";
		try {
			if (!email.equals("") && email != null || (!mobileNo.equals("") && mobileNo != null)) {
				otp = String.valueOf(myCartOtp.generateOTP());
				otp = otp.substring(otp.length() - 4, otp.length());
				JSONObject jsonObject = new JSONObject();
				request.getSession().setAttribute("sessionotp", otp);
				request.getSession().setAttribute("sessionemail", email);
				request.getSession().setAttribute("sessionmobileno", mobileNo);
				response.setResponseType("Success");
				responseDetails.setMessage("Otp Sent on registered email id");
				registrationMapper.setResponse(response);
				registrationMapper.setResponseDetails(responseDetails);
				MailUtility.send("jaimatadi11singh@gmail.com", "oazlngnzuyalagwq", email, "OTP Verification Mail From Mycart", otp);

			} else {
				response.setResponseType("Fail");
				responseDetails.setMessage(" email or mobile cannot be empty");
				registrationMapper.setResponse(response);
				registrationMapper.setResponseDetails(responseDetails);

			}

		} catch (Exception e) {

			response.setResponseType("Fail");
			responseDetails.setMessage(e.getMessage().toString());
			registrationMapper.setResponse(response);
			registrationMapper.setResponseDetails(responseDetails);
			// return
			// ResponseEntity.status(HttpStatus.BAD_REQUEST).body(registrationMapper);
		}

	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(registrationMapper);
		// return jsonObject.put("otp", otp).toString();

		 //return "otp";
	}

}
