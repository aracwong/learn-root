package com.kotall.learn.springcloud.eureka.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/7 0007 下午 10:17
 */
@EnableEurekaClient
@SpringBootApplication
public class EurekaProviderApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaProviderApp.class).web(true).run(args);
    }
}
