package com.nwafu.PISMDB.service;

import com.nwafu.PISMDB.dao.CompoundsDao;
import com.nwafu.PISMDB.entity.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
public interface CompoundsService {
    int getCompoundsCount();
    List<Compounds> findAll();
    List<Compounds> findById(Integer pageNum,Integer pageSize);
    List<Compounds> findById();
    List<CompoundsBasicInformationBean> FindBasicInformation();
    List<CompoundsBasic> FindBasic(Integer pageNum,Integer pageSize);
    List<CompoundsBasic> FindBasic();
    List<CompoundsPathway> FindPathway(Integer pageNum,Integer pageSize);
    List<String> FindPathwayID(Integer pageNum,Integer pageSize);
    List<String> FindPathwayID();
    List<CompoundsRelatedCompounds> FindRelatedCompounds();
    List<CompoundSupportingInformation> FindSupportingInformation(Integer pageNum,Integer pageSize);
    CompoundSupportingInformation FindSupportingInformation(String pismid);
    List<String> findRelatedById(String pismid);
    List<RelatedCompound> findRelatedCompound(String pismid);
    List<Reference> findReference();
    Reference findReferenceById(String pismid);
    Compounds findByPISMID(String pismid);
    void addselectIdAndDescription(String pismid,String moculardescription);
    List<CompoudsIdAndDescription> selectIdAndDescription();

    /**
     * find compounds of the pathway others
     */
    List<CompoundsBasic> FindBasicOfOthers(Integer pageNum,Integer pageSize);
    List<Compounds> findCompoundsOfOthers(Integer pageNum,Integer pageSize);
    List<CompoundSupportingInformation> FindSupportingInformationOfOthers(Integer pageNum,Integer pageSize);
    int getCompoundsCountOfOthers();
}
