package com.savbiz.payconiq.stock.web.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ErrorResponseDto {

  String errorCode;
  String errorMessage;
  List<String> messages;

}

