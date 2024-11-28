package com.easylive.web.controller;

import com.easylive.entity.vo.ResponseVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController extends ABaseController{
    @RequestMapping("/video/getSearchKeywordTop")
    public ResponseVO getSearchKeywordTop(){
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/message/getNoReadCount")
    public ResponseVO getNoReadCount(){
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/uhome/getUserInfo")
    public ResponseVO getUserInfo(){
        return getSuccessResponseVO(null);
    }



    @RequestMapping("/video/getVideoRecommend")
    public ResponseVO getVideoRecommend(){
        return getSuccessResponseVO(null);
    }
}
