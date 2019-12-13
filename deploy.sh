#!/bin/sh

echo "Compile project..."
mvn compile
echo "Clean install project..."
mvn clean install
echo "Run project..."
java -server jar target/ez4webcast-1.0.jar
