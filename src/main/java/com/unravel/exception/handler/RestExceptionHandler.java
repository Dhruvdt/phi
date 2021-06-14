package com.unravel.exception.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.unravel.universal.UnravelResponse;



@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

   @Override
   protected ResponseEntity<Object> handleMissingServletRequestParameter(
     MissingServletRequestParameterException ex, HttpHeaders headers, 
     HttpStatus status, WebRequest request) {
       String error = ex.getParameterName() + " parameter is missing";
       UnravelResponse universalResponse =  new UnravelResponse();
       universalResponse.setMessage(error);
       universalResponse.setStatus(0);
       Map<String,String> emptyMap =  new HashMap<>();
       universalResponse.setData(emptyMap);
       return new ResponseEntity<>(universalResponse,HttpStatus.BAD_REQUEST);
      

   }

   @Override
   protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){

	    StringBuilder message = new StringBuilder();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	       message.append(error.getDefaultMessage());
	    });
	    
       UnravelResponse universalResponse =  new UnravelResponse();
       universalResponse.setMessage(message.toString());
       universalResponse.setStatus(0);
       return new ResponseEntity<>(universalResponse,HttpStatus.BAD_REQUEST);
   }
   
 
   @ExceptionHandler({ ConstraintViolationException.class })
   public ResponseEntity<Object> handleConstraintViolation(
     ConstraintViolationException ex, WebRequest request) {
       List<String> errors = new ArrayList<String>();
       for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
           errors.add(violation.getRootBeanClass().getName() + " " + 
             violation.getPropertyPath() + ": " + violation.getMessage());
       }

       
       UnravelResponse universalResponse =  new UnravelResponse();
       universalResponse.setMessage(ex.getLocalizedMessage());
       universalResponse.setStatus(0);
       return new ResponseEntity<>(universalResponse,HttpStatus.BAD_REQUEST);
       
   }
      

  }

   //other exception handlers below

