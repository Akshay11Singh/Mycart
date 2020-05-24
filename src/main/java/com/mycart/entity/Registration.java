package com.mycart.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

@Entity
public class Registration {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	int id;
	@NotEmpty
	String channelId;
	@NotNull
	Date timeStamp;
	@NotEmpty
	String serviceProvider;
	@NotEmpty
	String requestType;
	@NotEmpty
	String userType;
	@NotEmpty
	String birthDate;
	@NotEmpty
	String firstName;
	@NotEmpty
	String lastName;
	@NotEmpty
	String mobileNumber;
	@NotEmpty
	String emailId;
	@Column(nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	boolean  saveVerificationFlag=false;
	@NotNull
	String otp;
	
	
	private static Registration registration;
	/**
	 * @return the channelId
	 */
	public String getChannelId() {
		return channelId;
	}

	/**
	 * @param channelId the channelId to set
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	/**
	 * @return the timeStamp
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the serviceProvider
	 */
	public String getServiceProvider() {
		return serviceProvider;
	}

	/**
	 * @param serviceProvider the serviceProvider to set
	 */
	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	/**
	 * @return the requestType
	 */
	public String getRequestType() {
		return requestType;
	}

	/**
	 * @param requestType the requestType to set
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * @return the birthDate
	 */
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		this.birthDate = sdf.format(birthDate);
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return "Registration [channelId=" + channelId + ", timeStamp=" + timeStamp + ", serviceProvider="
				+ serviceProvider + ", requestType=" + requestType + ", userType=" + userType + ", birthDate="
				+ birthDate + ", firstName=" + firstName + ", lastName=" + lastName + ", mobileNumber=" + mobileNumber
				+ ", emailId=" + emailId + "]";
	}

	private Registration() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public  static Registration getRegfactory() {
		
		if(registration==null)
			registration= new Registration();
		else 
			return registration;
		return registration;
		
	}
	/*static {
		registration= new Registration();
	}*/

	public boolean isSaveVerificationFlag() {
		return saveVerificationFlag;
	}

	public void setSaveVerificationFlag(boolean saveVerificationFlag) {
		this.saveVerificationFlag = saveVerificationFlag;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the registration
	 */
	public static Registration getRegistration() {
		return registration;
	}

	/**
	 * @param registration the registration to set
	 */
	public static void setRegistration(Registration registration) {
		Registration.registration = registration;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the otp
	 */
	public String getOtp() {
		return otp;
	}

	/**
	 * @param otp the otp to set
	 */
	public void setOtp(String otp) {
		this.otp = otp;
	}


	}
