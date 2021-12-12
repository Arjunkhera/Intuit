package com.arjun.intuit.service;

import com.arjun.intuit.configuration.Config;
import com.arjun.intuit.model.Output;
import com.arjun.intuit.model.Record;
import java.util.List;

public interface MatchService {

  Output match(Config config, Record sourceRecord, Record matchRecord);

  List<Output> match(Config config, Record sourceRecord, List<Record> matchRecords);
}
