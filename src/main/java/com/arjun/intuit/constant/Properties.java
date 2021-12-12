package com.arjun.intuit.constant;

import java.time.Instant;

public enum Properties {
  GSTIN(String.class),
  DATE(Instant.class),
  BILLNO(String.class),
  GSTRATE(Integer.class),
  TAXVALUE(Double.class),
  IGST(Double.class),
  CGST(Double.class),
  SGST(Double.class),
  TOTAL(Double.class);

  private final Class objectType;

  <T> Properties(Class<T> objectType) {
    this.objectType = objectType;
  }

  public Class getObjectType() {
    return this.objectType;
  }
}
