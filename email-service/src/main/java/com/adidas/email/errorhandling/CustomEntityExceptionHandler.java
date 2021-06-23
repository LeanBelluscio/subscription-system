/**
 * @author lbelluscio
 */
package com.adidas.email.errorhandling;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author leandrobelluscio
 *
 */
@ControllerAdvice
public class CustomEntityExceptionHandler extends ResponseEntityExceptionHandler {


  private static final Logger logger = LoggerFactory.getLogger(CustomEntityExceptionHandler.class);

	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);
        logger.error("Validation Error:", ex);	
        return new ResponseEntity<>(body, headers, status);

    }
	
	@ExceptionHandler(Exception.class)
	  public final ResponseEntity<ErrorDetails> handleGenericException(Exception ex, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), "Internarl Server Error: "+ex.getMessage(),
	        request.getDescription(false));
          logger.error("Internal Server Error:", ex);	
	    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
}
