# Monitoring system using Spring Kafka: Consumer

Описание:
Consumer для их обработки и анализа метрик, а также REST API для просмотра метрик.

1. Для того чтобы поднять сервис требуется наличие Java 17 и Docker на целевой системе.
2. Убедиться, что порт 8080 доступен и не занят другими сервисами.
3. Запустить docker-compose-db.yml.
4. Запустить metricsproduser.
5. Перейти в браузере по адресу http://localhost:8080/swagger-ui.
6. С помощью swagger-ui выполнить методы "Получение метрик".



