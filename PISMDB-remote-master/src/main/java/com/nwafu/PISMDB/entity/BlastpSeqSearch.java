package com.nwafu.PISMDB.entity;


import lombok.Data;

@Data
public class BlastpSeqSearch {
    private String filepath;
    private String TargetID;
    private String UniprotID;
    private String ProteinName;
    private String Organisml;
    private String Sequencel;
    private String RelateCommpounds;
    private String pdbid;
    private String pismid;
    private String bitscore;
    private String evalue;
    private String qcovs;
    private String ident;
    private String stitle;
}