FROM maven

WORKDIR /code

# Download dependencies
ADD pom.xml /code/pom.xml

# Adding source, compile and package into a fat jar
ADD src /code/src
RUN ["mvn", "package", "-DskipTests"]
# RUN ["mvn", "package"]

EXPOSE 8040
CMD ["java", "-jar", "target/icin-0.0.1-SNAPSHOT.jar"]