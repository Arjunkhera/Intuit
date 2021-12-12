package com.arjun.intuit.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringProcessorTest {

  @Test
  @DisplayName("String Processor Full Match Test")
  public void fullMatch() {
    Processor<String> processor = new StringProcessor(5);

    String firstWord = "Word";
    String secondWord = "Word";

    assertEquals(processor.process(firstWord, secondWord), 1.0d, "Score failed to match");
  }

  @Test
  @DisplayName("String Processor Partial Match Test")
  public void partialMatch() {
    Processor<String> processor = new StringProcessor(5);

    String firstWord = "Word";
    String secondWord = "Work";

    assertEquals(processor.process(firstWord, secondWord), 0.8d, "Score failed to match");
  }

  @Test
  @DisplayName("String Processor Zero Match")
  public void zeroMatchBelowThreshold() {
    Processor<String> processor = new StringProcessor(3);

    String firstWord = "Word";
    String secondWord = "";

    assertEquals(processor.process(firstWord, secondWord), 0.0d, "Score failed to match");
  }
}
