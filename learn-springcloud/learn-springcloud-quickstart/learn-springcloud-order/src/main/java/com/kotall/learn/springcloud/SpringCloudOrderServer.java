package com.kotall.learn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/3/16 11:42
 * @since 1.0.0
 */
@EnableFeignClients
@SpringBootApplication
public class SpringCloudOrderServer {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudOrderServer.class, args);
    }
}