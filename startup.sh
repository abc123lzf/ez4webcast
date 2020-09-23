#!/usr/bin/env bash

if [[ "$1" == "-p" || "$2" == "-p" ]]; then
  git pull
  if [ $? != 0 ]; then
    echo "Git拉取失败"
    exit 1
  fi
fi

python change-profile.py prod

if [ $? != 0 ]; then
  echo "切换Profile失败"
  exit 1
fi

mvn compile
if [ $? != 0 ]; then
  echo "Maven编译失败"
  exit 1
fi

mvn clean install -Dskiptests
if [ $? != 0 ]; then
  echo "Maven安装失败"
  exit 1
fi

RUNNING_PID=`cat .pid`
if [ $? == 0 ]; then
  echo "停止运行中的服务PID: $RUNNING_PID"
  kill -15 $RUNNING_PID
  rm -f .pid
fi

if [[ "$1" == "-t" || "$2" == "-t" ]]; then
  java -XX:+UseG1GC -jar target/ez4webcast-1.0.jar
else
  nohup java -XX:+UseG1GC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:/var/log/jsushoppingmall-gc.log -jar target/ez4webcast-1.0.jar > /dev/null 2>&1& echo $! > .pid
  RUNNING_PID=`cat .pid`
  echo "启动中的服务PID: $RUNNING_PID"
fi

