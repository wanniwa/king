package com.wanniwa.king.auth.controller;

import com.wanniwa.king.auth.constant.CookieConstant;
import com.wanniwa.king.auth.constant.RedisConstant;
import com.wanniwa.king.auth.entity.UserInfo;
import com.wanniwa.king.auth.service.UserInfoService;
import com.wanniwa.king.common.enums.ResultEnum;
import com.wanniwa.king.common.enums.RoleEnum;
import com.wanniwa.king.common.utils.CookieUtil;
import com.wanniwa.king.common.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author wanniwa
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/buyer")
    public Result buyer(String openId, HttpServletResponse response) {
        //openId和数据库比对
        UserInfo userInfo = userInfoService.findByOpenId(openId);
        if (userInfo == null) {
            return Result.error(ResultEnum.LOGIN_ERROR);
        }
        //判断角色
        if (RoleEnum.BUYER.getCode() != userInfo.getRole()) {
            return Result.error(ResultEnum.ROLE_ERROR);
        }
        //cookie里设置openId
        CookieUtil.set(response, CookieConstant.OPENID, openId, CookieConstant.EXPIRE);
        return Result.ok();
    }

    @GetMapping("/seller")
    public Result seller(String openId, HttpServletRequest request,HttpServletResponse response) {
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null && StringUtils.isNotEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {
            return Result.ok();
        }
        //openId和数据库比对
        UserInfo userInfo = userInfoService.findByOpenId(openId);
        if (userInfo == null) {
            return Result.error(ResultEnum.LOGIN_ERROR);
        }
        //判断角色
        if (RoleEnum.SELLER.getCode() != userInfo.getRole()) {
            return Result.error(ResultEnum.ROLE_ERROR);
        }
        //redis设置key=UUID
        String token = UUID.randomUUID().toString();
        int expire = CookieConstant.EXPIRE;
        stringRedisTemplate.opsForValue().set(
                String.format(RedisConstant.TOKEN_TEMPLATE, token),
                openId, expire, TimeUnit.SECONDS
        );
        //cookie里设置openId
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);
        return Result.ok();
    }
}
