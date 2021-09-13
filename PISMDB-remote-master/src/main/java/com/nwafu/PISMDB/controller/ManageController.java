package com.nwafu.PISMDB.controller;

import com.nwafu.PISMDB.entity.*;
import com.nwafu.PISMDB.service.impl.CheckSerciceImpl;
import com.nwafu.PISMDB.service.impl.CompoundsServiceImp;
import com.nwafu.PISMDB.service.impl.CorrectionServiceImpl;
import com.nwafu.PISMDB.service.impl.TargetsServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
public class ManageController {

    @Autowired
    private CheckSerciceImpl checkSercice;
    @Autowired
    private CorrectionServiceImpl correctionService;
    @Autowired
    private CompoundsServiceImp compoundsServiceImp;
    @Autowired
    private TargetsServiceImp targetsServiceImp;

    @GetMapping("manage")
    public String manage() {
        return "managelist";
    }

    @GetMapping("checkInfo")
    @ResponseBody
    public String checkInfo(@RequestParam String callback) {
        List<CheckedInformation> checkedInformationList = checkSercice.findAll();
        CallbackResult<List<CheckedInformation>> result = new CallbackResult();
        result.setCallback(callback);
        result.setData(checkedInformationList);
        return result.changToJsonp();
    }

    @GetMapping("checkInfodatil")
    public String checkInfoDatil(@Param("id") String cor) {
        return "checkdetil";
    }

    @GetMapping("correctionById")
    @ResponseBody
    public String correctionById(@RequestParam String callback, @Param("id") String id) {
        Correction correction = correctionService.findOne(Integer.valueOf(id));
        List<TargetName> targetNames = targetsServiceImp.findAllTargetsName();

        if (correction.getPismid() != null) {
            Compounds compounds = compoundsServiceImp.findByPISMID(correction.getPismid());
            if (correction.getFile_2D() == null) {
                correction.setFile_2D(compounds.getStructure());
            }
            if (correction.getFile_3D() == null) {
                correction.setFile_3D(compounds.getThreeD_Structure());
            }
        }
        CheckEntity checkEntity = new CheckEntity();
        checkEntity.setCorrection(correction);
        checkEntity.setTargetNameList(targetNames);

        CallbackResult<CheckEntity> result = new CallbackResult();
        result.setCallback(callback);
        result.setData(checkEntity);
        return result.changToJsonp();
    }

    @GetMapping("managecount")
    @ResponseBody
    public String manageCount(@RequestParam String callback) {
        int count = checkSercice.count();
        CallbackResult result = new CallbackResult();
        result.setCallback(callback);
        result.setData(count);
        return result.changToJsonp();
    }
}
