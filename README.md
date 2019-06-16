<h1 align="center"><a href="https://github.com/java-aodeng" target="_blank">Hope-Boot</a></h1>

<p align="center">
<a href="https://aodeng.cc"><img alt="Author" src="https://img.shields.io/badge/author-%E4%BD%8E%E8%B0%83%E5%B0%8F%E7%86%8A%E7%8C%AB-blue.svg"/></a>
<a href="https://github.com/hope-for/hope-boot"><img alt="JDK" src="https://img.shields.io/badge/JDK-1.8-orange.svg"/></a>
<a href="https://github.com/hope-for/hope-boot/blob/master/LICENSE"><img alt="license" src="https://img.shields.io/github/license/java-aodeng/hope.svg?style=flat-square"/></a>
<a href="https://jq.qq.com/?_wv=1027&k=574chhz"><img alt="QQ群" src="https://img.shields.io/badge/chat-%E4%BD%8E%E8%B0%83%E5%B0%8F%E7%86%8A%E7%8C%ABQQ%E7%BE%A4-yellow.svg"/></a>
<a href="https://t.me/joinchat/LSsyBxVKLGEkF5MtIhg6TQ"><img alt="Telegram" src="https://img.shields.io/badge/telegram-%E4%BD%8E%E8%B0%83%E5%B0%8F%E7%86%8A%E7%8C%AB--%E5%AE%98%E6%96%B9%E9%83%A8%E8%90%BD-orange.svg"/></a>
<a href="https://github.com/hope-for/hope-boot"><img alt="star" src="https://img.shields.io/github/stars/hope-for/hope-boot.svg?label=Stars&style=social"/></a>
<a href="https://github.com/hope-for/hope-boot/releases"><img alt="发行版本" src="https://img.shields.io/badge/release-%E5%8F%91%E8%A1%8C%E7%89%88%E6%9C%AC-red.svg"/></a>
</p>

# 简介

>:pencil2:基于𝓢𝓹𝓻𝓲𝓷𝓰 𝓑𝓸𝓸𝓽开发的一款现代化后台管理系统

> 基于Spring Boot 2.x开发 整合：Spring Boot 2.x/Apache Shiro+JWT/Spring Data JPA+Mybatis+Tk.Mybatis+PageHelper/Redis/Flyway/Swagger/SSO/Thymeleaf 动态权限管理 AES+MD5+盐加解密 代码生成 日志记录 Google验证码登录 单点登录 定时任务 项目地址：https://github.com/hope-for/hope-boot 主语言[java]

> 这是我的第一个开源作品，欢迎star。第二个开源作品地址：https://github.com/hope-for/hope-cloud
------------------------------

