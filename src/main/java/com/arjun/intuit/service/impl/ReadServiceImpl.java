package com.arjun.intuit.service.impl;

import com.arjun.intuit.model.ColumnConfig;
import com.arjun.intuit.model.Config;
import com.arjun.intuit.constant.ColumnProperty;
import com.arjun.intuit.exception.ReadServiceException;
import com.arjun.intuit.model.Record;
import com.arjun.intuit.service.ParsingService;
import com.arjun.intuit.service.ReadService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReadServiceImpl implements ReadService {

  @Autowired
  ParsingService parsingService;

  @Override
  public List<Record> read(Config config, String inputFilePath) throws ReadServiceException {
    List<Record> records = new ArrayList<>();

    try {
      // Read the contents from CSV file
      List<List<String>> values = readRecord(inputFilePath);

      // Parse the columns for keys
      Map<Integer, ColumnProperty> propertyIndexMap = generateKeyIndexMap(config, values.get(0));

      // Read the contents of the rows
      for (List<String> row : values.subList(1, values.size())) {
        Map<ColumnProperty, Object> recordValues = new HashMap<>();
        for (int index = 0; index < row.size(); index++) {
          String sourceValue = row.get(index);
          ColumnProperty property = propertyIndexMap.get(index);

          if (sourceValue.equals("")) {
            recordValues.put(property, null);
          } else if (property.getObjectType().isAssignableFrom(Double.class)) {
            recordValues.put(property, parsingService.parseDouble(sourceValue));
          } else if (property.getObjectType().isAssignableFrom(Instant.class)) {
            recordValues.put(property, parsingService.parseInstant(sourceValue));
          } else if (property.getObjectType().isAssignableFrom(Integer.class)) {
            recordValues.put(property, parsingService.parseInteger(sourceValue));
          } else if (property.getObjectType().isAssignableFrom(String.class)) {
            recordValues.put(property, parsingService.parseString(sourceValue));
          }
        }

        Record record = new Record(recordValues);
        log.info("Read record {}", record);
        records.add(record);
      }
    } catch (IOException | CsvValidationException exception) {
      log.error("Failed to read file {}", Arrays.toString(exception.getStackTrace()));
      throw new ReadServiceException("Failed to read file", exception);
    } catch (Exception e) {
      log.error("Failed to process read  {}", Arrays.toString(e.getStackTrace()));
      throw new ReadServiceException("Failed to process read operation", e);
    }

    return records;
  }

  /**
   * Utility method to read CSV file
   * @param inputFilePath Path to file which needs to be read
   * @return List of rows where each row consists of list of column values
   * @throws IOException
   * @throws CsvValidationException
   */
  private List<List<String>> readRecord(String inputFilePath)
      throws IOException, CsvValidationException {
    List<List<String>> records = new ArrayList<>();
    try (CSVReader csvReader = new CSVReader(new FileReader(inputFilePath))) {
      String[] values;
      while ((values = csvReader.readNext()) != null) {
        records.add(Arrays.asList(values));
      }
    }

    return records;
  }

  /**
   * Generate a map of columns values and their respective indexes
   * @param config provides keys to be read from the columns
   * @param keys the keys obtained from CSV file
   * @return set of config keys obtained from CSV and their index values
   */
  private Map<Integer, ColumnProperty> generateKeyIndexMap(Config config, List<String> keys) {
    Map<Integer, ColumnProperty> propertyIndexMap = new HashMap<>();
    for (int index = 0; index < keys.size(); index++) {
      String key = keys.get(index);

      for (ColumnConfig columnConfig : config.getColumnConfigList()) {
        if (key.equals(columnConfig.getColumnProperty().getKeyName())) {
          propertyIndexMap.put(index, columnConfig.getColumnProperty());
        }
      }
    }

    return propertyIndexMap;
  }
}
