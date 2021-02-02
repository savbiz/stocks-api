package com.savbiz.payconiq.stock.web.controller;

import com.savbiz.payconiq.stock.exception.StockNotFoundException;
import com.savbiz.payconiq.stock.web.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
@RestControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class StockControllerAdvice {

  @ExceptionHandler({StockNotFoundException.class})
  public ResponseEntity<ErrorResponseDto> handleStockNotFoundException(final StockNotFoundException exception) {

    log.error("Error occurred with message: [{}]", exception.getMessage());

    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(ErrorResponseDto.builder()
            .errorCode("S-400a")
            .errorMessage(exception.getMessage())
            .build());
  }

}
