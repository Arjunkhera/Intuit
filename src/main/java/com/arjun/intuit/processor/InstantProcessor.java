package com.arjun.intuit.processor;

import java.time.Duration;
import java.time.Instant;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InstantProcessor extends Processor<Instant, Duration> {
  public InstantProcessor(Duration threshold) {
    super(threshold);
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
