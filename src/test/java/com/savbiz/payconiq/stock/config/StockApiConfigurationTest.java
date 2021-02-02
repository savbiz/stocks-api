package com.savbiz.payconiq.stock.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(MockitoExtension.class)
class StockApiConfigurationTest {

  @InjectMocks
  private StockApiConfiguration stockApiConfiguration;

  @Test
  void test_modelMapper() {
    final ModelMapper modelMapper = stockApiConfiguration.modelMapper();

    assertThat(modelMapper, is(notNullValue()));
  }

}
