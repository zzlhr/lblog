### linux部署

#### jar包形式

- 自行编译部署
- 直接使用

#### 自行编译

```sehll 
# 创建目录
mkdir /var/blog && cd /var/blog
# 拉取文件
sudo wget https://github.com/zzlhr/lblog/archive/master.zip
# 解压
sudo unzip master.zip
# 进入解压目录
cd lblog-master
```

修改配置文件

```sudo vim src/main/resources/application.yml```
##### 注意此处有几个必须要修改的地方。
- github的密匙，需要自己创建一个。
- 数据库账号密码url
- blog里的内容，你的上传地址你的域名
- 日志存放地址


确认上述内容无误后
执行
```sudo mvn package```

打好的包在target/ 下

设置为linux服务的方法参照[设置spring-boot为linux](https://www.cnblogs.com/zhengshiqiang47/p/8119944.html)






