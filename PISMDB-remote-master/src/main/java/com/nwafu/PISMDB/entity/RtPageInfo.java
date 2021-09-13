package com.nwafu.PISMDB.entity;

import lombok.Data;

import java.util.List;

@Data
public class RtPageInfo {
    private List Data;
    private Integer PageNum;
    private Integer PageSize;
    private long TotalCount;
}
