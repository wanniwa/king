package com.wanniwa.king.order.controller;

import com.netflix.discovery.converters.Auto;
import com.wanniwa.king.order.client.ProductClient;
import com.wanniwa.king.order.dto.CartDTO;
import com.wanniwa.king.order.entity.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@RestController
@RefreshScope
public class ClientController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    //@Autowired
    //private RestTemplate restTemplate;
    @Autowired
    private ProductClient productClient;
    @Value("${girl.name}")
    private String name;

    @GetMapping("/getName")
    public String getName() {
        return name;
    }

    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        //1 第一种方式
        //RestTemplate restTemplate = new RestTemplate();
        //String s = restTemplate.getForObject("http://localhost:9001/msg", String.class);

        //第二种方式
        ServiceInstance serviceInstance = loadBalancerClient.choose("KING-PRODUCT");
        serviceInstance.getHost();
        String format = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort() + "/msg");
        RestTemplate restTemplate = new RestTemplate();
        String s = restTemplate.getForObject(format, String.class)+serviceInstance.getPort();
        //第三中方式
        //String s = restTemplate.getForObject("http://KING-PRODUCT/msg", String.class);
        //feign
        //String s = productClient.productMsg();
        return s;
    }

    @GetMapping("/getProductList")
    public List<ProductInfo> getProductList() {
        List<ProductInfo> productInfos = productClient.listForOrder(Arrays.asList("157875196366160022", "157875227953464068"));
        return productInfos;
    }

    @GetMapping("/productDecreaseStock")
    public String productDecreaseStock() {
        productClient.decreaseStock(Collections.singletonList(new CartDTO("164103465734242707", 2)));
        return "成功";
    }

}
