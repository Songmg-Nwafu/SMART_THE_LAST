package com.nwafu.PISMDB.entity;

import lombok.Data;

import java.util.List;

@Data
public class CompoundPathways {
    private String pismid;
    private List<Pathways> pathwaysList;
}
