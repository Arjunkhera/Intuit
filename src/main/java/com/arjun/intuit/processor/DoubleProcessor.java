package com.arjun.intuit.processor;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DoubleProcessor extends Processor<Double, Double> {

  public DoubleProcessor(Double threshold) {
    super(threshold);
  }

  @Override
  public double process(Double first, Double second) {
    double diff = Math.abs(first - second);
    diff = Math.min(diff, threshold);

    return (threshold - diff) / threshold;
  }
}
