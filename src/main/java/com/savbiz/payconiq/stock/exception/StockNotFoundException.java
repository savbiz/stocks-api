package com.savbiz.payconiq.stock.exception;

import java.util.function.Supplier;

public class StockNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public StockNotFoundException(String message) {
    super(message);
  }

  public static Supplier<StockNotFoundException> supplier(String message) {
    return () -> new StockNotFoundException(message);
  }
}
