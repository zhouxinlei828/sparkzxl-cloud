# sparksys-cloud
## spring cloud 项目基础架构组件
**采用spring cloud基本组件以及spring boot组件，对常见组件进行封装，做到开箱即用的效果，便于快速搭建开发平台，减轻项目搭建时间，节省公司成本以及人力资源的浪费**

### 组织结构
> 主要是统一了对外接口的api访问格式，web模块进行了封装，基于DDD领域驱动模型设计代码，具体落地实施，对常用的core包进行二次封装，简单易用，elasticsearch，mybatis组件。集成了oauth2，redis多级缓存的构建，分布式锁的封装等等

```text
sparksys-cloud
├── sparksys-activiti       	                  -- activiti微服务
├── sparksys-admin-server                         -- admin监控微服务
├── sparksys-authorization                        -- 用户微服务
├── sparksys-code-generator                       -- 代码生成工具
├── sparksys-file                                 -- 文件微服务
├── sparksys-gateway                              -- 网关微服务
├── sparksys-oauth-server                         -- 授权微服务
├── sparksys-sharding-demo                        -- 分库分表示例微服务
```

### 分布式系统架构
![分布式系统架构](https://oss.sparksys.top/images/system.png)
### 技术选型

技术 | 说明 | 官网
----|----|----
Spring Cloud | 全栈框架 | [https://spring.io/projects/spring-cloud/](https://spring.io/projects/spring-cloud/)
Spring Boot | 容器+MVC框架 | [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot)
nacos | 服务注册发现以及服务配置中心 | [https://www.eurekanetwork.org/](https://www.eurekanetwork.org/)
spring security | 认证框架 | [https://spring.io/projects/spring-security](https://spring.io/projects/spring-security)
oauth2 | 授权框架 | [https://oauth.net/2/](https://oauth.net/2/)
spring-cloud-openfeign | 服务调用 | [https://spring.io/projects/spring-cloud-openfeign](https://spring.io/projects/spring-cloud-openfeign)
Jackson | Json工具 | |
sentinel | 分布式系统的流量防卫兵 | [https://github.com/alibaba/Sentinel](https://github.com/alibaba/Sentinel)
MyBatis | ORM框架  | [http://www.mybatis.org/mybatis-3/zh/index.html](http://www.mybatis.org/mybatis-3/zh/index.html)
MyBatis-Plus | 数据层代码生成 | https://mp.baomidou.com/ 
Elasticsearch | 搜索引擎 | [https://github.com/elastic/elasticsearch](https://github.com/elastic/elasticsearch)
Redis | 分布式缓存 | [https://redis.io/](https://redis.io/)
Docker | 应用容器引擎 | [https://www.docker.com/](https://www.docker.com/)
Druid | 数据库连接池 | [https://github.com/alibaba/druid](https://github.com/alibaba/druid)
OSS | 对象存储 | [https://github.com/aliyun/aliyun-oss-java-sdk](https://github.com/aliyun/aliyun-oss-java-sdk)
JWT | JWT登录支持 | [https://github.com/jwtk/jjwt](https://github.com/jwtk/jjwt)
Logback | 日志收集 | [http://logback.qos.ch/](http://logback.qos.ch/)
Lombok | 简化对象封装工具 | [https://github.com/rzwitserloot/lombok](https://github.com/rzwitserloot/lombok)
