package com.wanniwa.king.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author 73493
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {
    //超时配置降级
    //@HystrixCommand(commandProperties = {
    //        @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    //})
    //@HystrixCommand(commandProperties = {
    //        @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
    //        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
    //        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //休眠时间窗口
    //        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    //})
    @HystrixCommand
    @GetMapping("/getProductInfoList")
    public String getProductInfoList(@RequestParam("number") Integer number) {
        if (number % 2 == 0) {
            return "success";
        }
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://localhost:9003/product/listForOrder",
                Arrays.asList("157875196366160022"),
                String.class);
    }

    public String fallback() {

        return "太拥挤了,请稍后再试";
    }

    public String defaultFallback() {

        return "defaultFallback太拥挤了,请稍后再试";
    }
}
