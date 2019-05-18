<h1 align="center"><a href="https://github.com/hope-for" target="_blank">Hope-Boot</a></h1>

<p align="center">
<a href="https://aodeng.cc"><img alt="Author" src="https://img.shields.io/badge/author-%E4%BD%8E%E8%B0%83%E5%B0%8F%E7%86%8A%E7%8C%AB-blue.svg"/></a>
<a href="https://github.com/hope-for/hope-boot"><img alt="JDK" src="https://img.shields.io/badge/JDK-1.8-orange.svg"/></a>
<a href="https://github.com/hope-for/hope-boot/blob/master/LICENSE"><img alt="license" src="https://img.shields.io/github/license/java-aodeng/hope.svg?style=flat-square"/></a>
<a href="https://jq.qq.com/?_wv=1027&k=574chhz"><img alt="QQ群" src="https://img.shields.io/badge/chat-%E4%BD%8E%E8%B0%83%E5%B0%8F%E7%86%8A%E7%8C%ABQQ%E7%BE%A4-yellow.svg"/></a>
<a href="https://t.me/joinchat/LSsyBxVKLGEkF5MtIhg6TQ"><img alt="Telegram" src="https://img.shields.io/badge/telegram-%E4%BD%8E%E8%B0%83%E5%B0%8F%E7%86%8A%E7%8C%AB--%E5%AE%98%E6%96%B9%E9%83%A8%E8%90%BD-orange.svg"/></a>
<a href="https://github.com/hope-for/hope-boot"><img alt="star" src="https://img.shields.io/github/stars/hope-for/hope-boot.svg?label=Stars&style=social"/></a>
<a href="https://github.com/hope-for/hope-boot/releases"><img alt="发行版本" src="https://img.shields.io/badge/release-%E5%8F%91%E8%A1%8C%E7%89%88%E6%9C%AC-red.svg"/></a>
</p>

# Introduction

>Have you been looking for a back-end system that suits you... So, Hope-boot is here now. I don't know why I want to do such a project, perhaps for learning, maybe I have never found a perfect system. Welcome everyone to ask questions, so that I can continue to improve

> Based on Spring Boot 2.x development integration: Spring Boot 2.x/Apache Shiro+JWT/Spring Data JPA+Mybatis+Tk.Mybatis+PageHelper/Redis/Flyway/Swagger/SSO/Thymeleaf Dynamic Rights Management AES+MD5+ Salt encryption and decryption Code generation log record Google verification code login single sign-on timed task project address: https://github.com/hope-for/hope-boot main language [java]

------------------------------

