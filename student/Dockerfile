FROM maven:3-openjdk-17-slim as build

COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean install


FROM openjdk:17-jdk
COPY --from=build /home/app/target/student-2.7.0.jar /usr/local/lib/student.jar

EXPOSE 8080
ENV LOG_NAME "student.log"

ENTRYPOINT ["java","-jar","-Dlogging.file.name=./log/${LOG_NAME}", "/usr/local/lib/student.jar"]

