package com.cooperl.hello.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Hello {
    private String code, name;
}
