package com.arjun.intuit.service;

import com.arjun.intuit.configuration.Config;
import com.arjun.intuit.model.Record;
import java.util.List;

public interface ReadService {
  List<Record> read(Config config, String inputFilePath);
}
