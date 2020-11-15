# Nginx简单入门

软件位置，不同安装方式可能不同，一般如下：

- docker容器：/usr/sbin/nginx  
- centos:：/usr/local/nginx/sbin 

配置环境变量即可在任意地方使用nginx命令

## 常用命令

默认已经配置环境变量

- 启动与关闭

  ```shell
  nginx					 # 启动
  nginx -s reload            # 重新载入配置文件，热部署、热加载
  nginx -s reopen            # 重启 Nginx
  nginx -s stop              # 停止 Nginx
  ```

- 检查配置文件nginx.conf的正确性命令 

  ```shell
  nginx -t
  ```

- 查看版本号

  ```shell
  nginx -v
  ```

## Nginx配置文件

nginx配置文件可以引入其它配置文件，即include操作

### 配置文件位置

- docker容器：/etc/nginx/nginx.conf
- centos：/usr/local/nginx/conf/nginx.conf

### 配置文件构成

官方初始的配置文件

```shell
user  nginx;
worker_processes  1;

error_log  /var/log/nginx/error.log warn;
pid        /var/run/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       /etc/nginx/mime.types;
    default_type  application/octet-stream;

    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  /var/log/nginx/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    keepalive_timeout  65;

    #gzip  on;
	server {
        listen       80;
        server_name  localhost;

        #charset koi8-r;
        #access_log  /var/log/nginx/host.access.log  main;

        location / {
            root   /usr/share/nginx/html;
            index  index.html index.htm;
        }

        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   /usr/share/nginx/html;
        }
	}

    include /etc/nginx/conf.d/*.conf;# 引入其它配置文件
}

```

构成如下：

1. 全局块

   文件开始到events块之前的内容，配置影响nginx整体运行的指令；

   ```shell
   worker_processes  1 #可设置值和CPU核心数一致
   error_log  /var/log/nginx/error.log warn; # 日志存放位置
   pid        /var/run/nginx.pid;# 进程id，不用管
   ```

   如worker_processes是nginx服务器处理并发服务的关键配置，值越大能力越强（还得看硬件配置）

2. events块

   events 块涉及的指令主要影响Nginx 服务器与用户的网络连接

   ```shell
   worker_connections  1024; #支持的最大连接数
   ```

3. http块。

   1. http全局块（server块之前）

      ```shell
      http {
          include       /etc/nginx/mime.types;#include代表引入一个外部的文件 ->/minle.types中放着大量的媒体类型
      
          default_type  application/octet-stream;
      
          log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                            '$status $body_bytes_sent "$http_referer" '
                            '"$http_user_agent" "$http_x_forwarded_for"';
      
          access_log  /var/log/nginx/access.log  main;
      
          sendfile        on;
          keepalive_timeout  65;
      
      	server {} # server块
      	# include /etc/nginx/conf. d/*.conf; -〉引入了conf.d目录下的以.conf为结尾的配置文件
      	include /etc/nginx/conf.d/*.conf;
      }
      ```

      http 全局块配置的指令包括文件引入、MIME-TYPE定义、日志自定义、连接超时时间、单链接请求数上限等

   2. server块（主要关注此处）

      ```shell
      http {
       	# server块
          server {
              listen       80; #监听端口
              server_name  localhost;# 域名
      
              #charset koi8-r;
              #access_log  /var/log/nginx/host.access.log  main;
      
              location / {# 匹配的url的规则
      		   #root:将接收到的请求根据/usr/share/nginx/html去查找静态资源
                  root   /usr/share/nginx/html;
                  #index:默认去上述的路径中找到index.html或者index. htm
                  index  index.html index.htm;
              }
              error_page   500 502 503 504  /50x.html;
              location = /50x.html {
                  root   /usr/share/nginx/html;
              }
      	}
      }
      
      ```
      }

## 反向代理

主要修改Http Server块的配置

```shell
server { # server块
        listen       80; #监听端口
        server_name  192.168.100.100;# 域名（一个主机可能有多个域名）

		# 匹配到 /app1 走 http://127.0.0.1:8081
        location /app1 {	
        	proxy_pass http://127.0.0.1:8081;
        }
        # 匹配到 /app2 走 http://127.0.0.1:8082
        location /app2 {	
        	proxy_pass http://127.0.0.1:8082; 
        }
}
```

