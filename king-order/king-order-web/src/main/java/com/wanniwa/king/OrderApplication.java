package com.wanniwa.king;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.wanniwa.king.order.mapper")
@EnableFeignClients
@SpringCloudApplication
//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker
@EnableHystrixDashboard
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
