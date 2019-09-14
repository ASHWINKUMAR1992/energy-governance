/****************************************************************
 * Copyright (C) Lowe's Companies, Inc. All rights reserved. This file is for internal use only at
 * Lowe's Companies, Inc.
 ****************************************************************/

package com.ashwin.energygovernance.common.exception.handler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.hibernate.id.IdentifierGenerationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.lowes.service.customerservice.common.enums.EnumErrorCode;
import com.lowes.service.customerservice.common.error.ErrorResponse;
import com.lowes.service.customerservice.common.error.ErrorResponseTemplate;
import com.ashwin.energygovernance.common.exception.BadRequestException;
import com.ashwin.energygovernance.common.exception.ConflictKeyException;
import com.ashwin.energygovernance.common.exception.ForbiddenException;
import com.ashwin.energygovernance.common.exception.InvalidIdentityTokenException;
import com.ashwin.energygovernance.common.exception.ResourceNotFoundException;
import com.lowes.service.customerservice.common.helper.CommonErrorResponseHelper;

/**
 * this class is meant for all the applicationExceptionHandler
 * 
 * @author dev
 *
 */
@ControllerAdvice
public class ApplicationExceptionHandler {

  // Static Constant value for ZERO
  private static final int ZERO = 0;

  // Static Constant value for COLON
  private static final String COLON = ":";

  // Static Constant value for ERROR_ONE
  private static final String ERROR_ONE = "` field is invalid. Only ";

  // Static Constant value for ERROR_TWO
  private static final String ERROR_TWO = " values allowed.";

  // CommonErrorResponseHelper object to show error messages
  private CommonErrorResponseHelper commonErrorResponseHelper;

  /**
   * 
   * @param commonErrorResponseHelper
   */
  @Autowired
  public void setCommonErrorResponseHelper(CommonErrorResponseHelper commonErrorResponseHelper) {
    this.commonErrorResponseHelper = commonErrorResponseHelper;
  }

