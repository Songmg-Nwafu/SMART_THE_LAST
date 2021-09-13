package com.nwafu.PISMDB.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 */

@Data
public class CompoundsBasicInformationBean {

    @Id
    private String pismid;
    private String targetId;
    private String iupacName;
    private String chemicalFormular;
    private String molecularWeight;
    private String alogP;
   // private String ThreeD_Structure;
    private String otherNames;
    private String smiles;
    private String chemicalNames;
    private String function;
    private String mechanism;
    private String phenotype;
//    private  String Address;

    public CompoundsBasicInformationBean(String PISMID, String IUPAC_Name, String ChemicalFormular, String AlogP,
                                         String Smiles,String chemicalNames,String Function,String Mechanism ,String Phenotype,
                                         String Address)  {
        this.pismid = PISMID;
        this.iupacName = IUPAC_Name;
        this.chemicalFormular = ChemicalFormular;
       // this.MolecularWeight = MolecularWeight;
        this.alogP = AlogP;
        //this.ThreeD_Structure = ThreeD_Structure;
        this.smiles = Smiles;
        this.chemicalNames=chemicalNames;
        this.function=Function;
        this.mechanism=Mechanism;
        this.phenotype=Phenotype;
//        this.Address=Address;
    }
}
