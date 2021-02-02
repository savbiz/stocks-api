package com.savbiz.payconiq.stock.web.controller;

import com.savbiz.payconiq.stock.exception.StockNotFoundException;
import com.savbiz.payconiq.stock.web.dto.ErrorResponseDto;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

class StockControllerAdviceTest {

  private final StockControllerAdvice controllerAdvice = new StockControllerAdvice();

  @Test
  void test_handleStockNotFoundException() {
    final StockNotFoundException ex = new StockNotFoundException("Stock not found!");

    final ResponseEntity<ErrorResponseDto> responseEntity = controllerAdvice.handleStockNotFoundException(ex);

    assertThat(responseEntity, is(notNullValue()));
    assertThat(responseEntity.getStatusCode().value(), is(HttpStatus.SC_BAD_REQUEST));
    assertThat(responseEntity.getBody(), is(notNullValue()));
    assertThat(responseEntity.getBody().getErrorCode(), is(notNullValue()));
    assertThat(responseEntity.getBody().getErrorMessage(), is(notNullValue()));
    assertThat(responseEntity.getBody().getMessages(), is(nullValue()));
  }
}