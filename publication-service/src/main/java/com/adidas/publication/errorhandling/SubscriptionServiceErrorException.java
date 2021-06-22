/**
 * 
 */
package com.adidas.publication.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author leandrobelluscio
 *
 */
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class SubscriptionServiceErrorException extends RuntimeException {


		public SubscriptionServiceErrorException(String message){
			super(message);
		}
}
