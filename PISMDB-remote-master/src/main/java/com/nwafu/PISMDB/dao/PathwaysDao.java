package com.nwafu.PISMDB.dao;

import com.nwafu.PISMDB.entity.CompoundsPathway;
import com.nwafu.PISMDB.entity.Pathways;
import com.nwafu.PISMDB.entity.Pic;
import com.nwafu.PISMDB.entity.Pictures;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PathwaysDao {
    int getPathwaysCount();
    int getPicturesCount();
    List<Pictures> showPictureInformation(@Param("picturesid") int picturesid);
    Pathways getPathwaysByPathwayID(@Param("pathwayId") String pathwayId);
    List<Pictures> showAllPictureInformation();
    List<Pic> showPictures();
    CompoundsPathway getPathwaysByPISMID(String pismid);
    List<Pathways> getPathwaysByTargetId(String targetId);
    List<String> getAllMolecularPISMID();
}
