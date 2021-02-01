package com.savbiz.payconiq.stock.web;

import com.savbiz.payconiq.stock.mapper.StockMapper;
import com.savbiz.payconiq.stock.service.StockService;
import com.savbiz.payconiq.stock.web.dto.StockDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class HomeController {

  private final StockMapper stockMapper;

  private final StockService stockService;

  @GetMapping("/")
  public String root(final Model model) {
    final List<StockDto> allStocks = stockService.findAllStocks().stream()
        .map(stockMapper::toDto)
        .collect(Collectors.toList());

    model.addAttribute("stocks", allStocks);

    return "index";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/logout")
  public String logout() {
    return "login";
  }

  @GetMapping("/access-denied")
  public String accessDenied() {
    return "/error/access-denied";
  }

}
