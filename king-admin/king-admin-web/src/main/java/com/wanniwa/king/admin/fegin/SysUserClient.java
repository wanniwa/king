package com.wanniwa.king.admin.fegin;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wanniwa.king.admin.entity.SysUser;
import com.wanniwa.king.admin.feign.ISysUserClient;
import com.wanniwa.king.admin.service.SysUserService;
import com.wanniwa.king.common.core.utils.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SysUserClient implements ISysUserClient {
    private SysUserService sysUserService;


    @Override
    @GetMapping("/user/info/{username}")
    public R<SysUser> loadUserByUsername(@PathVariable("username") String username) {
        SysUser sysUser = sysUserService.getOne(Wrappers.<SysUser>query()
                .lambda().eq(SysUser::getUsername, username));
        if (sysUser == null) {
            return R.error(String.format("用户:%s 信息为空", username));
        }
        return R.ok(sysUser);
    }
}
