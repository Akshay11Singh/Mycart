package com.mycart.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycart.entity.Registration;

@Repository
public interface MyRegistrationRepo extends CrudRepository<Registration, Integer> {
	public Optional<Registration> saveAndFlush(Registration registration);
	
	@Transactional
	@Modifying
	@Query("update Registration set save_verification_flag=:isRead  where email_id=:email_id")
	public void saveVerificationFlag(@Param("isRead") boolean isRead,@Param("email_id") String email_id);
	
	@Transactional
	@Modifying
	@Query("update Registration set otp=:isotp  where email_id=:email_id")
	public void saveOtp(@Param("isotp") String isotp,@Param("email_id") String email_id);
}
