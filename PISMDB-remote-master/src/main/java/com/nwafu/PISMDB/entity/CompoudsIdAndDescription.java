package com.nwafu.PISMDB.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 *  存放compounds的id和分子描述符
 */

@Data
public class CompoudsIdAndDescription {
    @Id
    private String pismid;
    private String mocularDescription;
}
