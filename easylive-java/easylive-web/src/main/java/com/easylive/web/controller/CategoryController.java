package com.easylive.web.controller;

import com.easylive.entity.vo.ResponseVO;
import com.easylive.service.CategoryInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/category")
public class CategoryController extends ABaseController{
    @Resource
    private CategoryInfoService categoryInfoService;//注入分类服务类

    @RequestMapping("/loadAllCategory")
    public ResponseVO loadAllCategory(){
        return getSuccessResponseVO(categoryInfoService.getAllCategoryList());
    }


}
