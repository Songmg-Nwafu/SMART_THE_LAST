package com.nwafu.PISMDB.controller;

import com.nwafu.PISMDB.entity.*;
import com.nwafu.PISMDB.service.BlastpSearchProteinService;
import com.nwafu.PISMDB.service.TargetsService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.*;
import java.util.List;

//@RestController
@Controller
@Slf4j
public class TargetsController {
    @Autowired
    private TargetsService targetsService;
    @Autowired
    private BlastpSearchProteinService blastpSearchProteinService;
    

    @ApiOperation(value = "跳转到Target页面", notes = "跳转到Target页面")
    @GetMapping("Browse_T")
    public String Browse_T() {
        return "Browse-Target3";
    }

    @ApiOperation(value = "跳转到Search_sequence页面", notes = "跳转到Search_sequence页面")
    @GetMapping("Search_sequence")
    public String Search_sequence() {
        return "Search-Sequence";
    }

    @ApiOperation(value = "跳转到结构搜索结果页面", notes = "跳转到结构搜索结果页面")
    @GetMapping("Search_sequence_result")
    public String Search_sequence_result() {
        return "Search-sequence-Result";
    }

    @ApiOperation(value = "蛋白质json数据", notes = "从数据库中获取的蛋白质的json数据")
    @GetMapping("browse-T")
    @ResponseBody
    public String showTargets1(@RequestParam(required = false, name = "pageNum") int pageNum,
                               @RequestParam(required = false, name = "pageSize") int pageSize,
                               @RequestParam String callback) {
        List<FormatData<Targets>> list = targetsService.findTargetsFormat(pageNum,pageSize);
        log.info("转换前：{}",list);
        CallbackResult<List<FormatData<Targets>>> result = new CallbackResult();
        result.setCallback(callback);
        result.setData(list);
        return result.changToJsonp();
    }

    @ApiOperation(value = "蛋白质json数据", notes = "从数据库中获取的蛋白质的json数据")
    @GetMapping("browse-t")
    @ResponseBody
    public String showTargets2(@RequestParam(required = false, name = "pageNum") int pageNum,
                               @RequestParam(required = false, name = "limitName") int pageSize,
                               @RequestParam String callback) {
        List<Targets> list = targetsService.findTargets(pageNum,pageSize);
        CallbackResult<List<Targets>> result = new CallbackResult();
        result.setCallback(callback);
        result.setData(list);
        return result.changToJsonp();
    }

    @ApiOperation(value = "蛋白质json数据", notes = "从数据库中获取的蛋白质的json数据")
    @GetMapping("getTargetsNum")
    @ResponseBody
    public String showTargets1(@RequestParam String callback) {
        int total = targetsService.getTargetsCount();
        CallbackResult result = new CallbackResult();
        result.setCallback(callback);
        result.setData(total);
        return result.changToJsonp();
    }

    @RequestMapping("seqSearchByFile")
    @ResponseBody
    public List<BlastpSeqSearch> seqSearchByFile(@RequestParam("file") MultipartFile file,@RequestParam("martix") String martix,@RequestParam("evalue") String evalue){
        log.info("上传文件的名称：{}",file.getOriginalFilename());
        log.info("传入参数:{}",martix);
        log.info("传入参数:{}",evalue);
        if(!file.isEmpty()){
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(
                        new File("/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/seqsearch/condition/"+file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }       //接收文件到resources下的文件夹中
            File fastaFile = new File("/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/seqsearch/condition/"+file.getOriginalFilename());
           log.info("controller中的文件路径为：{}",fastaFile.getAbsolutePath());
           log.info("martix：{}",martix);
            List<BlastpSeqSearch> result = blastpSearchProteinService.fileSearchProtein2(fastaFile,martix,evalue);
//            CallbackResult<List<FormatData<Targets>>> result1 = new CallbackResult();
//            result1.setCallback(callback);
//            result1.setData(result);
            return result;
        }
        else{
            log.info("接收文件为空");
            return null;
        }
    }

    @GetMapping("seqSearchByStr")
    @ResponseBody
    public String seqSearchByStr(@RequestParam String callback,@RequestParam String sequence,@RequestParam String martix,@RequestParam String evalue){
        log.info("传入参数:{}",sequence);
        log.info("传入参数:{}",martix);
        log.info("传入参数:{}",evalue);
        long startTime = System.currentTimeMillis();
        if(StringUtils.isEmpty(sequence)){
            throw new ServiceException("序列为空");
        }
        List<BlastpSeqSearch> result = blastpSearchProteinService.seqSearchProtein2(sequence,martix,evalue);
        log.info("seqSearchByStr调用成功，用时 {} ms",System.currentTimeMillis() - startTime);
        CallbackResult<List<BlastpSeqSearch>> result1 = new CallbackResult();
        result1.setCallback(callback);
        result1.setData(result);
        return result1.changToJsonp();
    }

    @GetMapping("getTargetByTargetId")
    @ResponseBody
    public String getTargetByTargetId(@RequestParam String callback, @Param("targetid") String targetid){
        Targets targets = targetsService.findTargetByTargetID(targetid);
        FormatData formatData = new FormatData();
        formatData.setBasic(targets);
        CallbackResult<FormatData> result = new CallbackResult();
        result.setCallback(callback);
        result.setData(formatData);
        return result.changToJsonp();
    }

    @GetMapping("getAllProteinName")
    @ResponseBody
    public String getAllProteinName(@RequestParam String callback){
        List<TargetName> targetNameList = targetsService.findAllTargetsName();
        CallbackResult<List<TargetName>> result = new CallbackResult();
        result.setCallback(callback);
        result.setData(targetNameList);
        return result.changToJsonp();
    }

}
