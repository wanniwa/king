package com.wanniwa.king.admin.feign.fallback;

import com.wanniwa.king.admin.entity.SysUser;
import com.wanniwa.king.admin.feign.ISysUserClient;
import com.wanniwa.king.common.core.utils.R;
import org.springframework.stereotype.Component;

@Component
public class ISysUserClientFallback implements ISysUserClient {

    @Override
    public R<SysUser> info(String username) {
        return R.error();
    }
}
