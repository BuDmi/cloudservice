FROM openjdk:17
EXPOSE 8081
COPY . .
ADD build/libs/cloudservice.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]