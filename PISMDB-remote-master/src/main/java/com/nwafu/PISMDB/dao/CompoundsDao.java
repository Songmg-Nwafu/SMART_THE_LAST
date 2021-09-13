package com.nwafu.PISMDB.dao;

import com.nwafu.PISMDB.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompoundsDao {
    int getCompoundsCount();
    int getCompoundsCountOfOthers();
    List<Compounds> findAll();
    List<Compounds> findById();
    List<CompoundsBasicInformationBean> FindBasicInformation();
    List<CompoundsBasic> FindBasic();
    List<CompoundsPathway> FindPathway();
    List<String> findPathwayId();
    List<CompoundsRelatedCompounds> FindRelatedCompounds();
    List<CompoundSupportingInformation> FindSupportingInformation();
    CompoundSupportingInformation findSupportingInformation(@Param("pismid") String pismid);
    List<CompoudsIdAndDescription> selectIdAndDescription();
    Compounds findByPISMID(String pismid);
    List<Reference> findReference();
    Reference findReferenceById(@Param("pismid")String pismid);
    void addselectIdAndDescription(@Param("pismid")String pismid,@Param("moculardescription")String moculardescription);

    List<String> findRelatedById(@Param("Pismid") String Pismid);
    List<RelatedCompound> findRelatedCompounds(@Param("Pismid") String pismid);

    List<CompoundsBasic> FindBasicOfOthers();

    List<Compounds> findCompoundsOfOthers();

    List<CompoundSupportingInformation> FindSupportingInformationOfOthers();
}

