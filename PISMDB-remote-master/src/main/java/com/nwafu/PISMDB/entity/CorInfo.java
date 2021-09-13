package com.nwafu.PISMDB.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "corInfo")
public class CorInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int  infoid;
    @Column
    private String pismid;
    @Column
    private String cordate;
    @Column
    private String username;
    @Column
    private String department;
    @Column
    private String supplementary;
}
