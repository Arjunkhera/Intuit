package com.arjun.intuit.constant;

import java.time.Instant;

public enum Properties {
  GSTIN("GSTIN", String.class),
  DATE("Date", Instant.class),
  BILLNO("Bill no", String.class),
  GSTRATE("GST rate(%)", Double.class),
  TAXVALUE("Taxable value", Double.class),
  IGST("IGST", Double.class),
  CGST("CGST", Double.class),
  SGST("SGST", Double.class),
  TOTAL("Total", Double.class);

  String keyName;
  private final Class objectType;

  <T> Properties(String keyName, Class<T> objectType) {
    this.keyName = keyName;
    this.objectType = objectType;
  }

  public Class getObjectType() {
    return this.objectType;
  }

  public String getKeyName() {
    return this.keyName;
  }
}
