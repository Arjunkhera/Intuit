package com.arjun.intuit.model;

import com.arjun.intuit.constant.ColumnProperty;
import java.util.Map;
import lombok.Data;

@Data
public class Record {
  private Map<ColumnProperty, Object> values;

  public Record(Map<ColumnProperty, Object> values) {
    this.values = values;
  }
}