🇨🇳简体中文 | 🇺🇸[English](./README-2.md) | [更新日志](https://github.com/hope-for/hope-boot/commits/master) | 当前版本:label:[0.0.4](https://github.com/hope-for/hope-boot)

# 模块划分

| 模块         | 释义                      |    
| ---------- | ----------------------- |
| hope-admin  | 后台管理模块 |      
| hope-core  | 核心业务类模块 |    
| hope-framework | 框架模块,提供数据操作,工具处理,通用Mapper,通用Service等 |
| hope-sso-server | 单点登录-认证中心模块，支持集群 |
| hope-generator | 代码生成模块-提供sql生成代码 |
| hope-flyway | 数据库版本管理工具模块 |
| hope-quartz | 定时任务模块 |

# 使用说明

[https://github.com/hope-for/hope-boot/wiki](https://github.com/hope-for/hope-boot/wiki)

# 账号

后台登录：账号：admin 密码：123456

资源监控：账号：hope-druid 密码：hope-druid

后端API文档：http://localhost:8886/swagger-ui.html

# 感谢

Hope-boot的诞生离不开下面这些项目（取之开源，用之开源）：

- [Spring Boot](https://github.com/spring-projects/spring-boot)：核心框架
- [Apache Shiro](https://github.com/apache/shiro)：权限框架
- [Redis](https://github.com/antirez/redis)：缓存框架
- [Thymeleaf](https://github.com/thymeleaf/thymeleaf)：模板引擎
- [MyBatis](https://github.com/mybatis/mybatis-3)：用于Java的MyBatis SQL Mapper框架
- [jpa](https://github.com/spring-projects/spring-data-jpa)：我也不知道为什么要把这个加上
- [PageHelper](https://github.com/pagehelper/Mybatis-PageHelper)：分页插件
- [tk.mybatis](https://github.com/abel533/Mapper)：通用Mapper
- [alibaba/druid](https://github.com/alibaba/druid)：数据库连接池
- [alibaba/fastjson](https://github.com/alibaba/fastjson)：用于Java的快速JSON解析器/生成器
- [Apache Maven](https://maven.apache.org/):依赖管理
- [shiro-redis](https://github.com/alexxiyang/shiro-redis)：一个可以由shiro使用的redis缓存工具
- [Lombok](https://www.projectlombok.org/)：让代码更简洁
- [Hutool](https://github.com/looly/hutool)：一个Java工具包，也只是一个工具包，它帮助我们简化每一行代码，减少每一个方法，让Java语言也可以“甜甜的”
- [Bootstrap](https://github.com/twbs/bootstrap.git)：使用最广泛的前端 ui 框架
- [JQuery](https://github.com/jquery/jquery.git)：使用最广泛的 JavaScript 框架
- [Layer](https://github.com/sentsin/layer.git)：弹出层组件
- [kaptcha](https://github.com/penggle/kaptcha)：Google验证码
- [jrebel](https://zeroturnaround.com/software/jrebel/)：热部署
- [swagger](https://github.com/swagger-api/swagger-ui)：Swagger（丝袜哥）是世界上最流行的 API 表达工具。
- [flyway](https://github.com/flyway/flyway)：Flyway by Boxfuse • Database Migrations Made Easy.
- [xxl-sso](https://github.com/xuxueli/xxl-sso/)：A distributed single-sign-on framework.
- [xxl-code](https://github.com/xuxueli/xxl-code-generator/)：A code generator for "controller/service/dao/mybatis/model" layer.
- [quartz](http://www.quartz-scheduler.org/)：定时任务

# 捐赠

**请作者吃个肉夹馍 :) 作者博客：[https://aodeng.cc](https://aodeng.cc)（我们不改变世界，我们跟着世界改变）微信公众号：低调小熊猫**

![](https://i.loli.net/2018/12/31/5c29d3b18826d.png)

# 界面展示

## 后台登录

![](https://i.loli.net/2019/05/01/5cc930100d298.png)

## 首页

![](https://i.loli.net/2019/05/01/5cc9300612ca5.png)

## 权限管理 -用户 -角色 -资源

![](https://i.loli.net/2019/05/01/5cc9300d45a92.png)
![](https://i.loli.net/2019/05/01/5cc9300d62006.png)
![](https://i.loli.net/2019/05/01/5cc9300d8fb70.png)

## 系统工具 -资源监控

![](https://i.loli.net/2019/05/01/5cc9300bc316d.png)
![](https://i.loli.net/2019/05/01/5cc93003ef3d6.png)

## 文档

![](https://i.loli.net/2019/05/01/5cc932128cc07.png)

## 我的链接：

- [我的个人博客](https://aodeng.cc)
- [我的微信公众号(低调小熊猫)](https://mp.weixin.qq.com/s/l5t8WSCG_-shiD4BPpLYiw) 
- [hope-boot使用GPL-v3.0协议开源](https://github.com/hope-for/hope-boot/blob/master/LICENSE)
- [低调小熊猫QQ群](https://jq.qq.com/?_wv=1027&k=5y4H7Nz) 
- [低调小熊猫Telegram群](https://t.me/joinchat/LSsyBxVKLGEkF5MtIhg6TQ)
- [我的Gitee链接](https://gitee.com/java-aodeng)
- [我的Github链接](https://github.com/java-aodeng)
- [我的开源组织(欢迎志同道合的朋友加入)](https://github.com/hope-for)
- 联系我的邮箱：java@aodeng.cc 申请加入hope-for组织也是发送到该邮箱哦，记得带上你的GitHub账号