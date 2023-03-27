FROM openjdk:17
EXPOSE 5500
COPY . .
ADD build/libs/cloudservice.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]