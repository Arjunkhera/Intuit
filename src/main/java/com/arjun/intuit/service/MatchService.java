package com.arjun.intuit.service;

import com.arjun.intuit.constant.Status;
import com.arjun.intuit.model.Config;
import com.arjun.intuit.model.Output;
import com.arjun.intuit.model.Record;
import java.util.List;

public interface MatchService {

  Output match(Config config, Record sourceRecord, Record matchRecord);

  List<Output> match(Config config, Record sourceRecord, List<Record> matchRecords);

  default void setStatus(Config config, Output output) {
    double score = output.getScore();
    if (score >= config.getFullMatchThreshold()) {
      output.setStatus(Status.FULL_MATCH);
    } else if (score > config.getPartialMatchThreshold()) {
      output.setStatus(Status.PARTIAL);
    } else {
      output.setStatus(Status.ONLY_IN_FIRST);
    }
  }
}
