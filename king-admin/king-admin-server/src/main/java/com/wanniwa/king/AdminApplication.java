package com.wanniwa.king;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 *
 * @author wanniwa
 * @date 2019/12/30
 */
@SpringCloudApplication
@EnableFeignClients
@MapperScan("com.wanniwa.king.admin.mapper")
@ComponentScan("com.wanniwa.king")
@EnableCaching
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}