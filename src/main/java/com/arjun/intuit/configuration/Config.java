package com.arjun.intuit.configuration;

import com.arjun.intuit.constant.Properties;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class Config {

  private double partialMatchThreshold;
  private double fullMatchThreshold;
  private int numberOfMatches;
  private final Map<Properties, Property> configMap;

  public Config(Map<Properties, Property> inputMap) {
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

    private final Map<Properties, Property> configMap;
    private double partialMatchThreshold;
    private double fullMatchThreshold;
    private int numberOfMatches;

    public Builder() {
      this.configMap = new HashMap<>();
      this.fullMatchThreshold = 1.0d;
      this.partialMatchThreshold = 0.0d;
      this.numberOfMatches = 5;
    }

    public Config.Builder withProperty(Properties propertyKey, Property property) {
      this.configMap.put(propertyKey, property);
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
