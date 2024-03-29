# DATE CONVERTER
Выполнил студент группы 250503 Мащенко Артур
---
Язык программирования Java
Фреймворк Spring
---
## Условие
1. Создать и запустить локально простейший веб/REST сервис, используя любой открытый пример с использованием Java stack: Spring (Spring Boot)/maven/gradle/Jersey/ Spring MVC. Добавить GET ендпоинт, принимающий входные параметры в качестве queryParams в URL согласно варианту, и возвращающий любой hard-coded результат в виде JSON согласно варианту.
2. Подключить в проект БД (PostgreSQL/MySQL/и т.д.). Реализация связи один ко многим @OneToMany. Реализация связи многие ко многим @ManyToMany. Реализовать CRUD-операции со всеми сущностями.
---
# USAGE EXAMPLE
get http://localhost:8080/time-data/
получить информацию из таблицы time-data

get http://localhost:8080/time-converter/
получить информацию из таблицы time-converter
get http://localhost:8080/time-data-converter-mapping/
получить информацию из таблицы time-data-converter-mapping
get http://localhost:8080/time-devices/all
получить информацию из таблицы time-devices
