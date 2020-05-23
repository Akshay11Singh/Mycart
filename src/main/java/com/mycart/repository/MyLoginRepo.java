package com.mycart.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mycart.entity.Registration;


@Repository
public interface MyLoginRepo extends CrudRepository<Registration, Integer> {

	public Optional<Registration> findByEmailId(String email);
	public Optional<Registration>findByMobileNumber(String mobileNo);
}
