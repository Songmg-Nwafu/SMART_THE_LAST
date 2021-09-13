package com.nwafu.PISMDB.entity;

/**
 * 存放上传的临时信息
 */

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "temp")
public class Temp {

    @Id
    private String PISMID;
    @Column
    private String creationdate;
    @Column
    private String ChemicalNames;
    @Column
    private String IUPAC_Name;
    @Column
    private String Smiles;
    @Column
    private String tempFunction;
    @Column
    private String proteinname;
    @Column
    private String gene;
    @Column
    private String uniportid;
    @Column
    private String pdbid;
    @Column
    private String sequencel;
    @Column
    private String username;
    @Column
    private String department;
    @Column
    private String mail;
}
