package com.wanniwa.king.common.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanniwa.king.common.core.utils.R;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 退出登录成功处理
 *
 * @author wanniwa
 * @date 2020/1/13
 */
public class KingLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.write(objectMapper.writeValueAsString(R.ok()));
        out.flush();
        out.close();
    }
}
