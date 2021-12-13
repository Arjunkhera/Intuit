package com.arjun.intuit.service;

import com.arjun.intuit.model.Config;
import com.arjun.intuit.model.ColumnConfig;
import com.arjun.intuit.constant.ColumnProperty;
import com.arjun.intuit.exception.ReadServiceException;
import com.arjun.intuit.processor.DoubleProcessor;
import com.arjun.intuit.processor.InstantProcessor;
import com.arjun.intuit.processor.StringProcessor;
import java.time.Duration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ComputeServiceTest {

  @Autowired
  ComputeService computeService;

  @Test
  @DisplayName("Compute Service Test")
  public void computeServiceTest() throws ReadServiceException {
    Config config = new Config.Builder()
        .withProperty(ColumnProperty.GSTIN, new ColumnConfig(new StringProcessor(1)))
        .withProperty(ColumnProperty.DATE, new ColumnConfig(new InstantProcessor(Duration.ofDays(3))))
        .withProperty(ColumnProperty.BILLNO, new ColumnConfig(new StringProcessor(3)))
        .withProperty(ColumnProperty.GSTRATE, new ColumnConfig(new DoubleProcessor(0.5d)))
        .withProperty(ColumnProperty.TAXVALUE, new ColumnConfig(new DoubleProcessor(1000d)))
        .withProperty(ColumnProperty.IGST, new ColumnConfig(new DoubleProcessor(0.5d)))
        .withProperty(ColumnProperty.CGST, new ColumnConfig(new DoubleProcessor(200d)))
        .withProperty(ColumnProperty.SGST, new ColumnConfig(new DoubleProcessor(200d)))
        .withProperty(ColumnProperty.TOTAL, new ColumnConfig(new DoubleProcessor(1000d)))
        .withPartialMatchThreshold(0.5d)
        .withMatchValues(3)
        .build();

    String buyerFilePath = "IDX-SE2-Craft-Buyer-Seller[776998]/IDX-SE2-Craft/Buyer (1).csv";
    String supplierFilePath = "IDX-SE2-Craft-Buyer-Seller[776998]/IDX-SE2-Craft/Supplier (1).csv";
    computeService.compute(config, buyerFilePath, supplierFilePath);
  }
}

