package com.ilab.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 异构服务
 *
 * @author wlh
 */
@SpringBootApplication
@MapperScan("com.ilab.service.mapper")
public class ILabServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ILabServiceApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  iLab服务模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
            " _  _             _     \n" +
            "(_)| |           | |    \n" +
            " _ | |      __ _ | |__  \n" +
            "| || |     / _` || '_ \\ \n" +
            "| || |____| (_| || |_) |\n" +
            "|_|\\_____/ \\__,_||_.__/ "
        );
    }

}
