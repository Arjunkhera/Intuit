package com.arjun.intuit.model;

import com.arjun.intuit.constant.ColumnProperty;
import com.arjun.intuit.processor.Processor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class ColumnConfig {

  ColumnProperty columnProperty;
  double weight;
  Processor processor;

  public ColumnConfig(ColumnProperty columnProperty, Processor processor) {
    this(columnProperty, processor, 1.0d);
  }

  public ColumnConfig(ColumnProperty columnProperty, Processor processor, double weight) {
    this.weight = weight;
    this.columnProperty = columnProperty;
    this.processor = processor;
  }
}