## 负载均衡

配置

```shell
http {
	upstream myServer { # 加入 upstream ,服务名 myServer
        server 192.168.0.100:9001;
        server 192.168.0.100:9002;
	}
	server { # server块
	    listen       80; #监听端口
        server_name  192.168.100.100;# 域名

		# 匹配到 /app1 走 http://myServer
        location /app1 {	
        	proxy_pass http://myServer;
        }
	}
}
```

负载均衡策略：

- 轮询（默认）;按照时间顺序分配请求，其中一台服务器挂掉时自动剔除

- 权重 weight；默认权重为 1，权重越高得到的请求越多

  ```shell
  upstream myServer { # 加入 upstream ,服务名 myServer
          server 192.168.0.100:9001 weight = 5;# 权重为 5 
          server 192.168.0.100:9002 weight = 10;# 权重为 10
  }
  ```

- IP hash；每个请求按访问ip.的hash结果分配,这样每个访客固定访问一个后端服务器,可以解决session的问题。

  ```shell
  upstream myServer { # 加入 upstream ,服务名 myServer
  		ip_hash
          server 192.168.0.100:9001;
          server 192.168.0.100:9002 ;
  }
  ```

- fair；按后端服务器的响应时间来分配请求，响应时间短的优先分配。

  ```shell
  upstream myServer { # 加入 upstream ,服务名 myServer
   	server 192.168.0.100:9001;
      server 192.168.0.100:9002 ;
      fair
  }
  ```

## 动静分离

​	Nginx动静分离简单来说就是把动态请求跟静态请求分开，不能理解成只是单纯的把动态页面和静态页面物理分离。**严格意义上说应该是动态请求跟静态请求分开**，可以理解成使用Nginx处理静态页面，Tomcat处理动态页面。动静分离从目前实现角度来讲大致分为两种：一种是纯粹把静态文件独立成单独的域名,放在独立的服务器上,也是目前主流推崇的方案；另外一种方法就是动态跟静态文件混合在一起发布，通过Nginx来分开。

​	通过location 指定不同的后缀名实现不同的请求转发。通过 expires（过期）参数设置，可以使浏览器缓存过期时间，减少与服务器之前的请求和流量。具体Expires定义:是给一个资源设定一个过期时间,也就是说无需去服务端验证,直接通过浏览器自身确认是否过期即可，所以不会产生额外的流量。此种方法非常适合不经常变动的资源。（如果经常更新的文件，不建议使用expires来缓存），我这里设置3d，表示在这3天之内访问这个URL，发送一个请求，比对服务器该文件最后更新时间没有变化，则不会从服务器抓取，返回状态码304，如果有修改，则直接从服务器重新下载，返回状态码200。

配置

- 假设有文件 /mypath/image/a.png

  ```shell
  server { # server块
  	 listen       80; #监听端口
        server_name  192.168.100.100;# 域名
        # 匹配到 /image
        location /image/ {	
          root   /mypath/; # 去此目录下找对应文件
          autoindex on; # 列出当前目录下的文件及文件夹
        }
  }
  ```

  访问 192.168.100.100/image 则得到一个类似ftp的页面，列出/mypath/static/image文件夹下的文件（因为配置了 autoindex on;）；

  访问 192.168.100.100/image/a.png 则得到一张图片，即 /mypath + /image/a.png ；

其它动态请求进行代理配置即可。

## server详解

```shell
server { # server块
	listen       80; #监听端口
     server_name  192.168.100.100;# 域名
}
```



## location详解

### 匹配类型

优先级：

(location = ) > (location /xxx/yyy/zzz) > (location ^~) > (location ~，~*) >(location /起始路径) > (location /)

1. ```shell
   # 1.=匹配
   location = / {
   	#精准匹配，主机名后面不能带任何的字符串
   }
   ```

2. ```shell
   # 2．通用匹配
   location /xxx{
   	#匹配所有以/xxx开头的路径
   }
   
   ```

