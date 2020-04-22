### mybatis generator工具

#### 配置文件
```bash
jdbc.driverClassName=com.mysql.jdbc.Driver
jdbc.url=<url>
jdbc.username=<username>
jdbc.password=<password>

model.package=Model实体类包路径
mapper.package=mapper接口包路径
mapperXML.package=mapperXML包路径(resources目录下)
```

#### 运行
main 方法运行即可

#### 注意
使用IDEA时,务必单独打开该工程,否则由于依赖问题,可能不会生成代码