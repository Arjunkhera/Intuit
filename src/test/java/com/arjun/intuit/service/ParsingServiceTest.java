package com.arjun.intuit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.arjun.intuit.service.impl.ParsingServiceImpl;
import java.time.Instant;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParsingServiceTest {

  private static ParsingService parsingService;

  @BeforeAll
  public static void init() {
    parsingService = new ParsingServiceImpl();
  }

  @Test
  @DisplayName("Parse Double")
  public void parseDouble() {
    String input = "2,099";
    Double output = parsingService.parseDouble(input);
    assertEquals(output, 2099.0d, "Incorrect parsing");
  }

  @Test
  @DisplayName("Parse Integer")
  public void parseInteger() {
    String input = "2,099";
    Integer output = parsingService.parseInteger(input);
    assertEquals(output, 2099, "Incorrect parsing");
  }

  @Test
  @DisplayName("Parse Instant")
  public void parseInstant() {
    String input = "3/9/17";
    Instant output = parsingService.parseInstant(input);
    assertEquals(output, Instant.parse("2017-09-02T18:30:00Z"), "Incorrect parsing");
  }

  @Test
  @DisplayName("Parse Instant Another Format")
  public void parseInstantAnotherFormat() {
    String input = "27-09-2017";
    Instant output = parsingService.parseInstant(input);
    System.out.println(output);
    assertEquals(output, Instant.parse("2017-09-26T18:30:00Z"), "Incorrect parsing");
  }
}
