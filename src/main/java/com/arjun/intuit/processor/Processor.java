package com.arjun.intuit.processor;

@FunctionalInterface
public interface Processor<T> {

  /**
   * Given two arguments, calculate the difference between the two as a percentage value
   *
   * @param first  The first argument
   * @param second The second argument
   * @return a double value between 0 and 1
   */
  double process(T first, T second);
}
