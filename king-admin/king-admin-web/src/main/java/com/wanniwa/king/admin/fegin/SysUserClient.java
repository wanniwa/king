package com.wanniwa.king.admin.fegin;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wanniwa.king.admin.dto.UserInfo;
import com.wanniwa.king.admin.entity.SysUser;
import com.wanniwa.king.admin.feign.ISysUserClient;
import com.wanniwa.king.admin.service.SysUserService;
import com.wanniwa.king.common.core.utils.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class SysUserClient implements ISysUserClient {
    private SysUserService sysUserService;

    @Override
    @GetMapping("/info/{username}")
    public R<UserInfo> info(@PathVariable("username") String username) {
        UserInfo userInfo = sysUserService.getUserInfoByUsername(username);
        if (userInfo == null) {
            return R.error(String.format("用户:%s 信息为空", username));
        }
        return R.ok(userInfo);
    }
}
