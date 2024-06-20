# ratingus-backend

## Как запустить проект?
### 1. В контейнере
Вы можете поднять проект в контейнере, для этого вам понадобится установленный Docker и Docker Compose. 
Вы можете использовать наш `compose.yml` файл, который находится в корне проекта либо создать свой.

Официальный актуальный контейнер backend: https://github.com/ratingus/ratingus-backend/pkgs/container/ratingus-backend
`ghcr.io/ratingus/ratingus-backend:latest`

Ещё вы можете использовать наш `Dockerfile` для сборки образа вместо контейнера из ghcr.

### 2. Локально
- Вы должны установить зависимости из `pom.xml` и запустить проект через `mvn spring-boot:run`.

- Также вы можете выполнить `mvn clean package` и запустить jar файл через `java -jar target/ratingus-backend-<Номер версии>.jar`.

- Либо запустить через IDE.

**В каждом случае запуска не забудьте прокинуть путь до .env!**

### Общая информация
Для корректного запуска необходимо создать файл `.env` в корне проекта и заполнить его данными для подключения к базе данных. Необходимые переменные окружения указаны в файле `.env.origin`

Также необходимо поднять базу данных.

Если вы планируете ко всему прочему поднимать веб приложение, не забудьте указать корректные настройки для cors фильтров в `src/main/java/ru/dnlkk/ratingusbackend/api/config/SecurityConfig.java` 