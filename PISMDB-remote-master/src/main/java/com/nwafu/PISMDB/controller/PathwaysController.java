package com.nwafu.PISMDB.controller;

import com.nwafu.PISMDB.entity.*;
import com.nwafu.PISMDB.service.CompoundsService;
import com.nwafu.PISMDB.service.PathwaysService;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
//@RequestMapping("/data")
@Slf4j
public class PathwaysController {
    @Autowired
    private PathwaysService pathwaysService;

    @Autowired
    private CompoundsService compoundsService;

    @RequestMapping("getPathwaysCount")
    @ResponseBody
    public int getTargetsCount() {
        return pathwaysService.getPathwaysCount();
    }

    @RequestMapping("getPicCount")
    @ResponseBody
    public int getPICCount() {
        return pathwaysService.getPicturesCount();
    }

    @GetMapping("getPathwaysByPISMID")
    @ResponseBody
    public CompoundsPathway getPathwaysByPISMDB(String pismid){
        return pathwaysService.getPathwaysByPISMID(pismid);
    }

    @ApiOperation(value = "显示所有pathway", notes = "显示所有pathway")
    @RequestMapping(value = "showAllPathwaysInformation",method = RequestMethod.GET)
    @ResponseBody
    public List<Pictures> showPicsAllinformation() {//@RequestParam String callback
        return pathwaysService.showAllPictureInformation();
    }

    @ApiOperation(value = "显示所有pathway", notes = "显示所有pathway")
    //@RequestMapping(value = "showAllPathways",method = RequestMethod.GET)
    @GetMapping("showAllPathways")
    @ResponseBody
    public String showPicsAll(@RequestParam String callback) {
        List<Pic> list1 = pathwaysService.showPictures();
        List<BrowsePathways> pathways = new ArrayList<>();

        for(int i=0;i < pathwaysService.getPicturesCount();i++){
            List<Pictures> list = pathwaysService.showPictureInformation(list1.get(i).getId());
            BrowsePathways p1 = new BrowsePathways();
            List<GroupFormat> groupFormats=new ArrayList<>();
            for(int j=0;j<list.size();j++){
                GroupFormat groupFormat=new GroupFormat();
                groupFormat.setGroupName(list.get(j).getGroupName());
                if(list.get(j).getMolecularPISMID()!= null) {
                    groupFormat.setId(Arrays.asList(list.get(j).getMolecularPISMID().split("%%")));
                }
                groupFormats.add(groupFormat);
            }
            List<GroupFormat> groupFormat1 = new ArrayList<>();
            for (int k = 0; k < list.size(); k++) {
                GroupFormat groupFormat = new GroupFormat();
                groupFormat.setGroupName(list.get(k).getProteinName());
                if (list.get(k).getProteinTargetID() != null) {
                    groupFormat.setId(Arrays.asList(list.get(k).getProteinTargetID().split("%%")));
                }
                groupFormat1.add(groupFormat);
            }
            p1.setCompoundGroup(groupFormats);
            p1.setPathwayName(pathwaysService.getPathwaysByPathwayID(list1.get(i).getDesc()).getPathwayName());
          /*p1.setPathwayName(pathwaysService.);*/
            p1.setPic(list1.get(i));
            p1.setPictures(list);
            p1.setProteinGroup(groupFormat1);
            pathways.add(p1);
        }
        CallbackResult<List<BrowsePathways>> result = new CallbackResult();
        result.setCallback(callback);
        result.setData(pathways);
        //log.info("pathway result{}",result);
        return result.changToJsonp();
    }

    @ApiOperation(value = "跳转到Pathway页面", notes = "跳转到Pathway页面")
    @GetMapping("Browse_P")
    public String Browse_P() {
        return "pathway_gallery";
    }

    @ApiOperation(value = "跳转到Pathway页面", notes = "跳转到Pathway页面")
    @GetMapping("pathway_gallery")
    public String Pathway_Gallery() {
        return "pathway_gallery";
    }

    @ApiOperation(value = "跳转到detail页面", notes = "跳转到detail页面")
    @GetMapping("pdetail")
    public String detail(@Param("pathwayId") String pathwayId) {
        return "Browse-Pathway";
    }



    @ApiOperation(value = "显示pathway", notes = "显示pathway")
    @RequestMapping(value = "show",method = RequestMethod.GET)
    @ResponseBody
    public String showPics(@RequestParam String callback) {

        List<Pic> list1 = pathwaysService.showPictures();
        List<Pictures> list = pathwaysService.showPictureInformation(list1.get(2).getId());
        List<BrowsePathways> pathways = new ArrayList<>();
        BrowsePathways p1 = new BrowsePathways();
        List<GroupFormat> groupFormats = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            GroupFormat groupFormat = new GroupFormat();
            groupFormat.setGroupName(list.get(i).getGroupName());
            if (list.get(i).getMolecularPISMID() != null) {
                groupFormat.setId(Arrays.asList(list.get(i).getMolecularPISMID().split("%%")));
            }
            groupFormats.add(groupFormat);
        }
        List<GroupFormat> groupFormat1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            GroupFormat groupFormat = new GroupFormat();
            groupFormat.setGroupName(list.get(i).getProteinName());
            if (list.get(i).getProteinTargetID() != null) {
                groupFormat.setId(Arrays.asList(list.get(i).getProteinTargetID().split("%%")));
            }
            groupFormat1.add(groupFormat);
        }
        p1.setPic(list1.get(2));
        p1.setPictures(list);
        p1.setProteinGroup(groupFormat1);
        p1.setCompoundGroup(groupFormats);
        pathways.add(p1);
        int size = list.size();
        System.out.println(size);
        CallbackResult<List<BrowsePathways>> result = new CallbackResult();
        result.setCallback(callback);
        result.setData(pathways);
        log.info("{}", result.changToJsonp());
        return result.changToJsonp();
    }
}
