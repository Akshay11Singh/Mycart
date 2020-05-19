package com.mycart.dto.request;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Header {
	@JsonProperty("ChannelId")
	String channelId;
	@JsonProperty("Timestamp")
	Date timeStamp;
	@JsonProperty("ServiceProvider")
	String serviceProvider;

	protected Header() {
		super();
		// TODO Auto-generated constructor stub3
	}

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Header [channelId=" + channelId + ", timeStamp=" + timeStamp + ", serviceProvider=" + serviceProvider
				+ "]";
	}
	
	
}
