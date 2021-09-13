package com.nwafu.PISMDB.service.impl;

import com.nwafu.PISMDB.Reposity.UserRepository;
import com.nwafu.PISMDB.entity.Temp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class ContributeServiceImpl {
    @Resource
    private UserRepository userRepository;
    @Transactional
    public Temp save(Temp temp){
        return userRepository.save(temp);
    }
}
