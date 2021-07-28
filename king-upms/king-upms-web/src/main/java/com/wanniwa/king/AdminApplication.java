package com.wanniwa.king;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动类
 *
 * @author wanniwa
 * @date 2019/12/30
 */
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.wanniwa.king.admin.mapper")
//@ComponentScan("com.wanniwa.king")
@EnableCaching
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
