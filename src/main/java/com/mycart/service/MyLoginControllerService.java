package com.mycart.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycart.entity.Registration;
import com.mycart.repository.MyLoginRepo;

@Service
public class MyLoginControllerService {
	
	@Autowired 
	MyLoginRepo myLoginRepo;
	
	public Optional<Registration> findByEmailId(String email) {
		return myLoginRepo.findByEmailId(email);
	}
	public Optional<Registration> findByMobileNumber(String mobileNo) {
		return myLoginRepo.findByMobileNumber(mobileNo);
		
	}

}
