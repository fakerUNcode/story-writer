package com.easylive.web.controller;

import com.easylive.entity.vo.ResponseVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController extends ABaseController{

    @RequestMapping("/message/getNoReadCount")
    public ResponseVO getNoReadCount(){
        return getSuccessResponseVO(null);
    }



}
