# Hash Service

#### Build the JAR using Maven
``
mvn clean install
``

#### Build the Docker Image using the Dockerfile
``
docker build --tag hash-service-container:0.1 .
``

#### Start a Docker Container using the Docker Image
``
docker run -d -t -p 127.0.0.1:8080:8080 --name hash-service hash-service-container:0.1
``

#### Connect to the bash of Docker Container 
``
docker exec -it hash-service /bin/bash
``

#### Start the Java Application inside the Docker Dontainer
``
java -jar -Dspring.profiles.active=dev hash-service-1.0-SNAPSHOT.jar
``