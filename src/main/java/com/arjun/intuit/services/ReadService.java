package com.arjun.intuit.services;

import com.arjun.intuit.configuration.Config;
import com.arjun.intuit.models.Record;
import java.util.List;

public interface ReadService {
  List<Record> read(Config config, String inputFilePath);
}
