# HomeworkManagementFinal

## 简介

一个基于 Springboot + Vue.js + Mybatis + MySQL + RestfulAPI + Docker 的简单作业管理系统。

Git 仓库地址：[HomeworkManagementFinal](https://github.com/dongshunyao/HomeworkManagementFinal)

这个系统在之前的作业管理系统 [HomeworkManagement](https://github.com/dongshunyao/HomeworkManagement) 的基础上**完全重构**，并添加了**新的功能与科技**。

------

### 快速上手

#### 使用 docker 快速部署

如果你已经正确安装 docker，可以通过下列命令快速部署：

```
docker pull registry.cn-beijing.aliyuncs.com/springwinter/17301122-homeworkmanagement:1.0
docker run -dit --name=test -p 8081:8081 registry.cn-beijing.aliyuncs.com/springwinter/17301122-homeworkmanagement:1.0
```

项目启动后访问 http://localhost:8081/ 即可。

#### 自行编译和构建

如果选择自行编译和构建，请使用 `git clone` 并使用 `Maven` 进行构建，或者使用提供的 `Jar`包。

前端请使用 `npm` 构建，构建结束后将输出放置在 `/src/main/resources/static` 目录下。

数据库部分使用 `hwm.sql` 进行导入，并在 `/src/main/resources/application.yml` 处修改相关配置。

------

### 功能特点

这个项目是为学生和教师设计的简易的作业的提交和管理平台。

对于教师，有以下功能：

- 注册账户
- 登录账户
- 登出账户
- 发布作业
- 查看已发布的作业列表和详情
- 查看提交的作业内容列表和详情
- 对提交的作业进行批阅，包括评分和评语

对于学生，有以下功能：

- 注册账户
- 登录账户
- 登出账户
- 查看已发布的作业列表和详情
- 提交作业，对已经提交但未批阅的作业可以进行修改
- 查看作业的状态
- 查看作业的评分和评语

---

## 开源许可证

Apache-2.0