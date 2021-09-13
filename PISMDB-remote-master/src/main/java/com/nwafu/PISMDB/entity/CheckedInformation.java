package com.nwafu.PISMDB.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "checkedinformation")
public class CheckedInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int checkid;
    @Column
    private int opid;
    @Column
    private String pismid;
    @Column
    private String optype;
    @Column
    private String createdate;
    @Column
    private String username;
    @Column
    private String department;
    @Column
    private boolean ischecked;
    @Column
    private boolean passed;
}
