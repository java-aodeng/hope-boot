package com.hope.controller;

import com.hope.model.dto.User;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 21:12
 **/
@RestController
public class TestController {
    public static void main(String[] args) {
        User user=new User("admin","123456");
        System.out.println(user.getUsername()+user.getPassword());
    }
}
