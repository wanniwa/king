package com.wanniwa.king.admin.controller;

import com.wanniwa.king.common.core.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @GetMapping("/list")
    //@PreAuthorize("hasAuthority('product:view')")
    public R<List<String>> list() {
        List<String> list = new ArrayList<>();
        list.add("眼镜");
        list.add("格子衬衣");
        list.add("双肩包");
        return R.ok(list);
    }
}
