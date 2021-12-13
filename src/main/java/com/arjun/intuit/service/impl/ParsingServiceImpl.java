package com.arjun.intuit.service.impl;

import com.arjun.intuit.service.ParsingService;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ParsingServiceImpl implements ParsingService {

  @Override
  public Double parseDouble(String input) {
    input = removeComma(input);
    return Double.valueOf(input);
  }

  @Override
  public Integer parseInteger(String input) {
    input = removeComma(input);
    return Integer.valueOf(input);
  }

  @Override
  public String parseString(String input) {
    return input;
  }

  @Override
  public Instant parseInstant(String input) {
    DateTimeFormatter[] formats = new DateTimeFormatter[] {
        DateTimeFormatter.ofPattern("d/M/yy"),
        DateTimeFormatter.ofPattern("dd-MM-yyyy")
    };
    LocalDate localDate = parseFormats(input, formats);
    ZonedDateTime zdt = localDate.atStartOfDay().atZone(ZoneId.of("Asia/Kolkata"));

    return zdt.toInstant();
  }

  /**
   * utility method to remove comma's from string
   *
   * @param input input numeric string
   * @return input string with comma removed
   */
  private String removeComma(String input) {
    StringBuilder output = new StringBuilder();
    for (char ch : input.toCharArray()) {
      if (ch != ',') {
        output.append(ch);
      }
    }
    return output.toString();
  }

  /**
   * Match string against various date time formats
   *
   * @param input      input string
   * @param formatters date time formatters to check against
   * @return LocalDate if parsing is successful
   */
  private LocalDate parseFormats(String input, DateTimeFormatter... formatters) {
    for (DateTimeFormatter formatter : formatters) {
      try {
        return LocalDate.parse(input, formatter);
      } catch (DateTimeParseException dateTimeParseException) {
        // log.error("Input {} does not match formatter {}", input, formatter.toString());
      }
    }
    throw new DateTimeParseException("Failed to parse input with existing formatters", input, 0);
  }
}
