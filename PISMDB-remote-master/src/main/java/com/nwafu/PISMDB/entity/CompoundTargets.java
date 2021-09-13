package com.nwafu.PISMDB.entity;

import lombok.Data;

import java.util.List;

@Data
public class CompoundTargets {
    private String pismid;
    private List<Targets> targetsList;
}
