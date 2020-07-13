# Docker

## 安装篇

### docker for centos

> 1. 卸载旧版本

``````c
 $ sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
``````

> 2.  安装`yum-utils`软件包（提供`yum-config-manager` 实用程序）并设置**稳定的**存储库。

``````c
$ sudo yum install -y yum-utils

$ sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo

``````

> 3. 安装 docker 引擎

``````c
$ sudo yum install docker-ce docker-ce-cli containerd.io
``````

> 4. 启动 docker

``````c
$ sudo systemctl start docker
``````

> 5. 通过运行`hello-world` 映像来验证是否正确安装了Docker Engine 

``````c
$ sudo docker run hello-world
``````

> 6. 配置国内镜像

``````c
//Docker中国官方镜像加速
--registry-mirror=https://registry.docker-cn.com

//网易163镜像加速
--registry-mirror=http://hub-mirror.c.163.com

//中科大镜像加速
--registry-mirror=https://docker.mirrors.ustc.edu.cn

//阿里云镜像加速
--registry-mirror=https://{your_id}.mirror.aliyuncs.com

//daocloud镜像加速
--registry-mirror=http://{your_id}.m.daocloud.io

//创建文件夹

$ sudo mkdir -p /etc/docker
//编辑/etc/docker/daemon.json文件，并输入国内镜像源地址

$ sudo vi /etc/docker/daemon.json
//Docker中国官方镜像加速

{
  "registry-mirrors": ["https://registry.docker-cn.com"]
}
//网易163镜像加速

{
"registry-mirrors": ["http://hub-mirror.c.163.com"]
}
//中科大镜像加速

{
    "registry-mirrors": ["https://docker.mirrors.ustc.edu.cn"]     
}

//修改完地址后，重新加载配置文件，重启docker服务

$ sudo systemctl daemon-reload
$ sudo systemctl restart docker
``````

### docker for ubuntu

> 1. 卸载旧版本

``````c
$ sudo apt-get remove docker docker-engine docker.io containerd runc
``````

> 2. 设置存储库

``````kotlin
$ sudo apt-get update

$ sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg-agent \
    software-properties-common
$ curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

$ sudo apt-key fingerprint 0EBFCD88

$ sudo add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable"
``````

> 3. 安装docker引擎

``````c
$ sudo apt-get update
  
$ sudo apt-get install docker-ce docker-ce-cli containerd.io
``````

> 4. 通过运行`hello-world` 映像来验证是否正确安装了Docker Engine 

``````c
$ sudo docker run hello-world
``````

> 5. 设置源

``````c
$ sudo mkdir /etc/docker

$ sudo vim /etc/docker/daemon.json

{
    "registry-mirrors": ["https://docker.mirrors.ustc.edu.cn"]
}

$ sudo systemctl daemon-reload

$ sudo systemctl restart docker
``````

### docker for minit

> 1 卸载旧版本docker

``````kotlin
$ sudo apt-get remove docker docker-engine docker.io
``````

> 2 更新apt包索引

``````kotlin
$ sudo apt-get update
``````

> 3 安装包以允许apt通过HTTPS使用存储库

``````kotlin
$ sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    software-properties-common
``````

> 4 添加Docker的官方GPG密钥

``````kotlin
$ curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
``````

> 5  使用以下命令设置稳定存储库

``````kotlin
$ sudo add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable"
``````

> 6 更新apt包索引

``````kotlin
 $ sudo apt-get update
``````

> 7 安装最新版本的Docker CE

``````kotlin
$ sudo apt-get install docker-ce
``````

> 8 避免每次使用docker命令加sudo

``````kotlin
$ sudo usermod -a -G docker $USER
or
$ sudo usermod -aG docker $USER
$ reboot
``````



## 命令篇

### 容器安装

#### nginx

``````c
$ docker run -p 8081:80 --name nginx -v $PWD/www:/www -v $PWD/conf/nginx.conf:/etc/nginx/nginx.conf -v $PWD/logs:/wwwlogs  -d nginx
``````

#### rabbitmq

``````kotlin
$ docker run -d -it --hostname rbq --name rmq -p 5671:5671 -p 5672:5672 -p 4369:4369 -p 25672:25672 -p 15671:15671 -p 15672:15672 rabbitmq:3.6-management-alpine
``````

#### mysql

``````c
$ docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:8.0
``````

#### redis 

``````c
$ docker run --name redis -p 6379:6379 -d --restart=always redis:6 redis-server --appendonly yes --requirepass "Cxt1997116"
``````

#### consul

