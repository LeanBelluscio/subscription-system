/**
 * 
 */
package com.adidas.publication.errorhandling;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author leandrobelluscio
 *
 */
public class ErrorDetails {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
	private Date date;

	private String message;
	
	private String details;
	
	public ErrorDetails(Date date, String message, String details){
		super();
		this.date = date;
		this.message = message;
		this.details = details;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
