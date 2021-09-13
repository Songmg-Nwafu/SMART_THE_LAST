package com.nwafu.PISMDB.service;

import org.dom4j.DocumentException;

import java.rmi.ServerException;

/**
* @Description: 远程连接远端服务器，上传文件，调用服务器的dragon计算描述符，将描述符文件下载到本地服务器后调用文件搜索去比对
* @Param:  
* @return:
* @Date: 2019/10/15 
*/
public interface ConnectServerAndChangeFileService {
     void connectToSever();
     void disConnect();
     boolean uploadFile(String remoteFilePath, String localFilePath);      //在上传时要将文件属性设置为不可删除
     boolean caculateMolecularDescriptor(String uploadFilePath, String localFilePath) throws ServerException;
     boolean dowloadResultFile(String remoteFilePath, String localFilePath);       //下载任务完成时将文件属性设置为可以删除，以便定时任务定期删除缓存
     void deleteCacheFiles();
     void makeXMLScript(String uploadFilePath) throws DocumentException;
}
