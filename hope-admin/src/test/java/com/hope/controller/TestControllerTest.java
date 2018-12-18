package com.hope.controller;

import com.hope.model.beans.SysResource;
import com.hope.model.dto.Resource;
import com.hope.service.SysResourceService;
import com.hope.utils.UsingAesHopeUtil;
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
        List<SysResource> list=sysResourceService.selectAlls();
        List<SysResource> list1=sysResourceService.listAll();
        log.info("[资源名字]-[{}]",list.get(1).getName());
        log.info("[数据]-[{}]",list1.get(0).getName());
    }
    @Test
    public void test2(){

        try {
            String a=UsingAesHopeUtil.decrypt("eedqXGyUJwa/dFCLOZ+IYg==","admin2");
            System.out.println(a);
        }catch (Exception e){

        }
    }
}