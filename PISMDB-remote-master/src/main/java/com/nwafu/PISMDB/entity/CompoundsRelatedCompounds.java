package com.nwafu.PISMDB.entity;

/**
 * 分子的相关分子（不只一个）
 */

import lombok.Data;

import java.util.List;

@Data
public class CompoundsRelatedCompounds {
    private String pismid;
    List<String> compoundsList;

    public CompoundsRelatedCompounds(){}
    public CompoundsRelatedCompounds(String PISMID,List<String> compoundsList){
        this.pismid =  PISMID;
        this.compoundsList = compoundsList;
    }

    @Override
    public String toString() {
        return "CompoundsRelatedCompounds{" +
                "PISMID='" + pismid + '\'' +
                ", compoundsList=" + compoundsList +
                '}';
    }
}
