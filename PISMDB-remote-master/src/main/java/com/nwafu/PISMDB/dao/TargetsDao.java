package com.nwafu.PISMDB.dao;

import com.nwafu.PISMDB.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TargetsDao {
    int getTargetsCount();
    List<Targets> findTargets();
    Targets findTargetByUniportID(String UniprotID);
    Targets findTargetByTargetID(@Param("targetid") String targetid);
    List<TargetName> findAllTargetName();
}
