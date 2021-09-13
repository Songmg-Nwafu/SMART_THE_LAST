package com.nwafu.PISMDB.service.impl;

import com.github.pagehelper.PageHelper;
import com.nwafu.PISMDB.dao.CompoundsDao;
import com.nwafu.PISMDB.entity.*;
import com.nwafu.PISMDB.service.CompoundsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompoundsServiceImp implements CompoundsService {
    private Compounds compounds;

    @Autowired
    private CompoundsDao compoundsDao;

    @Override
    public List<Compounds> findById(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize,true);
        return compoundsDao.findById();
    }

    @Override
    public List<Compounds> findById() {
        return compoundsDao.findById();
    }

    @Override
    public Compounds findByPISMID(String pismid){return compoundsDao.findByPISMID(pismid);}

    @Override
    public void addselectIdAndDescription(String pismid, String moculardescription) {
        compoundsDao.addselectIdAndDescription(pismid,moculardescription);
    }

    @Override
    public List<CompoudsIdAndDescription> selectIdAndDescription() {
        List<CompoudsIdAndDescription> list = compoundsDao.selectIdAndDescription();
       return  list;
    }

    @Override
    public List<CompoundsBasic> FindBasicOfOthers(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize,true);
        return compoundsDao.FindBasicOfOthers();
    }

    @Override
    public List<Compounds> findCompoundsOfOthers(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize,true);
        return compoundsDao.findCompoundsOfOthers();
    }

    @Override
    public List<CompoundSupportingInformation> FindSupportingInformationOfOthers(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize,true);
        return compoundsDao.FindSupportingInformationOfOthers();
    }

    @Override
    public int getCompoundsCountOfOthers() {
        return compoundsDao.getCompoundsCountOfOthers();
    }

    @Override
    public int getCompoundsCount() {
        return compoundsDao.getCompoundsCount();
    }
    @Override
    public  List<Compounds> findAll(){

        return compoundsDao.findAll();
    }

    @Override
    public  List<CompoundsBasic> FindBasic(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize,true);
        return compoundsDao.FindBasic();
    }

    @Override
    public List<CompoundsBasic> FindBasic() {
        return compoundsDao.FindBasic();
    }

    @Override
    public  List<CompoundsBasicInformationBean> FindBasicInformation(){
        return compoundsDao.FindBasicInformation();
    }

    @Override
    public  List<CompoundsPathway> FindPathway(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize,true);
        return  compoundsDao.FindPathway();
    }

    @Override
    public List<String> FindPathwayID(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize,true);
        return  compoundsDao.findPathwayId();
    }

    @Override
    public List<String> FindPathwayID() {
        return  compoundsDao.findPathwayId();
    }

    @Override
    public  List<CompoundsRelatedCompounds> FindRelatedCompounds(){
        return  compoundsDao.FindRelatedCompounds();
    }

    @Override
    public List<CompoundSupportingInformation> FindSupportingInformation(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize,true);
        return compoundsDao.FindSupportingInformation();
    }

    @Override
    public CompoundSupportingInformation FindSupportingInformation(String pismid) {
        return compoundsDao.findSupportingInformation(pismid);
    }

    @Override
    public List<String> findRelatedById(String pismid){
        return compoundsDao.findRelatedById(pismid);
    }

    @Override
    public List<RelatedCompound> findRelatedCompound(String pismid) {
        return compoundsDao.findRelatedCompounds(pismid);
    }

    @Override
    public List<Reference> findReference(){
        return compoundsDao.findReference();}

    @Override
    public Reference findReferenceById(String pismid) {
        return compoundsDao.findReferenceById(pismid);
    }
}