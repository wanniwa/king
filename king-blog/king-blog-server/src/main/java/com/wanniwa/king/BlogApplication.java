package com.wanniwa.king;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动类
 *
 * @author wanniwa
 * @date 2019/12/30
 */
@SpringCloudApplication
@EnableFeignClients
@MapperScan("com.wanniwa.king.blog.mapper")
//@ComponentScan("com.wanniwa.king")
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
