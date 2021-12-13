package com.arjun.intuit.service;

import java.time.Instant;

public interface ParsingService {

  Double parseDouble(String input);

  Integer parseInteger(String input);

  String parseString(String input);

  /**
   * Parse date time values according to pre defined formats
   */
  Instant parseInstant(String input);
}
