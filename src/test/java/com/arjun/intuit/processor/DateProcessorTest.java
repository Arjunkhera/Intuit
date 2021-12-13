package com.arjun.intuit.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DateProcessorTest {

  @Test
  @DisplayName("Instant Processor Full Match Test")
  public void fullMatch() {
    Processor<Instant, Duration> processor = new InstantProcessor(Duration.ofDays(1));

    long now = System.currentTimeMillis();
    Instant first = Instant.ofEpochMilli(now);
    Instant second = Instant.ofEpochMilli(now);

    assertEquals(processor.compare(first, second), 1.0d, "Score failed to match");
  }

  @Test
  @DisplayName("Instant Processor Partial Match Test")
  public void partialMatch() {
    Processor<Instant, Duration> processor = new InstantProcessor(Duration.ofDays(1));

    Instant first = Instant.now();
    Instant second = Instant.now().minus(Duration.ofHours(6));

    assertEquals(processor.compare(first, second), 0.75d, "Score failed to match");
  }

  @Test
  @DisplayName("Instant Processor Zero Match")
  public void zeroMatchBelowThreshold() {
    Processor<Instant, Duration> processor = new InstantProcessor(Duration.ofDays(1));

    Instant first = Instant.now();
    Instant second = Instant.now().minus(Duration.ofDays(2));

    assertEquals(processor.compare(first, second), 0.0d, "Score failed to match");
  }
}
