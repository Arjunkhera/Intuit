package com.arjun.intuit.processor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class Processor<T, U> {

  protected U threshold;

  public Processor(U threshold) {
    this.threshold = threshold;
  }

  /**
   * Given two arguments, calculate the difference between the two as a percentage value
   *
   * @param first  The first argument
   * @param second The second argument
   * @return a double value between 0 and 1
   */
  public double compare(T first, T second) {
    if (isArgumentNull(first, second)) {
      return getEmptyScore(first, second);
    }

    if(threshold == null) {
      return first.equals(second) ? 1.0d : 0.0d;
    }

    return process(first, second);
  }

  protected abstract double process(T first, T second);

  protected boolean isArgumentNull(T first, T second) {
    return first == null || second == null;
  }

  protected double getEmptyScore(T first, T second) {
    if (first == null && second == null) {
      return 1.0d;
    }
    return 0.0d;
  }
}
