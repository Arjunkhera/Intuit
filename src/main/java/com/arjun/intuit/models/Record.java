package com.arjun.intuit.models;

import com.arjun.intuit.constants.Properties;
import java.util.Map;
import lombok.Data;

@Data
public class Record {
  private Map<Properties, Object> values;
}
