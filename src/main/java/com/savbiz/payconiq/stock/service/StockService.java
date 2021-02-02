package com.savbiz.payconiq.stock.service;

import com.savbiz.payconiq.stock.entity.Stock;
import com.savbiz.payconiq.stock.exception.StockNotFoundException;
import com.savbiz.payconiq.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

  private final StockRepository stockRepository;

  @Transactional
  public Stock findStockById(final Long id) {
    return findById(id);
  }

  @Transactional
  public List<Stock> findAllStocks() {
    return stockRepository.findAll();
  }

  @Transactional
  public Stock createStock(final Stock stock) {
    return stockRepository.save(stock);
  }

  @Transactional
  public Stock updateStock(final Long id, final BigDecimal newPrice) {
    final Stock stock = findById(id);

    stock.setCurrentPrice(newPrice);

    return stockRepository.save(stock);
  }

  private Stock findById(final Long id) {
    return stockRepository.findById(id)
        .orElseThrow(
            StockNotFoundException.supplier(String.format("No stock found for id '%s'.", id))
        );
  }
}
