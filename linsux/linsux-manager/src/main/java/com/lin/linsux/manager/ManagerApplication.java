package com.lin.linsux.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>TODO</p>
 *
 * @author linsz
 * @version v1.0
 * @date 2023/10/29 02:19
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.lin.linsux")
public class ManagerApplication {
    public static void main(String[] args) {
        System.out.println("LINUSX-MANAGER-成功启动---");
        SpringApplication.run(ManagerApplication.class, args);
    }
}
