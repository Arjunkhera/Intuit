package com.arjun.intuit.configuration;

import com.arjun.intuit.comparators.Processor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Property {
  double weight;
  Processor processor;

  Property(double weight, Processor processor) {
    this.weight = weight;
    this.processor = processor;
  }
}
