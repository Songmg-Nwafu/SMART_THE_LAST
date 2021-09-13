package com.nwafu.PISMDB.entity;

import lombok.Data;

import java.util.List;

@Data
public class CheckEntity {
    private Correction correction;
    private List<TargetName> targetNameList;
}
