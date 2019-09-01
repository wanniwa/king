package com.wanniwa.king.auth.controller;

import com.wanniwa.king.auth.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    //@GetMapping("/buyer")
    //public R buyer(String openId, HttpServletResponse response) {
    //    //openId和数据库比对
    //    UserInfo userInfo = userInfoService.findByOpenId(openId);
    //    if (userInfo == null) {
    //        return R.error(ResultEnum.LOGIN_ERROR);
    //    }
    //    //判断角色
    //    if (RoleEnum.BUYER.getCode() != userInfo.getRole()) {
    //        return R.error(ResultEnum.ROLE_ERROR);
    //    }
    //    //cookie里设置openId
    //    CookieUtil.set(response, CookieConstant.OPENID, openId, CookieConstant.EXPIRE);
    //    return R.ok();
    //}
    //
    //@GetMapping("/seller")
    //public R seller(String openId, HttpServletRequest request,HttpServletResponse response) {
    //    Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
    //    if (cookie != null && StrUtil.isNotEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {
    //        return R.ok();
    //    }
    //    //openId和数据库比对
    //    UserInfo userInfo = userInfoService.findByOpenId(openId);
    //    if (userInfo == null) {
    //        return R.error(ResultEnum.LOGIN_ERROR);
    //    }
    //    //判断角色
    //    if (RoleEnum.SELLER.getCode() != userInfo.getRole()) {
    //        return R.error(ResultEnum.ROLE_ERROR);
    //    }
    //    //redis设置key=UUID
    //    String token = UUID.randomUUID().toString();
    //    int expire = CookieConstant.EXPIRE;
    //    stringRedisTemplate.opsForValue().set(
    //            String.format(RedisConstant.TOKEN_TEMPLATE, token),
    //            openId, expire, TimeUnit.SECONDS
    //    );
    //    //cookie里设置openId
    //    CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);
    //    return R.ok();
    //}
}
