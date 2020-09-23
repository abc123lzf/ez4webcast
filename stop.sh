#!/usr/bin/env bash

RUNNING_PID=`cat .pid`
if [ $? == 0 ]; then
  echo "停止运行中的服务PID: $RUNNING_PID"
  kill -15 $RUNNING_PID
  rm -f .pid
fi
