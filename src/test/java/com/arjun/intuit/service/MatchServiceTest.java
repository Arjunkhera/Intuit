package com.arjun.intuit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.arjun.intuit.constant.ColumnProperty;
import com.arjun.intuit.constant.Status;
import com.arjun.intuit.model.Config;
import com.arjun.intuit.model.Output;
import com.arjun.intuit.model.Record;
import com.arjun.intuit.service.impl.ColumnConfigFactory;
import com.arjun.intuit.service.impl.MatchServiceImpl;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MatchServiceTest {

  @Test
  @DisplayName("Simple Full Match Test")
  public void simpleFullMatchTest() {
    MatchService matchService = new MatchServiceImpl();
    Config config = new Config.Builder()
        .withProperty(ColumnConfigFactory.create(ColumnProperty.GSTIN, 1.0, 5.0d))
        .withProperty(ColumnConfigFactory.create(ColumnProperty.TOTAL, 1.0, 5.0d))
        .build();

    Map<ColumnProperty, Object> sourceValues = new HashMap<>();
    sourceValues.put(ColumnProperty.GSTIN, "Word");
    sourceValues.put(ColumnProperty.TOTAL, 1276.0d);
    Record sourceRecord = new Record(sourceValues);

    Map<ColumnProperty, Object> matchValues = new HashMap<>();
    matchValues.put(ColumnProperty.GSTIN, "Word");
    matchValues.put(ColumnProperty.TOTAL, 1276.0d);
    Record matchRecord = new Record(matchValues);

    Output output = matchService.match(config, sourceRecord, matchRecord);
    assertEquals(output.getScore(), 1.0d, "Score failed to match");
    assertEquals(output.getStatus(), Status.FULL_MATCH, "Status failed to match");
  }

  @Test
  @DisplayName("Simple Partial Match Test")
  public void simplePartialMatchTest() {
    MatchService matchService = new MatchServiceImpl();
    Config config = new Config.Builder()
        .withProperty(ColumnConfigFactory.create(ColumnProperty.GSTIN, 1.0, 5.0d))
        .withProperty(ColumnConfigFactory.create(ColumnProperty.TOTAL, 1.0, 5.0d))
        .build();

    Map<ColumnProperty, Object> sourceValues = new HashMap<>();
    sourceValues.put(ColumnProperty.GSTIN, "Word");
    sourceValues.put(ColumnProperty.TOTAL, 1276.0d);
    Record sourceRecord = new Record(sourceValues);

    Map<ColumnProperty, Object> matchValues = new HashMap<>();
    matchValues.put(ColumnProperty.GSTIN, "WordGam");
    matchValues.put(ColumnProperty.TOTAL, 1280.0d);
    Record matchRecord = new Record(matchValues);

    Output output = matchService.match(config, sourceRecord, matchRecord);
    assertEquals(output.getStatus(), Status.PARTIAL, "Status failed to match");
  }

  @Test
  @DisplayName("Simple No Match Test")
  public void simpleNoMatchTest() {
    MatchService matchService = new MatchServiceImpl();
    Config config = new Config.Builder()
        .withProperty(ColumnConfigFactory.create(ColumnProperty.GSTIN, 1.0, 5.0d))
        .withProperty(ColumnConfigFactory.create(ColumnProperty.TOTAL, 1.0, 5.0d))
        .build();

    Map<ColumnProperty, Object> sourceValues = new HashMap<>();
    sourceValues.put(ColumnProperty.GSTIN, "Word");
    sourceValues.put(ColumnProperty.TOTAL, 1276.0d);
    Record sourceRecord = new Record(sourceValues);

    Map<ColumnProperty, Object> matchValues = new HashMap<>();
    matchValues.put(ColumnProperty.GSTIN, "WordGames");
    matchValues.put(ColumnProperty.TOTAL, 1289.0d);
    Record matchRecord = new Record(matchValues);

    Output output = matchService.match(config, sourceRecord, matchRecord);
    assertEquals(output.getScore(), 0.0d, "Score failed to match");
    assertEquals(output.getStatus(), Status.ONLY_IN_FIRST, "Status failed to match");
  }
}
