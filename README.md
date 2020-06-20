# sparksys-cloud
## spring cloud 项目基础架构组件
**采用spring cloud基本组件以及spring boot组件，对常见组件进行封装，做到开箱即用的效果，便于快速搭建开发平台，减轻项目搭建时间，节省公司成本以及人力资源的浪费**

### 组织结构
> 主要是统一了对外接口的api访问格式，web模块进行了封装，基于DDD领域驱动模型设计代码，具体落地实施，对常用的core包进行二次封装，简单易用，elasticsearch，mybatis组件。集成了oauth2，redis多级缓存的构建，分布式锁的封装等等

```text
sparksys-cloud
├── sparksys-business       	                  -- 业务模块
├──── sparksys-authorization                        -- oauth平台授权模块
├──── sparksys-file                                 -- 文件模块
├── sparksys-commons                              -- 核心组件模块
├──── sparksys-commons-activiti-starter             -- activiti组件封装
├──── sparksys-commons-core-starter                 -- 工具类组件
├──── sparksys-commons-elasticsearch-starter        -- 搜索引擎组件封装
├──── sparksys-commons-generator                    -- 代码自动生成组件
├──── sparksys-commons-mail-starter                 -- 邮件组件封装
├──── sparksys-commons-mybatis-starter              -- mybatis组件封装
├──── sparksys-commons-oauth2-starter               -- oauth2平台授权组件封装
├──── sparksys-commons-redis-starter                -- redis组件封装
├──── sparksys-commons-security-starter             -- 权限框架组件封装
├──── sparksys-commons-sentinel-starter             -- 限流组件封装
├──── sparksys-commons-web-starter                  -- web统一组件封装
├──── sparksys-commons-zookeeper-starter            -- zookeeper组件封装
├── sparksys-gateway                              -- 网关模块
```

### 技术架构
![分布式系统架构.png](https://oss.sparksys.top/images/%E5%88%86%E5%B8%83%E5%BC%8F%E7%B3%BB%E7%BB%9F%E6%9E%B6%E6%9E%84%E5%9B%BE.jpg)
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
