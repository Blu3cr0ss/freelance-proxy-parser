FROM gradle:7.4.2-jdk17
WORKDIR /app
COPY . .
# RUN ./gradlew build
# RUN gradle shadowJar

RUN gradle clean

CMD ["gradle","run"]
EXPOSE 8080