package com.wanniwa.king.auth.controller;

import com.wanniwa.king.auth.entity.UserInfo;
import com.wanniwa.king.auth.service.UserInfoService;
import com.wanniwa.king.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanniwa
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    @GetMapping("/findByOpenId")
    public Result<UserInfo> findByOpenId(String openId) {
        return Result.ok(userInfoService.findByOpenId(openId));
    }
}
