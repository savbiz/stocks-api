package com.savbiz.payconiq.stock.web.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
public class StockDto {

  private static final int STOCK_MIN_PRICE_VALUE = 0;

  @NotNull
  private Long id;

  @NotEmpty(message = "Stock name is required")
  private String name;

  @NotNull(message = "Stock price is required")
  @Min(value = STOCK_MIN_PRICE_VALUE, message = "Stock price requires a positive number")
  private BigDecimal currentPrice;

  private ZonedDateTime lastUpdate;

}
