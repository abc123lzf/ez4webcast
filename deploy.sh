#!/bin/sh

echo "Compile project..."
mvn compile
echo "Clean install project..."
mvn clean install
echo "Run project..."
mvn exec:java -Dexec.mainClass="com.lzf.ez4webcast.Bootstrap"
