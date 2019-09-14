/****************************************************************
 * Copyright (C) Lowe's Companies, Inc. All rights reserved. This file is for internal use only at
 * Lowe's Companies, Inc.
 ****************************************************************/

package com.ashwin.energygovernance.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

/**
 * @author rajak
 *
 */
public class ConflictKeyException extends Exception {

  // Constant variable for serialVersionUID
  private static final long serialVersionUID = -15419048752687839L;

  // ObjectError for the error
  private final ObjectError objectError;

  /* defaults to CONFLICT STATUS Code error */
  public ConflictKeyException() {
    this.objectError =
        new ObjectError(ConflictKeyException.class.getSimpleName(), HttpStatus.CONFLICT.toString());
  }

  /**
   * @return the objeError
   */
  public ObjectError getObjectError() {
    return objectError;
  }

}
