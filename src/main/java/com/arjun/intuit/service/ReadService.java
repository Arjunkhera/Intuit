package com.arjun.intuit.service;

import com.arjun.intuit.model.Config;
import com.arjun.intuit.exception.ReadServiceException;
import com.arjun.intuit.model.Record;
import java.util.List;

public interface ReadService {

  /**
   * Read records from file defined by given configuration
   * Configuration provides the columns to read from the csv file
   * @param config Input Configuration
   * @param inputFilePath CSV file containing input data
   * @return List of read records
   * @throws ReadServiceException failure to read records
   */
  List<Record> read(Config config, String inputFilePath) throws ReadServiceException;
}
