package com.arjun.intuit.service;

import com.arjun.intuit.model.Config;
import com.arjun.intuit.exception.ReadServiceException;
import com.arjun.intuit.model.Record;
import java.util.List;

public interface ReadService {

  List<Record> read(Config config, String inputFilePath) throws ReadServiceException;
}
