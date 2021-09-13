package com.nwafu.PISMDB.config;

import com.nwafu.PISMDB.service.impl.ConnectServerAndChangeFileServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @program: PISMDB
 * @description: 项目关闭时断开链接
 * @create: 2019-10-16 10:10
 **/
@Slf4j
//@Component
public class DestroyConnection {

    @PreDestroy
    public void disConnectionDragonSever(){
        log.info("项目关闭，准备断开链接————");
        ConnectServerAndChangeFileServiceImpl.getInstance().disConnect();
        log.info("链接已断开");

    }
}