🇸English | 🇨🇳[简体中文](./README-EN.md) | [Update log](https://github.com/hope-for/hope-boot/commits/master) | current version:label:[0.0.4](https://github.com/hope-for/hope-boot)

# Module division

| Module         | Interpretation                      |    
| ---------- | ----------------------- |
| hope-admin  | Background management module |      
| hope-core  | Core business class module |    
| hope-framework | Framework module, providing data manipulation, tool processing, generic Mapper, general service, etc. |
| hope-sso-server | Single sign-on-certification center module, support cluster |
| hope-generator | Code generation module - provides sql generated code |
| hope-flyway | Database version management tool module |
| hope-quartz | Timed task module |

# Instructions for use

```bash
# 1.Use the command to pull the code：
    git clone https://github.com/hope-for/hope-boot.git 
# 2.Create a database (name)：hope， character set：utf8mb4;(Note: you only need to create the database, the character set is not utf8, but utf8mb4)
# 3.Import the project using IDEA
# 4.Change setting
    A.Open the hope-flyway module and configure the database connection:
        spring:
          datasource:
              url: Your database address
              username: Your database username
              password: Your database password
    B.Open the hope-admin module, configure the database connection and redis connection:
        a.Database configuration (searchable datasource or locate to L.17)
        b.Redis configuration (search for redis or locate L.29, note: the project must be installed with redis service to start)
# 5.Running the project (database management module)    
    a.Run directly under the hope-flyway directory HopeFlywayApplication.java
    b.Check if the database automatically generates tables and initialized data
# 6.Running project (background management module)
    a.Run directly under the hope-admin directoryHopeAdminApplication.java
    b.Browser access：http://127.0.0.1:8886
# 7.Run the project (single sign-on module)
    a.Run directly under the hope-sso-server directory HopeSsoServerApplication.java
    b.Browser access：http://127.0.0.1:8887
# 8.Running project (code generation module)
    a.Run directly under the hope-generator directory HopeGeneratorApplication.java
    b.Browser access：http://127.0.0.1:8888 
# 9.Running project (timed task module)
    a.Run directly under the hope-quartz directory HopeQuartzApplication.java
    b.port：8889
# 10.Package attention
    a.Packaged directly in the hope-boot directory：mvn clean install That is, the generated jar package is in the target directory under the respective module. run：java -jar Package names
    b.The flyway module does not provide a package plugin, which is mainly used by developers and does not require deployment.
```

# account number

Login in the background: Account: admin Password: 123456

Resource Monitoring: Account: hope-druid Password: hope-druid

Backend API documentation：http://localhost:8886/swagger-ui.html

# thank

The birth of Hope-boot is inseparable from the following projects (open source, open source):

- [Spring Boot](https://github.com/spring-projects/spring-boot)：Core framework
- [Apache Shiro](https://github.com/apache/shiro)：Permission framework
- [Redis](https://github.com/antirez/redis)：Cache framework
- [Thymeleaf](https://github.com/thymeleaf/thymeleaf)：Template engine
- [MyBatis](https://github.com/mybatis/mybatis-3)：MyBatis SQL Mapper framework for Java
- [jpa](https://github.com/spring-projects/spring-data-jpa)：I don't know why I want to add this.
- [PageHelper](https://github.com/pagehelper/Mybatis-PageHelper)：Paging plugin
- [tk.mybatis](https://github.com/abel533/Mapper)：Generic Mapper
- [alibaba/druid](https://github.com/alibaba/druid)：Database connection pool
- [alibaba/fastjson](https://github.com/alibaba/fastjson)：Fast JSON parser/generator for Java
- [Apache Maven](https://maven.apache.org/):Dependency management
- [shiro-redis](https://github.com/alexxiyang/shiro-redis)：A redis caching tool that can be used by shiro
- [Lombok](https://www.projectlombok.org/)：Make the code simpler
- [Hutool](https://github.com/looly/hutool)：A Java toolkit is just a toolkit that helps us simplify every line of code, reduce every method, and make the Java language "sweet"
- [Bootstrap](https://github.com/twbs/bootstrap.git)：The most widely used front-end ui framework
- [JQuery](https://github.com/jquery/jquery.git)：The most widely used JavaScript framework
- [Layer](https://github.com/sentsin/layer.git)：Popup layer component
- [kaptcha](https://github.com/penggle/kaptcha)：Google verification code
- [jrebel](https://zeroturnaround.com/software/jrebel/)：Hot deployment
- [swagger](https://github.com/swagger-api/swagger-ui)：Swagger is the world's most popular API expression tool.
- [flyway](https://github.com/flyway/flyway)：Flyway by Boxfuse • Database Migrations Made Easy.
- [xxl-sso](https://github.com/xuxueli/xxl-sso/)：A distributed single-sign-on framework.
- [xxl-code](https://github.com/xuxueli/xxl-code-generator/)：A code generator for "controller/service/dao/mybatis/model" layer.
- [quartz](http://www.quartz-scheduler.org/)：Timed task

# Donation

**Please author to eat a meat folder :) Author blog：[https://aodeng.cc](https://aodeng.cc)（We don't change the world, we change with the world）WeChat public number: low-key little panda**

![](https://i.loli.net/2018/12/31/5c29d3b18826d.png)

# Follow-up function

- [x] **RESTful APIs Constructed as a front-end separation project**
- [x] **Swagger Interface management**
- [X] **sso sign in**
- [ ] **Data permission separation**
- [ ] **Payment module**
- [X] **Timing service**
- [ ] **Third party authorized login**
- [ ] **J2Cache Cache**
- [X] **Code generation**
- [ ] **Front end use [Ant](https://ant.design/)Refactoring**

# Interface display

The front-end page is temporarily borrowed from [Ruyi], and has obtained the author's consent, thanks to open source. Later, I will take time to reconstruct with [Ant]

## Background login

![](https://i.loli.net/2019/05/01/5cc930100d298.png)

## Home

![](https://i.loli.net/2019/05/01/5cc9300612ca5.png)

## Rights Management - User - Role - Resources

![](https://i.loli.net/2019/05/01/5cc9300d45a92.png)
![](https://i.loli.net/2019/05/01/5cc9300d62006.png)
![](https://i.loli.net/2019/05/01/5cc9300d8fb70.png)

## System Tools - Resource Monitoring

![](https://i.loli.net/2019/05/01/5cc9300bc316d.png)
![](https://i.loli.net/2019/05/01/5cc93003ef3d6.png)

## Document

![](https://i.loli.net/2019/05/01/5cc932128cc07.png)

## My link：

- [My personal blog](https://aodeng.cc)
- [My WeChat public number (low-key panda)](https://mp.weixin.qq.com/s/l5t8WSCG_-shiD4BPpLYiw) 
- [Hope-boot uses the GPL-v3.0 protocol to open source](https://github.com/hope-for/hope-boot/blob/master/LICENSE)
- [Low-key little panda QQ group](https://jq.qq.com/?_wv=1027&k=5y4H7Nz) 
- [Low-key red panda Telegram group](https://t.me/joinchat/LSsyBxVKLGEkF5MtIhg6TQ)
- [My Gitee link](https://gitee.com/java-aodeng)
- [My Github link](https://github.com/java-aodeng)
- [My open source organization (welcome like-minded friends to join)](https://github.com/hope-for)
- Contact me: java@aodeng.cc Apply to join the hope-for organization and send it to this email. Remember to bring your GitHub account.