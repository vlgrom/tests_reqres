# Используем образ Maven
FROM maven:3.8.6-jdk-11 AS build

# Указываем рабочую директорию
WORKDIR /app

# Копируем pom.xml и файл с исходным кодом
COPY pom.xml .
COPY src ./src

# Собираем проект
RUN mvn clean package

# Выбираем образ JDK для выполнения тестов
FROM openjdk:11-jre-slim

# Копируем скомпилированный проект из предыдущего шага
COPY --from=build /app/target/my-project-1.0.jar /app/my-project.jar

# Устанавливаем Allure для генерации отчетов
RUN apt-get update && \
    apt-get install -y allure

# Определяем команду по умолчанию
CMD ["java", "-jar", "/app/my-project.jar"]