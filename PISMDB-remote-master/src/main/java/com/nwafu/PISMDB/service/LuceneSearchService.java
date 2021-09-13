package com.nwafu.PISMDB.service;

import com.nwafu.PISMDB.entity.Compounds;
import com.nwafu.PISMDB.entity.FormatData;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
* @Description: lucene搜索引擎索引创建，关键字查询
* @Param:  
* @return:  
* @Author: liu qinchang
* @Date: 2019/9/30 
*/
public interface LuceneSearchService {
    Integer createIndex() throws IOException;
    List<FormatData> searchIndex(String keyword) throws IOException, InvalidTokenOffsetsException, ParseException;
}
