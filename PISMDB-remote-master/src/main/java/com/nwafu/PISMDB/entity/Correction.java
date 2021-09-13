package com.nwafu.PISMDB.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "correction")
public class Correction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int  corid;
    @Column
    private String pismid;
    @Column
    private String iupacName;
    @Column
    private String chemicalName;
    @Column
    private String chemicalFormula;
    @Column
    private String smiles;
    @Column
    private String molecularWeight;
    @Column
    private String functionandphen;
    @Column
    private String protein;
    @Column
    private String pathway;
    @Column
    private String supplementary;
    @Column
    private String proteinName;
    @Column
    private String uniprotID;
    @Column
    private String organism;
    @Column
    private String userName;
    @Column
    private String department;
    @Column
    private String emailAddress;
    @Column
    private String createDate;
    @Column
    private String file_2D;
    @Column
    private String file_3D;
    @Column
    private String file_seq;
    @Column
    private String files;
    @Column
    private String alogp;
}
