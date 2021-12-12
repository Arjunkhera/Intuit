package com.arjun.intuit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.arjun.intuit.configuration.Config;
import com.arjun.intuit.configuration.Property;
import com.arjun.intuit.constant.Properties;
import com.arjun.intuit.constant.Status;
import com.arjun.intuit.model.Output;
import com.arjun.intuit.model.Record;
import com.arjun.intuit.processor.IntegerProcessor;
import com.arjun.intuit.processor.StringProcessor;
import com.arjun.intuit.service.impl.MatchServiceImpl;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MatchServiceTest {

  @Test
  @DisplayName("Simple Full Match Test")
  public void simpleFullMatchTest() {
    MatchServiceImpl matchService = new MatchServiceImpl();
    Config config = new Config.Builder()
        .withProperty(Properties.GSTIN, new Property(new StringProcessor(5)))
        .withProperty(Properties.TOTAL, new Property(new IntegerProcessor(5)))
        .build();

    Map<Properties, Object> sourceValues = new HashMap<>();
    sourceValues.put(Properties.GSTIN, "Word");
    sourceValues.put(Properties.TOTAL, 1276);
    Record sourceRecord = new Record(sourceValues);

    Map<Properties, Object> matchValues = new HashMap<>();
    matchValues.put(Properties.GSTIN, "Word");
    matchValues.put(Properties.TOTAL, 1276);
    Record matchRecord = new Record(matchValues);

    Output output = matchService.match(config, sourceRecord, matchRecord);
    assertEquals(output.getScore(), 1.0d, "Score failed to match");
    assertEquals(output.getStatus(), Status.FULL_MATCH, "Status failed to match");
  }

  @Test
  @DisplayName("Simple Partial Match Test")
  public void simplePartialMatchTest() {
    MatchServiceImpl matchService = new MatchServiceImpl();
    Config config = new Config.Builder()
        .withProperty(Properties.GSTIN, new Property(new StringProcessor(5)))
        .withProperty(Properties.TOTAL, new Property(new IntegerProcessor(5)))
        .build();

    Map<Properties, Object> sourceValues = new HashMap<>();
    sourceValues.put(Properties.GSTIN, "Word");
    sourceValues.put(Properties.TOTAL, 1276);
    Record sourceRecord = new Record(sourceValues);

    Map<Properties, Object> matchValues = new HashMap<>();
    matchValues.put(Properties.GSTIN, "WordGam");
    matchValues.put(Properties.TOTAL, 1280);
    Record matchRecord = new Record(matchValues);

    Output output = matchService.match(config, sourceRecord, matchRecord);
    assertEquals(output.getStatus(), Status.PARTIAL, "Status failed to match");
  }

  @Test
  @DisplayName("Simple No Match Test")
  public void simpleNoMatchTest() {
    MatchServiceImpl matchService = new MatchServiceImpl();
    Config config = new Config.Builder()
        .withProperty(Properties.GSTIN, new Property(new StringProcessor(5)))
        .withProperty(Properties.TOTAL, new Property(new IntegerProcessor(5)))
        .build();

    Map<Properties, Object> sourceValues = new HashMap<>();
    sourceValues.put(Properties.GSTIN, "Word");
    sourceValues.put(Properties.TOTAL, 1276);
    Record sourceRecord = new Record(sourceValues);

    Map<Properties, Object> matchValues = new HashMap<>();
    matchValues.put(Properties.GSTIN, "WordGames");
    matchValues.put(Properties.TOTAL, 1289);
    Record matchRecord = new Record(matchValues);

    Output output = matchService.match(config, sourceRecord, matchRecord);
    assertEquals(output.getScore(), 0.0d, "Score failed to match");
    assertEquals(output.getStatus(), Status.ONLY_IN_ONE, "Status failed to match");
  }
}
