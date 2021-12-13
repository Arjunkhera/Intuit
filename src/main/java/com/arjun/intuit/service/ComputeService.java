package com.arjun.intuit.service;

import com.arjun.intuit.model.Config;
import com.arjun.intuit.exception.ReadServiceException;

public interface ComputeService {

  void compute(Config config, String firstFilePath, String secondFilePath)
      throws ReadServiceException;
}
