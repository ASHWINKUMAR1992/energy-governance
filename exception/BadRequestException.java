/****************************************************************
 * Copyright (C) Lowe's Companies, Inc. All rights reserved. This file is for internal use only at
 * Lowe's Companies, Inc.
 ****************************************************************/

package com.ashwin.energygovernance.common.exception;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.ObjectError;
import com.lowes.service.customerservice.common.enums.EnumErrorCode;

/**
 * Handles 400 exceptions. ObjectErrors holds the errors from spring validation.
 * 
 * @author ashwin
 *
 */
public class BadRequestException extends Exception {

  private static final long serialVersionUID = -15419048752687839L;

  // List of all the object errors
  private List<ObjectError> allErrors;

  /**
   * constructor for the BadRequestException
   */
  public BadRequestException() {}

  /**
   * 
   * @param enumErrorCode - parameterized constructor for BadRequestException
   */
  public BadRequestException(EnumErrorCode enumErrorCode) {
    allErrors = new ArrayList<>();
    allErrors.add(new ObjectError(enumErrorCode.toString(), enumErrorCode.toString()));
  }

  /**
   * 
   * @param allErrors
   */
  public void setObjectErrors(List<ObjectError> allErrors) {
    this.allErrors = allErrors;
  }

  /**
   * 
   * @return List<ObjectError>
   */
  public List<ObjectError> getObjectErrors() {
    return this.allErrors;
  }
}
