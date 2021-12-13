package com.arjun.intuit.processor;

public class IntegerProcessor implements Processor<Integer> {

  double threshold;

  public IntegerProcessor(double threshold) {
    this.threshold = threshold;
  }

  @Override
  public double process(Integer first, Integer second) {
    if(isArgumentNull(first, second)) {
      return getEmptyScore(first, second);
    }

    double diff = Math.abs(first - second);
    diff = Math.min(diff, threshold);

    return (threshold - diff) / threshold;
  }
}
