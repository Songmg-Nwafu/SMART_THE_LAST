package com.nwafu.PISMDB.service;

import com.nwafu.PISMDB.entity.CompoudsIdAndDescription;
import com.nwafu.PISMDB.entity.Compounds;
import com.nwafu.PISMDB.entity.FormatData;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description:  文件搜索部分
* @Param:
* @return:
* @Date: 2019/9/30
*/

public interface FileSearchService {

    //读取用户要搜索的文件
    String readFile(String file_path);


    List<String> saveDataToArr_1(String str);

    float caculate(List<String> a,List<String> b);

    List<FormatData> structureSearch(String p,String min1,String type);

    void useDragonChangeStrutureToDescription(String type,String fileName);

    void buildsmilesDescription(String fileName);

    void useDragonCalDescription(String fileName, String calType);

    List<FormatData> structureSearchAllType(String fileName,String min,String calType);
}
