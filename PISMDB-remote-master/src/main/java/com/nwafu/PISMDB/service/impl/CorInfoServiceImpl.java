package com.nwafu.PISMDB.service.impl;

import com.nwafu.PISMDB.Reposity.CorInfoRepository;
import com.nwafu.PISMDB.entity.CorInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class CorInfoServiceImpl {
    @Resource
    private CorInfoRepository corInfoRepository;
    @Transactional
    public CorInfo save(CorInfo corInfo){
        return corInfoRepository.save(corInfo);
    }

    public List<CorInfo> findByPismid(String pismid){
        return corInfoRepository.findByPismid(pismid);
    }

}
