package com.nwafu.PISMDB.service.impl;

import com.nwafu.PISMDB.Reposity.CorrectionRepository;
import com.nwafu.PISMDB.entity.Correction;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class CorrectionServiceImpl {
    @Resource
    private CorrectionRepository correctionRepository;
    @Transactional
    public Correction save(Correction correction){
        return correctionRepository.save(correction);
    }

    public Correction findOne(int id){
        return correctionRepository.findById(id).get();
    }
}
