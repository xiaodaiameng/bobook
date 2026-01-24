# 使用你已经成功拉取的 alpine 镜像
FROM alpine:latest

# 安装 OpenJDK 17
RUN apk add --no-cache openjdk17

# 设置工作目录
WORKDIR /app

# 复制 JAR 文件
COPY target/sp001-0.0.1-SNAPSHOT.jar app.jar

# 暴露端口
EXPOSE 8080

# 运行应用
ENTRYPOINT ["java", "-jar", "app.jar"]