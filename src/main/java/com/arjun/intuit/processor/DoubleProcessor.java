package com.arjun.intuit.processor;

public class DoubleProcessor implements Processor<Double> {
  double threshold;

  public DoubleProcessor(double threshold) {
    this.threshold = threshold;
  }

  @Override
  public double process(Double first, Double second) {
    if(validate(first, second)) {
      return getEmptyScore(first, second);
    }

    double diff = Math.abs(first - second);
    diff = Math.min(diff, threshold);

    return (threshold - diff) / threshold;
  }
}
