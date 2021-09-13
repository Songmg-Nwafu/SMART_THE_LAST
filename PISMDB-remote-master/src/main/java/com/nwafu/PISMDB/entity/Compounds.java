package com.nwafu.PISMDB.entity;

/**
 * 存放完整分子信息
 */

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "compound")
public class Compounds {

    @Id
    private String pismid;
    @Column(name = "targetid")
    private String targetId;
    @Column(name = "pathwayid")
    private String pathwayId;
    @Column
    private String version;
    @Column(name = "creationdate")
    private String creationDate;
    @Column(name = "updatedate")
    private String updateDate;
    @Column(name = "chemicalnames")
    private String chemicalNames;
    @Column
    private String IUPAC_Name;
    @Column(name = "othernames")
    private String otherNames;
    @Column(name = "chemicalformular")
    private String chemicalFormular;
    @Column(name = "molecularweight")
    private String molecularWeight;
    @Column
    private String alogP;
    @Column
    private String threeD_Structure;
    @Column
    private String structure;
    @Column
    private String smiles;
    @Column
    private String function;
    @Column
    private String mechanism;
    @Column
    private String phenotype;
    @Column(name = "groupdescription")
    private String groupDescription;
    @Column(name = "structureactivityrelationship")
    private String structureActivityRelationship;
    @Column(name = "structuralsimilarity")
    private String structuralSimilarity;
    @Column
    private String referenceId;
    @Column(name = "moculardescription")
    private String mocularDescription;
    /**
     * smg
     * 添加三个字段  分别用于链接其他数据库以及以后用于关联分子的存储
     */
    @Column
    private String chemSpider;
    @Column
    private String pubChem;
    @Column
    private String relatedCompounds;
}