3. ```shell
   # 3．正则匹配
   location ~ /xxx {
   	#匹配所有以/xxx开头的路径
   }
   ```

4. ```shell
   # 4．匹配开头路径
   location ^~ /images/ {
   #匹配所有以/images/开头的路径
   }
   ```

5. ```shell
   # 5.匹配后缀
   location ~* \. (gifl jpglpng)$ {
   #匹配以gif或者jpg或者png为结尾的路径
   }
   ```

# Nginx深入

## Nginx变量

Nginx的配置文件使用的就是一门微型的编程语言，变量说白了就是存放“值”的容器。而所谓“值”，在许多编程语言里，既可以是3.14这样的数值，也可以是 hello world 这样的字符串，甚至可以是像数组、哈希表这样的复杂数据结构。然而，在 Nginx 配置中，变量只能存放一种类型的值，因为也只存在一种类型的值，那就是字符串。

```shell
set $a "hello world";
```

所有的 Nginx 变量在 Nginx 配置文件中引用时都须带上 $ 前缀，直接把变量嵌入到字符串常量中以构造出新的字符串：

### 变量插值

关键在于引号

```shell
# 1
$tt="123" 
print 'this is $tt.'; # this is $tt.
print "this is $tt."; # this is 123.
# 2 
set $a hello;
set $b "$a, $a"; # hello， hello

# 3 获取美元符$
geo $dollar { # 借助geo模块定义变量
    default "$";
}
server {
    listen 8080;
    location /test {
        echo "This is a dollar sign: $dollar"; # This is a dollar sign: $
    }
}
```

使用花括号

```shell
server {
    ...
    location /test-brace {
        set $first "hello ";
        echo "${first}world"; # hello world
    }
}
```

### 变量生命周期

Nginx 变量的创建和赋值操作发生在全然不同的时间阶段。Nginx 变量的创建只能发生在 Nginx 配置加载的时候，或者说 Nginx 启动的时候；而赋值操作则只会发生在请求实际处理的时候。这意味着不创建而直接使用变量会导致启动失败，同时也意味着我们无法在请求处理时动态地创建新的 Nginx 变量。我们无法在请求处理时动态地创建新的 Nginx 变量。

Nginx 变量名的可见范围虽然是整个配置，但每个请求都有所有变量的独立副本，或者说都有各变量用来存放值的容器的独立副本，彼此互不干扰。比如前面我们请求了/bar接口后，\$foo变量被赋予了值32，但它丝毫不会影响后续对/foo接口的请求所对应的$foo值（它仍然是空的！），因为各个请求都有自己独立的$foo变量的副本。
Nginx 变量理解成某种在请求之间全局共享的东西，或者说“全局变量”。而事实上，Nginx 变量的生命期是不可能跨越请求边界的

```shell
server {
        listen 8080;
        location /foo {
            echo "foo = [$foo]";
        }
        location /bar {
            set $foo 32;
            echo "foo = [$foo]";
        }
}
```

```shell
# 请求
$ curl 'http://localhost:8080/bar'
foo = [32]
$ curl 'http://localhost:8080/foo'
foo = []
```

简而言之，nginx的全局变量生命周期是和请求的生命周期一致，每个子请求有自己的全局变量。 

### Nginx内置变量

在配置基于nginx服务器的网站时，必然会用到 nginx内置变量。内置变量存放在  ngx_http_core_module 模块中，变量的命名方式和apache 服务器变量是一致的。总而言之，这些变量代表着客户端请求头的内容，例如\$http_user_agent, $http_cookie, 等等。下面是nginx支持的所有内置变量：

