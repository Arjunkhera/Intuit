package com.arjun.intuit.processor;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IntegerProcessor extends Processor<Integer, Double> {

  public IntegerProcessor(Double threshold) {
    super(threshold);
  }

  @Override
  public double process(Integer first, Integer second) {
    double diff = Math.abs(first - second);
    diff = Math.min(diff, threshold);

    return (threshold - diff) / threshold;
  }
}
