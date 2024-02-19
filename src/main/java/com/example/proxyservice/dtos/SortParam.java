package com.example.proxyservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortParam {
    private String fieldName;
    private SortType SortType;
}
