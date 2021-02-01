package com.savbiz.payconiq.stock.service;

import com.savbiz.payconiq.stock.entity.Stock;
import com.savbiz.payconiq.stock.exception.StockNotFoundException;
import com.savbiz.payconiq.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

  private final StockRepository stockRepository;

  @Transactional
  public Stock findStockById(Long id) {
    return stockRepository.findById(id)
        .orElseThrow(
            StockNotFoundException.supplier(String.format("No order found for id '%s'.", id))
        );
  }

  @Transactional
  public List<Stock> findAllStocks() {
    return stockRepository.findAll();
  }

  @Transactional
  public Stock saveStock(Stock stock) {
    return stockRepository.save(stock);
  }

  @Transactional
  public void deleteStockById(Long id) {
    stockRepository.delete(findStockById(id));
  }
}
