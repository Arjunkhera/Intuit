package com.arjun.intuit.models;

import com.arjun.intuit.constants.Status;
import lombok.Data;

@Data
public class Output {
  private Double score;
  private Status status;
  private Record record;
}
