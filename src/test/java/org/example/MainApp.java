package org.example;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("Запуск приложения...");

        // Запуск тестов
        org.junit.runner.JUnitCore.main("ApiTests");
    }
}
