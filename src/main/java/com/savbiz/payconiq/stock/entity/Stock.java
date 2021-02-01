package com.savbiz.payconiq.stock.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "stocks")
public class Stock {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private BigDecimal currentPrice;

  @Setter(value = AccessLevel.PRIVATE)
  private ZonedDateTime lastUpdate;

  @PreUpdate
  protected void onUpdate() {
    this.lastUpdate = ZonedDateTime.now(ZoneOffset.UTC);
  }
}
