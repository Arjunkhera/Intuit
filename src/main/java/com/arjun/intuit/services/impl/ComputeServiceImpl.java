package com.arjun.intuit.services.impl;

import com.arjun.intuit.configuration.Config;
import com.arjun.intuit.models.Record;
import com.arjun.intuit.services.MatchService;
import com.arjun.intuit.services.ReadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class ComputeServiceImpl {

  private Config config;

  @Autowired
  ReadService readService;

  @Autowired
  MatchService matchService;

  public ComputeServiceImpl(Config config) {
    this.config = config;
  }

  void compute(String firstFilePath, String secondFilePath) {
    // Read first file
    List<Record> firstRecords = readService.read(config, firstFilePath);

    // Read second file
    List<Record> secondRecords = readService.read(config, secondFilePath);


  }
}
