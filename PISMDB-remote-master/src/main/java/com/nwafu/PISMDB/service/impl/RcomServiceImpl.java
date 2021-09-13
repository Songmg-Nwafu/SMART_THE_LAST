package com.nwafu.PISMDB.service.impl;

import com.nwafu.PISMDB.Reposity.RcomRepository;
import com.nwafu.PISMDB.entity.Rcom;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class RcomServiceImpl {
    @Resource
    private RcomRepository rcomRepository;
    @Transactional
    public Rcom save(Rcom rcom){
        return rcomRepository.save(rcom);
    }
}
