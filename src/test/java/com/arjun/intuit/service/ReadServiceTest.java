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
public class ReadServiceTest {

  @Autowired
  ReadService readService;

  @Test
  @DisplayName("Read Service Test")
  public void readSuccessTest() throws ReadServiceException {
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
        .build();

    String inputFilePath = "IDX-SE2-Craft-Buyer-Seller[776998]/IDX-SE2-Craft/Buyer (1).csv";
    readService.read(config, inputFilePath);
  }
}
