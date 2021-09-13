package com.nwafu.PISMDB.service.impl;

import com.nwafu.PISMDB.dao.CompoundsDao;
import com.nwafu.PISMDB.dao.PathwaysDao;
import com.nwafu.PISMDB.entity.*;
import com.nwafu.PISMDB.service.CompoundsService;
import com.nwafu.PISMDB.service.FileSearchService;
import com.nwafu.PISMDB.service.PathwaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class FileSearchServiceImpl implements FileSearchService {

    @Autowired
    private CompoundsService compoundsService;
    @Autowired
    private PathwaysService pathwaysService;
    @Autowired
    private CompoundsDao compoundsDao;


    @Override
    public String readFile(String file_path) {      //读取用户要搜索的文件,这时的文件已经转换为分子描述符文件
        String res = "";
        String temp = "";
        File file = new File(file_path);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((temp = br.readLine()) != null) {
                res += temp;
            }
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public List<String> saveDataToArr_1(String str) {   //读入用户待查询的描述符文件到列表
        String[] a = str.split("\t");
        List<String> b = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (i >= 5273) {
                b.add(a[i]);
            }
        }
        return b;
    }

    public List<String> saveDataToArr_ref(String str) {     //读入单个数据库查询出的描述符
        //读取单个compoudsIdAndDescription中的数据到data_2中
        String[] a = str.split("\t");
        List<String> b = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (i >= 2) {
                b.add(a[i]);
            }
        }
        return b;
    }


    public List<String> saveDatatoArrNone(String str){
        String[] a = str.split("\t");
        List<String> b = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (i >= 2) {
                b.add(a[i]);
            }
        }
        return b;
    }

    public List<String> saveDatatoArrFingerPrint(String str){
        String[] a = str.split("\t");
        List<String> b = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (i >= 1) {
                b.add(a[i]);
            }
        }
        return b;
    }
    public String getPismid(String str){
        String[] a = str.split("\t");
        return a[1];
    }

    public String getPismidNone(String str){
        String[] a = str.split("\t");
        return a[1];
    }

    public String getPismidFingerPrint(String str){
        String[] a = str.split("\t");
        return a[0];
    }

    public float calfinger(List<String> a, List<String> b){
        float x = 0,y = 0,z = 0;
        float calresult = 0;
        for (int i = 0 ; i < a.size() ; i++){
            float tmpa = Float.parseFloat(a.get(i));
            float tmpb = Float.parseFloat(b.get(i));

            x += tmpa;
            y += tmpb;
            z += tmpa * tmpb;

            calresult = z  / (x + y - z);
        }
        return calresult;
    }

    @Override
    public float caculate(List<String> a, List<String> b) {
        float sum_xiyi = 0;

        float sum_xi2 = 0;
        float sum_yi2 = 0;
        int count_xiyi = 0;
        int count_xi = 0;
        int count_yi  = 0;
        int i;
        int len = a.size();
        if (a.size() >= b.size()) {
            len = b.size();
        }
        for (i = 0; i < len; i++) {
            String tmpa = a.get(i);
            String tmpb = b.get(i);
            if (tmpa.equals("na") || tmpb.equals("na")){
                continue;
            }
            count_xiyi++;
            sum_xiyi += Float.parseFloat(tmpa) * Float.parseFloat(tmpb);
        }
        int lena = a.size();
        for (i = 0; i < lena; i++) {
            String tmpa = a.get(i);
            if (tmpa.equals("na")){
                continue;
            }
            count_xi++;
            sum_xi2 += Float.parseFloat(tmpa) * Float.parseFloat(tmpa);  //xi平方的和
        }

        int lenb = b.size();
        for (i = 0; i < lenb; i++) {
            String tmpb = b.get(i);
            if (tmpb.equals("na")){
                continue;
            }
            count_yi++;
            sum_yi2 += Float.parseFloat(tmpb) * Float.parseFloat(tmpb);  //yi的平方和
        }

        float result = sum_xiyi / (sum_xi2 + sum_yi2 - sum_xiyi);
        //float result = (float) (sum_xiyi / (Math.sqrt(sum_xi2) * Math.sqrt(sum_yi2)));
        return result;
    }

    /*@Override
    public List<FormatData> structureSearch(String p, String min1, String type) {
        String path1 = "/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/dragon/txt/" + p + ".txt";
        List<String> PISMIDs = new ArrayList<>();
        List<CompoudsIdAndDescription> list = compoundsService.selectIdAndDescription();
        //List<String> a = deleteZeroAndNa(saveDataToArr_1(readFile(path1)));
        //System.out.println(a.toString());
        List<String> a = saveDataToArr_1(readFile(path1));
        System.out.println(a.get(0));
        List<Compounds> lc = new ArrayList<>();
        List<FormatData> formatResult = new ArrayList<>();

        String head = "/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/dragon/dbtxt/";

        if (type.equals("smiles")){
            head = "/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/dragon/smile_txt/";
        }  else if (type.equals("mol2")){
            head = "/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/dragon/dbtxt/";
        }

        List<Pathways> pathways;
        for (CompoudsIdAndDescription ciad : list) {
            if ("" != ciad.getMocularDescription() && null != ciad.getMocularDescription()) {
                String path2 = head + ciad.getMocularDescription();
                //List<String> b =deleteZeroAndNa(saveDataToArr_1(readFile(path2)));

                List<List<String>> ab = deleteNa(a,saveDataToArr_1(readFile(path2)));
                a = ab.get(0);
                List<String> b = ab.get(1);
                Float result = caculate(a, saveDataToArr_1(readFile(path2)));
                //System.out.println(result);
                // System.out.println(ciad.getPismid()+": "+result);
                if (result > Float.parseFloat(min1)) {
                    PISMIDs.add(ciad.getPismid());
                    FormatData formatData = new FormatData();
                    formatData.setId(ciad.getPismid());
                    Compounds compounds = compoundsDao.findByPISMID(ciad.getPismid());
                    CompoundDataSource compoundDataSource = new CompoundDataSource();
                    formatData.setName(compounds.getChemicalNames());
                    CompoundsBasic compoundsBasic = new CompoundsBasic();
                    compoundsBasic.setPismid(compounds.getPismid());
                    compoundsBasic.setAlogP(compounds.getAlogP());
                    compoundsBasic.setChemicalFormular(compounds.getChemicalFormular());
                    compoundsBasic.setIupacName(compounds.getIUPAC_Name());
                    compoundsBasic.setMolecularWeight(compounds.getMolecularWeight());
                    compoundsBasic.setSmiles(compounds.getSmiles());
                    compoundDataSource.setChemSpider(compounds.getChemSpider());
                    compoundDataSource.setPubChem(compounds.getPubChem());
                    formatData.setImgurl(compounds.getStructure());
                    formatData.setBasic(compoundsBasic);
                    formatData.setCompoundDataSource(compoundDataSource);
                    // 添加相关分子信息
                    RelatedCompounds compoundsRelatedCompounds = new RelatedCompounds();
                    compoundsRelatedCompounds.setPismid(ciad.getPismid());
                    compoundsRelatedCompounds.setCompoundsList(compoundsService.findRelatedCompound(ciad.getPismid()));
                    formatData.setRelatedCompounds(compoundsRelatedCompounds);

                    CompoundPathways compoundPathways = new CompoundPathways();

                    List<String> pathwayIDList = null;
                    if(compounds.getPathwayId() != null){
                        pathwayIDList = Arrays.asList(compounds.getPathwayId().split("%%"));
                    }
                    //System.out.print(pathwayIDList.size());
                    //System.out.println(pathwayIDList.get(0));
                    int pathwayNum = pathwayIDList.size();
                    //System.out.println(pathwayNum);

                    pathways = new ArrayList<>();
                    for (int j = 0 ; j < pathwayNum; j++){
                        String pid = pathwayIDList.get(j);
                        Pathways pathway = pathwaysService.getPathwaysByPathwayID(pid);
                        pathways.add(pathway);
                    }
                    //System.out.println("test");
                    compoundPathways.setPismid(ciad.getPismid());
                    compoundPathways.setPathwaysList(pathways);
                    formatData.setPathways(compoundPathways);

                    formatData.setIdLink(ciad.getPismid());
                    formatData.setSupporting(compoundsService.FindSupportingInformation(ciad.getPismid()));
                    formatData.setValue(result);
                    formatResult.add(formatData);
                    lc.add(compoundsService.findByPISMID(ciad.getPismid()));
                    log.info(ciad.getPismid() + ":" + result);
                }
            }
        }
        formatResult.sort((x,y) -> Float.compare(y.getValue(),x.getValue()));
        return formatResult;
    }*/


    @Override
    public List<FormatData> structureSearch(String p, String min1, String type) {
        String path1 = "/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/dragon/txt/" + p + ".txt";
        //List<String> a = deleteZeroAndNa(saveDataToArr_1(readFile(path1)));
        //System.out.println(a.toString());
        List<String> a = saveDataToArr_1(readFile(path1));
        System.out.println(a.get(0));
        List<FormatData> formatResult = new ArrayList<>();

        //String head = "/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/dragon/dbtxt/";
        String ref_description = "";
        if (type.equals("smiles")){
            //head = "/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/dragon/smile_txt/";
            ref_description = "/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/dragon/smart_v1_finger_by_mol2.txt";
        }  else if (type.equals("mol2")){
            //head = "/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/dragon/dbtxt/";
            ref_description = "/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/dragon/smart_v1_by_mol2.txt";
        }

        String temp = "";
        List<Pathways> pathways;
        File file = new File(ref_description);
        try {
            System.out.println("开始读取文件");
            System.out.println("文件路径:" + ref_description);
            BufferedReader br = new BufferedReader(new FileReader(file));
            //temp = br.readLine();
            while ((temp = br.readLine()) != null) {
                List<String> ref = saveDataToArr_ref(temp);
                String pismid = getPismid(temp);
                //List<List<String>> ab = deleteNa(a,ref);
                //a = ab.get(0);
                //List<String> b = ab.get(1);
                Float result = caculate(a, ref);

                //System.out.println(pismid+":"+result);
                //System.out.println(result);
                // System.out.println(ciad.getPismid()+": "+result);
                if (result > Float.parseFloat(min1)) {
                    FormatData formatData = new FormatData();
                    formatData.setId(pismid);
                    Compounds compounds = compoundsDao.findByPISMID(pismid);
                    CompoundDataSource compoundDataSource = new CompoundDataSource();
                    formatData.setName(compounds.getChemicalNames());
                    CompoundsBasic compoundsBasic = new CompoundsBasic();
                    compoundsBasic.setPismid(compounds.getPismid());
                    compoundsBasic.setAlogP(compounds.getAlogP());
                    compoundsBasic.setChemicalFormular(compounds.getChemicalFormular());
                    compoundsBasic.setIupacName(compounds.getIUPAC_Name());
                    compoundsBasic.setMolecularWeight(compounds.getMolecularWeight());
                    compoundsBasic.setSmiles(compounds.getSmiles());
                    compoundDataSource.setChemSpider(compounds.getChemSpider());
                    compoundDataSource.setPubChem(compounds.getPubChem());
                    formatData.setImgurl(compounds.getStructure());
                    formatData.setBasic(compoundsBasic);
                    formatData.setCompoundDataSource(compoundDataSource);
                    // 添加相关分子信息
                    RelatedCompounds compoundsRelatedCompounds = new RelatedCompounds();
                    compoundsRelatedCompounds.setPismid(pismid);
                    compoundsRelatedCompounds.setCompoundsList(compoundsService.findRelatedCompound(pismid));
                    formatData.setRelatedCompounds(compoundsRelatedCompounds);

                    CompoundPathways compoundPathways = new CompoundPathways();

                    List<String> pathwayIDList = null;
                    if(compounds.getPathwayId() != null){
                        pathwayIDList = Arrays.asList(compounds.getPathwayId().split("%%"));
                    }
                    //System.out.print(pathwayIDList.size());
                    //System.out.println(pathwayIDList.get(0));
                    int pathwayNum = pathwayIDList.size();
                    //System.out.println(pathwayNum);

                    pathways = new ArrayList<>();
                    for (int j = 0 ; j < pathwayNum; j++){
                        String pid = pathwayIDList.get(j);
                        Pathways pathway = pathwaysService.getPathwaysByPathwayID(pid);
                        pathways.add(pathway);
                    }
                    //System.out.println("test");
                    compoundPathways.setPismid(pismid);
                    compoundPathways.setPathwaysList(pathways);
                    formatData.setPathways(compoundPathways);

                    formatData.setIdLink(pismid);
                    formatData.setSupporting(compoundsService.FindSupportingInformation(pismid));
                    formatData.setValue(result);
                    formatResult.add(formatData);

                    log.info(pismid + ":" + result);
                }
            }
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        formatResult.sort((x,y) -> Float.compare(y.getValue(),x.getValue()));
        return formatResult;
    }


    //接收结构文件之后吗，存到dragon_descriptors.drs所对应的相对应的目录之下，然后取出名字，修改对应的字段，
    // 调用服务器中的dragon，生成分子描述符文件，存到相应的目录之下，然后调用结构搜索方法即可。
    @Override
    public void useDragonChangeStrutureToDescription(String type,String fileName) {
        //第一步：加载前端传过来的结构文件，存到相应的目录中，此处的目录和dragon_descriptors.drs中的
        //应该相同，然后取出文件名
        String newCon ="";
        if(type.equals("mol2"))
        {
            newCon= fileName + ".mol2";
        }else {
            newCon = fileName + ".smiles";
        }

        //第二步 通过文件名，将dragon_descriptors.drs中的MOLFILES的相应文件名修改
//2.1建立新的Drs文件，复制dragon_descriptors.drs
        //smg
        String targetSmilesOrMol2 = "/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/dragon/txt/"+newCon;

        String sourceFile = "/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/dragon/dragon_descriptors.drs";
        String targetFile = "/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/dragon/drs/" + fileName + ".drs";
//	创建对象
        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader(sourceFile);
            fw = new FileWriter(targetFile);
//		循环读和循环写
            int len = 0;
            while ((len = fr.read()) != -1) {
                fw.write((char) len);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
        File file = new File(targetFile);
        DocumentBuilder newDocumentBuilder = null;
        try {
            newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            doc = newDocumentBuilder.parse(file);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2.2修改新文件中的MOLFILES，也就是上传的结构文件的路径
        String NodeName = "";
        String Noderesult = "";
        String NewNodeReslt = "";
        NodeList nodeList = doc.getElementsByTagName("MOLFILES");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element e = (Element) nodeList.item(i);
            Node node = e.getElementsByTagName("molFile").item(0);
            NamedNodeMap nnm = node.getAttributes();
            for (int j = 0; j < nnm.getLength(); j++) {
                Node attrNode = (Node) nnm.item(j);
                NodeName = attrNode.getNodeName();
                Noderesult = attrNode.getFirstChild().getNodeValue();
                System.out.println("NodeName:" + NodeName);
                System.out.println("OldNodeResult:" + Noderesult);

                NewNodeReslt = "/home/songmg/SMART_Dragon/Data/" + newCon;
                attrNode.setNodeValue(NewNodeReslt);
                //2，3修改输出描述符文件的路径及其名字
                String NodeName1 = "";
                String Noderesult1 = "";
                String NewNodeReslt1 = "";
                NodeList nodeList1 = doc.getElementsByTagName("OUTPUT");
                for (int i1 = 0; i1 < nodeList1.getLength(); i1++) {
                    Element e1 = (Element) nodeList1.item(i1);
                    Node node1 = e1.getElementsByTagName("SaveFilePath").item(0);
                    NamedNodeMap nnm1 = node1.getAttributes();
                    for (int j1 = 0; j1 < nnm1.getLength(); j1++) {
                        Node attrNode1 = (Node) nnm1.item(j1);
                        NodeName1 = attrNode1.getNodeName();
                        Noderesult1 = attrNode1.getFirstChild().getNodeValue();
                        System.out.println("NodeName:" + NodeName1);
                        System.out.println("OldNodeResult:" + Noderesult1);

                        NewNodeReslt1 = "/home/songmg/SMART_Dragon/Result/" + fileName+".txt";
                        attrNode1.setNodeValue(NewNodeReslt1);
                        //设置完值之后是在缓存中改变，磁盘中没有改变，因此需要将磁盘中的值也同步更新。
                        Transformer transformer = null;
                        try {
                            transformer = TransformerFactory.newInstance().newTransformer();
                        } catch (TransformerConfigurationException e2) {
                            e2.printStackTrace();
                        }
                        Source source = new DOMSource(doc);
                        Result result2 = new StreamResult(file);
                        try {
                            transformer.transform(source, result2);
                        } catch (TransformerException e2) {
                            e2.printStackTrace();
                        }
                        System.out.println("NewNodeResult:" + attrNode.getFirstChild().getNodeValue());
                        System.out.println("NewNodeResult1:" + attrNode1.getFirstChild().getNodeValue());
                    }

                }
                //第三步 调用dragon服务器，将结构文件转为分子描述符文件。
                //命令是：/storage_server/software/dragon7/dragon7shell -s /home/lixy/LXY-Dragon/dragon_descriptors.drs
               /* String command_1 = "/storage_server/software/dragon7/dragon7shell -s "+targetFile;*/
                try {
                    String[] path = new String[]{"bash","/home/songmg/SMART/scripts/upload.sh",targetFile,targetSmilesOrMol2};
                    //String[] path = new String[]{"blastp","-task","blastp","-query",fastaFile.getAbsolutePath(),"-db",blastLocalPath,"-out",resultFile.getAbsolutePath(),};
                    log.info("执行命令：{}" ,path);

                    ProcessBuilder processBuilder = new ProcessBuilder(path);
                    processBuilder.redirectErrorStream(true);
                    Process p = processBuilder.start();
                    InputStream is = p.getInputStream();
                    BufferedReader bs = new BufferedReader(new InputStreamReader(is));
                    /*Process process = Runtime.getRuntime().exec(command_1);*/
                    String line;
                    while ((line = bs.readLine()) != null) {
                        System.out.println(line);
                    }

                    int exitCode = p.waitFor();
                    System.out.println(exitCode);
                } catch (IOException | InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    @Override
    public void buildsmilesDescription(String fileName) {
        String newCon = fileName + ".smiles";
        String sourceFile = "/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/dragon/dragon_descriptors.drs";
        String targetFile = "/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/dragon/drs/" + fileName + ".drs";
        File newFile = new File(targetFile);

//	创建对象
        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader(sourceFile);
            fw = new FileWriter(targetFile);
//		循环读和循环写
            int len = 0;
            while ((len = fr.read()) != -1) {
                fw.write((char) len);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        File file = new File(targetFile);
        DocumentBuilder newDocumentBuilder = null;
        try {
            newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            doc = newDocumentBuilder.parse(file);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2.2修改新文件中的MOLFILES，也就是上传的结构文件的路径
        String NodeName = "";
        String Noderesult = "";
        String NewNodeReslt = "";
        NodeList nodeList = doc.getElementsByTagName("MOLFILES");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element e = (Element) nodeList.item(i);
            Node node = e.getElementsByTagName("molFile").item(0);
            NamedNodeMap nnm = node.getAttributes();
            for (int j = 0; j < nnm.getLength(); j++) {
                Node attrNode = (Node) nnm.item(j);
                NodeName = attrNode.getNodeName();
                Noderesult = attrNode.getFirstChild().getNodeValue();
                System.out.println("NodeName:" + NodeName);
                System.out.println("OldNodeResult:" + Noderesult);
                /*String[] result = Noderesult.split("/");
                for (int k = 0; k < result.length - 1; k++) {
                    NewNodeReslt += result[k] + "/";
                }*/

                NewNodeReslt = "/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/dragon/smile_txt/" + newCon;
                attrNode.setNodeValue(NewNodeReslt);
                //2，3修改输出描述符文件的路径及其名字
                String NodeName1 = "";
                String Noderesult1 = "";
                String NewNodeReslt1 = "";
                NodeList nodeList1 = doc.getElementsByTagName("OUTPUT");
                for (int i1 = 0; i1 < nodeList1.getLength(); i1++) {
                    Element e1 = (Element) nodeList1.item(i1);
                    Node node1 = e1.getElementsByTagName("SaveFilePath").item(0);
                    NamedNodeMap nnm1 = node1.getAttributes();
                    for (int j1 = 0; j1 < nnm1.getLength(); j1++) {
                        Node attrNode1 = (Node) nnm1.item(j1);
                        NodeName1 = attrNode1.getNodeName();
                        Noderesult1 = attrNode1.getFirstChild().getNodeValue();
                        System.out.println("NodeName:" + NodeName1);
                        System.out.println("OldNodeResult:" + Noderesult1);
                        /*String[] result1 = Noderesult1.split("/");
                        for (int k1 = 0; k1 < result1.length - 1; k1++) {
                            NewNodeReslt1 += result1[k1] + "/";
                        }*/
                        NewNodeReslt1 = "/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/dragon/smile_txt/" + fileName+".txt";
                        attrNode1.setNodeValue(NewNodeReslt1);
                        //设置完值之后是在缓存中改变，磁盘中没有改变，因此需要将磁盘中的值也同步更新。
                        Transformer transformer = null;
                        try {
                            transformer = TransformerFactory.newInstance().newTransformer();
                        } catch (TransformerConfigurationException e2) {
                            e2.printStackTrace();
                        }
                        Source source = new DOMSource(doc);
                        Result result2 = new StreamResult(file);
                        try {
                            transformer.transform(source, result2);
                        } catch (TransformerException e2) {
                            e2.printStackTrace();
                        }
                        System.out.println("NewNodeResult:" + attrNode.getFirstChild().getNodeValue());
                        System.out.println("NewNodeResult1:" + attrNode1.getFirstChild().getNodeValue());
                    }

                }
                //第三步 调用dragon服务器，将结构文件转为分子描述符文件。
                //命令是：/storage_server/software/dragon7/dragon7shell -s /home/lixy/LXY-Dragon/dragon_descriptors.drs
                /* String command_1 = "/storage_server/software/dragon7/dragon7shell -s "+targetFile;*/
                try {
                    String[] path = new String[]{"bash","/home/songmg/SMART/dragon.sh",targetFile};
                    //String[] path = new String[]{"blastp","-task","blastp","-query",fastaFile.getAbsolutePath(),"-db",blastLocalPath,"-out",resultFile.getAbsolutePath(),};
                    log.info("执行命令：{}" ,path);

                    ProcessBuilder processBuilder = new ProcessBuilder(path);
                    processBuilder.redirectErrorStream(true);
                    Process p = processBuilder.start();
                    InputStream is = p.getInputStream();
                    BufferedReader bs = new BufferedReader(new InputStreamReader(is));
                    /*Process process = Runtime.getRuntime().exec(command_1);*/
                    String line;
                    while ((line = bs.readLine()) != null) {
                        System.out.println(line);
                    }

                    int exitCode = p.waitFor();
                    System.out.println(exitCode);
                } catch (IOException | InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    @Override
    public void useDragonCalDescription(String fileName, String calType) {
        try {
            String[] path = new String[]{"bash","/home/songmg/SMART/scripts/updata.sh",fileName,calType};
            //String[] path = new String[]{"blastp","-task","blastp","-query",fastaFile.getAbsolutePath(),"-db",blastLocalPath,"-out",resultFile.getAbsolutePath(),};
            log.info("执行命令：{}" ,path);

            ProcessBuilder processBuilder = new ProcessBuilder(path);
            processBuilder.redirectErrorStream(true);
            Process p = processBuilder.start();
            InputStream is = p.getInputStream();
            BufferedReader bs = new BufferedReader(new InputStreamReader(is));
            /*Process process = Runtime.getRuntime().exec(command_1);*/
            String line;
            while ((line = bs.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = p.waitFor();
            System.out.println(exitCode);
        } catch (IOException | InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public List<FormatData> structureSearchAllType(String fileName, String min, String calType) {
        //将文件名拆分
        String file_name = fileName.split("\\.")[0];
        String file_type = fileName.split("\\.")[1];
        System.out.println(calType);
        //准备加载计算结果文件
        String result_path = "/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/dragon/txt/" + file_name + ".txt";
        String ref_description = "";
        List<FormatData> formatResult = new ArrayList<>();
        List<String> arr1;
        boolean flag = false;

        //选择分子描述符文件并载入计算结果
        if (calType.equals("none")) {
            System.out.println(calType);
            if (file_type.equals("mol2")) {
                ref_description = "/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/dragon/smart_v1_by_mol2.txt";
            } else if (file_type.equals("smiles")) {
                ref_description = "/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/dragon/smart_v1_by_smile.txt";
            }
            arr1 = saveDatatoArrNone(readFile(result_path));
            flag = true;
        }  else if (calType.equals("ECFP")) {
            ref_description = "/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/dragon/smart_v1_ECFP_by_mol2.txt";
            arr1 = saveDatatoArrFingerPrint(readFile(result_path));
        }  else {
            ref_description = "/home/songmg/SMART/apache-tomcat-9.0.34/webapps/SMART/WEB-INF/classes/dragon/smart_v1_PFP_by_mol2.txt";
            arr1 = saveDatatoArrFingerPrint(readFile(result_path));
        }

        String temp = "";
        String pismid = "";
        File file = new File(ref_description);
        List<Pathways> pathways;
        Float result;

        try {
            System.out.println("开始读取文件");
            BufferedReader br = new BufferedReader(new FileReader(file));
            //temp = br.readLine();
            while ((temp = br.readLine()) != null) {
                if (flag){
                    List<String> ref = saveDatatoArrNone(temp);
                    result = caculate(arr1, ref);
                } else {
                    List<String> ref = saveDatatoArrFingerPrint(temp);
                    result = calfinger(arr1, ref);
                }

                if (result > Float.parseFloat(min)) {

                    if (flag){
                        pismid = getPismidNone(temp);
                    } else {
                        pismid = getPismidFingerPrint(temp);
                    }

                    FormatData formatData = new FormatData();
                    formatData.setId(pismid);
                    Compounds compounds = compoundsDao.findByPISMID(pismid);
                    CompoundDataSource compoundDataSource = new CompoundDataSource();
                    formatData.setName(compounds.getChemicalNames());
                    CompoundsBasic compoundsBasic = new CompoundsBasic();
                    compoundsBasic.setPismid(compounds.getPismid());
                    compoundsBasic.setAlogP(compounds.getAlogP());
                    compoundsBasic.setChemicalFormular(compounds.getChemicalFormular());
                    compoundsBasic.setIupacName(compounds.getIUPAC_Name());
                    compoundsBasic.setMolecularWeight(compounds.getMolecularWeight());
                    compoundsBasic.setSmiles(compounds.getSmiles());
                    compoundDataSource.setChemSpider(compounds.getChemSpider());
                    compoundDataSource.setPubChem(compounds.getPubChem());
                    formatData.setImgurl(compounds.getStructure());
                    formatData.setBasic(compoundsBasic);
                    formatData.setCompoundDataSource(compoundDataSource);
                    // 添加相关分子信息
                    RelatedCompounds compoundsRelatedCompounds = new RelatedCompounds();
                    compoundsRelatedCompounds.setPismid(pismid);
                    compoundsRelatedCompounds.setCompoundsList(compoundsService.findRelatedCompound(pismid));
                    formatData.setRelatedCompounds(compoundsRelatedCompounds);

                    CompoundPathways compoundPathways = new CompoundPathways();

                    List<String> pathwayIDList = null;
                    if(compounds.getPathwayId() != null){
                        pathwayIDList = Arrays.asList(compounds.getPathwayId().split("%%"));
                    }
                    //System.out.print(pathwayIDList.size());
                    //System.out.println(pathwayIDList.get(0));
                    int pathwayNum = pathwayIDList.size();
                    //System.out.println(pathwayNum);

                    pathways = new ArrayList<>();
                    for (int j = 0 ; j < pathwayNum; j++){
                        String pid = pathwayIDList.get(j);
                        Pathways pathway = pathwaysService.getPathwaysByPathwayID(pid);
                        pathways.add(pathway);
                    }
                    //System.out.println("test");
                    compoundPathways.setPismid(pismid);
                    compoundPathways.setPathwaysList(pathways);
                    formatData.setPathways(compoundPathways);

                    formatData.setIdLink(pismid);
                    formatData.setSupporting(compoundsService.FindSupportingInformation(pismid));
                    formatData.setValue(result);
                    formatResult.add(formatData);

                    log.info(pismid + ":" + result);
                }
            }
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        formatResult.sort((x,y) -> Float.compare(y.getValue(),x.getValue()));
        return formatResult;
    }
}