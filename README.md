<div align="center">

# 🍽️ TABLE RESERVATION API

### ✦ Система бронирования столиков в ресторане ✦

[![Java](https://img.shields.io/badge/Java-17-%23ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-%236DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![JPA](https://img.shields.io/badge/JPA-Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white)](https://hibernate.org/)
[![H2](https://img.shields.io/badge/H2-Database-0078D4?style=for-the-badge&logo=h2&logoColor=white)](https://www.h2database.com/)
[![Postman](https://img.shields.io/badge/Postman-Testing-FF6C37?style=for-the-badge&logo=postman&logoColor=white)](https://www.postman.com/)
[![SonarCloud](https://img.shields.io/badge/SonarCloud-Quality%20Gate-F3702A?style=for-the-badge&logo=sonarcloud&logoColor=white)](https://sonarcloud.io/summary/overall?id=ZZzelya_table-reservation&branch=main)

</div>

---

## 📖 О ПРОЕКТЕ

**REST API сервис** для бронирования столиков в ресторане. Разработан в рамках лабораторной работы по дисциплине *"
Программирование на языках высокого уровня"*.

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

<table>
  <tr>
    <th width="5%">№</th>
    <th width="25%">Требование</th>
    <th width="70%">Реализация</th>
  </tr>
  <tr>
    <td align="center">1</td>
    <td><b>Подключение реляционной БД</b></td>
    <td>H2 Database (in-memory) + JPA / Hibernate ORM</td>
  </tr>
  <tr>
    <td align="center">2</td>
    <td><b>5 сущностей</b></td>
    <td><code>Restaurant</code> • <code>RestaurantTable</code> • <code>Customer</code> • <code>MenuItem</code> • <code>Reservation</code></td>
  </tr>
  <tr>
    <td align="center">3</td>
    <td><b>OneToMany связь</b></td>
    <td><code>Restaurant</code> → <code>RestaurantTable</code></td>
  </tr>
  <tr>
    <td align="center">4</td>
    <td><b>ManyToMany связь</b></td>
    <td><code>Reservation</code> ↔ <code>MenuItem</code></td>
  </tr>
  <tr>
    <td align="center">5</td>
    <td><b>CRUD операции</b></td>
    <td>Create • Read • Update • Delete для всех сущностей</td>
  </tr>
  <tr>
    <td align="center">6</td>
    <td><b>CascadeType & FetchType</b></td>
    <td>Настроены и обоснованы</td>
  </tr>
  <tr>
    <td align="center">7</td>
    <td><b>Проблема N+1</b></td>
    <td>✅ Демонстрация • ✅ Решение через fetch join</td>
  </tr>
  <tr>
    <td align="center">8</td>
    <td><b>Транзакции</b></td>
    <td>⚠️ Частичное сохранение без @Transactional • 🔄 Полный откат с @Transactional</td>
  </tr>
</table>

---

## ✨ ФУНКЦИОНАЛЬНОСТЬ

### 🏨 Управление ресторанами (`/api/restaurants`)

| Метод    | Endpoint                                | Описание              |
|----------|-----------------------------------------|-----------------------|
| `GET`    | `/api/restaurants`                      | Все рестораны         |
| `GET`    | `/api/restaurants/{id}`                 | Ресторан по ID        |
| `GET`    | `/api/restaurants/{id}/with-tables`     | Ресторан со столиками |
| `GET`    | `/api/restaurants/by-cuisine?cuisine=`  | Поиск по кухне        |
| `GET`    | `/api/restaurants/demo/nplus1?cuisine=` | Демо N+1              |
| `GET`    | `/api/restaurants/demo/solved?cuisine=` | Решение N+1           |
| `POST`   | `/api/restaurants`                      | Создать ресторан      |
| `PUT`    | `/api/restaurants/{id}`                 | Обновить ресторан     |
| `DELETE` | `/api/restaurants/{id}`                 | Удалить ресторан      |

### 👤 Управление клиентами (`/api/customers`)

| Метод    | Endpoint                                | Описание         |
|----------|-----------------------------------------|------------------|
| `GET`    | `/api/customers`                        | Все клиенты      |
| `GET`    | `/api/customers/{id}`                   | Клиент по ID     |
| `GET`    | `/api/customers/{id}/with-reservations` | Клиент с бронями |
| `POST`   | `/api/customers`                        | Создать клиента  |
| `PUT`    | `/api/customers/{id}`                   | Обновить клиента |
| `DELETE` | `/api/customers/{id}`                   | Удалить клиента  |

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
| `GET`    | `/api/reservations/{id}/with-details`      | Бронь с блюдами              |
| `PATCH`  | `/api/reservations/{id}/status?status=`    | Обновить статус              |
| `DELETE` | `/api/reservations/{id}`                   | Отменить бронирование        |

---

## 📊 ДЕМОНСТРАЦИЯ N+1 ПРОБЛЕМЫ

**Проблема (в логах консоли):**

```sql
-- 1 запрос на рестораны
SELECT *
FROM restaurants
WHERE cuisine_type = 'Italian'

-- N запросов на столики (для каждого ресторана)
SELECT *
FROM tables
WHERE restaurant_id = 1
SELECT *
FROM tables
WHERE restaurant_id = 2
SELECT *
FROM tables
WHERE restaurant_id = 3
```

**Решение (через fetch join):**

```sql
-- ОДИН запрос с JOIN
SELECT r
FROM Restaurant r
         LEFT JOIN FETCH r.tables
WHERE r.cuisineType = 'Italian'
```

**Результат:** Вместо 1 + N запросов → **всего 1 запрос** 🚀

---

## 📊 СРАВНЕНИЕ РЕЗУЛЬТАТОВ РАБОТЫ ТРАНЗАКЦИЙ

| Сценарий                 | @Transactional |   Бронирование    |      Блюда       |          Итог           |
|--------------------------|:--------------:|:-----------------:|:----------------:|:-----------------------:|
| ✅ Успешное бронирование  |       ✅        |   ✅ Сохранилось   |  ✅ Сохранились   |      ✅ **Всё ОК**       |
| ⚠️ Ошибка БЕЗ транзакции |       ❌        | ✅ **Сохранилось** | ❌ Не сохранились | ⚠️ **Частичные данные** |
| 🔄 Ошибка С транзакцией  |       ✅        | ❌ **Откатилось**  | ❌ Не сохранились |     ✅ **Чистая БД**     |

---

## 📊 ТАБЛИЦА СВЯЗЕЙ

| Связь                             | Тип связи     | CascadeType       | FetchType        | Обоснование                                                                                                                                        |
|-----------------------------------|---------------|-------------------|------------------|----------------------------------------------------------------------------------------------------------------------------------------------------|
| `Restaurant` → `RestaurantTable`  | `@OneToMany`  | `CascadeType.ALL` | `FetchType.LAZY` | Столики являются неотъемлемой частью ресторана и не могут существовать отдельно. При удалении ресторана все его столики также должны быть удалены. |
| `Restaurant` → `MenuItem`         | `@OneToMany`  | `CascadeType.ALL` | `FetchType.LAZY` | Меню полностью принадлежит ресторану. Блюда не имеют смысла без ресторана, поэтому каскадное удаление необходимо.                                  |
| `RestaurantTable` → `Reservation` | `@OneToMany`  | `CascadeType.ALL` | `FetchType.LAZY` | Бронирования привязаны к конкретному столику. При удалении столика все связанные бронирования должны быть удалены.                                 |
| `Customer` → `Reservation`        | `@OneToMany`  | `CascadeType.ALL` | `FetchType.LAZY` | Клиент может иметь множество бронирований. При удалении клиента все его бронирования теряют смысл и должны быть удалены.                           |
| `Reservation` → `Customer`        | `@ManyToOne`  | -                 | `FetchType.LAZY` | Клиент может существовать независимо от бронирования. Нельзя удалить клиента, если на него ссылается бронирование (защита целостности данных).     |
| `Reservation` → `RestaurantTable` | `@ManyToOne`  | -                 | `FetchType.LAZY` | Столик может существовать без бронирования. Нельзя удалить столик, если на него есть активные бронирования.                                        |
| `Reservation` ↔ `MenuItem`        | `@ManyToMany` | -                 | `FetchType.LAZY` | Связь многие-ко-многим через промежуточную таблицу. Блюда и бронирования существуют независимо друг от друга.                                      |

---

## 🛠️ ТЕХНОЛОГИЧЕСКИЙ СТЕК

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

---

## 🔗 SONARCLOUD АНАЛИЗ

<div align="center">

[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=ZZzelya_table-reservation&metric=alert_status)](https://sonarcloud.io/summary/overall?id=ZZzelya_table-reservation&branch=main)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=ZZzelya_table-reservation&metric=bugs)](https://sonarcloud.io/summary/overall?id=ZZzelya_table-reservation&branch=main)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=ZZzelya_table-reservation&metric=code_smells)](https://sonarcloud.io/summary/overall?id=ZZzelya_table-reservation&branch=main)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ZZzelya_table-reservation&metric=coverage)](https://sonarcloud.io/summary/overall?id=ZZzelya_table-reservation&branch=main)
[![Duplicated Lines](https://sonarcloud.io/api/project_badges/measure?project=ZZzelya_table-reservation&metric=duplicated_lines_density)](https://sonarcloud.io/summary/overall?id=ZZzelya_table-reservation&branch=main)

👉 [**Перейти к полному анализу на SonarCloud
**](https://sonarcloud.io/summary/overall?id=ZZzelya_table-reservation&branch=main)

</div>

---

## 🚀 ЗАПУСК ПРОЕКТА

**Предварительные требования:**

- JDK 17 или выше
- Maven 3.6+

**Установка и запуск:**

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

<details>
<summary><b>📝 1. Создание клиента</b></summary>

```json
POST http://localhost:8080/api/customers
{
"name": "Иван Петров",
"email": "ivan@mail.com",
"phoneNumber": "+375-29-123-45-67"
}
```

</details>

<details>
<summary><b>🏨 2. Создание ресторана</b></summary>

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

</details>

<details>
<summary><b>🪑 3. Создание столика</b></summary>

```json
POST http://localhost:8080/api/tables
{
"restaurantId": 1,
"tableNumber": 1,
"capacity": 4,
"isAvailable": true
}
```

</details>

<details>
<summary><b>🍕 4. Создание блюда</b></summary>

```json
POST http://localhost:8080/api/menu-items
{
"restaurantId": 1,
"name": "Паста Карбонара",
"price": 650.00
}
```

</details>

<details>
<summary><b>✅ 5. Успешное бронирование</b></summary>

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

</details>

<details>
<summary><b>📊 6. Демонстрация N+1 проблемы</b></summary>

```
GET http://localhost:8080/api/restaurants/demo/nplus1?cuisine=Italian
```

</details>

<details>
<summary><b>🚀 7. Демонстрация решения N+1</b></summary>

```
GET http://localhost:8080/api/restaurants/demo/solved?cuisine=Italian
```

</details>

<details>
<summary><b>⚠️ 8. Частичное сохранение (БЕЗ @Transactional)</b></summary>

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

</details>

<details>
<summary><b>🔄 9. Полный откат (С @Transactional)</b></summary>

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

</details>

<details>
<summary><b>🔍 10. Проверка бронирований клиента</b></summary>

```
GET http://localhost:8080/api/reservations/customer/1
```

</details>

---

## 🏆 РЕЗУЛЬТАТЫ ТЕСТИРОВАНИЯ

**N+1 проблема в логах консоли:**

- До решения: 1 + N запросов 🐌
- После решения: 1 запрос 🚀

**Транзакции в действии:**

- `POST /api/reservations` → ✅ id=1 (с блюдами)
- `POST /demo/without-tx` → ⚠️ id=2 (без блюд)
- `POST /demo/with-tx` → ❌ id=3 не появился

---


