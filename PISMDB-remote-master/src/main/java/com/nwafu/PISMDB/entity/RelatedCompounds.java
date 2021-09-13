package com.nwafu.PISMDB.entity;

import lombok.Data;

import java.util.List;

@Data
public class RelatedCompounds {
    private String pismid;
    private List<RelatedCompound> compoundsList;
}
