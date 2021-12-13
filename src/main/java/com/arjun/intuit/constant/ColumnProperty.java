package com.arjun.intuit.constant;

import java.time.Instant;

public enum ColumnProperty {
  GSTIN("GSTIN", String.class),
  DATE("Date", Instant.class),
  BILLNO("Bill no", String.class),
  GSTRATE("GST rate(%)", Double.class),
  TAXVALUE("Taxable value", Double.class),
  IGST("IGST", Double.class),
  CGST("CGST", Double.class),
  SGST("SGST", Double.class),
  TOTAL("Total", Double.class);

  private final String keyName;
  private final Class objectType;

  ColumnProperty(String keyName, Class objectType) {
    this.keyName = keyName;
    this.objectType = objectType;
  }

  public String getKeyName() {
    return this.keyName;
  }

  public Class getObjectType() {
    return this.objectType;
  }
}
