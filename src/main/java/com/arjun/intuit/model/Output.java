package com.arjun.intuit.model;

import com.arjun.intuit.constant.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Output {
  private Double score;
  private Status status;
  private Record record;

  public Output(Record record) {
    this.record = record;
  }
}
