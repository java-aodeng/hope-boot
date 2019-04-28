[![log](https://i.loli.net/2018/12/30/5c28d022c657d.png)](https://github.com/java-aodeng/hope-plus)

> Hope-plus是一款权限管理系统。

<p align="center">
<a href="https://aodeng.cc"><img alt="Author" src="https://img.shields.io/badge/author-%E4%BD%8E%E8%B0%83%E5%B0%8F%E7%86%8A%E7%8C%AB-blue.svg"/></a>
<a href="https://github.com/java-aodeng/hope-plus"><img alt="JDK" src="https://img.shields.io/badge/JDK-1.8-orange.svg"/></a>
<a href="https://github.com/java-aodeng/hope-plus/blob/master/LICENSE"><img alt="license" src="https://img.shields.io/badge/license-MIT-red.svg"/></a>
<a href="https://jq.qq.com/?_wv=1027&k=574chhz"><img alt="QQ群" src="https://img.shields.io/badge/chat-%E4%BD%8E%E8%B0%83%E5%B0%8F%E7%86%8A%E7%8C%ABQQ%E7%BE%A4-yellow.svg"/></a>
<a href="https://t.me/joinchat/LSsyBxVKLGEkF5MtIhg6TQ"><img alt="Telegram群组" src="https://img.shields.io/badge/telegram-%E4%BD%8E%E8%B0%83%E5%B0%8F%E7%86%8A%E7%8C%AB--%E5%AE%98%E6%96%B9%E9%83%A8%E8%90%BD-orange.svg"/></a>
<a href="https://github.com/java-aodeng/hope-plus"><img alt="star" src="https://img.shields.io/github/stars/java-aodeng/hope-plus.svg?label=Stars&style=social"/></a>
<a href="https://github.com/java-aodeng/hope-plus/releases"><img alt="发行版本" src="https://img.shields.io/badge/release-%E5%8F%91%E8%A1%8C%E7%89%88%E6%9C%AC-red.svg"/></a>
</p>

------------------------------

🇨🇳简体中文 | 🇺🇸[English](./README-EN.md) | [更新日志](https://github.com/java-aodeng/hope-plus/commits/master) | 版本:label:[0.7.0](https://github.com/java-aodeng/hope-plus)

## 简介：

**Hope-plus**是我学习过程中诞生的，算我学习的结晶吧。主语言[java]

>项目地址： https://github.com/java-aodeng/hope-plus

## 模块划分

| 模块         | 释义                      |    
| ---------- | ----------------------- |
| hope-admin  | 后台管理模块 |      
| hope-core  | 核心业务类模块 |    
| hope-framework | 框架模块,提供数据操作,工具处理,通用Mapper,通用Service等 |
| hope-sso-server | 单点登录-认证中心模块，支持集群 |
| hope-generator | 代码生成模块-提供sql生成代码 |
| hope-flyway | 数据库版本管理工具模块 |

## 使用说明(请仔细看,看不懂也不要来问我哦！！！)

```bash
# 1.使用命令拉取代码：
    git clone https://github.com/java-aodeng/hope-plus.git 
# 2.创建数据库（取名）：hope， 字符集：utf8mb4;（注意：只需要你创建数据库即可，字符集不是utf8，而是utf8mb4）
# 3.使用IDEA导入该项目
# 4.修改配置
    A.打开hope-flyway模块，配置数据库连接:
        spring:
          datasource:
              url: 你的数据库地址
              username: 你的数据库用户名
              password: 你的数据库密码
    B.打开hope-admin模块，配置数据库连接和redis连接:
        a.数据库配置(可搜索datasource或定位到L.17)
        b.redis配置(可搜索redis或定位到L.29,注：该项目必须安装redis服务才能启动)
# 5.运行项目(数据库管理模块)    
    a.直接运行hope-flyway目录下的HopeFlywayApplication.java
    b.查看数据库是否自动生成表和初始化的数据
# 6.运行项目(后台管理模块)
    a.直接运行hope-admin目录下的HopeAdminApplication.java
    b.浏览器访问：http://127.0.0.1:8886
# 7.运行项目(单点登录模块)
    a.直接运行hope-sso-server目录下的HopeSsoServerApplication.java
    b.浏览器访问：http://127.0.0.1:8887
# 8.运行项目(代码生成模块)
    a.直接运行hope-generator目录下的HopeGeneratorApplication.java
    b.浏览器访问：http://127.0.0.1:8888
```

## 账号

后台登录：账号：admin 密码：123456

资源监控：账号：hope-druid 密码：hope-druid

后端API管理：localhost:8886/swagger-ui.html

## 感谢：

Hope-plus的诞生离不开下面这些项目（取之开源，用之开源）：

- [Spring Boot](https://github.com/spring-projects/spring-boot)：核心框架
- [Apache Shiro](https://github.com/apache/shiro)：权限框架
- [Redis](https://github.com/antirez/redis)：缓存框架
- [Thymeleaf](https://github.com/thymeleaf/thymeleaf)：模板引擎
- [MyBatis](https://github.com/mybatis/mybatis-3)：用于Java的MyBatis SQL Mapper框架
- [PageHelper](https://github.com/pagehelper/Mybatis-PageHelper)：分页插件
- [tk.mybatis](https://github.com/abel533/Mapper)：通用Mapper
- [alibaba/druid](https://github.com/alibaba/druid)：数据库连接池
- [alibaba/fastjson](https://github.com/alibaba/fastjson)：用于Java的快速JSON解析器/生成器
- [shiro-redis](https://github.com/alexxiyang/shiro-redis)：一个可以由shiro使用的redis缓存工具
- [Lombok](https://www.projectlombok.org/)：让代码更简洁
- [Hutool](https://github.com/looly/hutool)：一个Java工具包，也只是一个工具包，它帮助我们简化每一行代码，减少每一个方法，让Java语言也可以“甜甜的”
- [Bootstrap](https://github.com/twbs/bootstrap.git)：使用最广泛的前端 ui 框架
- [JQuery](https://github.com/jquery/jquery.git)：使用最广泛的 JavaScript 框架
- [Layer](https://github.com/sentsin/layer.git)：弹出层组件
- [kaptcha](https://github.com/penggle/kaptcha)：Google验证码
- [jrebel](https://zeroturnaround.com/software/jrebel/)：热部署
- [swagger](https://github.com/swagger-api/swagger-ui)：Swagger（丝袜哥）是世界上最流行的 API 表达工具。

## 捐赠

**请作者吃个肉夹馍 :) 作者博客：[https://aodeng.cc](https://aodeng.cc)（我们不改变世界，我们跟着世界改变）微信公众号：低调小熊猫**

![](https://i.loli.net/2018/12/31/5c29d3b18826d.png)

## 后续功能

- [x] 整合 **RESTful APIs 构建成前后端分离项目**
- [x] 整合 **Swagger接口管理**
- [X] 整合 **sso单点登录**
- [ ] 整合 **数据权限分离**
- [ ] 整合 **支付模块**
- [ ] 整合 **定时服务系统**
- [ ] 整合 **授权登录系统**
- [ ] 整合 **J2Cache缓存**
- [X] 整合 **代码生成**
- [ ] 整合 **国际化I18N**
- [ ] 重构 **前端暂定Vue**
- [ ] 重构 **项目使用微服务架构（待定）**

## 界面展示

![](https://i.loli.net/2018/12/31/5c29cfa636b7a.png)
![](https://i.loli.net/2018/12/31/5c29cfe24ef3c.png)
![](https://i.loli.net/2018/12/31/5c29d0f3e7f89.png)
![](https://i.loli.net/2018/12/31/5c29d08c40128.png)
![](https://i.loli.net/2018/12/31/5c29d08c4b71f.png)
![](https://i.loli.net/2018/12/31/5c29d188d68fe.png)
![](https://i.loli.net/2018/12/31/5c29d188daba1.png)
![](https://i.loli.net/2018/12/31/5c29d1ce428a5.png)
![](https://i.loli.net/2018/12/31/5c29d1ce60893.png)
![](https://i.loli.net/2018/12/31/5c29d1ce68318.png)
![](https://i.loli.net/2018/12/31/5c29d1ce66275.png)
![](https://i.loli.net/2018/12/31/5c29d1ce86b5e.png)
![](https://i.loli.net/2019/03/17/5c8e0bd37267a.png)