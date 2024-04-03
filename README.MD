# 科技项目管理系统 开发人员备注
代码框架概述和相关规范见`pms2-web/README.MD`，本文档用于开发人员相关事项的备注。

## 运行环境
在IDEA的`Run/Debug Configurations`中的`Program arguments`配置`--spring.profiles.active=test`用来指定运行的profile。
较新版本的IDEA可能可以通过`Active profiles`来直接指定profile，但是可能某些地方会因自动添加前缀等问题匹配不上。

### 运行环境说明
- `application.yml`：项目的基本通用配置，开发中主要会用到：
  1. `xss`白名单，用于避免转义带来的数据内容错误； 
  2. `skip-url`白名单，用于登记不用登陆即可访问的接口。
- `application-dev.yml`：项目的连接服务等配置，适用于在杭州的开发人员；
- `application-test.yml`：项目的连接服务等配置，适用于非业务系统的开发人员，仅用于查看、测试、演示等用途；
- 使用`dev`或`gz`配置的，需要确认pom文件中的仓库地址，启用对应部分地址、注释另外部分地址。
