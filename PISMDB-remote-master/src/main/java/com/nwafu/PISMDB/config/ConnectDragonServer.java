package com.nwafu.PISMDB.config;

import com.nwafu.PISMDB.service.impl.ConnectServerAndChangeFileServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


/**
 * @program: PISMDB
 * @description: 在项目启动时自动连接Dragon服务器
 * @create: 2019-10-15 11:51
 **/

//@Component
@Slf4j
public class ConnectDragonServer implements ApplicationRunner {
    @Value("${Dragon.url}")
    private String url;
    @Value("${Dragon.username}")
    private String username;
    @Value("${Dragon.psw}")
    private String psw;
    @Value("${Dragon.port}")
    private String port;

    public static ConnectServerAndChangeFileServiceImpl csacf = ConnectServerAndChangeFileServiceImpl.getInstance();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Dragon服务器连接准备---");
        csacf.setUrl(url);
        csacf.setUsername(username);
        csacf.setPsw(psw);
        if(port.equals("")){
            csacf.setPort(22);
        }
        else{
            csacf.setPort(Integer.parseInt(port));
        }

        while(csacf.isConnect == 0){        //若监听到Dragon服务器未连接
            csacf.connectToSever();     //主动调用连接方法
            Thread.sleep(5000);     //休息5S
        }

    }
}
