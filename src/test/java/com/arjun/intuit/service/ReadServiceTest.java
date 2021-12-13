package com.arjun.intuit.service;

import com.arjun.intuit.constant.ColumnProperty;
import com.arjun.intuit.exception.ReadServiceException;
import com.arjun.intuit.model.Config;
import com.arjun.intuit.processor.InstantProcessor;
import com.arjun.intuit.service.impl.ColumnConfigFactory;
import java.time.Duration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReadServiceTest {

  @Autowired
  ReadService readService;

  @Test
  @DisplayName("Read Service Test")
  public void readSuccessTest() throws ReadServiceException {
    Config config = new Config.Builder()
        .withProperty(ColumnConfigFactory.create(ColumnProperty.GSTIN, 1.0, 1))
        .withProperty(ColumnConfigFactory
            .create(ColumnProperty.DATE, 1.0, new InstantProcessor(Duration.ofDays(3))))
        .withProperty(ColumnConfigFactory.create(ColumnProperty.BILLNO, 1.0, 3.0d))
        .withProperty(ColumnConfigFactory.create(ColumnProperty.GSTRATE, 1.0, 0.5d))
        .withProperty(ColumnConfigFactory.create(ColumnProperty.TAXVALUE, 1.0, 1000.0d))
        .withProperty(ColumnConfigFactory.create(ColumnProperty.IGST, 1.0, 0.5d))
        .withProperty(ColumnConfigFactory.create(ColumnProperty.CGST, 1.0, 200.0d))
        .withProperty(ColumnConfigFactory.create(ColumnProperty.SGST, 1.0, 200.0d))
        .withProperty(ColumnConfigFactory.create(ColumnProperty.TOTAL, 1.0, 1000.0d))
        .withPartialMatchThreshold(0.5d)
        .withMatchValues(3)
        .build();

    String inputFilePath = "IDX-SE2-Craft-Buyer-Seller[776998]/IDX-SE2-Craft/Buyer (1).csv";
    readService.read(config, inputFilePath);
  }
}
