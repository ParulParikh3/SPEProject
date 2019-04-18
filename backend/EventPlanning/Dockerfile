FROM java:8-jdk-alpine

ADD  /var/lib/jenkins/workspace/Build/backend/Event_Planning/target/eventplanning-0.0.1-SNAPSHOT.jar  /usr/app/

WORKDIR /usr/app

ENTRYPOINT ["java","-jar","eventplanning-0.0.1-SNAPSHOT.jar"]  
