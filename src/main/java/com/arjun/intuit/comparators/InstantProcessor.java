package com.arjun.intuit.comparators;

import java.time.Duration;
import java.time.Instant;

public class InstantProcessor implements Processor<Instant> {

  Duration threshold;

  InstantProcessor(Duration threshold) {
    this.threshold = threshold;
  }

  @Override
  public double process(Instant first, Instant second) {
    Duration diff = Duration.between(first, second).abs();

    if(diff.compareTo(threshold) > 0) {
      return 0.0d;
    }

    Duration magnitude = threshold.minus(diff);
    return (double) magnitude.toSeconds() / threshold.toSeconds();
  }
}
