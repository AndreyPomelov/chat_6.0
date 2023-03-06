Сетевой чат, версия 6.0.

Проект содержит три модуля:
- серверная часть
- клиентская часть
- модуль общих констант

История обновлений:

- 6.0:
    - в разработке

- 5.0:
    - данные о пользователях теперь хранятся в реальной базе данных
      (на локальной машине должна быть установлена база данных MySQL
      с созданной схемой `chat`, при этом база данных автоматически
      наполняется тестовыми данными)

- 4.0:
    - реализована авторизация и отображение никнеймов (на данном этапе
      нет подключения к реальной базе данных, тестовые данные
      пользователей хранятся прямо в коде)

- 3.0:
    - реализована возможность подключения к серверу нескольких клиентов
      (на данном этапе не реализованы никнеймы, то есть непонятно, 
      кто именно написал то или иное сообщение)

- 2.0:
    - клиентская часть преобразована из консольного приложения в оконное

- 1.0:
    - клиентская часть реализована как консольное приложение
    - сервер поддерживает соединение только с одним клиентом