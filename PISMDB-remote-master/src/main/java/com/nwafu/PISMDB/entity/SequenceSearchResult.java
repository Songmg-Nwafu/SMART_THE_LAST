package com.nwafu.PISMDB.entity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @description: 序列搜索的返回结果对象
 */

@Data
public class SequenceSearchResult {
    private String id;
    private String identity;
    private String queryCoverage;
    private String protein;
    private String organisml;
    private String uniportID;
    private String gene;
    private String function;
}
