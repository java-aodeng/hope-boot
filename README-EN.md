[![log](https://github.com/java-aodeng/hope-plus/blob/master/docs/img/logo.png)](https://github.com/java-aodeng/hope-plus)

<h1><a href="#">Hope-plus 0.6.1</a></h1>

🇨🇳[简体中文](./README.md) | 🇺🇸English | [更新日志](https://github.com/java-aodeng/hope-plus/commits/master)

[![author](https://img.shields.io/badge/author-%E4%BD%8E%E8%B0%83%E5%B0%8F%E7%86%8A%E7%8C%AB-blue.svg)](https://aodeng.cc)
[![JDK](https://img.shields.io/badge/JDK-1.8-orange.svg)](https://github.com/java-aodeng/hope-plus)
[![license](https://img.shields.io/badge/license-MIT-red.svg)](https://github.com/java-aodeng/hope-plus/blob/master/LICENSE)
[![QQ群](https://img.shields.io/badge/chat-%E4%BD%8E%E8%B0%83%E5%B0%8F%E7%86%8A%E7%8C%ABQQ%E7%BE%A4-yellow.svg)](https://jq.qq.com/?_wv=1027&k=574chhz)
[![Telegram群组](https://img.shields.io/badge/telegram-%E4%BD%8E%E8%B0%83%E5%B0%8F%E7%86%8A%E7%8C%AB--%E5%AE%98%E6%96%B9%E9%83%A8%E8%90%BD-orange.svg)](https://t.me/joinchat/LSsyBxVKLGEkF5MtIhg6TQ)
[![star](https://img.shields.io/github/stars/java-aodeng/hope-plus.svg?label=Stars&style=social)](https://github.com/java-aodeng/hope-plus)
[![发行版本](https://img.shields.io/badge/release-%E5%8F%91%E8%A1%8C%E7%89%88%E6%9C%AC-red.svg)](https://github.com/java-aodeng/hope-plus/releases)

## brief introduction：

>Hope-plus is a nice privilege management system https://github.com/java-aodeng/hope-plus

>Based on Springboot2 development, integrated use of mybatis + Shiro + redis + thymeleaf + Maven and other practical technologies. Page template uses h+, which has beautiful interface, many powerful UI components that can be reconfigured, jQuery plug-ins and so on. It can be used in all Web applications, such as the background of website management system, CMS, CRM, OA and so on. Of course, you can also customize in depth to make a stronger system.

>If you think it's good, give Star support! Your Star, my Open Source Power!
#### Module partition

| Modular         | Interpretation                      |    
| ---------- | ----------------------- |
| hope-admin  | Background management module |      
| hope-core  | Core Business Class Module |    
| hope-framework | Framework module, providing data operation, tool processing, general Mapper, general service, etc. |

#### Instructions
```bash
# 1.Use command pull substitution code：
git clone https://github.com/java-aodeng/hope-plus.git 
# 2.Import this project using IDEA
# 3.New database create database hope; Import database files docs/db/hope.sql
# 4.modify(hope-admin/resources/application.yaml)configuration file
    a.Database Configuration(Searchable data source or location toL.17)
    b.redis Configuration(Searchable redis or location to L.29, Note: The project must install redis service to start)
# 5.Running projects (in many ways, the simplest is recommended)
    a.Run the hope-admin directory directlyHopeAdminApplication.java
# 6.Browser access:
http://127.0.0.1:8886
```
#### Account number
Backstage login：Account number：admin password：123456

Resource monitoring：Account number：hope-druid password：hope-druid

API address: localhost:8886/swagger-ui.html
### Thank：
Hope-plus was born out of the following projects（Open Source, Open Source for Use）：

- [Spring Boot](https://github.com/spring-projects/spring-boot)：Core framework
- [Apache Shiro](https://github.com/apache/shiro)：Permission framework
- [Redis](https://github.com/antirez/redis)：Caching framework
- [Thymeleaf](https://github.com/thymeleaf/thymeleaf)：template engine
- [MyBatis](https://github.com/mybatis/mybatis-3)：MyBatis SQL Mapper Framework for Java
- [PageHelper](https://github.com/pagehelper/Mybatis-PageHelper)：jPaginate
- [tk.mybatis](https://github.com/abel533/Mapper)：General Mapper
- [alibaba/druid](https://github.com/alibaba/druid)：Database connection pool
- [alibaba/fastjson](https://github.com/alibaba/fastjson)：Fast JSON parser/generator for Java
- [shiro-redis](https://github.com/alexxiyang/shiro-redis)：A redis caching tool that Shiro can use
- [Lombok](https://www.projectlombok.org/)：Make the code more concise
- [Hutool](https://github.com/looly/hutool)：A Java toolkit, also just a toolkit, helps us simplify every line of code, reduce every method, and make the Java language "sweet"
- [Bootstrap](https://github.com/twbs/bootstrap.git)：The most widely used front-end UI framework
- [JQuery](https://github.com/jquery/jquery.git)：The most widely used JavaScript framework
- [Layer](https://github.com/sentsin/layer.git)：Ejection Layer Component
- [kaptcha](https://github.com/penggle/kaptcha)：Google Verification Code
- [jrebel](https://zeroturnaround.com/software/jrebel/)：Thermal deployment
- [swagger](https://github.com/swagger-api/swagger-ui)：Swagger is the most popular API expression tool in the world.

## Donation

**Ask the author to eat a meat bun :) Author blog：[https://aodeng.cc](https://aodeng.cc)（We don't change the world, we change with the world.）Wechat Public Number：低调小熊猫**

![](https://i.loli.net/2018/12/31/5c29d3b18826d.png)

## Interface display

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

## Last

In order to prevent the world from being destroyed, in order to love and justice, it is recommended to look at the wisdom of asking questions. http://doc.zengrong.net/smart-questions/cn.html