#!/bin/sh

yum install -y git gcc gcc-c++ autoconf automake zlib zlib-devel openssl openssl-devel pcre || \
 apt install -y git gcc gcc-c++ autoconf automake zlib zlib-devel openssl openssl-devel pcre

cd tmp || exit
wget http://nginx.org/download/nginx-1.16.1.tar.gz
tar -zxvf nginx-1.16.1.tar.gz -C /usr/local

cd /usr/local/nginx-1.16.1 || exit

cd src || exit

git clone https://github.com/winshining/nginx-http-flv-module.git
mv nginx-http-flv-module-master nginx-http-flv-module
cd ..

./configure --add-module=src/nginx-http-flv-module \
  --with-stream \
   --prefix=/usr/local/nginx  \
    --with-http_ssl_module \
    --with-http_stub_status_module \
    --with-http_gzip_static_module \
    --with-pcre && make && make install