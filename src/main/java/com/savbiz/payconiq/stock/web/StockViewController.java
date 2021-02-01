package com.savbiz.payconiq.stock.web;

import com.savbiz.payconiq.stock.entity.Stock;
import com.savbiz.payconiq.stock.mapper.StockMapper;
import com.savbiz.payconiq.stock.service.StockService;
import com.savbiz.payconiq.stock.web.dto.StockDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping({"/api/stocks"})
@Controller
@RequiredArgsConstructor
public class StockViewController {

  private final StockService stockService;

  private final StockMapper stockMapper;

  @RequestMapping({"/"})
  public String index(final Model model) {
    final List<StockDto> allStockDtos = stockService.findAllStocks().stream()
        .map(stockMapper::toDto)
        .collect(Collectors.toList());

    model.addAttribute("stocks", allStockDtos);
    return "index";
  }

  @GetMapping("/new")
  public String showAddStockForm(final Stock stock) {
    return "add-stock";
  }

  @PostMapping("/add")
  public String addStock(@Valid final StockDto stockDto, final BindingResult result,
                         final Model model) {
    final Stock stock = stockMapper.toEntity(stockDto);

    if (result.hasErrors()) {
      return "add-stock";
    }

    stockService.saveStock(stock);

    final List<StockDto> allStockDtos = stockService.findAllStocks().stream()
        .map(stockMapper::toDto)
        .collect(Collectors.toList());

    model.addAttribute("stocks", allStockDtos);

    return "index";
  }

  @GetMapping("/edit/{id}")
  public String showUpdateForm(@PathVariable("id") final Long id, final Model model) {
    final StockDto stockDto = stockMapper.toDto(stockService.findStockById(id));

    model.addAttribute("stock", stockDto);

    return "update-stock";
  }

  @PostMapping("/update/{id}")
  public String updateStock(@PathVariable("id") final Long id, @Valid final StockDto stockDto,
                            BindingResult result, Model model) {
    final Stock stock = stockMapper.toEntity(stockDto);

    if (result.hasErrors()) {
      stock.setId(id);
      return "update-stock";
    }

    stockService.saveStock(stock);

    final List<StockDto> allStockDtos = stockService.findAllStocks().stream()
        .map(stockMapper::toDto)
        .collect(Collectors.toList());

    model.addAttribute("stocks", allStockDtos);

    return "index";
  }

  @GetMapping("/delete/{id}")
  public String deleteStock(@PathVariable("id") final Long id, final Model model) {
    stockService.deleteStockById(id);

    final List<StockDto> allStockDtos = stockService.findAllStocks().stream()
        .map(stockMapper::toDto)
        .collect(Collectors.toList());

    model.addAttribute("stocks", allStockDtos);

    return "index";
  }
}
