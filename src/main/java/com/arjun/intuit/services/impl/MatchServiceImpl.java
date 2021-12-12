package com.arjun.intuit.services.impl;

import com.arjun.intuit.comparators.Processor;
import com.arjun.intuit.configuration.Config;
import com.arjun.intuit.configuration.Property;
import com.arjun.intuit.constants.Properties;
import com.arjun.intuit.constants.Status;
import com.arjun.intuit.models.Output;
import com.arjun.intuit.models.Record;
import com.arjun.intuit.services.MatchService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class MatchServiceImpl implements MatchService {

  @Override
  public Output match(Config config, Record sourceRecord, Record matchRecord) {
    Output output = new Output();

    double score = 0.0d, total = 0.0d;
    for (Entry<Properties, Property> singleConfig : config.getConfigMap().entrySet()) {
      Processor processor = singleConfig.getValue().getProcessor();
      double weight = singleConfig.getValue().getWeight();

      score += processor.process(sourceRecord.getValues().get(singleConfig.getKey()),
          matchRecord.getValues().get(singleConfig.getKey())) * weight;
      total += weight;
    }
    output.setScore(score / total);
    setStatus(config, output);

    return output;
  }

  @Override
  public List<Output> match(Config config, Record sourceRecord, List<Record> matchRecords) {
    PriorityQueue<Output> outputs = new PriorityQueue<>(Comparator.comparingDouble(Output::getScore));

    for (Record matchRecord : matchRecords) {
      outputs.offer(match(config, sourceRecord, matchRecord));
      if(outputs.size() > config.getNumberOfMatches()) {
        outputs.poll();
      }
    }

    return new ArrayList<>(outputs);
  }

  private void setStatus(Config config, Output output) {
    double score = output.getScore();
    if (score >= config.getFullMatchThreshold()) {
      output.setStatus(Status.FULL_MATCH);
    } else if (score >= config.getPartialMatchThreshold()) {
      output.setStatus(Status.PARTIAL);
    } else {
      output.setStatus(Status.ONLY_IN_ONE);
    }
  }
}
