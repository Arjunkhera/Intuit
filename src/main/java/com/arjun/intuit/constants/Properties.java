package com.arjun.intuit.constants;

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

  <T> Properties(Class<T> Class) {
  }
}
