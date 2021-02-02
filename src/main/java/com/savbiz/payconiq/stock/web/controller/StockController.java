package com.savbiz.payconiq.stock.web.controller;

import com.savbiz.payconiq.stock.entity.Stock;
import com.savbiz.payconiq.stock.mapper.StockMapper;
import com.savbiz.payconiq.stock.service.StockService;
import com.savbiz.payconiq.stock.web.dto.StockDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/api/stocks")
@RestController
public class StockController {

  private final StockService stockService;
  private final StockMapper stockMapper;

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  public List<StockDto> getStocks() {
    return stockService.findAllStocks().stream()
        .map(stockMapper::toDto)
        .collect(Collectors.toList());
  }

  @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  public StockDto getStock(@PathVariable Long id) {
    final Stock stock = stockService.findStockById(id);
    return stockMapper.toDto(stock);
  }

  @PutMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public StockDto updateStock(@PathVariable Long id, @RequestParam("newPrice") @PositiveOrZero BigDecimal newPrice) {
    final Stock updatedStock = stockService.updateStock(id, newPrice);
    return stockMapper.toDto(updatedStock);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public StockDto createStock(@Validated @RequestBody StockDto stockDto) {
    final Stock newStock = stockMapper.toEntity(stockDto);
    return stockMapper.toDto(stockService.createStock(newStock));
  }
}
