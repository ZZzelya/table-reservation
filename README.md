<div align="center">

# 🍽️ TABLE RESERVATION API

### Система бронирования столиков в ресторане

<img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=500&size=24&duration=3000&pause=500&color=2F81F7&center=true&vCenter=true&width=500&lines=Java+17;Spring+Boot+3;JPA+%2F+Hibernate;H2+Database;N%2B1+Solved;%40Transactional+Demo" alt="Typing SVG" />

[![Java](https://img.shields.io/badge/Java-17-%23ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-%236DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![JPA](https://img.shields.io/badge/JPA-Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white)](https://hibernate.org/)
[![H2](https://img.shields.io/badge/H2-Database-0078D4?style=for-the-badge&logo=h2&logoColor=white)](https://www.h2database.com/)
[![Postman](https://img.shields.io/badge/Postman-Testing-FF6C37?style=for-the-badge&logo=postman&logoColor=white)](https://www.postman.com/)
[![SonarCloud](https://img.shields.io/badge/SonarCloud-Quality%20Gate-%23F3702A?style=for-the-badge&logo=sonarcloud&logoColor=white)](https://sonarcloud.io/summary/overall?id=ZZzelya_table-reservation&branch=main)

---

## 📋 СОДЕРЖАНИЕ

- [📖 О проекте](#-о-проекте)
- [✅ Выполненные требования](#-выполненные-требования)
- [✨ Функциональность](#-функциональность)
- [📊 Демонстрация N+1 проблемы](#-демонстрация-n1-проблемы)
- [🔄 Демонстрация транзакций](#-демонстрация-транзакций)
- [🛠️ Технологический стек](#️-технологический-стек)
- [🔗 SonarCloud анализ](#-sonarcloud-анализ)
- [🚀 Запуск проекта](#-запуск-проекта)
- [📬 Примеры запросов для Postman](#-примеры-запросов-для-postman)

---

## 📖 О ПРОЕКТЕ

Данный проект представляет собой **REST API сервис** для бронирования столиков в ресторане. Разработан в рамках
лабораторной работы по дисциплине *"Программирование на языках высокого уровня"*.

**Цель работы:** Создание Spring Boot приложения с подключением реляционной базы данных, реализация связей между
сущностями, демонстрация и решение проблемы N+1, исследование поведения транзакций.

**Ключевые особенности:**

- ✅ 5 JPA сущностей со связями OneToMany и ManyToMany
- ✅ Полноценные CRUD операции
- ✅ Демонстрация и решение проблемы N+1 через fetch join
- ✅ Исследование @Transactional (частичное сохранение vs полный откат)
- ✅ Интеграция с SonarCloud для контроля качества кода

---

## ✅ ВЫПОЛНЕННЫЕ ТРЕБОВАНИЯ

### 1. Подключение реляционной БД

- **H2 Database** (in-memory)
- **JPA / Hibernate** ORM

### 2. Модель данных (5 сущностей)

| Сущность          | Описание     | Связи                                                    |
|-------------------|--------------|----------------------------------------------------------|
| `Restaurant`      | Ресторан     | OneToMany → `Table`, `MenuItem`                          |
| `RestaurantTable` | Столик       | ManyToOne → `Restaurant`, OneToMany → `Reservation`      |
| `Customer`        | Клиент       | OneToMany → `Reservation`                                |
| `MenuItem`        | Блюдо в меню | ManyToOne → `Restaurant`, ManyToMany → `Reservation`     |
| `Reservation`     | Бронирование | ManyToOne → `Customer`, `Table`, ManyToMany → `MenuItem` |

### 3. Реализованные связи

- ✅ **OneToMany:** `Restaurant` → `RestaurantTable`
- ✅ **ManyToMany:** `Reservation` ↔ `MenuItem`

### 4. CRUD операции

Полноценные CRUD операции для всех 5 сущностей:

- `GET` (все записи, по ID, с фильтрацией)
- `POST` (создание)
- `PUT` (обновление)
- `DELETE` (удаление)

### 5. CascadeType и FetchType

| Связь                            | CascadeType | FetchType | Обоснование                         |
|----------------------------------|-------------|-----------|-------------------------------------|
| `Restaurant` → `RestaurantTable` | `ALL`       | `LAZY`    | Столики не существуют без ресторана |
| `Restaurant` → `MenuItem`        | `ALL`       | `LAZY`    | Меню не существует без ресторана    |
| `Reservation` ↔ `MenuItem`       | -           | `LAZY`    | Оптимизация производительности      |

### 6. Проблема N+1

- ✅ **Демонстрация** проблемы N+1
- ✅ **Решение** через `fetch join`

### 7. Транзакции

- ✅ **Частичное сохранение** без `@Transactional`
- ✅ **Полный откат** с `@Transactional`

---

## ✨ ФУНКЦИОНАЛЬНОСТЬ

### 🏨 Управление ресторанами (`/api/restaurants`)

| Метод    | Endpoint                                | Описание                           |
|----------|-----------------------------------------|------------------------------------|
| `GET`    | `/api/restaurants`                      | Все рестораны                      |
| `GET`    | `/api/restaurants/{id}`                 | Ресторан по ID                     |
| `GET`    | `/api/restaurants/{id}/with-tables`     | Ресторан со столиками (fetch join) |
| `GET`    | `/api/restaurants/by-cuisine?cuisine=`  | Поиск по типу кухни                |
| `GET`    | `/api/restaurants/demo/nplus1?cuisine=` | Демонстрация N+1                   |
| `GET`    | `/api/restaurants/demo/solved?cuisine=` | Решение N+1                        |
| `POST`   | `/api/restaurants`                      | Создать ресторан                   |
| `PUT`    | `/api/restaurants/{id}`                 | Обновить ресторан                  |
| `DELETE` | `/api/restaurants/{id}`                 | Удалить ресторан                   |

### 👤 Управление клиентами (`/api/customers`)

| Метод    | Endpoint                                | Описание                |
|----------|-----------------------------------------|-------------------------|
| `GET`    | `/api/customers`                        | Все клиенты             |
| `GET`    | `/api/customers/{id}`                   | Клиент по ID            |
| `GET`    | `/api/customers/{id}/with-reservations` | Клиент с бронированиями |
| `POST`   | `/api/customers`                        | Создать клиента         |
| `PUT`    | `/api/customers/{id}`                   | Обновить клиента        |
| `DELETE` | `/api/customers/{id}`                   | Удалить клиента         |

### 🪑 Управление столиками (`/api/tables`)

| Метод    | Endpoint                                | Описание          |
|----------|-----------------------------------------|-------------------|
| `GET`    | `/api/tables/restaurant/{restaurantId}` | Столики ресторана |
| `GET`    | `/api/tables/{id}`                      | Столик по ID      |
| `POST`   | `/api/tables`                           | Создать столик    |
| `PUT`    | `/api/tables/{id}`                      | Обновить столик   |
| `DELETE` | `/api/tables/{id}`                      | Удалить столик    |

### 🍕 Управление меню (`/api/menu-items`)

| Метод    | Endpoint                                    | Описание       |
|----------|---------------------------------------------|----------------|
| `GET`    | `/api/menu-items/restaurant/{restaurantId}` | Меню ресторана |
| `GET`    | `/api/menu-items/{id}`                      | Блюдо по ID    |
| `POST`   | `/api/menu-items`                           | Создать блюдо  |
| `PUT`    | `/api/menu-items/{id}`                      | Обновить блюдо |
| `DELETE` | `/api/menu-items/{id}`                      | Удалить блюдо  |

### 📅 Система бронирования (`/api/reservations`)

| Метод    | Endpoint                                   | Описание                     |
|----------|--------------------------------------------|------------------------------|
| `POST`   | `/api/reservations`                        | ✅ Успешное бронирование      |
| `POST`   | `/api/reservations/demo/without-tx`        | ⚠️ Демо без транзакции       |
| `POST`   | `/api/reservations/demo/with-tx`           | 🔄 Демо с транзакцией        |
| `POST`   | `/api/reservations/restaurant-with-tables` | 🏗️ Создать ресторан со всем |
| `GET`    | `/api/reservations/customer/{customerId}`  | Бронирования клиента         |
| `GET`    | `/api/reservations/{id}`                   | Бронирование по ID           |
| `GET`    | `/api/reservations/{id}/with-details`      | Бронирование с блюдами       |
| `PATCH`  | `/api/reservations/{id}/status?status=`    | Обновить статус              |
| `DELETE` | `/api/reservations/{id}`                   | Отменить бронирование        |

---

### Таблица для демонстрации работы проблемы N+1

| Сценарий              | @Transactional | Бронирование      | Блюда            | Итог                    |
|-----------------------|----------------|-------------------|------------------|-------------------------|
| Успешное бронирование | ✅              | ✅ Сохранилось     | ✅ Сохранились    | ✅ Всё ОК                |
| Ошибка БЕЗ транзакции | ❌              | ✅ **Сохранилось** | ❌ Не сохранились | ⚠️ **Частичные данные** |
| Ошибка С транзакцией  | ✅              | ❌ **Откатилось**  | ❌ Не сохранились | ✅ **Чистая БД**         |

---

## 🛠️ ТЕХНОЛОГИЧЕСКИЙ СТЕК

<div align="center">

| Категория            | Технологии                              |
|----------------------|-----------------------------------------|
| **Язык**             | Java 17                                 |
| **Фреймворк**        | Spring Boot 3.2                         |
| **ORM**              | JPA, Hibernate                          |
| **База данных**      | H2 (in-memory)                          |
| **Сборка**           | Maven                                   |
| **Архитектура**      | REST API, Controller-Service-Repository |
| **Качество кода**    | Checkstyle, SonarCloud                  |
| **Тестирование API** | Postman                                 |

</div>

---

## 🔗 SONARCLOUD АНАЛИЗ

<div align="center">
  <a href="https://sonarcloud.io/summary/overall?id=ZZzelya_table-reservation&branch=main">
    <img src="https://sonarcloud.io/api/project_badges/measure?project=ZZzelya_table-reservation&metric=alert_status" alt="Quality Gate Status">
    <img src="https://sonarcloud.io/api/project_badges/measure?project=ZZzelya_table-reservation&metric=bugs" alt="Bugs">
    <img src="https://sonarcloud.io/api/project_badges/measure?project=ZZzelya_table-reservation&metric=code_smells" alt="Code Smells">
    <img src="https://sonarcloud.io/api/project_badges/measure?project=ZZzelya_table-reservation&metric=coverage" alt="Coverage">
    <img src="https://sonarcloud.io/api/project_badges/measure?project=ZZzelya_table-reservation&metric=duplicated_lines_density" alt="Duplicated Lines">
  </a>

👉 [**Перейти к полному анализу на SonarCloud
**](https://sonarcloud.io/summary/overall?id=ZZzelya_table-reservation&branch=main)

</div>

---

## 🚀 ЗАПУСК ПРОЕКТА

### Предварительные требования

- JDK 17 или выше
- Maven 3.6+

### Установка и запуск

```bash
# 1. Клонируйте репозиторий
git clone https://github.com/ZZzelya/table-reservation.git

# 2. Перейдите в директорию проекта
cd table-reservation

# 3. Соберите проект с помощью Maven
./mvnw clean package

# 4. Запустите приложение
java -jar target/table-reservation-*.jar
```

---

## 📬 ПРИМЕРЫ ЗАПРОСОВ ДЛЯ POSTMAN

### 1. Создание клиента

```json
POST http://localhost:8080/api/customers
{
"name": "Иван Петров",
"email": "ivan@mail.com",
"phoneNumber": "+375-29-123-45-67"
}
```

### 2. Создание ресторана

```json
POST http://localhost:8080/api/restaurants
{
"name": "Итальянский дворик",
"address": "ул. Ленина, 10",
"phoneNumber": "+375-29-987-65-43",
"cuisineType": "Italian",
"tableCount": 10
}
```

### 3. Создание столика

```json
POST http://localhost:8080/api/tables
{
"restaurantId": 1,
"tableNumber": 1,
"capacity": 4,
"isAvailable": true
}
```

### 4. Создание блюда

```json
POST http://localhost:8080/api/menu-items
{
"restaurantId": 1,
"name": "Паста Карбонара",
"price": 650.00
}
```

### 5. Успешное бронирование

```json
POST http://localhost:8080/api/reservations
{
"customerId": 1,
"tableId": 1,
"reservationTime": "2024-03-15T17:00:00Z",
"partySize": 2,
"menuItemIds": [1]
}
```

### 6. Демонстрация N+1 проблемы

```
GET http://localhost:8080/api/restaurants/demo/nplus1?cuisine=Italian
```

### 7. Демонстрация решения N+1

```
GET http://localhost:8080/api/restaurants/demo/solved?cuisine=Italian
```

### 8. Частичное сохранение (БЕЗ @Transactional)

```json
POST http://localhost:8080/api/reservations/demo/without-tx
{
"customerId": 1,
"tableId": 1,
"reservationTime": "2024-03-15T18:00:00Z",
"partySize": 2,
"menuItemIds": [999]
}
```

### 9. Полный откат (С @Transactional)

```json
POST http://localhost:8080/api/reservations/demo/with-tx
{
"customerId": 1,
"tableId": 1,
"reservationTime": "2024-03-15T19:00:00Z",
"partySize": 2,
"menuItemIds": [999]
}
```

### 10. Проверка бронирований клиента

```
GET http://localhost:8080/api/reservations/customer/1
```

