package com.wanniwa.king.admin.api.feign.fallback;

import com.wanniwa.king.admin.api.dto.UserInfo;
import com.wanniwa.king.admin.api.feign.SysUserClient;
import com.wanniwa.king.common.core.utils.R;
import org.springframework.stereotype.Component;

//@Component
//public class SysUserClientFallback implements SysUserClient {
//
//    @Override
//    public R<UserInfo> info(String username) {
//        return R.fail();
//    }
//}
