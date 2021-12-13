package com.arjun.intuit.model;

import com.arjun.intuit.constant.ColumnProperty;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class Config {

  private double partialMatchThreshold;
  private double fullMatchThreshold;
  private int numberOfMatches;
  private final Map<ColumnProperty, ColumnConfig> configMap;

  public Config(Map<ColumnProperty, ColumnConfig> inputMap) {
    this.configMap = inputMap;
    this.fullMatchThreshold = 1.0d;
    this.partialMatchThreshold = 0.0d;
    this.numberOfMatches = 5;
  }

  private Config(Config.Builder builder) {
    this.configMap = builder.configMap;
    this.partialMatchThreshold = builder.partialMatchThreshold;
    this.fullMatchThreshold = builder.fullMatchThreshold;
    this.numberOfMatches = builder.numberOfMatches;
  }

  public static class Builder {

    private double partialMatchThreshold;
    private double fullMatchThreshold;
    private int numberOfMatches;
    private final Map<ColumnProperty, ColumnConfig> configMap;

    public Builder() {
      this.configMap = new HashMap<>();
      this.fullMatchThreshold = 1.0d;
      this.partialMatchThreshold = 0.0d;
      this.numberOfMatches = 5;
    }

    public Config.Builder withProperty(ColumnProperty propertyKey, ColumnConfig columnConfig) {
      this.configMap.put(propertyKey, columnConfig);
      return this;
    }

    public Config.Builder withPartialMatchThreshold(double partialMatchThreshold) {
      this.partialMatchThreshold = partialMatchThreshold;
      return this;
    }

    public Config.Builder withFullMatchThreshold(double fullMatchThreshold) {
      this.fullMatchThreshold = fullMatchThreshold;
      return this;
    }

    public Config.Builder withMatchValues(int matchValues) {
      this.numberOfMatches = matchValues;
      return this;
    }

    public Config build() {
      return new Config(this);
    }
  }
}
