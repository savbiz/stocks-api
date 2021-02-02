package com.savbiz.payconiq.stock.service;

import com.savbiz.payconiq.stock.entity.Stock;
import com.savbiz.payconiq.stock.exception.StockNotFoundException;
import com.savbiz.payconiq.stock.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class StockServiceTest {

  @InjectMocks
  private StockService stockService;

  @Mock
  private StockRepository stockRepository;

  @Test
  void test_getStock() {
    final Long id = 1L;

    when(stockRepository.findById(any(Long.class)))
        .thenReturn(Optional.of(mock(Stock.class)));

    stockService.findStockById(id);

    verify(stockRepository).findById(id);
  }

  @Test
  void test_getStocks() {
    final List<Stock> stocks = List.of(mock(Stock.class));

    when(stockRepository.findAll())
        .thenReturn(stocks);

    stockService.findAllStocks();

    verify(stockRepository).findAll();
  }

  @Test
  void test_getStock_shouldThrowStockNotFoundException_whenIncorrectIdIsProvided() {
    final Long id = 1L;

    when(stockRepository.findById(id))
        .thenReturn(Optional.empty());

    assertThrows(StockNotFoundException.class, () -> stockService.findStockById(id));
    verify(stockRepository).findById(id);
  }

  @Test
  void test_createStock() {
    final Stock stock = mock(Stock.class);

    when(stockRepository.save(stock))
        .then(returnsFirstArg());

    stockService.createStock(stock);

    verify(stockRepository).save(stock);
  }

  @Test
  void test_updateStock() {
    final Long id = 1L;
    final BigDecimal newPrice = BigDecimal.TEN;
    final Stock stock = newStock();

    when(stockRepository.findById(eq(id)))
        .thenReturn(Optional.of(stock));

    when(stockRepository.save(stock))
        .then(returnsFirstArg());

    final Stock result = stockService.updateStock(id, newPrice);

    verify(stockRepository).findById(id);
    verify(stockRepository).save(stock);
    assertEquals(newPrice, result.getCurrentPrice());
  }

  @Test
  void test_updateStock_shouldThrowStockNotFoundException_whenIncorrectIdIsProvided() {
    final Long id = 1L;
    final BigDecimal newPrice = BigDecimal.TEN;

    when(stockRepository.findById(id))
        .thenReturn(Optional.empty());

    assertThrows(StockNotFoundException.class, () -> stockService.updateStock(id, newPrice));
    verify(stockRepository).findById(id);
  }

  private Stock newStock() {
    return Stock.builder()
        .id(1L)
        .name("test stock")
        .currentPrice(BigDecimal.ONE)
        .build();
  }
}