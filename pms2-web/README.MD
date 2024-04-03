
# 科技项目管理系统第2版
## 概述


## 开发规范
### 名词解释
|名词|解释|
|--|--|
|po|persistent object,就是在Object/Relation Mapping框架中的Entity，po的每个属性基本上都对应数据库表里面的某个字段。 完全是一个符合Java Bean规范的纯Java对象，没有增加别的属性和方法|
|dto|Data Transfer Object数据传输对象,主要用于远程调用等需要传输对象的地方,是一组需要跨进程或网络边界传输的聚合数据的简单容器|
|||

### 子模块划分

单个项目按功能接口，业务代码，数据库访问代码，服务发布方式分为：api,biz,dao,web四个子模块，如果有共公代码，再加一个common模块,划分的目的是为了更好的应对各类非功能性需求变动（例如更换数据库类型，添加服务发布方式）。模块名以：<**项目名>-<模块名>** 的方式命名。以pms2项目为例，目录结构大致如下：

```
.
├── pms2-api
│   ├── pom.xml
│   ├── src
│   │   └── main
│   │       └── java
│   │           └── cn
│   │               └── topcheer
│   │                   └── pms2
│   │                       └── api
│   └── target
│
├── pms2-biz
│   ├── pom.xml
│   ├── src
│   │   └── main
│   │       └── java
│   │           └── cn
│   │               └── topcheer
│   │                   └── pms2
│   │                       └── biz
│   └── target
│
├── pms2-common
│   ├── pom.xml
│   ├── src
│   │   └── main
│   │       └── java
│   │           └── cn
│   │               └── topcheer
│   │                   └── pms2
│   │                       └── common
│   └── target
│
├── pms2-dao
│   ├── pom.xml
│   ├── src
│   │   └── main
│   │       └── java
│   │           └── cn
│   │               └── topcheer
│   │                   └── pms2
│   │                       └── dao
│   │                           ├── jpa
│   │                           └── mapper
│   └── target
│
├── pms2-web
│   ├── pom.xml
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── cn
│   │   │   │       └── topcheer
│   │   │   │           └── pms2
│   │   │   │               ├── Pms2Application.java
│   │   │   │               └── web
│   │   │   │                   └── controller
│   │   │   └── resources
│   │   │       ├── application-dev.yml
│   │   │       ├── application.yml
│   │   │       ├── banner.txt
│   │   │       └── log
│   │   │           └── logback-dev.xml
│   │   └── test
│   └── target
├── pom.xml
└── target
```

### 各子模块包命名规则

**cn.topcheer<.项目><.子模块><.逻辑层>\[.功能\]**

关于是功能层放在前面还是逻辑层放在前面的考虑：**因为已经用多模块的模式开发了，在功能这个集合下面，逻辑层基本上都是1到2个，不是太多了；而从包扫描的角度来说（@**EntityScan之类的**），逻辑层放在功能层前面，可以减少扫描位置**

### 各子模块功能说明及文件位置规定

#### api子模块

api子模块主要提供功能的描述及定义，例如有哪些功能，每个功能传入什么参数，返回什么值之类的。api模块里不应该包含业务实现代码及涉及数据库底层的代码

**可能会出现的包名** 

| 包名  | 说明（包含哪些文件） |
| --- | --- |
| cn.topcheer.pms2.api.user.po | 实体对象 |
| cn.topcheer.pms2.api.user.dto | 数据传输对象（当实体对象不能直接传到前台时，或组合对象之类需求） |
| cn.topcheer.pms2.api.user.service | 功能接口定义，只是功能和dao层应该是多对多关系，这里应该都是Interface |
|     |     |

#### biz子模块

biz模块主要包含项目的业务逻辑代码，它位于服务发布层（web）与数据访问层（dao）之间。

biz子模块强依赖api子模块，弱依赖dao子模块。

- 它应该完整的实现了api层定义的功能接口（服务接口）
  
- 它与上下两层交互应该使用api层定义的数据结构对象(po,dto)及它们的组合体
  
- 除了某些特殊的服务（文件上传下载，用户，web转发，websocket服务之类），他不能限定其上层一定就是web方式访问。
  
- 除了某些特殊的服务（专门针对某个类型的数据库服务），他不能限定其下层数据访问方式，只能限定期下层提供的接口方法。
  

**可能会出现的包名** 

| 包名  | 说明（包含哪些文件） |
| --- | --- |
| cn.topcheer.pms2.biz.user.impl | 功能接口实现 |
|     |     |

#### dao子模块

dao模块主要包含数据存储读取代码（数据库），应该只有增删改查这四种，可以考虑简单级联保存，可以有实现自动保存创建用户、创建时间这一类数据操作类代码， 但不应该有业务逻辑代码，业务逻辑代码应该放到biz层去。

dao子模块强依赖api子模块

dao的实现，如果是新项目，建议使用Mybatis-Plus。如果是老项目，已经在使用JPA或Hibernate，建议复杂查询、Sql查询、部分字段更新改成Mybatis-Plus实现，应该能从性能上，获得比较好的回报。

基于项目会Mybatis系与Hibernate系并存，为了便于区分及后面可能会出现的移植，两类数据访问类放到不同的包下面，Mybatis使用mapper包，Hibernate使用dao包。

另外 Mybatis的Mapper.xml与数据访问类需要放在一起，文件名一致，避免再去配置xml扫描位置。

**可能会出现的包** 

| 包名  | 说明（包含哪些文件） |
| --- | --- |
| cn.topcheer.pms2.dao.user.jpa | JPA,Hibernate 数据访问对象/接口 |
| cn.topcheer.pms2.dao.user.mapper | Mybatis,Mybatis-Plus数据访问对象/接口，mapper.xml文件 |

#### web子模块

web子模块主要提供一种供外部调用功能接口的方式，目前一般都是基于http REST 风格的方式，实现也基本是Spring Rest Controller.

web子模块，只提供将http格式转换成功能接口调用，并将功能接口调用结果以http格式返回给调用方

web子模块强依赖api子模块,弱依赖biz模块，绝对不应该依赖dao模块

**可能会出现的包名** 

| 包名  | 说明（包含哪些文件） |
| --- | --- |
| cn.topcheer.pms2.user.web.controller | Controller /RestController |
|     |     |


