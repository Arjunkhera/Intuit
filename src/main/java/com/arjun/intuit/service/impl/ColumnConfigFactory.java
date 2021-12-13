package com.arjun.intuit.service.impl;

import com.arjun.intuit.constant.ColumnProperty;
import com.arjun.intuit.model.ColumnConfig;

public class ColumnConfigFactory {

  public static ColumnConfig create(ColumnProperty columnProperty) {
    return generate(columnProperty);
  }

  public static ColumnConfig create(ColumnProperty columnProperty, Double weight) {
    ColumnConfig columnConfig = create(columnProperty);
    columnConfig.setWeight(weight);
    return columnConfig;
  }

  public static ColumnConfig create(ColumnProperty columnProperty, Double weight,
      Object threshold) {
    ColumnConfig columnConfig = create(columnProperty);
    columnConfig.setWeight(weight);
    // TODO: Set assignable check for threshold
    columnConfig.getProcessor().setThreshold(threshold);
    return columnConfig;
  }

  private static ColumnConfig generate(ColumnProperty columnProperty) {
    return new ColumnConfig(columnProperty, ProcessorFactory.create(columnProperty));
  }
}
