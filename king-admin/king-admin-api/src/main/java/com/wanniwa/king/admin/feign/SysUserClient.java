package com.wanniwa.king.admin.feign;

import cn.hutool.system.UserInfo;
import com.wanniwa.king.admin.entity.SysUser;
import com.wanniwa.king.admin.feign.fallback.SysUserFallback;
import com.wanniwa.king.common.core.constant.ClientNameConstants;
import com.wanniwa.king.common.core.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        value = ClientNameConstants.ADMIN_CLIENT,
        fallback = SysUserFallback.class
)
public interface SysUserClient {
    /**
     * 通过用户名查询用户、角色信息
     *
     * @param username 用户名
     * @return R
     */
    @GetMapping("/user/info/{username}")
    R<SysUser> info(@PathVariable("username") String username);


}
