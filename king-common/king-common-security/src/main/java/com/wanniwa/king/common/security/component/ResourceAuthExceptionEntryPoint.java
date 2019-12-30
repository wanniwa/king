package com.wanniwa.king.common.security.component;

import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanniwa.king.common.core.constant.CommonConstants;
import com.wanniwa.king.common.core.utils.R;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Slf4j
@Component
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {
	/**
	 * ObjectMapper 是一个使用 Swift 语言编写的数据模型转换框架，我们可以方便的将模型对象转换为JSON，或者JSON生成相应的模型类
	 */
	@Autowired
	private  ObjectMapper objectMapper;

	@Override
	@SneakyThrows
	public void commence(HttpServletRequest request, HttpServletResponse response,
						 AuthenticationException authException) {
		response.setCharacterEncoding(CommonConstants.UTF8);
		R<String> result = R.fail();
		if (authException != null) {
			result.setMsg(authException.getMessage());
		}
		response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
		PrintWriter printWriter = response.getWriter();
		printWriter.append(objectMapper.writeValueAsString(result));
	}
}
