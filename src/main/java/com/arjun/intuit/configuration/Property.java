package com.arjun.intuit.configuration;

import com.arjun.intuit.processor.Processor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Property {

  double weight;
  Processor processor;

  public Property(Processor processor) {
    this.weight = 1.0;
    this.processor = processor;
  }

  public Property(double weight, Processor processor) {
    this.weight = weight;
    this.processor = processor;
  }
}
