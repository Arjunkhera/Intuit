package com.arjun.intuit.model;

import com.arjun.intuit.constant.Properties;
import java.util.Map;
import lombok.Data;

@Data
public class Record {
  private Map<Properties, Object> values;

  public Record(Map<Properties, Object> values) {
    this.values = values;
  }
}
