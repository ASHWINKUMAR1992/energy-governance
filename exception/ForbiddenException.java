/****************************************************************
 * Copyright (C) Lowe's Companies, Inc. All rights reserved. This file is for internal use only at
 * Lowe's Companies, Inc.
 ****************************************************************/

package com.ashwin.energygovernance.common.exception;

import org.springframework.validation.ObjectError;
import com.lowes.service.customerservice.common.enums.EnumErrorCode;

/**
 * This class is for forbidden exception
 * 
 * @author dev
 *
 */
public class ForbiddenException extends Exception {

  private static final long serialVersionUID = -15419048752687842L;

  // The error object
  private final ObjectError error;

  /* defaults to insufficient user permissions error */
  public ForbiddenException() {
    this.error = new ObjectError(ForbiddenException.class.getSimpleName(),
        EnumErrorCode.INSUFFICIENT_USER_PERMISSIONS.toString());
  }

  /**
   * 
   * @return error
   */
  public ObjectError getObjectError() {
    return this.error;
  }
}
