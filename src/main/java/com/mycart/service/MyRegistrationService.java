package com.mycart.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycart.entity.Registration;
import com.mycart.repository.MyRegistrationRepo;

@Service
public class MyRegistrationService {

	@Autowired
	MyRegistrationRepo myRegistrationRepo;

	public Optional<Registration> save(Registration registration) {
		return myRegistrationRepo.saveAndFlush(registration);

	}

}
