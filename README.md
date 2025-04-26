# Bookstore Rate Limiter

Проект представляет собой Spring Boot приложение для книжного магазина с реализацией rate limiting и кэширования.

## Основные функции

- Аутентификация и авторизация пользователей
- Управление книгами (CRUD операции)
- Поиск книг с использованием Google Books API
- Rate limiting для защиты API
- Кэширование с использованием Redis
- Swagger документация API

## Технологии

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Redis
- PostgreSQL
- Docker
- Swagger/OpenAPI

## Установка и запуск

1. Клонируйте репозиторий:
```bash
git clone https://github.com/arypax/proj9.git
```

2. Создайте файл `.env` на основе `.env.example` и настройте переменные окружения

3. Запустите приложение с помощью Docker Compose:
```bash
docker-compose up -d
```

## API Endpoints

### Аутентификация
- POST /api/auth/register - Регистрация нового пользователя
- POST /api/auth/login - Вход в систему

### Книги
- GET /api/books - Получить список всех книг
- GET /api/books/{id} - Получить детали книги
- POST /api/books - Создать новую книгу
- PUT /api/books/{id} - Обновить книгу
- DELETE /api/books/{id} - Удалить книгу

### Поиск
- GET /api/search?query={query} - Поиск книг
- GET /api/search/google?query={query} - Поиск через Google Books API

### Кэш
- GET /api/cache/books - Получить кэшированные книги
- POST /api/cache/refresh - Обновить кэш

## Документация API

Swagger UI доступен по адресу: http://localhost:8080/swagger-ui.html

## Структура проекта

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── bookstore/
│   │           ├── config/         # Конфигурации Spring
│   │           ├── controller/     # REST контроллеры
│   │           ├── dto/           # Data Transfer Objects
│   │           ├── model/         # Сущности
│   │           ├── repository/    # Репозитории
│   │           ├── service/       # Бизнес-логика
│   │           └── BookstoreApplication.java
│   └── resources/
│       ├── application.properties # Конфигурация приложения
│       └── data.sql              # Начальные данные
```

## Лицензия

MIT 