``````kotlin
$ docker run -d -p 8500:8500 -v /usr/local/xt/consul:/consul/data -e CONSUL_BIND_INTERFACE='eth0' --name=consul consul agent -server -bootstrap -ui -client='0.0.0.0'
``````

#### mongo

``````kotlin
$ docker run -d -p 27017:27017 --name mongodb -e MONGO_INITDB_ROOT_USERNAME=mongoadmin -e MONGO_INITDB_ROOT_PASSWORD=mongoadmin mongo:4.1.6
``````

#### nacos

``````c
$ docker run --env MODE=standalone --name nacos -d -p 8848:8848 nacos/nacos-server:1.0.0
``````

#### jenkins

``````c
$ docker run -itd -p 8088:8080 -p 50000:50000 --name jenkins --privileged=true  -v $PWD/jenkis:/var/jenkins_home jenkins/jenkins:2.222.3
``````

#### es

``````c
$ docker pull elasticsearch:7.2.0 //安装es
  
$ docker run --name elasticsearch -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -e ES_JAVA_OPTS="-Xms512m -Xmx512m" -d elasticsearch:7.2.0 //启动es http://localhost:9200
  
$ docker exec -it elasticsearch /bin/bash //修改配置，解决跨域访问问题
$ cd /usr/share/elasticsearch/config/
$ vi elasticsearch.yml
  //在elasticsearch.yml的文件末尾加上:
$ http.cors.enabled: true
  http.cors.allow-origin: "*"
    
$ docker restart elasticsearch

    
    
$ docker run --name logstash -d -p 5044:5044 -p 9600:9600 logstash:7.2.0 
  
$ docker pull kibana:7.2.0 //启动kibana

$ docker run --name kibana --link=elasticsearch:test  -p 5601:5601 -d kibana:7.2.0

$ docker start kibana //http://localhost:5601
  
  
``````

#### registry

> ``````
> $  docker pull registry
> 
> $  sudo docker run -d -p 5000:5000 --restart=always --name registry registry
> 
> $  curl 127.0.0.1:5000/v2/_catalog
> 
> ``````
>
> 

### 容器命令

#### 删除镜像或容器

``````kotlin
$ docker stop  $(docker ps -a -q) //暂停所有容器

$ docker rmi  $(docker images -a -q) //直接删除所有镜像

$ docker rm  $(docker images -a -q) //直接删除所有容器

$ docker rmi $(docker images | grep "none" | awk '{print $3}')  //删除名字中带‘none’镜像

$ docker rm $(docker ps -a | grep "none" | awk '{print $1}')  //删除名字中带‘none’容器

$ docker image prune -a //删除未被使用的镜像

``````

#### 进入容器

``````c
$ docker exec -it 'id' /bin/bash
``````

## 集成springboot

### Dockerfile

> ``````java
> FROM openjdk:11.0.3-jdk-stretch
> RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
> RUN echo 'Asia/Shanghai' >/etc/timezone
> VOLUME /tmp
> ADD zy-system-0.0.1-SNAPSHOT.jar system.jar
> ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/system.jar"]
> ``````

### pom

> ~~~~~~java
>  <plugin>
>                 <groupId>com.spotify</groupId>
>                 <artifactId>docker-maven-plugin</artifactId>
>                 <version>1.2.0</version>
>                 <configuration>
>                     <imageName>${docker.image.prefix}/${project.artifactId}</imageName>
>                     <dockerDirectory>src/main/docker</dockerDirectory>
>                     <dockerHost>http://localhost:2375</dockerHost>
>                     <resources>
>                         <resource>
>                             <targetPath>/</targetPath>
>                             <directory>${project.build.directory}</directory>
>                             <include>${project.build.finalName}.jar</include>
>                         </resource>
>                     </resources>
>                 </configuration>
>             </plugin>
> ~~~~~~

### 配置(ubuntu)

> ``````kotlin
> $ sudo vim /etc/default/docker
>  //文件末尾新增docker端口
> DOCKER_OPTS="-H unix:///var/run/docker.sock -H tcp://0.0.0.0:2375"
> 
> //配置生效及配置国内镜像源头
> $ sudo vim /lib/systemd/system/docker.service
> EnvironmentFile=-/etc/default/docker
> ExecStart=/usr/bin/dockerd -H fd:// $DOCKER_OPTS  --containerd=/run/containerd/containerd.sock --insecure-registry https://hub-mirror.c.163.com
> ExecReload=/bin/kill -s HUP $MAINPID
> 
> //查看配置
> $ ps -ef | grep docker
> 
> //重启docker
> $ sudo systemctl daemon-reload
> $ sudo systemctl restart docker
> ``````
>
> 

