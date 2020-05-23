package com.mycart.service;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class MyCartOtp {

	Long l;
	
	public int generateOTP() {
		return  new Date().hashCode();
		//localDateTime.getMinute();
	}
}
