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

  public ColumnConfig(Processor processor) {
    this.weight = 1.0;
    this.processor = processor;
  }

  public ColumnConfig(ColumnProperty columnProperty, Processor processor) {
    this.weight = 1.0;
    this.columnProperty = columnProperty;
    this.processor = processor;
  }

  public ColumnConfig(double weight, Processor processor) {
    this.weight = weight;
    this.processor = processor;
  }
}
