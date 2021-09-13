package com.nwafu.PISMDB.service;

import com.nwafu.PISMDB.entity.*;

import java.util.List;

public interface TargetsService {
    int getTargetsCount();
    List<Targets> findTargets(Integer pageNum,Integer pageSize);
    List<FormatData<Targets>> findTargetsFormat(Integer pageNum,Integer pageSize);
    Targets findTargetByUniportID(String UniprotID);
    Targets findTargetByTargetID(String targetid);
    List<TargetName> findAllTargetsName();
}
