# MySpringBoot
Простое приложение Spring Boot.
## Требования
1. Java 11+
2. Maven
## Начало работы
1. Убедитесь, что требования выполнены.
2. Клонируйте репозиторий.
3. Запустите приложение, находясь в папке MySpringBoot с помощью команды mvn spring-boot:run .
## Описание
Главный класс: Application.java
В приложении использованы технологии для работы с базой данных, web-приложением и безопасностью пользователей.
* Работа с базой данных реализована с помощью JDBC и JPA.
  * Для JDBC стандарта использован подход JDBCTemplate, который сам заботится об открытии и закрытии соединения с базой данных, позволяет создавать запросы и перехвтывает исключения JDBC, представляя их в корректном виде. Для работы созданы сущности, репозитории и их реализация.
  * JPA - это спецификация API Java EE. В проекте используется ее реализация Hibernate. Для JPA созданы сущности и репозитории.
* Модель MVC представлена с помощью взаимодействия контроллеров, сервисов и dto.
* Безопасность представлена кодированием паролей пользователей при сохранении в базу данных с помощью PasswordEncoder. При аутентификации происходит выдача bearer токена, с помощью которого происходит авторизация.
* В пакете `screenshots` представлены некоторые результаты работы endpoint-ов.
## Документация
[MySpringBoot](https://oksyou.github.io/MySpringBoot/)
