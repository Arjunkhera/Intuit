package com.arjun.intuit.service;

import com.arjun.intuit.configuration.Config;

public interface ComputeService {

  void compute(Config config, String firstFilePath, String secondFilePath);
}
