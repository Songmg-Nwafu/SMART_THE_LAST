package com.nwafu.PISMDB.entity;
/**
 * pathway信息展示时用
 * Lombok能通过注解的方式，在编译时自动为属性生成构造器、getter/setter、equals、hashcode、toString方法
 */
import lombok.Data;
import java.util.List;

@Data
public class BrowsePathways {
    private Pic pic;
    private String pathwayName;
    private List<Pictures> pictures;
    private List<GroupFormat> compoundGroup;
    private List<GroupFormat> proteinGroup;
}
