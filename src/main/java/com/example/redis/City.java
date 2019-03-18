package com.example.redis;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Builder
@Data
public class City implements Serializable {
    private  long id;
    private String description;
    private long provinceId;
}
