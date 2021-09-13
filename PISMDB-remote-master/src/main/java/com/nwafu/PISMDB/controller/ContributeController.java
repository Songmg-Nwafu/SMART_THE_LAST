package com.nwafu.PISMDB.controller;

import com.nwafu.PISMDB.entity.CheckedInformation;
import com.nwafu.PISMDB.entity.CorInfo;
import com.nwafu.PISMDB.entity.Correction;
import com.nwafu.PISMDB.service.CompoundsService;
import com.nwafu.PISMDB.service.impl.CheckSerciceImpl;
import com.nwafu.PISMDB.service.impl.CorInfoServiceImpl;
import com.nwafu.PISMDB.service.impl.CorrectionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@Slf4j
public class ContributeController {

    @Autowired
    private CorrectionServiceImpl correctionService;

    @Autowired
    private CheckSerciceImpl checkSercice;

    @Autowired
    private CorInfoServiceImpl corInfoService;

    @RequestMapping("correction")
    @ResponseBody
    public String correction(@RequestParam(value = "2Dfile", required = false) MultipartFile file_2D,
                             @RequestParam(value = "3Dfile", required = false) MultipartFile file_3D,
                             @RequestParam(value = "sequencefile", required = false) MultipartFile file_sequence,
                             Correction cor) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
        cor.setCreateDate(sdf.format(d));
        String[] filelist = cor.getFiles().split(",");
        System.out.println(cor.toString());
        if (filelist[0].equals("1") && !file_2D.isEmpty()) {
            String fileName1 = file_2D.getOriginalFilename().split("\\.")[0] + System.currentTimeMillis() + ".png";
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(
                        new File("/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/Correction/file_2D/" + fileName1)));
                out.write(file_2D.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }       //接收文件到resources下的文件夹中
            String path1 = "./Correction/file_2D/" + fileName1;
            cor.setFile_2D(path1);
        }
        if (filelist[1].equals("1") && !file_3D.isEmpty()) {
            String fileName2 = file_3D.getOriginalFilename().split("\\.")[0] + System.currentTimeMillis() + ".mol2";
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(
                        new File("/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/Correction/file_3D/" + fileName2)));
                out.write(file_3D.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }       //接收文件到resources下的文件夹中
            String path2 = "./Correction/file_3D/" + fileName2;
            cor.setFile_3D(path2);
        }
        if (filelist[2].equals("1") && !file_sequence.isEmpty()) {
            String fileName3 = file_3D.getOriginalFilename().split("\\.")[0] + System.currentTimeMillis() + ".fasta";
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(
                        new File("/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/Correction/file_3D/" + fileName3)));
                out.write(file_sequence.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }       //接收文件到resources下的文件夹中
            String path3 = "./Correction/file_sequence/" + fileName3;
            cor.setFile_seq(path3);
        }

        Correction tmp = correctionService.save(cor);

        CheckedInformation checkedInformation = new CheckedInformation();
        checkedInformation.setOpid(tmp.getCorid());
        checkedInformation.setPismid(tmp.getPismid());
        checkedInformation.setOptype("Correction");
        checkedInformation.setCreatedate(tmp.getCreateDate());
        checkedInformation.setUsername(tmp.getUserName());
        checkedInformation.setDepartment(tmp.getDepartment());
        checkedInformation.setIschecked(false);
        checkedInformation.setPassed(false);

        checkSercice.save(checkedInformation);

        CorInfo corInfo = new CorInfo();
        corInfo.setPismid(tmp.getPismid());
        corInfo.setCordate(tmp.getCreateDate());
        corInfo.setUsername(tmp.getUserName());
        corInfo.setDepartment(tmp.getDepartment());
        corInfo.setSupplementary(tmp.getSupplementary());
        corInfoService.save(corInfo);

        return "success";
    }
    @RequestMapping("contribute")
    @ResponseBody
    public String contribute(@RequestParam(value = "2Dfile", required = false) MultipartFile file_2D,
                             @RequestParam(value = "3Dfile", required = false) MultipartFile file_3D,
                             @RequestParam(value = "sequencefile", required = false) MultipartFile file_sequence,
                             Correction cor) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
        cor.setCreateDate(sdf.format(d));
        String[] filelist = cor.getFiles().split(",");

        if (filelist[0].equals("1") && !file_2D.isEmpty()) {
            String fileName1 = file_2D.getOriginalFilename().split("\\.")[0] + System.currentTimeMillis() + ".png";
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(
                        new File("/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/Correction/file_2D/" + fileName1)));
                out.write(file_2D.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }       //接收文件到resources下的文件夹中
            String path1 = "./Correction/file_2D/" + fileName1;
            cor.setFile_2D(path1);
        }
        if (filelist[1].equals("1") && !file_3D.isEmpty()) {
            String fileName2 = file_3D.getOriginalFilename().split("\\.")[0] + System.currentTimeMillis() + ".mol2";
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(
                        new File("/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/Correction/file_3D/" + fileName2)));
                out.write(file_3D.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }       //接收文件到resources下的文件夹中
            String path2 = "./Correction/file_3D/" + fileName2;
            cor.setFile_3D(path2);
        }
        if (filelist[2].equals("1") && !file_sequence.isEmpty()) {
            String fileName3 = file_3D.getOriginalFilename().split("\\.")[0] + System.currentTimeMillis() + ".fasta";
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(
                        new File("/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/Correction/file_sequence/" + fileName3)));
                out.write(file_sequence.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }       //接收文件到resources下的文件夹中
            String path3 = "./Correction/file_sequence/" + fileName3;
            cor.setFile_seq(path3);
        }

        Correction tmp = correctionService.save(cor);

        CheckedInformation checkedInformation = new CheckedInformation();
        checkedInformation.setOpid(tmp.getCorid());
        checkedInformation.setOptype("Contribute");
        checkedInformation.setCreatedate(tmp.getCreateDate());
        checkedInformation.setUsername(tmp.getUserName());
        checkedInformation.setDepartment(tmp.getDepartment());
        checkedInformation.setIschecked(false);
        checkedInformation.setPassed(false);

        checkSercice.save(checkedInformation);

        return "success";
    }

}
