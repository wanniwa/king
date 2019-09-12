package com.wanniwa.king.admin.feign;

import com.wanniwa.king.admin.dto.UserInfo;
import com.wanniwa.king.admin.entity.SysUser;
import com.wanniwa.king.admin.feign.fallback.ISysUserClientFallback;
import com.wanniwa.king.common.core.constant.ClientNameConstants;
import com.wanniwa.king.common.core.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        value = ClientNameConstants.ADMIN_CLIENT,
        fallback = ISysUserClientFallback.class,
        path = "/user"
)
public interface ISysUserClient {
    /**
     * 通过用户名查询用户、角色信息
     *
     * @param username 用户名
     * @return R
     */
    @GetMapping("/info/{username}")
    R<UserInfo> info(@PathVariable("username") String username);


}
