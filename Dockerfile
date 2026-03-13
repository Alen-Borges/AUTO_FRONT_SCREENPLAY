FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY build.gradle settings.gradle ./
RUN apt-get update && apt-get install -y wget unzip && \
    wget https://services.gradle.org/distributions/gradle-8.5-bin.zip && \
    unzip gradle-8.5-bin.zip && \
    mv gradle-8.5 /opt/gradle && \
    ln -s /opt/gradle/bin/gradle /usr/bin/gradle
COPY src ./src
CMD ["gradle", "test", "--info"]
