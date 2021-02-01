package com.savbiz.payconiq.stock.config;

import org.h2.server.web.WebServlet;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockApiConfiguration {

  @Bean
  public ServletRegistrationBean<WebServlet> h2servletRegistration() {
    final ServletRegistrationBean<WebServlet> registration = new ServletRegistrationBean<>(
        new WebServlet());
    registration.addUrlMappings("/h2-console/*");
    return registration;
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
