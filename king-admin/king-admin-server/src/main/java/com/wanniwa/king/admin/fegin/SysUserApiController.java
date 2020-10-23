package com.wanniwa.king.admin.fegin;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wanniwa.king.admin.api.dto.UserInfo;
import com.wanniwa.king.admin.api.entity.SysUser;
import com.wanniwa.king.admin.api.feign.SysUserClient;
import com.wanniwa.king.admin.service.SysUserService;
import com.wanniwa.king.common.core.utils.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class SysUserApiController  {
    private final SysUserService sysUserService;

    @GetMapping("/info/{username}")
    public R<UserInfo> info(@PathVariable("username") String username) {
        SysUser sysUser = sysUserService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUsername, username));
        if (sysUser == null) {
            return R.fail(String.format("用户:%s不存在", username));
        }
        UserInfo userInfo = sysUserService.getUserInfo(sysUser);
        return R.ok(userInfo);
    }

    @GetMapping("/sys/user")
    public R<UserInfo> info(Integer userId) {
        SysUser user = sysUserService.getById(userId);
        if (user == null) {
            return R.fail("获取当前用户信息失败");
        }
        return R.ok(sysUserService.getUserInfo(user));
    }

}
