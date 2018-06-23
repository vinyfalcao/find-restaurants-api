FROM openjdk:alpine
LABEL maintainer="vinyfalcao@hotmail."
RUN apk --no-cache add netcat-openbsd
COPY target/api-1.0.0.jar /opt/spring-cloud/lib/
RUN java -jar /opt/spring-cloud/lib/api-1.0.0.jar