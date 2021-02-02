package com.savbiz.payconiq.stock.config;

import org.h2.server.web.WebServlet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.web.servlet.ViewResolver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(MockitoExtension.class)
class WebConfigTest {

  @InjectMocks
  private WebConfig webConfig;

  @Test
  void test_viewResolver() {
    final ViewResolver viewResolver = webConfig.viewResolver();

    assertThat(viewResolver, is(notNullValue()));
  }

  @Test
  void test_modelMapper() {
    final ServletRegistrationBean<WebServlet> h2servletRegistration = webConfig.h2servletRegistration();

    assertThat(h2servletRegistration, is(notNullValue()));
  }

}
