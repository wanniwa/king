package com.wanniwa.king.blog.controller;

import com.wanniwa.king.admin.api.dto.UserInfo;
import com.wanniwa.king.admin.api.feign.SysUserClient;
import com.wanniwa.king.common.core.utils.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
@AllArgsConstructor
public class BlogController {
    private final SysUserClient sysUserClient;

    @GetMapping("/test")
    private R<UserInfo> test(Integer id) {
        return sysUserClient.info(id);
    }

}
