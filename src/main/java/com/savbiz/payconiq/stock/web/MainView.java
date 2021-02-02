package com.savbiz.payconiq.stock.web;

import com.savbiz.payconiq.stock.mapper.StockMapper;
import com.savbiz.payconiq.stock.service.StockService;
import com.savbiz.payconiq.stock.web.dto.StockDto;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.stream.Collectors;

@Route
public class MainView extends VerticalLayout {

  private final StockService stockService;
  private final StockMapper stockMapper;
  final Grid<StockDto> stockGrid;

  public MainView(StockService stockService, StockMapper stockMapper) {
    this.stockService = stockService;
    this.stockMapper = stockMapper;
    this.stockGrid = new Grid<>();

    listCustomers();
    add(stockGrid);
  }

  private void listCustomers() {
    stockGrid.setItems(stockService.findAllStocks().stream()
        .map(stockMapper::toDto)
        .collect(Collectors.toList()));

    stockGrid.addColumn(StockDto::getId)
        .setHeader("ID")
        .setSortable(true)
        .setAutoWidth(true);

    stockGrid.addColumn(StockDto::getName)
        .setHeader("Name")
        .setSortable(true)
        .setAutoWidth(true);

    stockGrid.addColumn(StockDto::getCurrentPrice)
        .setHeader("Current Price")
        .setSortable(true)
        .setAutoWidth(true);

    stockGrid.addColumn(StockDto::getLastUpdate)
        .setHeader("Last Update")
        .setSortable(true)
        .setAutoWidth(true);
  }

}