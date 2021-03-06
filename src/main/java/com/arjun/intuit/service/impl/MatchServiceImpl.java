package com.arjun.intuit.service.impl;

import com.arjun.intuit.constant.ColumnProperty;
import com.arjun.intuit.model.ColumnConfig;
import com.arjun.intuit.model.Config;
import com.arjun.intuit.model.Output;
import com.arjun.intuit.model.Record;
import com.arjun.intuit.processor.Processor;
import com.arjun.intuit.service.MatchService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MatchServiceImpl implements MatchService {

  @Override
  public Output match(Config config, Record sourceRecord, Record matchRecord) {
    Output output = new Output(matchRecord);
    double score = 0.0d, total = 0.0d;

    for (Entry<ColumnProperty, ColumnConfig> singleConfig : config.getConfigMap().entrySet()) {
      Processor<Object> processor = singleConfig.getValue().getProcessor();
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

    List<Output> result = new ArrayList<>();
    while(!outputs.isEmpty()) {
      result.add(outputs.poll());
    }
    Collections.reverse(result);

    return result;
  }
}
