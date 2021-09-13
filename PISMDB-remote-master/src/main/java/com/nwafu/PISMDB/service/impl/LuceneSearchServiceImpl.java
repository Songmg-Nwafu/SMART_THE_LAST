package com.nwafu.PISMDB.service.impl;

import com.nwafu.PISMDB.dao.CompoundsDao;
import com.nwafu.PISMDB.entity.*;
import com.nwafu.PISMDB.service.CompoundsService;
import com.nwafu.PISMDB.service.LuceneSearchService;
import com.nwafu.PISMDB.service.PathwaysService;
import com.nwafu.PISMDB.service.TargetsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.Token;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Slf4j
@Service
public class LuceneSearchServiceImpl implements LuceneSearchService {

    @Autowired
    CompoundsService compoundsService;
    @Autowired
    CompoundsDao compoundsDao;
    @Autowired
    PathwaysService pathwaysService;
    @Autowired
    TargetsService targetsService;

    @Override
    public Integer createIndex() throws IOException {      //搜索引擎是将数据库里的coumpounds表中所有文本读出，存储成关键字
        //把索引库保存到磁盘上
        Directory directory = FSDirectory.open(new File("/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/luceneindex").toPath());
//        Directory directory = FSDirectory.open(new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\PISMDB-0.0.1-SNAPSHOT\\WEB-INF\\classes\\luceneindex").toPath());
        //会在index中生成索引目录
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(analyzer));
        List<CompoundsBasicInformationBean> list = compoundsService.FindBasicInformation();
        System.out.println(list.size());
        for (CompoundsBasicInformationBean cbi : list) {
            log.info("cbi:{}",cbi);
            String PISMID_ = cbi.getPismid();
            String ChemicalNames_ = cbi.getChemicalNames();
            String IUPAC_Name_ = cbi.getIupacName();
            String OtherNames = cbi.getOtherNames();
            String ChemicalFormular_ = cbi.getChemicalFormular();
            String MolecularWeight = cbi.getMolecularWeight();
            String AlogP_ = cbi.getAlogP();
            String Smiles = cbi.getSmiles();
            String Function = cbi.getFunction();
            String Mechanism = cbi.getMechanism();
            String Phenotype = cbi.getPhenotype();

            String ProteinName = null;
            String FullName = null;
            String UniportID = null;

            List<String> targetList = null;
            List<Targets> targets = null;
/*
            CompoundPathways compoundPathways = new CompoundPathways();
            List<String>  pathwayIDList = null;
            List<Pathways> pathways = null;
            if (compounds.getPathwayId() != null){
                pathwayIDList = Arrays.asList(compounds.getPathwayId().split("%%"));
                int pathNum = pathwayIDList.size();
                pathways = new ArrayList<>();
                for (int i = 0 ; i < pathNum ; i++){
                    Pathways pathway = pathwaysService.getPathwaysByPathwayID(pathwayIDList.get(i));
                    pathways.add(pathway);
                }
            }*/


            if (cbi.getTargetId() != null){
                targetList = Arrays.asList(cbi.getTargetId().split("%%"));
                int targetNum = targetList.size();
                targets = new ArrayList<>();
                for (int i = 0 ; i < targetNum ; i++){
                    Targets target = targetsService.findTargetByTargetID(targetList.get(i));
                    targets.add(target);
                }

                for (Targets targets1 : targets){
                    ProteinName = ProteinName + "%%" +targets1.getProteinName();
                    FullName = FullName + "%%" + targets1.getFullName();
                    UniportID = UniportID +"%%" + targets1.getUniprotID();
                }
            }

            String content = PISMID_ + " <1>" + ChemicalNames_ + " <1>" + IUPAC_Name_ + " <1>" + ChemicalFormular_ + " <1>" + MolecularWeight + " <1>" + AlogP_ + " <1>" + OtherNames + " <1>" + Smiles + " <1>" + Function + " <1>" + Mechanism + " <1>" + Phenotype + " <1>" + ProteinName + " <1>" + FullName + " <1>" + UniportID;
            System.out.println(content);
            //创建域    域的名称、域的值、是否存储到磁盘
            Field fieldPISMID_ = new TextField("PISMID", PISMID_, Field.Store.YES);
            Field fieldContent = new TextField("Content", content, Field.Store.YES);
            //创建文档对象
            Document document = new Document();
            document.add(fieldPISMID_);
            document.add(fieldContent);

            indexWriter.addDocument(document);
        }
        indexWriter.close();
        log.info("搜索引擎创建完成");
        return null;
    }       //content中的值  id,chemicalNames,IUPAC_Name,ChemicalFormular,

    @Override
    public List<FormatData> searchIndex(String keyword) throws IOException, InvalidTokenOffsetsException, ParseException {
        System.out.println(keyword);

//        C:\Program Files\Apache Software Foundation\Tomcat 8.5\webapps\PISMDB-0.0.1-SNAPSHOT\WEB-INF\classes\luceneindex
//H:\bysj\PISMDB-remote-master\src\main\resources
        Directory directory = FSDirectory.open(new File("/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/luceneindex").toPath());  //服务器端路径
//        Directory directory = FSDirectory.open(new File("D:\\Tomcat\\apache-tomcat-9.0.27\\webapps\\PISMDB-0.0.1-SNAPSHOT\\WEB-INF\\classes\\luceneindex").toPath());//本地tomcat路径
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        Analyzer analyzer = new StandardAnalyzer();
        long startTime = System.currentTimeMillis();
//        Query query = new WildcardQuery(new Term("PISMID","*pis*"));
//        //  查询对象，查询结果返回的最大数
        QueryParser queryParser = new QueryParser("Content", analyzer);
        Query query = queryParser.parse(keyword);
        TopDocs topDocs = indexSearcher.search(query, 25);
        log.info("查询总数量为 ：{}" , topDocs.totalHits);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        QueryScorer scorer=new QueryScorer(query);
        /*Fragmenter fragmenter=new SimpleSpanFragmenter(scorer);
        SimpleHTMLFormatter simpleHTMLFormatter=new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");
        Highlighter highlighter=new Highlighter(simpleHTMLFormatter, scorer);
        highlighter.setTextFragmenter(fragmenter);*/
        List<Compounds> list = new ArrayList<>();
        Analyzer analyzer2 = new StandardAnalyzer();
        List<FormatData> formatResult = new ArrayList<>();
        log.info("{}",scoreDocs.length);
        for (ScoreDoc doc : scoreDocs) {
            int docId = doc.doc;
            log.info(Integer.valueOf(docId).toString());
            //根据文档id 获取文档对象
            Document document = indexSearcher.doc(docId);
            String s = null;
            String foodname=document.get("Content");

            SimpleHTMLFormatter simpleHTMLFormatter=new SimpleHTMLFormatter("<span style=\"color: red\">","</span>");
            Highlighter highlighter=new Highlighter(simpleHTMLFormatter, scorer);
            highlighter.setTextFragmenter(new SimpleFragmenter(foodname.length() + 180));

            log.info("内容为：{}",foodname);
            //s = document.get("Content");
           /* s = highlighter.getBestFragment(analyzer, "Content", foodname) ;*/
            TokenStream tokenStream = analyzer.tokenStream("Content",new StringReader(foodname));
            s = highlighter.getBestFragment(tokenStream,foodname);


            log.info("s的值:{}",s);

            if(s.equals("") || s == null){
                System.out.println("为空");
            }else{
                System.out.println(document.get("Content"));
            }
            String[] str = s.split("<1>");
            log.info("分隔后的值:{}",str);
            for(int i = 0;i < str.length; i++){
                /*String regex="<b>< ";
                str[i] = str[i].replaceAll(regex,"<b><font ");*/
                System.out.println("第"+i+str[i]);
            }

            FormatData formatData = new FormatData();

            RelatedCompounds relatedCompounds = new RelatedCompounds();
            if (str[0].charAt(0) == '<'){
                str[0] = str[0].substring(21,30);
            }
            formatData.setId(str[0]);
            Compounds compounds = compoundsDao.findByPISMID(str[0]);
            CompoundDataSource compoundDataSource = new CompoundDataSource();
            compoundDataSource.setChemSpider(compounds.getChemSpider());
            compoundDataSource.setPubChem(compounds.getPubChem());
            formatData.setIdLink(compounds.getPismid());
            formatData.setName(compounds.getIUPAC_Name());
            formatData.setImgurl(compounds.getStructure());
            formatData.setBasic(compounds);
            formatData.setCompoundDataSource(compoundDataSource);
            compounds.setChemicalNames(str[1]);
            compounds.setIUPAC_Name(str[2]);
            compounds.setChemicalFormular(str[3]);
            compounds.setSmiles(str[7]);
            relatedCompounds.setPismid(str[0]);
            relatedCompounds.setCompoundsList(compoundsService.findRelatedCompound(str[0]));
            formatData.setRelatedCompounds(relatedCompounds);

            CompoundSupportingInformation supportingInformation = new CompoundSupportingInformation();

            String cfunction = compounds.getFunction();
            String cmechanism = compounds.getMechanism();
            String cphenotype = compounds.getPhenotype();

            if (cfunction != null && cfunction.length() > 0){
                supportingInformation.setFunction(str[8]);
            }  else {
                supportingInformation.setFunction(null);
            }
            if (cmechanism != null && cmechanism.length() > 0){
                supportingInformation.setMechanism(str[9]);
            } else {
                supportingInformation.setMechanism(null);
            }
            if (cphenotype != null && cphenotype.length() > 0){
                supportingInformation.setPhenotype(str[10]);
            } else {
                supportingInformation.setPhenotype(null);
            }

            formatData.setSupporting(supportingInformation);

            CompoundPathways compoundPathways = new CompoundPathways();
            List<String>  pathwayIDList = null;
            List<Pathways> pathways = null;
            if (compounds.getPathwayId() != null){
                pathwayIDList = Arrays.asList(compounds.getPathwayId().split("%%"));
                int pathNum = pathwayIDList.size();
                pathways = new ArrayList<>();
                for (int i = 0 ; i < pathNum ; i++){
                    Pathways pathway = pathwaysService.getPathwaysByPathwayID(pathwayIDList.get(i));
                    pathways.add(pathway);
                }
            }
            compoundPathways.setPismid(str[0]);
            compoundPathways.setPathwaysList(pathways);
            formatData.setPathways(compoundPathways);

            CompoundTargets compoundTargets = new CompoundTargets();
            List<String> targetList = null;
            List<Targets> targets = null;
            if (compounds.getTargetId() != null){
                targetList = Arrays.asList(compounds.getTargetId().split("%%"));
                int targetNum = targetList.size();
                targets = new ArrayList<>();
                for (int i = 0 ; i < targetNum ; i++){
                    Targets target = targetsService.findTargetByTargetID(targetList.get(i));
                    targets.add(target);
                }
            }
            compoundTargets.setPismid(str[0]);
            compoundTargets.setTargetsList(targets);
            formatData.setCompoundTargets(compoundTargets);


            formatResult.add(formatData);
            /*formatData.setName(compounds.getChemicalNames());
            CompoundsBasic compoundsBasic = new CompoundsBasic();
            compoundsBasic.setPismid(compounds.getPismid());
            compoundsBasic.setAlogP(compounds.getAlogP());
            compoundsBasic.setChemicalFormular(compounds.getChemicalFormular());
            compoundsBasic.setIupacName(compounds.getIUPAC_Name());
            compoundsBasic.setMolecularWeight(compounds.getMolecularWeight());
            compoundsBasic.setSmiles(compounds.getSmiles());
            formatData.setImgurl(compounds.getStructure());
            formatData.setBasic(compoundsBasic);
            List<String> relatedList = compoundsService.findRelatedById(str[0]);
            formatData.setRelated(new CompoundsRelatedCompounds(str[0],relatedList));
            CompoundsPathway compoundsPathway = new CompoundsPathway();
            formatData.setPathway(compoundsPathway);
            formatData.setIdLink(str[0]);
            formatResult.add(formatData);*/
        }

        log.info("数组大小：{}" , list.size());
        long endTime = System.currentTimeMillis();
        log.info("消耗时间：{}" ,(endTime - startTime)+"ms");
        return formatResult;
    }

}
