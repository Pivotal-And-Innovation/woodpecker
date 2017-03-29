package com.woodpecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot 启动类
 *
 * @author Glenn
 * @since 2017-03-25
 */
@SpringBootApplication
//@EnableConfigurationProperties({DataBaseModel.class})
public class ApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
    }
}
