FROM java:8-jdk-alpine

COPY  /var/lib/jenkins/workspace/Event_Planning/backend/EventPlanning/target/eventplanning-0.0.1-SNAPSHOT.jar  /usr/home/parul

WORKDIR /usr/home/parul

ENTRYPOINT ["java","-jar","eventplanning-0.0.1-SNAPSHOT.jar"]  
