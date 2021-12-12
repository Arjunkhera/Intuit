package com.arjun.intuit.comparators;

public class IntegerProcessor implements Processor<Integer> {

  double threshold;

  IntegerProcessor(double threshold) {
    this.threshold = threshold;
  }

  @Override
  public double process(Integer first, Integer second) {
    double diff = Math.abs(first - second);
    diff = Math.min(diff, threshold);

    return (threshold - diff) / threshold;
  }
}
