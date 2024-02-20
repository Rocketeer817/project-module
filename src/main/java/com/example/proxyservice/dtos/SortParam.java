package com.example.proxyservice.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SortParam {
    private String fieldName;
    private String sortType;
}
