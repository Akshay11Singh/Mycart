package com.mycart.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mycart.entity.Registration;

@Repository
public interface MyRegistrationRepo extends CrudRepository<Registration, Integer> {
	public Optional<Registration> saveAndFlush(Registration registration);
}
