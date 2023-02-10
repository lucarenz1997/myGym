package com.gymservice.web.errorhandling;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.hibernate.TransientObjectException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/** This class handles exceptions from the API calls */

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GenericExceptionHandler {

    private final String responseStatusLabel = "Response Status";
    private final String errorMessageLabel = "Message";

    private String responseStatus;
    private String errorMessage;
    private Map<String, String> map;

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Object> handleForbiddenException(ForbiddenException ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }


    // Handles Incorrect/Incomplete JSON Keys
    @ExceptionHandler(value = {UnrecognizedPropertyException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleUnrecognizedPropertyException(UnrecognizedPropertyException ex) {

        map = new LinkedHashMap<>();
        responseStatus = HttpStatus.BAD_REQUEST.value() + " " + HttpStatus.BAD_REQUEST.getReasonPhrase();
        errorMessage = "Invalid key " + "'" + ex.getPropertyName() +  "' AND " + ex.getMessage();

        map.put(responseStatusLabel, responseStatus);
        map.put(errorMessageLabel, errorMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }

    @ExceptionHandler(value = {InvalidFormatException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex) {

        map = new LinkedHashMap<>();
        responseStatus = HttpStatus.BAD_REQUEST.value() + " " + HttpStatus.BAD_REQUEST.getReasonPhrase();
        errorMessage = "Invalid value for key";

        map.put(responseStatusLabel, responseStatus);
        map.put(errorMessageLabel, errorMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }

    // Handles invalid PUT requests, where object may not exist
    @ExceptionHandler(value = {NoSuchElementException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex) {

        map = new LinkedHashMap<>();
        responseStatus = HttpStatus.BAD_REQUEST.value() + " " + HttpStatus.BAD_REQUEST.getReasonPhrase();
        errorMessage = "The item specified does not exist!";

        map.put(responseStatusLabel, responseStatus);
        map.put(errorMessageLabel, errorMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }

    // Handles invalid DELETE requests, where object does not exist
    @ExceptionHandler(value = {EmptyResultDataAccessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleDeletionOfNonExistantValue(EmptyResultDataAccessException ex) {

        map = new LinkedHashMap<>();
        responseStatus = HttpStatus.BAD_REQUEST.value() + " " + HttpStatus.BAD_REQUEST.getReasonPhrase();
        errorMessage = "The item specified does not exist!";

        map.put(responseStatusLabel, responseStatus);
        map.put(errorMessageLabel, errorMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleNullValueInItemName(MethodArgumentNotValidException ex) {

        map = new LinkedHashMap<>();
        responseStatus = HttpStatus.BAD_REQUEST.value() + " " + HttpStatus.BAD_REQUEST.getReasonPhrase();
        errorMessage = "The argument is not valid: " + ex.getMessage();

        map.put(responseStatusLabel, responseStatus);
        map.put(errorMessageLabel, errorMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }

     @ExceptionHandler(value = {TransientObjectException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleInvalidListItems(TransientObjectException ex) {
        map = new LinkedHashMap<>();
        responseStatus = HttpStatus.BAD_REQUEST.value() + " " + HttpStatus.BAD_REQUEST.getReasonPhrase();
        errorMessage = "Item can't be added to list. Item does not exist! Please provide a valid ID.";

        map.put(responseStatusLabel, responseStatus);
        map.put(errorMessageLabel, errorMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }

    @ExceptionHandler(MissingAttributeException.class)
    public ResponseEntity<Object> handleMissingAttributeException(MissingAttributeException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }





    // handle invalid HTTP message format
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleInvalidRequestFormat(HttpMessageNotReadableException ex) {
        map = new LinkedHashMap<>();
        responseStatus = HttpStatus.BAD_REQUEST.value() + " " + HttpStatus.BAD_REQUEST.getReasonPhrase();
        errorMessage = "Invalid request format.";

        map.put(responseStatusLabel, responseStatus);
        map.put(errorMessageLabel, errorMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }


    // Handles general exceptions
    /*
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleGenericException(Exception ex) {
        System.out.println(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
    } */
}