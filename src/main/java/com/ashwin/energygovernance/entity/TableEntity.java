/****************************************************************
 * Copyright (C) Lowe's Companies, Inc. All rights reserved. This file is for internal use only at
 * Lowe's Companies, Inc.
 ****************************************************************/

package com.ashwin.energygovernance.entity;

import static com.ashwin.energygovernance.common.constants.CommonConstants.TABLE_JSON_DATE_PATTERN;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import com.fasterxml.jackson.annotation.JsonFormat;

// Table entity class with audit listener
@MappedSuperclass
public abstract class TableEntity<K> {

  // private variable created
  @Column(name = "updated")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TABLE_JSON_DATE_PATTERN)
  private OffsetDateTime updated;

  /**
   * 
   * 
   * @return abstract
   */
  public abstract K getId();

  public OffsetDateTime getUpdated() {
    return updated;
  }

  public void setUpdated() {
    this.updated = OffsetDateTime.now();
  }

  public void setUpdated(OffsetDateTime updated) {
    this.updated = updated;
  }
}
