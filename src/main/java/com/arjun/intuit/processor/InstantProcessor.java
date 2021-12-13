package com.arjun.intuit.processor;

import java.time.Duration;
import java.time.Instant;

public class InstantProcessor implements Processor<Instant> {

  Duration threshold;

  public InstantProcessor(Duration threshold) {
    this.threshold = threshold;
  }

  @Override
  public double process(Instant first, Instant second) {
    if(isArgumentNull(first, second)) {
      return getEmptyScore(first, second);
    }

    Duration diff = Duration.between(first, second).abs();

    if(diff.compareTo(threshold) > 0) {
      return 0.0d;
    }

    Duration magnitude = threshold.minus(diff);
    return (double) magnitude.toSeconds() / threshold.toSeconds();
  }
}
