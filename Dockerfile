# Используем Maven с OpenJDK 21
FROM maven:3.8.5-eclipse-temurin-21-alpine

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем все файлы проекта в контейнер
COPY . .

# Скачиваем зависимости и собираем проект
RUN mvn clean package

# Выполняем тесты при запуске контейнера
CMD ["mvn", "test"]
