package com.wanniwa.king.product.service.impl;

import com.google.common.collect.Lists;
import com.wanniwa.king.product.entity.ProductCategory;
import com.wanniwa.king.product.service.ProductCategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    ProductCategoryService productCategoryService;
    @Test
    public void findByCategoryTypeIn() {
        Set<Integer> integers = new HashSet<>();
        integers.add(1);
        List<ProductCategory> list = productCategoryService.findByCategoryTypeIn(integers);
        Assert.assertTrue(list.size()>0);
    }
}