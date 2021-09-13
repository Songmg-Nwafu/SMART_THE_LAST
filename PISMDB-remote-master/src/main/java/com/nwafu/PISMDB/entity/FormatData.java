package com.nwafu.PISMDB.entity;

/**
 * 格式化数据，方便前端调用
 */

import lombok.Data;

@Data
public class FormatData<T> {
    private String id;
    private String idLink;
    private String name;
    private String imgurl;
    private float value;
    private T basic;    //这里根据不同的分子显示不同的信息
    private CompoundsPathway pathway;
    private CompoundPathways pathways;
    private CompoundsRelatedCompounds related;
    private CompoundSupportingInformation supporting;
    private RelatedCompounds relatedCompounds;
    private CompoundTargets compoundTargets;
    private CompoundDataSource compoundDataSource;

    @Override
    public String toString() {
        return "FormatData{" +
                "id='" + id + '\'' +
                ", idLink='" + idLink + '\'' +
                ", name='" + name + '\'' +
                ", basic=" + basic +
                ", pathway=" + pathway +
                ", related=" + related +
                ", supporting=" + supporting +
                '}';
    }
}
