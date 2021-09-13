package com.nwafu.PISMDB.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @program: PISMDB
 * @description: 定时任务：定期删除项目中的缓存文件
 * @create: 2019-10-14 15:27
 **/

@Component
@Configuration
@EnableScheduling
@Slf4j
public class DeleteCacheFiles {

    @Value("${blast.junkPath1}")
    private String dir1;

    @Value("${blast.junkPath2}")
    private String dir2;

    @Value("${blast.junkPath3}")
    private String dir3;

    @Value("${blast.junkPath4}")
    private String dir4;

    @Value("${blast.junkPath5}")
    private String dir5;

    @Value("${blast.junkPath6}")
    private String dir6;

    @Scheduled(fixedDelay = 600000)     //定时任务每5分钟删除一次缓存
    public void deleteSeqSearchCache(){

        File[] dir1Files = new File(dir1).listFiles();
        File[] dir2Files = new File(dir2).listFiles();
        File[] dir3Files = new File(dir3).listFiles();
        File[] dir4Files = new File(dir4).listFiles();
        File[] dir5Files = new File(dir5).listFiles();
        File[] dir6Files = new File(dir6).listFiles();

        Thread thread1 = new Thread(new DeleteThread(dir1Files));
        Thread thread2 = new Thread(new DeleteThread(dir2Files));
        Thread thread3 = new Thread(new DeleteThread(dir3Files));
        Thread thread4 = new Thread(new DeleteThread(dir4Files));
        Thread thread5 = new Thread(new DeleteThread(dir5Files));
        Thread thread6 = new Thread(new DeleteThread(dir6Files));

        thread1.start();
        thread2.start();
        thread3.start();    //开三个线程同步删，防止文件过多，过于占用系统资源
        thread4.start();
        thread5.start();
        thread6.start();
    }
}

@Slf4j
class DeleteThread implements Runnable{
    private File[] files = null;

    public DeleteThread(File[] files){
        this.files = files;
    }
    public void setFiles(File[] files){
        this.files = files;
    }
    @Override
    public void run() {
        if(files == null || files.length == 0){
            log.info("文件夹中没有缓存");
            return;
        }
        for(int i = 0; i < files.length/2; i++){        //每次删除缓存只删除当前总数的一半，防止刚生成的文件没有被使用就被删除
            if(files[i].exists()){
                if(files[i].delete()){
                    log.info("删除缓存文件成功,{}",files[i].getName());
                }
                else{
                    log.info("删除文件失败，可能是文件正在被占用");
                }
            }
            else{
                log.error("{}, 文件不存在",files[i].getName());
            }
        }
    }
}