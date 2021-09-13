package com.nwafu.PISMDB.entity;

/**
 * 通路存储的地址信息
 */

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="pictures")
@Data
public class Pic {

    @Id
    @GeneratedValue
    int id;
    String desc;
    String url;
    //List<Pictures> list;

}