```shell
$arg_name

# 请求中的的参数名，即“?”后面的arg_name=arg_value形式的arg_name

$args

# 请求中的参数值

$binary_remote_addr

# 客户端地址的二进制形式, 固定长度为4个字节

$body_bytes_sent

# 传输给客户端的字节数，响应头不计算在内；这个变量和Apache的mod_log_config模块中的“%B”参数保持兼容

$bytes_sent

# 传输给客户端的字节数 (1.3.8, 1.2.5)

$connection

# TCP连接的序列号 (1.3.8, 1.2.5)

$connection_requests

# TCP连接当前的请求数量 (1.3.8, 1.2.5)

$content_length

# “Content-Length” 请求头字段

$content_type

# “Content-Type” 请求头字段

$cookie_name

# cookie名称

$document_root

# 当前请求的文档根目录或别名

$document_uri

# 同 $uri

$host

# 优先级如下：HTTP请求行的主机名>”HOST”请求头字段>符合请求的服务器名

$hostname

# 主机名

$http_name

# 匹配任意请求头字段； 变量名中的后半部分“name”可以替换成任意请求头字段，如在配置文件中需要获取http请求头：“Accept-Language”，那么将“－”替换为下划线，大写字母替换为小写，形如：$http_accept_language即可。

$https

# 如果开启了SSL安全模式，值为“on”，否则为空字符串。

$is_args

# 如果请求中有参数，值为“?”，否则为空字符串。

$limit_rate

# 用于设置响应的速度限制，详见 limit_rate。

$msec

# 当前的Unix时间戳 (1.3.9, 1.2.6)

$nginx_version

# nginx版本

$pid

工作进程的PID

$pipe

# 如果请求来自管道通信，值为“p”，否则为“.” (1.3.12, 1.2.7)

$proxy_protocol_addr

# 获取代理访问服务器的客户端地址，如果是直接访问，该值为空字符串。(1.5.12)

$query_string

# 同 $args

$realpath_root

# 当前请求的文档根目录或别名的真实路径，会将所有符号连接转换为真实路径。

$remote_addr

# 客户端地址

$remote_port

客户端端口

$remote_user

用于HTTP基础认证服务的用户名

$request

# 代表客户端的请求地址

$request_body

# 客户端的请求主体

# 此变量可在location中使用，将请求主体通过proxy_pass, fastcgi_pass, uwsgi_pass, 和 scgi_pass传递给下一级的代理服务器。

$request_body_file

# 将客户端请求主体保存在临时文件中。文件处理结束后，此文件需删除。如果需要之一开启此功能，需要设置client_body_in_file_only。如果将次文件传递给后端的代理服务器，需要禁用request body，即设置proxy_pass_request_body off，fastcgi_pass_request_body off, uwsgi_pass_request_body off, or scgi_pass_request_body off 。

$request_completion

# 如果请求成功，值为”OK”，如果请求未完成或者请求不是一个范围请求的最后一部分，则为空。

$request_filename

# 当前连接请求的文件路径，由root或alias指令与URI请求生成。

$request_length

# 请求的长度 (包括请求的地址, http请求头和请求主体) (1.3.12, 1.2.7)

$request_method

# HTTP请求方法，通常为“GET”或“POST”

$request_time

# 处理客户端请求使用的时间 (1.3.9, 1.2.6); 从读取客户端的第一个字节开始计时。

$request_uri

# 这个变量等于包含一些客户端请求参数的原始URI，它无法修改，请查看$uri更改或重写URI，不包含主机名，例如：”/cnphp/test.php?arg=freemouse”。

$scheme

# 请求使用的Web协议, “http” 或 “https”

$sent_http_name

# 可以设置任意http响应头字段； 变量名中的后半部分“name”可以替换成任意响应头字段，如需要设置响应头Content-length，那么将“－”替换为下划线，大写字母替换为小写，形如：$sent_http_content_length 4096即可。

$server_addr

# 服务器端地址，需要注意的是：为了避免访问linux系统内核，应将ip地址提前设置在配置文件中。

$server_name

# 服务器名，www.cnphp.info

$server_port

# 服务器端口

$server_protocol

# 服务器的HTTP版本, 通常为 “HTTP/1.0” 或 “HTTP/1.1”

$status

# HTTP响应代码 (1.3.2, 1.2.2)

$tcpinfo_rtt, $tcpinfo_rttvar, $tcpinfo_snd_cwnd, $tcpinfo_rcv_space

# 客户端TCP连接的具体信息

$time_iso8601

# 服务器时间的ISO 8610格式 (1.3.12, 1.2.7)

$time_local

# 服务器时间（LOG Format 格式） (1.3.12, 1.2.7)

$uri

# 请求中的当前URI(不带请求参数，参数位于$args)，可以不同于浏览器传递的$request_uri的值，它可以通过内部重定向，或者使用index指令进行修改，$uri不包含主机名，如”/foo/bar.html”。
```



