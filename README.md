[![log](https://i.loli.net/2018/12/30/5c28d022c657d.png)](https://github.com/java-aodeng/hope-plus)

<h1><a href="#">Hope-plus</a></h1>

🇨🇳简体中文 | 🇺🇸[English](./README-EN.md) | [更新日志](https://github.com/java-aodeng/hope-plus/commits/master)

[![author](https://img.shields.io/badge/author-%E4%BD%8E%E8%B0%83%E5%B0%8F%E7%86%8A%E7%8C%AB-blue.svg)](https://aodeng.cc)
[![JDK](https://img.shields.io/badge/JDK-1.8-orange.svg)](https://github.com/java-aodeng/hope-plus)
[![license](https://img.shields.io/badge/license-GPL--3.0-red.svg)](https://github.com/java-aodeng/hope-plus/blob/master/LICENSE)
[![QQ群](https://img.shields.io/badge/chat-%E4%BD%8E%E8%B0%83%E5%B0%8F%E7%86%8A%E7%8C%ABQQ%E7%BE%A4-yellow.svg)](https://jq.qq.com/?_wv=1027&k=574chhz)
[![Telegram群组](https://img.shields.io/badge/telegram-%E4%BD%8E%E8%B0%83%E5%B0%8F%E7%86%8A%E7%8C%AB--%E5%AE%98%E6%96%B9%E9%83%A8%E8%90%BD-orange.svg)](https://t.me/joinchat/LSsyBxVKLGEkF5MtIhg6TQ)
[![star](https://img.shields.io/github/stars/java-aodeng/hope-plus.svg?label=Stars&style=social)](https://github.com/java-aodeng/hope-plus)
[![发行版本](https://img.shields.io/badge/release-0.2.0-green.svg)](https://github.com/java-aodeng/hope-plus/releases)



## 简介：

>Hope-plus是一款nice的权限管理系统 https://github.com/java-aodeng/hope-plus

>基于Springboot2开发，整合使用mybatis+shiro+redis+thymeleaf+maven等实用技术。页面模板使用了界面美观的h+，内置诸多的强大的可以重新组合的UI组件，jQuery插件等。可以用于所有的Web应用程序，如网站管理系统后台，CMS，CRM，OA等等。当然，您也可以进行深度定制，做出更强的系统。

>如果觉得不错，给个Star支持一下吧！你的Star，我的动力！
#### 模块划分

| 模块         | 释义                      |    
| ---------- | ----------------------- |
| hope-admin  | 后台管理模块 |      
| hope-core  | 核心业务类模块 |    
| hope-framework | 框架模块,提供数据操作,工具处理,通用Mapper,通用Service等 |

#### 使用说明
```bash
# 1.使用命令拉取代码：
git clone https://github.com/java-aodeng/hope-plus.git 
# 2.使用IDEA导入本项目
# 3.新建数据库create database hope; 导入数据库文件docs/db/hope.sql
# 4.修改(hope-admin/resources/application.yaml)配置文件
    a.数据库配置(可搜索datasource或定位到L.17)
    b.redis配置(可搜索redis或定位到L.29,注：该项目必须安装redis服务才能启动)
# 5.运行项目(方式很多，推荐最简单的一种)
    a.直接运行hope-admin目录下的HopeAdminApplication.java
# 6.浏览器访问：
http://127.0.0.1:8886
```
#### 账号
后台登录：账号：admin 密码：123456

资源监控：账号：hope-druid 密码：hope-druid

API地址：localhost:8886/swagger-ui.html
### 感谢：
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
![](https://i.loli.net/2019/02/13/5c63cd02c39e2.png)