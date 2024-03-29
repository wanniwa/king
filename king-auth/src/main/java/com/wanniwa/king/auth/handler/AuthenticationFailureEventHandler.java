package com.wanniwa.king.auth.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationFailureEventHandler implements ApplicationListener<AbstractAuthenticationFailureEvent> {

    @Override
    public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
        Authentication authentication = event.getAuthentication();
        log.info("用户：【{}】登录失败", authentication.getPrincipal());
    }
}
