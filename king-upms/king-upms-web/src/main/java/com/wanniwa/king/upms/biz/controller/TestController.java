package com.wanniwa.king.upms.biz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping
    public List<ServiceInstance> setDiscoveryClient() {
        List<ServiceInstance> instances = this.discoveryClient.getInstances("king-admin");
        return instances;
    }


}
