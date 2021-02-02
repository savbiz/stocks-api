package com.savbiz.payconiq.stock.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockApiConfiguration {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

}
