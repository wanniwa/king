package com.wanniwa.king.upms.api.feign;

import com.wanniwa.king.common.core.constant.ClientNameConstants;
import com.wanniwa.king.common.core.utils.R;
import com.wanniwa.king.upms.api.dto.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        value = ClientNameConstants.ADMIN_CLIENT
        //fallback = SysUserClientFallback.class
)
public interface SysUserClient {
    /**
     * 通过用户名查询用户、角色信息
     *
     * @param username 用户名
     * @return R
     */
    @GetMapping("/api/user/info/{username}")
    R<UserInfo> info(@PathVariable("username") String username);

    /**
     * 通过用户名ID查询用户、角色信息
     *
     * @param userId 用户ID
     * @return R
     */
    @GetMapping("/api/user/sys/user")
    R<UserInfo> info(@RequestParam("userId") Integer userId);
}
