package com.nwafu.PISMDB.entity;

/**
 * 参考文献信息
 */

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reference")
@Data
public class Reference {
    @Id
    private String referenceID;
    @Column(name="referencename")
    private String referenceName;
}
