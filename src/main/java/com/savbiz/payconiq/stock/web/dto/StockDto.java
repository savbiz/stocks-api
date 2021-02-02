package com.savbiz.payconiq.stock.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {

  private static final int STOCK_MIN_PRICE_VALUE = 0;

  private Long id;

  @NotEmpty(message = "Stock name is required")
  private String name;

  @NotNull(message = "Stock price is required")
  @Min(value = STOCK_MIN_PRICE_VALUE, message = "Stock price requires a positive number")
  private BigDecimal currentPrice;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEE MMM dd HH:mm:ss Z yyyy")
  private ZonedDateTime lastUpdate;

}
