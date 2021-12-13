package com.arjun.intuit.service.impl;

import com.arjun.intuit.model.Config;
import com.arjun.intuit.exception.ReadServiceException;
import com.arjun.intuit.model.Output;
import com.arjun.intuit.model.Record;
import com.arjun.intuit.service.ComputeService;
import com.arjun.intuit.service.MatchService;
import com.arjun.intuit.service.ReadService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ComputeServiceImpl implements ComputeService {

  @Autowired
  ReadService readService;

  @Autowired
  MatchService matchService;

  @Override
  public void compute(Config config, String firstFilePath, String secondFilePath)
      throws ReadServiceException {
    // Read first file
    List<Record> firstRecords = readService.read(config, firstFilePath);

    // Read second file
    List<Record> secondRecords = readService.read(config, secondFilePath);

    for(Record record : firstRecords) {
      List<Output> outputs = matchService.match(config, record, secondRecords);
      generateOutput(record, outputs);
    }
  }

  private void generateOutput(Record sourceRecord, List<Output> outputs) {
    log.info("Source {}", sourceRecord.getValues());

    int index = 1;
    for(Output output : outputs) {
      log.info("Match {} {}", index++,output.toString());
    }
  }
}
