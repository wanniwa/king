package com.wanniwa.king.common.security.component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lengleng
 * @date 2020-03-11
 * <p>
 * 资源服务器对外直接暴露URL,如果设置contex-path 要特殊处理
 */
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "security.oauth2.ignore")
@Getter
@Setter
public class SecurityIgnoreUrlProperties {

	private List<String> urls = new ArrayList<>();



}
