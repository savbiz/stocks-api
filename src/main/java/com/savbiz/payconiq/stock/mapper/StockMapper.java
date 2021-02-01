package com.savbiz.payconiq.stock.mapper;

import com.savbiz.payconiq.stock.entity.Stock;
import com.savbiz.payconiq.stock.web.dto.StockDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockMapper {

  private final ModelMapper modelMapper;

  public StockDto toDto(Stock stock) {
    return modelMapper.map(stock, StockDto.class);
  }

  public Stock toEntity(StockDto stockDto) {
    return modelMapper.map(stockDto, Stock.class);
  }
}
