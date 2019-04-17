FROM java:8-jdk-alpine

COPY /var/lib/jenkins/workspace/Event_Planning/backend/EventPlanning/target/eventplanning-0.0.1-SNAPSHOT.jar  /usr/app/

WORKDIR /usr/app

ENTRYPOINT ["java","-jar","eventplanning-0.0.1-SNAPSHOT.jar"]  
