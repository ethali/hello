package com.cooperl.hello.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class ErrorResponse {
    Long timestamp;
    String path;
    Integer status;
    String error;
    String message;
    String traceId;
}
