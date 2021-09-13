package com.nwafu.PISMDB.service.impl;

import com.google.common.collect.Lists;
import com.nwafu.PISMDB.Reposity.CheckRepository;
import com.nwafu.PISMDB.entity.CheckedInformation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class CheckSerciceImpl {
    @Resource
    private CheckRepository checkRepository;
    @Transactional
    public CheckedInformation save(CheckedInformation checkedInformation){
        return checkRepository.save(checkedInformation);
    }

    public List<CheckedInformation> findAll(){
        List<CheckedInformation> checkedInformationList = Lists.newArrayList(checkRepository.findAll());

        return checkedInformationList;
    }

    public int count(){
        return (int) checkRepository.count();
    }
}
