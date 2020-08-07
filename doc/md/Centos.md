#  Centos

## 命令篇

###  find 

``````c
find / -name 'filename' //查找文件
find / -name 'path' -type d  //查找文件夹（目录）
find . | xargs grep -ri 'content' //查找内容
find . | xargs grep -ril 'content' //只显示文件名称
``````

###  axel 

``````c
yum install axel
axel -n 100 "http://xxxxx"
``````

###  scp 

``````c
scp apache-maven-3.6.3-bin.tar.gz root@118.89.173.93:/home/  //上传文件

scp -r test root@118.89.173.93:/home  //上传目录

``````

###  chmod 

``````kotlin
sudo chmod 777 /home/*
``````

### ufw

``````kotlin
$ sudo apt install ufw
$ sudo ufw status verbose / status / enable
$ sudo ufw allow 7722/tcp
``````

### vim

> gg 文件头
>
> G 文件尾
>
> :set nu 数字
>
> :0,$d 清除全部

### alien

``````kotlin
sudo alien -i jdk-7u71-linux-x64.rpm // rpm->deb
``````

### dpkg

``````kotlin
dpkg -i electron-ssr_0.2.6-120_amd64.deb
``````

### ip

``````kotlin
$ telnet 118.89.173.93 80

$ ssh -v -p 80 root@118.89.173.93

$ curl 118.89.173.93:80

$ wget 118.89.173.93:80

$ netstat -ntl

``````











## 配置篇

###  jdk/maven

``````kotlin
$ sudo vim ~/.bashrc

$ 
export JAVA_HOME=/usr/local/xt/jdk-11.0.7
export JRE_HOME=/usr/local/xt/jdk-11.0.7/jre
export MAVEN_HOME=/usr/local/xt/apache-maven-3.6.3
export CLASSPATH=$CLASSPATH:$JAVA_HOME/lib:$JAVA_HOME/jre/lib
export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$MAVEN_HOME/bin:$PATH:$HOME/bin

$ source ~/.bashrc 
``````

###  source.list

``````kotlin
$ sudo cp /etc/apt/sources.list /etc/apt/sources.list.bak 
$ sudo vim /etc/apt/sources.list
$ 
deb http://mirrors.aliyun.com/ubuntu/ trusty main restricted universe multiverse
deb http://mirrors.aliyun.com/ubuntu/ trusty-security main restricted universe multiverse
deb http://mirrors.aliyun.com/ubuntu/ trusty-updates main restricted universe multiverse
deb http://mirrors.aliyun.com/ubuntu/ trusty-proposed main restricted universe multiverse
deb http://mirrors.aliyun.com/ubuntu/ trusty-backports main restricted universe multiverse
deb-src http://mirrors.aliyun.com/ubuntu/ trusty main restricted universe multiverse
deb-src http://mirrors.aliyun.com/ubuntu/ trusty-security main restricted universe multiverse
deb-src http://mirrors.aliyun.com/ubuntu/ trusty-updates main restricted universe multiverse
deb-src http://mirrors.aliyun.com/ubuntu/ trusty-proposed main restricted universe multiverse
deb-src http://mirrors.aliyun.com/ubuntu/ trusty-backports main restricted universe multiverse

$ sudo apt-get update
``````

### npm

``````kotlin
$ sudo  apt-get  install npm
$ sudo  npm  install  -g  n
$ sudo  n  latest
$ sudo npm install npm@latest -g //升级npm为最新版本
$ sudo  node  -v
$ sudo npm  -v
``````

### git

``````
$ ssh-keygen -t rsa -C "youremail@example.com" //把id_rsa.pub内容添加到github的SSH keys页面
$ ssh -T git@github.com
$ git config --global user.name "Your Name"  
$ git config --global user.email "youremail@domain.com"
$ git config --list 
$ git revert <commit ID> //生成一个新的提交来撤销某次提交
``````

### python

``````kotlin
$ sudo ln -s /usr/bin/python2.7 /usr/bin/python
``````



## 换源

``````kotlin
curl -o /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
``````

### mysql 8.0

``````kotlin
$ wget https://dev.mysql.sql.com/get/mysql80-community-release-el7-3.noarch.rpm
rpm -ivh mysql80-community-release-el7-3.noarch.rpm

$ yum clean all && yum makecache

#安装
sudo yum install -y mysql-community-server

#启动服务
sudo systemctl start mysqld

#查看版本信息
mysql -V

#1、查看MySQL为Root账号生成的临时密码
grep "A temporary password" /var/log/mysqld.log

#2、进入MySQL shell
mysql -u root -p

#3、修改密码
ALTER USER 'root'@'localhost' IDENTIFIED BY 'Mypwd123!';

#CentOS 7
#开放端口
firewall-cmd --add-port=3306/tcp --permanent

#重新加载防火墙设置
firewall-cmd --reload

# 登陆授权
mysql -u root -p

mysql>use mysql;

mysql>update user set host = '%' where user = 'root';

mysql>select host, user from user;

mysql>FLUSH   PRIVILEGES;  
``````

配置文件

``````kotlin

``````

启动服务

``````kotlin
#启动服务
systemctl start mysqld

#查看版本信息
mysql -V

#查看状态
systemctl status mysqld

##开机启动
systemctl enable mysqld
systemctl daemon-reload
``````

修改账号密码

``````kotlin
#1、查看MySQL为Root账号生成的临时密码
grep "A temporary password" /var/log/mysqld.log

#2、进入MySQL shell
mysql -u root -p

#3、修改密码
ALTER USER 'root'@'localhost' IDENTIFIED BY 'MyNewPass4!';
``````

### redis 6

``````
# 依赖升级
$ yum install centos-release-scl scl-utils-build -y

$ yum list all --enablerepo='centos-sclo-rh' | grep "devtoolset-"

$ yum install devtoolset-8-toolchain -y

$ scl enable devtoolset-8 bash 

$ gcc --version

$ echo "source /opt/rh/devtoolset-8/enable" >>/etc/profile

$ yum install tcl -y

# 下载
$ wget http://download.redis.io/releases/redis-6.0.5.tar.gz
tar xvf redis-6.0.5.tar.gz
mv redis-6.0.5 /usr/local/redis
cd /usr/local/redis/
make
make test
make install

$  vi redis.conf 

daemonize yes
logfile "/data/redis/logs/redis.log"
dir /data/redis/data/
maxmemory 128MB 
bind 127.0.0.1修改为bind 0.0.0.0

# 配置
$ vi /etc/systemd/system/redis.service

Description=Redis
After=network.target

[Service]
Type=forking
PIDFile=/var/run/redis_6379.pid
ExecStart=/usr/local/soft/redis6/bin/redis-server /usr/local/redis/conf/redis.conf
ExecReload=/bin/kill -s HUP $MAINPID
ExecStop=/bin/kill -s QUIT $MAINPID
PrivateTmp=true

[Install]
WantedBy=multi-user.target

# 刷新 
$  systemctl daemon-reload 

$ systemctl start redis

# 验证
$ /usr/local/redis/bin/redis-cli -h 127.0.0.1   -p 6379 -a Cxt1997116
``````



### nacos

``````kotlin
$ vim nacos/conf/application.properties

spring.datasource.platform=mysql
 
db.num=1
db.url.0=jdbc:mysql://121.52.33.213:3306/nacos_config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true
db.user=root
db.password=123456



$ mysql 执行 nacos/conf/nacos-mysql.sql


$ firewall-cmd --add-port=8848/tcp --permanent

$ firewall-cmd --reload

//cd nacos/bin
$ sh startup.sh -m standalone

$ sh shutdown.sh

//vim startup.sh 修改vim启动大小
``````



### nginx

``````kotlin
$ sudo yum -y install nginx   # 安装 nginx
$ sudo yum remove nginx  # 卸载 nginx
$ sudo systemctl enable nginx # 设置开机启动 
$ sudo service nginx start # 启动 nginx 服务
$ sudo service nginx stop # 停止 nginx 服务
$ sudo service nginx restart # 重启 nginx 服务
$ sudo service nginx reload # 重新加载配置，一般是在修改过 nginx 配置文件时使用。

$ 使用 yum 进行 Nginx 安装时，Nginx 配置文件在 /etc/nginx 目录下。

// 开启 80 443 端口
sudo firewall-cmd --permanent --zone=public --add-service=http
sudo firewall-cmd --permanent --zone=public --add-service=https
sudo firewall-cmd --reload
``````



### rabbitmq

### es





``````
user nginx;
worker_processes auto;
error_log /var/log/nginx/error.log;
pid /run/nginx.pid;

# Load dynamic modules. See /usr/share/doc/nginx/README.dynamic.
include /usr/share/nginx/modules/*.conf;

events {
    worker_connections 1024;
}

http {
    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  /var/log/nginx/access.log  main;

    sendfile            on;
    tcp_nopush          on;
    tcp_nodelay         on;
    keepalive_timeout   65;
    types_hash_max_size 2048;

    include             /etc/nginx/mime.types;
    default_type        application/octet-stream;

 
    include /etc/nginx/conf.d/*.conf;

}

``````



