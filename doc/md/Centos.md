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
scp apache-maven-3.6.3-bin.tar.gz root@118.89.173.93:/home  //上传文件

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

### alien

``````kotlin
sudo alien -i jdk-7u71-linux-x64.rpm // rpm->deb
``````

### dpkg

``````kotlin
dpkg -i electron-ssr_0.2.6-120_amd64.deb
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

