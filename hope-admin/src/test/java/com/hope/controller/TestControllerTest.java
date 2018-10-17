package com.hope.controller;

import com.hope.beans.SysResource;
import com.hope.service.SysResourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest

public class TestControllerTest{

    private static final Logger log= LoggerFactory.getLogger(TestControllerTest.class);

    @Autowired
    private SysResourceService sysResourceService;

    @Test
    public void test(){
        List<SysResource> list=sysResourceService.listAll();
        log.info("[资源名字]-[{}]",list.get(1).getName());
    }
}