  /**
   * Handles Exceptions related to Bad Requests
   * 
   * @param BadRequestException
   * @return ResponseEntity
   */
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorResponseTemplate> handleBadRequestException(BadRequestException ex) {
    ErrorResponseTemplate errorResponseTemplate = new ErrorResponseTemplate();
    errorResponseTemplate.setTimeStamp(OffsetDateTime.now());
    List<ErrorResponse> errorResponseList = new ArrayList<>();
    for (final ObjectError error : ex.getObjectErrors()) {
      errorResponseList.add(new ErrorResponse(error.getDefaultMessage(),
          EnumErrorCode.valueOf(error.getDefaultMessage()).getErrorDescription()));
    }
    errorResponseTemplate.setErrors(errorResponseList);
    // returns the bad request status
    return new ResponseEntity<>(errorResponseTemplate, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles UnAuthorized Exceptions
   * 
   * @param InvalidIdentityTokenException
   * @return ResponseEntity
   */
  @ExceptionHandler(InvalidIdentityTokenException.class)
  public final ResponseEntity<ErrorResponseTemplate> handleInvalidIdentityTokenException(
      InvalidIdentityTokenException ex) {
    ErrorResponseTemplate errorResponseTemplate = new ErrorResponseTemplate();
    // set current date time
    errorResponseTemplate.setTimeStamp(OffsetDateTime.now());
    List<ErrorResponse> errorResponseList = new ArrayList<>();
    errorResponseList.add(new ErrorResponse(ex.getMessage(),
        EnumErrorCode.valueOf(ex.getMessage()).getErrorDescription()));
    errorResponseTemplate.setErrors(errorResponseList);
    // returns the unauthorized if the token is invalid
    return new ResponseEntity<>(errorResponseTemplate, HttpStatus.UNAUTHORIZED);
  }

  /**
   * Handles Forbidden Exception
   * 
   * @param ForbiddenException
   * @return
   */
  @ExceptionHandler(ForbiddenException.class)
  public final ResponseEntity<ErrorResponseTemplate> handleForbiddenException(
      ForbiddenException ex) {
    ErrorResponseTemplate errorResponseTemplate = new ErrorResponseTemplate();
    // set current date time in response template
    errorResponseTemplate.setTimeStamp(OffsetDateTime.now());
    List<ErrorResponse> errorResponseList = new ArrayList<>();
    errorResponseList.add(new ErrorResponse(ex.getObjectError().getDefaultMessage(),
        EnumErrorCode.valueOf(ex.getObjectError().getDefaultMessage()).getErrorDescription()));
    errorResponseTemplate.setErrors(errorResponseList);
    // returns the forbidden error if the token is expired.
    return new ResponseEntity<>(errorResponseTemplate, HttpStatus.FORBIDDEN);
  }

  /**
   * Handles DuplicateKey Exceptions
   * 
   * @param ConflictKeyException
   * @return
   */
  @ExceptionHandler(ConflictKeyException.class)
  public ResponseEntity<ErrorResponseTemplate> handleConflictKeyException(ConflictKeyException ex) {
    ErrorResponseTemplate errorResponseTemplate = new ErrorResponseTemplate();
    // set current date time in response template
    errorResponseTemplate.setTimeStamp(OffsetDateTime.now());
    List<ErrorResponse> errorResponseList = new ArrayList<>();
    errorResponseList.add(new ErrorResponse(ex.getObjectError().getDefaultMessage(),
        EnumErrorCode.valueOf(ex.getObjectError().getDefaultMessage()).getErrorDescription()));
    errorResponseTemplate.setErrors(errorResponseList);
    // returns the conflict if the user tries to enter the duplicate data
    return new ResponseEntity<>(errorResponseTemplate, HttpStatus.CONFLICT);
  }

  /**
   * Handles Constraint Violation Exceptions
   * 
   * @param ConstraintViolationException
   * @return ResponseEntity
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResponseTemplate> handleConstraintViolationException(
      ConstraintViolationException ex) {
    ErrorResponseTemplate errorResponseTemplate = new ErrorResponseTemplate();
    errorResponseTemplate.setTimeStamp(OffsetDateTime.now());
    List<ErrorResponse> errorResponseList = new ArrayList<>();
    for (ConstraintViolation<?> cv : ex.getConstraintViolations()) {
      errorResponseList.add(new ErrorResponse(cv.getMessage(),
          EnumErrorCode.valueOf(cv.getMessage()).getErrorDescription()));
    }
    errorResponseTemplate.setErrors(errorResponseList);
    // returns the bad request for the constraints violation
    return new ResponseEntity<>(errorResponseTemplate, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles ResourceNotFoundException
   * 
   * @param ResourceNotFoundException
   * @return ResponseEntity
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponseTemplate> resourceNotFound(ResourceNotFoundException ex) {
    ErrorResponseTemplate errorResponseTemplate = new ErrorResponseTemplate();
    errorResponseTemplate.setTimeStamp(OffsetDateTime.now());
    List<ErrorResponse> errorResponseList = new ArrayList<>();
    errorResponseList.add(new ErrorResponse("RESOURCE_NOT_FOUND", ex.getEntity() + " not found."));
    errorResponseTemplate.setErrors(errorResponseList);
    // returns not found if the data is unavailable
    return new ResponseEntity<>(errorResponseTemplate, HttpStatus.NOT_FOUND);

  }

  /**
   * Handles DataIntegrityViolationException
   * 
   * @param DataIntegrityViolationException
   * @return ResponseEntity
   */
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorResponseTemplate> duplicateResourceFound(
      DataIntegrityViolationException ex) {
    ErrorResponseTemplate errorResponseTemplate = commonErrorResponseHelper.createErrorResponse(ex);
    return new ResponseEntity<>(errorResponseTemplate, HttpStatus.CONFLICT);

  }

  /**
   * Handles MethodArgumentNotValidException
   * 
   * @author dev
   * @param MethodArgumentNotValidException
   * @return ResponseEntity
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponseTemplate> badRequest(MethodArgumentNotValidException ex) {
    ErrorResponseTemplate errorResponseTemplate = new ErrorResponseTemplate();
    errorResponseTemplate.setTimeStamp(OffsetDateTime.now());
    List<ErrorResponse> errorResponseList = new ArrayList<>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errorResponseList.add(new ErrorResponse(error.getDefaultMessage().split(COLON)[ZERO],
          error.getDefaultMessage().substring(error.getDefaultMessage().indexOf(COLON) + 1,
              error.getDefaultMessage().length()),
          error.getField()));
    }

    errorResponseTemplate.setErrors(errorResponseList);
    return new ResponseEntity<>(errorResponseTemplate, HttpStatus.BAD_REQUEST);

  }

  /**
   * Handles IdentifierGenerationException
   * 
   * @author dev
   * @param IdentifierGenerationException
   * @return ResponseEntity
   */
  @ExceptionHandler(IdentifierGenerationException.class)
  public ResponseEntity<ErrorResponseTemplate> idNotGenerated(IdentifierGenerationException ex) {
    ErrorResponseTemplate errorResponseTemplate = new ErrorResponseTemplate();
    errorResponseTemplate.setTimeStamp(OffsetDateTime.now());
    List<ErrorResponse> errorResponseList = new ArrayList<>();
    errorResponseList.add(new ErrorResponse("ID_NOT_GENERATED", ex.getLocalizedMessage()));
    errorResponseTemplate.setErrors(errorResponseList);
    return new ResponseEntity<>(errorResponseTemplate, HttpStatus.BAD_REQUEST);

  }

  /**
   * Handles DeserializeAndSerializeExceptions
   * 
   * @author vdubey
   * @param HttpMessageNotReadableException
   * @return ResponseEntity
   */

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponseTemplate> handleDeserializeAndSerializeExceptions(
      HttpMessageNotReadableException ex) {

    ErrorResponseTemplate errorResponseTemplate = new ErrorResponseTemplate();
    InvalidFormatException cause = (InvalidFormatException) ex.getCause();
    errorResponseTemplate.setTimeStamp(OffsetDateTime.now());
    List<ErrorResponse> errorResponseList = new ArrayList<>();
    errorResponseList.add(new ErrorResponse("JSON_PARSE_ERROR",
        "`" + cause.getPath().get(cause.getPath().size() - 1).getFieldName() + ERROR_ONE
            + cause.getTargetType().getSimpleName().toLowerCase() + ERROR_TWO));
    errorResponseTemplate.setErrors(errorResponseList);
    return new ResponseEntity<>(errorResponseTemplate, HttpStatus.BAD_REQUEST);

  }

}
