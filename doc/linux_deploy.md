### linux部署

#### jar包形式

- 自行编译部署
- 直接使用

#### 1.下载源码

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

#### 2. 修改配置文件

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

#### 3.还原数据库
还原数据库 [文件](../sql/install.sql)

#### 4.配置服务
设置为linux服务的方法参照[设置spring-boot为linux](https://www.cnblogs.com/zhengshiqiang47/p/8119944.html)

```$shell
/usr/lib/systemd/system/blog.service

[Unit]
Description=frbao-publicity
After=network.target

[Service]
WorkingDirectory=/var/blog
ExecStart=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.161-0.b14.el7_4.x86_64/jre/bin/java -Dsun.misc.URLClassPath.disableJarChecking=true -jar /var/blog/blog.jar --spring.datasource.password=root
ExecStop=kill $MAINPID
Restart=always

[Install]
WantedBy=multi-user.target
```

```$shell
# 启动
systemctl start blog
# 停止
systemctl stop blog
# 重启
systemctl restart blog
# 查看日志
journalctl -u blog
```

#### 5.启动
systemctl start blog


#### 6.后续可拓展
配置服务自启守护等等




一个小技巧，如果服务器内存在1g以上个人推荐可以安装appnode面板进行配置，可以配置自启、守护服务等功能非常方便


