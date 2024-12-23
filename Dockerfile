# Используем OpenJDK 21 в качестве базового образа
FROM eclipse-temurin:21-jdk

# Устанавливаем Maven вручную
RUN apt-get update && apt-get install -y maven

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файлы проекта
COPY . .

# Скачиваем зависимости и собираем проект
RUN mvn clean package

# Запускаем тесты
CMD ["mvn", "test"]
