package com.hope.controller;

import com.github.pagehelper.PageInfo;
import com.hope.model.beans.SysRole;
import com.hope.model.dto.Role;
import com.hope.model.vo.RoleConditionVo;
import com.hope.service.SysRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleControllerTest {

    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void list(RoleConditionVo vo) {
        PageInfo<SysRole> pageInfo=sysRoleService.findPageBreakByCondition(vo);
    }
}