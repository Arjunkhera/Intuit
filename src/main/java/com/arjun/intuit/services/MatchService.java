package com.arjun.intuit.services;

import com.arjun.intuit.configuration.Config;
import com.arjun.intuit.models.Output;
import com.arjun.intuit.models.Record;
import java.util.List;

public interface MatchService {

  Output match(Config config, Record sourceRecord, Record matchRecord);

  List<Output> match(Config config, Record sourceRecord, List<Record> matchRecords);
}
