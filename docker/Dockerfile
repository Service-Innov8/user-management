FROM alpine:3.18.0

ENV MAVEN_VERSION 3.9.2
ENV MAVEN_HOME /usr/lib/maven
ENV PATH=${MAVEN_HOME}/bin:${PATH}

RUN apk update
RUN apk add --no-cache openjdk17
RUN apk add --no-cache curl
RUN mkdir -p /usr/lib/maven
RUN curl -fsSL https://dlcdn.apache.org/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
    | tar -xzC /usr/lib/maven --strip-components=1
RUN ln -s /usr/lib/maven/bin/mvn /usr/bin/mvn
RUN apk del curl

WORKDIR /app

COPY ./pom.xml ./pom.xml

RUN mvn dependency:go-offline

COPY ./src/ ./src

RUN mvn clean install -DskipTests

EXPOSE 8081

CMD ["mvn", "spring-boot:run"]
