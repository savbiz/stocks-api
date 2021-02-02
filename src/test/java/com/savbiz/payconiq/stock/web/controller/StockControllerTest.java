package com.savbiz.payconiq.stock.web.controller;

import com.savbiz.payconiq.stock.config.WebConfig;
import com.savbiz.payconiq.stock.entity.Stock;
import com.savbiz.payconiq.stock.mapper.StockMapper;
import com.savbiz.payconiq.stock.service.StockService;
import com.savbiz.payconiq.stock.test_utils.ResourceLoader;
import com.savbiz.payconiq.stock.web.dto.StockDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = StockController.class)
@Import(WebConfig.class)
class StockControllerTest {

  @MockBean
  private StockService stockService;

  @MockBean
  private StockMapper stockMapper;

  @Autowired
  private MockMvc mockMvc;

  @BeforeEach
  void setMockMvc() {
    this.mockMvc = standaloneSetup(new StockController(stockService, stockMapper)).build();
  }

  @Test
  void test_getStocks() throws Exception {
    when(stockService.findAllStocks()).thenReturn(List.of(newStock()));
    when(stockMapper.toDto(any(Stock.class))).thenReturn(newStockDto());

    mockMvc.perform(get("/api/stocks")
        .contentType(APPLICATION_JSON_VALUE))
        .andExpect(status().isOk());

    verify(stockService).findAllStocks();
    verify(stockMapper).toDto(any(Stock.class));
  }

  @Test
  void test_getStock() throws Exception {
    final Long id = 1L;

    when(stockService.findStockById(id)).thenReturn(newStock());
    when(stockMapper.toDto(any(Stock.class))).thenReturn(newStockDto());

    mockMvc.perform(get("/api/stocks/" + id)
        .contentType(APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.id").value("1"))
        .andExpect(jsonPath("$.name").isString())
        .andExpect(jsonPath("$.currentPrice").isNotEmpty())
        .andExpect(status().isOk());

    verify(stockService).findStockById(id);
    verify(stockMapper).toDto(any(Stock.class));
  }

  @Test
  void test_createStock() throws Exception {
    when(stockMapper.toEntity(any(StockDto.class))).thenReturn(newStock());
    when(stockService.createStock(any(Stock.class))).then(returnsFirstArg());

    mockMvc.perform(post("/api/stocks")
        .content(ResourceLoader.getResource("/json/valid_stock_request.json"))
        .contentType(APPLICATION_JSON_VALUE))
        .andExpect(status().isCreated());

    verify(stockMapper).toEntity(any(StockDto.class));
    verify(stockService).createStock(any(Stock.class));
  }

  @Test
  void test_updateStock() throws Exception {
    final Long id = 1L;
    final BigDecimal newPrice = BigDecimal.valueOf(350.99);

    when(stockService.updateStock(id, newPrice)).thenReturn(newStock());
    when(stockMapper.toDto(any(Stock.class))).thenReturn(newStockDto());

    mockMvc.perform(put("/api/stocks/" + id)
        .queryParam("newPrice", newPrice.toPlainString())
        .contentType(APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.id").isNotEmpty())
        .andExpect(status().isOk());

    verify(stockService).updateStock(id, newPrice);
    verify(stockMapper).toDto(any(Stock.class));
  }

  private Stock newStock() {
    return Stock.builder()
        .id(1L)
        .name("test stock")
        .currentPrice(BigDecimal.TEN)
        .build();
  }

  private StockDto newStockDto() {
    return StockDto.builder()
        .id(1L)
        .name("test stock")
        .currentPrice(BigDecimal.TEN)
        .build();
  }
}
