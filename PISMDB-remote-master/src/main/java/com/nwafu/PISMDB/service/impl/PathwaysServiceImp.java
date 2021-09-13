package com.nwafu.PISMDB.service.impl;

import com.nwafu.PISMDB.dao.PathwaysDao;
import com.nwafu.PISMDB.entity.CompoundsPathway;
import com.nwafu.PISMDB.entity.Pic;
import com.nwafu.PISMDB.entity.Pictures;
import com.nwafu.PISMDB.entity.Pathways;
import com.nwafu.PISMDB.service.PathwaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PathwaysServiceImp implements PathwaysService {
    @Autowired
    private PathwaysDao pathwaysDao;

    @Override
    public int getPathwaysCount() {
        return pathwaysDao.getPathwaysCount();
    }

    @Override
    public int getPicturesCount(){
        return pathwaysDao.getPicturesCount();
    }

    @Override
    public List<Pictures> showPictureInformation(int picturesid) {
        return pathwaysDao.showPictureInformation(picturesid);
    }

    @Override
    public List<Pictures> showAllPictureInformation(){return pathwaysDao.showAllPictureInformation();}

    @Override
    public List<Pic> showPictures() {
        return pathwaysDao.showPictures();
    }

    @Override
    public CompoundsPathway getPathwaysByPISMID(String pismid) {
        return pathwaysDao.getPathwaysByPISMID(pismid);
    }

    @Override
    public List<Pathways> getPathwaysByTargetId(String targetId) {
        return pathwaysDao.getPathwaysByTargetId(targetId);
    }

    @Override
    public Pathways getPathwaysByPathwayID(String pathwayId) {
        return pathwaysDao.getPathwaysByPathwayID(pathwayId);
    }

    @Override
    public List<String> getAllMolecularPISMID() {
        return pathwaysDao.getAllMolecularPISMID();
    }

}
