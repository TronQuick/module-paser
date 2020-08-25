# README

本项目服务于【多模块maven项目】，通过扫描项目下所有文件夹以及子文件夹中的pom.xml，将所有模块的模块信息以及模块间的依赖关系存入数据库中，

目前提供的查询功能有：

- 查询上级依赖等级总级数
- 分级查询上级依赖
- 查询所有上级依赖



## 配置指南

请务必查看注意事项，这将影响你的项目使用



### 配置数据库地址

在application.yml中配置数据库地址



### 配置端口号

application.yml中配置端口号。





### 运行

- 构建项目
- 运行application启动类



## 接口

### 扫描项目全局pom文件

- 请求类型：**POST**

- URL：

  ```http
  http://localhost:端口号/mp/scanPom
  ```

- 入参：

  ```json
  {  "projectPath": "项目路径"}
  ```
  注意：使用扫描项目全局pom文件接口时，输入路径请使用【/】斜划线，
  如C:/user/tron/desktop/project，在文件浏览器中复制出来的路径使用的可能会是【\】，请修改为【/】后再提交请求！
  



### 查询上级依赖等级总级数

- 请求类型：**POST**

- URL：

  ```http
  http://localhost:端口号/mp/getLevel
  ```

- 入参：

  ```json
  {
   "artifactid": "模块名",
   "version": "版本号"
  }
  ```

  



### 分级查询上级依赖

- 请求类型：**POST**

- URL：

  ```http
  http://localhost:端口号/mp/findByLevel
  ```

- 入参：

  ```json
  {
   "artifactid": "模块名",
   "version": "版本号", 
   "level": 依赖级数
  }
  ```

  



### 查询所有上级依赖

- 请求类型：**POST**

- URL：

  ```http
  http://localhost:端口号/mp/findDependencies
  ```

- 入参：

  ```json
  {
   "artifactid": "模块名",
   "version": "版本号"
  }
  ```



## 注意事项

- 请先使用扫描pom接口获取数据后，再进行其他查询
- 使用扫描项目全局pom文件接口时，输入路径请使用【/】斜划线，如`C:/user/tron/desktop/project`，在文件浏览器中复制出来的路径使用的可能会是【\】，请修改为【/】后再提交请求！
- 当已在一个项目中使用完后，需要在另外一个项目中使用时，请删除数据库中的所有表格，或者另外新建配置一个数据库，否者可能会出错。
- 当模块数量或者依赖关系数量超过1000时，可能会查询出错。
