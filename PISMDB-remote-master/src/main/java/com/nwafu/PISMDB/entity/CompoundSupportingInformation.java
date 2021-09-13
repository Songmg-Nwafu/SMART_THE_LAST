package com.nwafu.PISMDB.entity;

/**
 * Compound其他信息
 */

import lombok.Data;

@Data
public class CompoundSupportingInformation {
    private String pismid;
    private String function;
    private String mechanism;
    private String phenotype;

    @Override
    public String toString() {
        return "CompoundSupportingInformation{" +
                "PISMID='" + pismid + '\'' +
                ", Function='" + function + '\'' +
                ", Mechanism='" + mechanism + '\'' +
                ", Phenotype='" + phenotype + '\'' +
                '}';
    }
}
