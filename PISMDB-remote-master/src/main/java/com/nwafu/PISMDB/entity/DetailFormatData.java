package com.nwafu.PISMDB.entity;

import lombok.Data;

import java.util.List;

@Data
public class DetailFormatData {
    private Compounds compounds;
    private List<CorInfo> corInfoList;
    private List<Pathways> pathwaysList;
    private List<Reference> referenceList;
    private List<RelatedCompound> compoundsList;
    private List<Targets> targetsList;
    private List<TargetName> allTargets;
}

