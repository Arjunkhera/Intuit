package com.arjun.intuit.service;

import com.arjun.intuit.configuration.Config;
import com.arjun.intuit.configuration.Property;
import com.arjun.intuit.constant.Properties;
import com.arjun.intuit.processor.DoubleProcessor;
import com.arjun.intuit.processor.InstantProcessor;
import com.arjun.intuit.processor.StringProcessor;
import com.arjun.intuit.service.impl.ReadServiceImpl;
import java.time.Duration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReadServiceTest {

  @Test
  @DisplayName("Read Service Test")
  public void readSuccessTest() {
    ReadService readService = new ReadServiceImpl();
    Config config = new Config.Builder()
        .withProperty(Properties.GSTIN, new Property(new StringProcessor(1)))
        .withProperty(Properties.DATE, new Property(new InstantProcessor(Duration.ofDays(3))))
        .withProperty(Properties.BILLNO, new Property(new StringProcessor(3)))
        .withProperty(Properties.GSTRATE, new Property(new DoubleProcessor(0.5d)))
        .withProperty(Properties.TAXVALUE, new Property(new DoubleProcessor(1000d)))
        .withProperty(Properties.IGST, new Property(new DoubleProcessor(0.5d)))
        .withProperty(Properties.CGST, new Property(new DoubleProcessor(200d)))
        .withProperty(Properties.SGST, new Property(new DoubleProcessor(200d)))
        .withProperty(Properties.TOTAL, new Property(new DoubleProcessor(1000d)))
        .build();

    String inputFilePath = "IDX-SE2-Craft-Buyer-Seller[776998]/IDX-SE2-Craft/Buyer (1).csv";
    readService.read(config, inputFilePath);
  }
}
