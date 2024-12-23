# Используем OpenJDK 21 в качестве базового образа
FROM eclipse-temurin:21-jdk

# Устанавливаем Maven вручную
RUN apt-get update && apt-get install -y maven

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файлы проекта
COPY . .

# Скачиваем зависимости
RUN mvn clean install

# Генерируем Allure-отчёт
RUN mvn allure:report

# Указываем папку site в качестве результата
CMD ["tail", "-f", "/dev/null"]
