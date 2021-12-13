package com.arjun.intuit.service.impl;

import com.arjun.intuit.constant.ColumnProperty;
import com.arjun.intuit.processor.DoubleProcessor;
import com.arjun.intuit.processor.InstantProcessor;
import com.arjun.intuit.processor.Processor;
import com.arjun.intuit.processor.StringProcessor;

public class ProcessorFactory {

  public static Processor create(ColumnProperty columnProperty) {
    switch (columnProperty) {
      case GSTIN:
      case BILLNO:
        return new StringProcessor();
      case DATE:
        return new InstantProcessor();
      case GSTRATE:
      case TAXVALUE:
      case IGST:
      case CGST:
      case TOTAL:
      case SGST:
        return new DoubleProcessor();
    }
    return null;
  }
}
