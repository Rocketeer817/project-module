package com.example.proxyservice.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class SearchRequestDto {
    private String query;
    private int pageNo;
    private int pageSize;
    private List<SortParam> sortParamList;
}
