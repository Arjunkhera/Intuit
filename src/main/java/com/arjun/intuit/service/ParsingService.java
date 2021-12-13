package com.arjun.intuit.service;

import java.time.Instant;

public interface ParsingService {

  Double parseDouble(String input);

  Integer parseInteger(String input);

  String parseString(String input);

  Instant parseInstant(String input);
}
