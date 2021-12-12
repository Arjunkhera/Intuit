package com.arjun.intuit.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IntegerProcessorTest {

  @Test
  @DisplayName("Integer Processor Full Match Test")
  public void fullMatch() {
    Processor<Integer> processor = new IntegerProcessor(5);

    Integer first = 10;
    Integer second = 10;

    assertEquals(processor.process(first, second), 1.0d, "Score failed to match");
  }

  @Test
  @DisplayName("Integer Processor Partial Match Test")
  public void partialMatch() {
    Processor<Integer> processor = new IntegerProcessor(5);

    Integer first = 10;
    Integer second = 6;

    assertEquals(processor.process(first, second), 0.2d, "Score failed to match");
  }

  @Test
  @DisplayName("Integer Processor Zero Match")
  public void zeroMatchBelowThreshold() {
    Processor<Integer> processor = new IntegerProcessor(5);

    Integer first = 10;
    Integer second = 4;

    assertEquals(processor.process(first, second), 0.0d, "Score failed to match");
  }
}
