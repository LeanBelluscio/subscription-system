/**
 * 
 */
package com.adidas.subscription.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author leandrobelluscio
 *
 */
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class EmailServiceErrorException extends RuntimeException {


		public EmailServiceErrorException(String message){
			super(message);
		}
}
