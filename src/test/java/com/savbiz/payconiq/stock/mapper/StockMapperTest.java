package com.savbiz.payconiq.stock.mapper;

import com.savbiz.payconiq.stock.entity.Stock;
import com.savbiz.payconiq.stock.web.dto.StockDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class StockMapperTest {

  @Mock
  ModelMapper modelMapper;

  @InjectMocks
  private StockMapper stockMapper;

  @Test
  void test_toDto() {
    final Long id = 1L;
    final String name = "stock 1";
    final BigDecimal currentPrice = BigDecimal.TEN;
    final ZonedDateTime lastUpdated = ZonedDateTime.now(ZoneOffset.UTC);

    final Stock stock = Stock.builder()
        .id(id)
        .name(name)
        .currentPrice(currentPrice)
        .lastUpdate(lastUpdated)
        .build();

    stockMapper.toDto(stock);

    verify(modelMapper).map(eq(stock), eq(StockDto.class));
  }

  @Test
  void test_toEntity() {
    final Long id = 1L;
    final String name = "stock 1";
    final BigDecimal currentPrice = BigDecimal.TEN;
    final ZonedDateTime lastUpdated = ZonedDateTime.now(ZoneOffset.UTC);

    final StockDto stockDto = StockDto.builder()
        .id(id)
        .name(name)
        .currentPrice(currentPrice)
        .lastUpdate(lastUpdated)
        .build();


    stockMapper.toEntity(stockDto);

    verify(modelMapper).map(eq(stockDto), eq(Stock.class));
  }

}
