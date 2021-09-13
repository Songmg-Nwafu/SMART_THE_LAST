package com.nwafu.PISMDB.entity;

/**
 * 通路图中的位置点的坐标信息以及次坐标处的详细信息
 */

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="places")
@Data
public class Pictures {
    @Id
    @GeneratedValue
    int id;
    int picturesid;
    float startX;
    float startY;
    float endX;
    float endY;
    String type;
    String information;
    @Column(name = "proteintargetid")
    String proteinTargetID;
    @Column(name = "proteinname")
    String proteinName;
    @Column(name = "molecularpismid")
    String molecularPISMID;
    @Column(name = "groupname")
    String groupName;
    @Column(name = "pathwayid")
    String pathwayID;

    public Pictures(){}
    public Pictures(float startX,float startY,float endX,float endY,String information)
    {
        this.startX=startX;
        this.startY=startY;
        this.endX=endX;
        this.endY=endY;
        this.information=information;
    }

    public int getPicturesid() {
        return picturesid;
    }

    public void setPicturesid(int picturesid) {
        this.picturesid = picturesid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public float getEndX() {
        return endX;
    }

    public void setEndX(float endX) {
        this.endX = endX;
    }

    public float getEndY() {
        return endY;
    }

    public void setEndY(float endY) {
        this.endY = endY;
    }


}
