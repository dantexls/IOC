FROM 100.125.0.198:20202/iss_smartcity/maven:latest

RUN mkdir -p /home/ioc-project

WORKDIR /home/ioc-project

ADD . ./

RUN mvn clean package

CMD ["java","-jar","/home/ioc-project/target/ioc-project-0.0.1-SNAPSHOT.jar"]