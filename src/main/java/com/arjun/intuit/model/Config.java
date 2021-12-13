package com.arjun.intuit.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Config {

  private double partialMatchThreshold;
  private double fullMatchThreshold;
  private int numberOfMatches;
  private final List<ColumnConfig> columnConfigList;

  public Config(List<ColumnConfig> columnConfigList) {
    this.columnConfigList = columnConfigList;
    this.fullMatchThreshold = 1.0d;
    this.partialMatchThreshold = 0.0d;
    this.numberOfMatches = 5;
  }

  private Config(Config.Builder builder) {
    this.columnConfigList = builder.columnConfigList;
    this.partialMatchThreshold = builder.partialMatchThreshold;
    this.fullMatchThreshold = builder.fullMatchThreshold;
    this.numberOfMatches = builder.numberOfMatches;
  }

  public static class Builder {

    private double partialMatchThreshold;
    private double fullMatchThreshold;
    private int numberOfMatches;
    private final List<ColumnConfig> columnConfigList;

    public Builder() {
      this.columnConfigList = new ArrayList<>();
      this.fullMatchThreshold = 1.0d;
      this.partialMatchThreshold = 0.0d;
      this.numberOfMatches = 5;
    }

    public Config.Builder withProperty(ColumnConfig columnConfig) {
      this.columnConfigList.add(columnConfig);
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
