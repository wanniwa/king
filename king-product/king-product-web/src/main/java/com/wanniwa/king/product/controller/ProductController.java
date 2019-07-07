package com.wanniwa.king.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wanniwa.king.common.utils.Result;
import com.wanniwa.king.product.dto.CartDTO;
import com.wanniwa.king.product.entity.ProductCategory;
import com.wanniwa.king.product.entity.ProductInfo;
import com.wanniwa.king.product.service.ProductCategoryService;
import com.wanniwa.king.product.service.ProductInfoService;
import com.wanniwa.king.product.vo.ProductInfoVO;
import com.wanniwa.king.product.vo.ProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;


    /**
     * 1. 查询所有在架的商品
     * 2. 获取类目type列表
     * 3. 查询类目
     * 4. 构造数据
     */
    @GetMapping("/list")
    //@CrossOrigin(allowCredentials = "true") //跨域
    public Result<List<ProductVO>> list() {
        //1. 查询所有在架的商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        //2. 获取类目type列表
        Set<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toSet());

        //3. 从数据库查询类目
        List<ProductCategory> categoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);

        //4. 构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory: categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo: productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return Result.ok(productVOList);
    }

    /**
     * 获取商品列表(给订单服务)
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productIdList) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LambdaQueryWrapper <ProductInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ProductInfo::getProductId, productIdList);
        return productInfoService.list(queryWrapper);
    }

    @PostMapping("/decreaseStock")
    public Result decreaseStock(@RequestBody List<CartDTO> cartDTOList) {
        productInfoService.decreaseStock(cartDTOList);
        return Result.ok();
    }
}