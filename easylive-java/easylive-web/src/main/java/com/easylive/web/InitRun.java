package com.easylive.web;

import com.easylive.component.EsSearchComponent;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/*初始化*/
@Component
public class InitRun implements ApplicationRunner {
    @Resource
    private EsSearchComponent esSearchComponent;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        esSearchComponent.createIndex();
    }
}
