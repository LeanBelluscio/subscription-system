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
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SubscriptionUniqueErrorException extends RuntimeException {


		public SubscriptionUniqueErrorException(String message){
			super(message);
		}
}
