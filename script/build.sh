#!/usr/bin/env bash
set -e
export JAVA_HOME="/usr/local/openjdk8"
export PATH=$PATH:$JAVA_HOME
mvn clean package -Dmaven.test.skip=true

cd target
pwd
cat > Dockerfile <<EOF
FROM openjdk:8-jre-slim

COPY user-center-1.0.0.jar /app/index.jar

ENTRYPOINT [ "java", "-jar", "/app/index.jar" ]
EOF

docker build -t $NAME .
docker push $NAME
