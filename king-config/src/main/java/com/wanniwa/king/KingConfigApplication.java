package com.wanniwa.king;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class KingConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(KingConfigApplication.class, args);
    }

}