## 常见模块

### geo

根据客户端地址创建新变量，常见写法

```shell
geo $remote_addr $geo {# 跟姐客户端IP定义 $geo 变量的值
    default 0; # 这里0和1都是可以的
    127.0.0.1 1;
    192.168.0.1 1;
    192.168.1.0/24 1; # 网段写法，都可以
}
```

location中使用

```shell
# 如果不在白名单，返回403
location = / {
	if($geo = 0){  
		return 403; 
	}
}
```

注意点

1. 如果geo指令后不输入address，那么默认就使用变量remite_addr作为ip地址
2. {} 内的指令匹配：优先最长匹配

### map模块

ngx_http_map_module ；默认编译进Nginx ；通过--without-http_map_module禁用

基于已有变量，使用类似switch{case: ... default: ...}的语法创建新变量，为其他基于变量值实现功能的模块提供更多的可能性。

```shell
# 跟姐主机名称给 $name赋值
map $http_host $name {
    hostnames;
    default 0;
    ~map!.taolw+l.org.cn 1;
    *.taohui.org.cn 2;
    map.taohui.tech 3;
    map.taohui.* 4;
}
# 使用
server {
    listen 10001;
    default_type text/plain;
    location /{
        return 200 '$name:aaa'; 
    }
}
```

default规则：
没有匹配到任何规则时，使用default缺失default时，返回空字符串给新变量

## 指令

### set指令

set 指令是用于定义一个变量，并且赋值

```shell
server,location,if # 应用环境
```

例：

```shell
set $a hello;
set $b "$a, $a"; # hello， hello
```

### return 指令

return 指令用于返回状态码给客户端

```shell
server,location,if # 应用环境
```

例：

```shell
location ~* \.sh$ {
   return 403;
 }
```

# 其它

## Nginx高可用

问题：Nginx作为客户端与后端（tomcat）的中间桥梁，假如Nginx服务器宕机，则客户端无法访问后端tomcat，所以要整高可用。

### keepalive

//todo

## Nginx原理

### master与worker进程

请求争抢机制//todo

好处：

- 热部署；‘可以使用nginx -s reload热部署，利用nginx进行热部署操作
- 每个woker是独立的进程，如果有其中的一个woker,出现问题，其他woker独立的继续进行争抢,实现请求过程,不会造成服务中断
- 首先，对于每个worker进程来说，独立的进程，不需要加锁，所以省掉了锁带来的开销，同时在编程以及问题查找时，也会方便很多。其次，采用独立的进程，可以让互相之间不会影响，一个进程退出后，其它进程还在工作，服务不会中断，master进程则很快启动新的worker进程。当然，worker进程的异常退出，肯定是程序有bug了，异常退出，会导致当前worker上的所有请求失败，不过不会影响到所有请求，所以降低了风险。
- Nginx同redis,类似都采用了io.多路复用机制,每个worker都是一个独立的进程，但每个进程里只有一个主线程，通过异步非阻塞的方式来处理请求，即使是千上万个请求也不在话下。每个worker的线程可以把一个cpu的性能发挥到极致。所以 worker数和服务器的cpu数相等是最为适宜的。设少了会浪费cpu性能，设多了会造成cpu频繁切换上下文带来的损耗。

### 连接数worker_connection

这个值是表示每个worker进程所能建立连接的最大值，所以，一个nginx.能建立的最大连接数，应该是 worker_connections * worker_processes。当然，这里说的是最大连接数，对于HTTP请求本地资源来说，能够支持的最大并发数量是worker_connections * worker_processes，如果是支持http1.1的浏览器每次访问要占两个连接，所以普通的静态访问最大并发数是：worker_connections * worker_processes / 2，而如果是HTTP作为反向代理来说，最大并发数量应该是worker..connections * worker_processes / 4。因为作为反向代理服务器,每个并发会建立与客户端的连接和与后端服务的连接，会占用两个连接。