package com.wanniwa.king.auth.controller;

import com.wanniwa.king.auth.constant.CookieConstant;
import com.wanniwa.king.auth.entity.UserInfo;
import com.wanniwa.king.auth.service.UserInfoService;
import com.wanniwa.king.common.enums.ResultEnum;
import com.wanniwa.king.common.enums.RoleEnum;
import com.wanniwa.king.common.utils.CookieUtil;
import com.wanniwa.king.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author wanniwa
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserInfoService userInfoService;
    @GetMapping("/buyer")
    public Result buyer (String openId, HttpServletResponse response){
        //openId和数据库比对
        UserInfo userInfo = userInfoService.findByOpenId(openId);
        if (userInfo==null) {
            return Result.error(ResultEnum.LOGIN_ERROR);
        }
        //判断角色
        if (RoleEnum.BUYER.getCode() == userInfo.getRole()) {
            return Result.error(ResultEnum.ROLE_ERROR);
        }
        //cookie里设置openId
        CookieUtil.set(response, CookieConstant.OPENID,openId,CookieConstant.EXPIRE);
        return Result.ok();
    }
    @GetMapping("/seller")
    public Result seller (String openId, HttpServletResponse response){
        //openId和数据库比对
        UserInfo userInfo = userInfoService.findByOpenId(openId);
        if (userInfo==null) {
            return Result.error(ResultEnum.LOGIN_ERROR);
        }
        //判断角色
        if (RoleEnum.BUYER.getCode() == userInfo.getRole()) {
            return Result.error(ResultEnum.ROLE_ERROR);
        }
        return Result.ok();
    }
}
