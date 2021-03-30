# model-spring
My test-demo in Spring-Cloud

### **项目结构**

model-spring

- common：公共模块
  - dao：实体/泛型包
  - Util：工具包
- common-db：数据库相关公共模块
  - dao
  - entity
  - service
  - mapper
- core：核心功能模块
  - controller：功能模块
  - loginController：登录功能模块
- eureka：注册中心
- test-server：测试新功能的功能模块



### 已实现与待实现目录

- swagger API文档UI自动生成  ✅
- mybatis & mybatis-plus  ✅
- mybatis-plus代码生成器 ✅
- 分页工具 ✅
- hutool ✅
- login 登陆 ✅
- 验证码工具类实现（需要先实现jwt，因为需要以token为key，保存验证码到redis） ✅
- 密码加密储存 ✅
- 验证码 ✅
- AOUTH2  
- 配置redis ✅
- ELK
- jwt
- 邮件发送功能
- 管理多数据源
- IO流
- xxl-job同步任务
- 文件上传下载
- minio
- Sharding-JDBC
- skywalking 和网关

