package com.arjun.intuit.model;

import com.arjun.intuit.constant.Status;
import lombok.Data;

@Data
public class Output {
  private Double score;
  private Status status;
  private Record record;
}
