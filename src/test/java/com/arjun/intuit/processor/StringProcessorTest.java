package com.arjun.intuit.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringProcessorTest {

  @Test
  @DisplayName("String Processor Full Match Test")
  public void fullMatch() {
    Processor<String, Double> processor = new StringProcessor(5.0d);

    String firstWord = "Word";
    String secondWord = "Word";

    assertEquals(processor.compare(firstWord, secondWord), 1.0d, "Score failed to match");
  }

  @Test
  @DisplayName("String Processor Partial Match Test")
  public void partialMatch() {
    Processor<String, Double> processor = new StringProcessor(5.0d);

    String firstWord = "Word";
    String secondWord = "Work";

    assertEquals(processor.compare(firstWord, secondWord), 0.8d, "Score failed to match");
  }

  @Test
  @DisplayName("String Processor Zero Match")
  public void zeroMatchBelowThreshold() {
    Processor<String, Double> processor = new StringProcessor(3.0d);

    String firstWord = "Word";
    String secondWord = "";

    assertEquals(processor.compare(firstWord, secondWord), 0.0d, "Score failed to match");
  }
}
