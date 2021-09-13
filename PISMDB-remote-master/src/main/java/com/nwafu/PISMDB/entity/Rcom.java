package com.nwafu.PISMDB.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "rcom")
public class Rcom {
    @Id
    private String pid;
    @Column
    private String relatedcompounds;

}
