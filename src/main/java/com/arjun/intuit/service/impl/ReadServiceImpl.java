package com.arjun.intuit.service.impl;

import com.arjun.intuit.configuration.Config;
import com.arjun.intuit.constant.Properties;
import com.arjun.intuit.model.Record;
import com.arjun.intuit.service.ReadService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadServiceImpl implements ReadService {

  @Override
  public List<Record> read(Config config, String inputFilePath) {
    List<Record> records = new ArrayList<>();

    Map<Integer, Properties> propertyIndexMap = new HashMap<>();
    List<List<String>> values;
    try {
      values = readRecord(inputFilePath);

      List<String> keys = values.get(0);
      for (int index = 0; index < keys.size(); index++) {
        String key = keys.get(index);

        for (Properties property : config.getConfigMap().keySet()) {
          if (key.equals(property.getKeyName())) {
            propertyIndexMap.put(index, property);
          }
        }
      }

      for (List<String> row : values.subList(1, values.size())) {
        Map<Properties, Object> recordValues = new HashMap<>();
        for (int index = 0; index < row.size(); index++) {
          String val = row.get(index);
          recordValues.put(propertyIndexMap.get(index), val);

          Properties property = propertyIndexMap.get(index);
          if (property.getObjectType().isAssignableFrom(Double.class)) {
            recordValues.put(property, Double.valueOf(val));
          } else if (property.getObjectType().isAssignableFrom(Instant.class)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yy");
            LocalDateTime ldt = LocalDate.parse( val , formatter ).atStartOfDay();
            ZoneId z = ZoneId.of( "America/Toronto" ) ;
            ZonedDateTime zdt = ldt.atZone( z ) ;
            recordValues.put(property, zdt.toInstant());
            // 3/9/17
          } else if (property.getObjectType().isAssignableFrom(Integer.class)) {
            recordValues.put(property, Integer.valueOf(val));
          } else if (property.getObjectType().isAssignableFrom(String.class)) {
            recordValues.put(property, val);
          }
        }
        records.add(new Record(recordValues));
      }
    } catch (Exception exception) {
      log.error("Failed to read file with exception {}", exception.toString());
    }

    return records;
  }

  private List<List<String>> readRecord(String inputFilePath)
      throws IOException, CsvValidationException {
    List<List<String>> records = new ArrayList<>();
    try (CSVReader csvReader = new CSVReader(new FileReader(inputFilePath))) {
      String[] values = null;
      while ((values = csvReader.readNext()) != null) {
        records.add(Arrays.asList(values));
      }
    }

    return records;
  }
}
