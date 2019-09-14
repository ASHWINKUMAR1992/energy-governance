/****************************************************************
 * Copyright (C) Lowe's Companies, Inc. All rights reserved. This file is for internal use only at
 * Lowe's Companies, Inc.
 ****************************************************************/

package com.ashwin.energygovernance.common.exception;

import com.lowes.service.customerservice.common.enums.EnumErrorCode;

/**
 * @author nakm Exception class related Jwt Token
 */
public class InvalidIdentityTokenException extends Exception {
  private static final long serialVersionUID = -15419048752687839L;

  /**
   * 
   * @param enumErrorCode
   */
  public InvalidIdentityTokenException(EnumErrorCode enumErrorCode) {
    super(enumErrorCode.name());
  }

  /**
   * 
   * @param e
   * @param enumErrorCode
   */
  public InvalidIdentityTokenException(Exception e, EnumErrorCode enumErrorCode) {
    super(enumErrorCode.name(), e);
  }

}
