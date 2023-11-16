package com.lin.linsux.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>TODO</p>
 *
 * @author linsz
 * @version v1.0
 * @date 2023/10/28 15:25
 */
@SpringBootApplication
public class ServiceMainApplication {
    public static void main(String[] args) {
        System.out.println("==linsux-common-service==正常启动==");
        SpringApplication.run(ServiceMainApplication.class, args);
    }
}
