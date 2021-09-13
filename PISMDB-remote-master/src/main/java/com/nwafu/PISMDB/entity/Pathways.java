package com.nwafu.PISMDB.entity;

/**
 * pathway信息
 */

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pathway")
@Data
public class Pathways {
    @Id
    private String pathwayID;
    @Column(name="pathwayname")
    private String pathwayName;

}
