FROM openjdk:8
EXPOSE 8080
#RUN gradle clean build
ADD build/libs/write.jar writeimage.jar
ENTRYPOINT ["java","-jar", "/writeimage.jar"]