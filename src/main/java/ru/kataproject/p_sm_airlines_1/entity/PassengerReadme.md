Описание к сущности Passenger Entity, task №28 by Maria Kolachevskaya / @mvkola

Main Entity Passenger, API Airline user (client).
An abstract superclass User will be created later (the class Passenger extends User).
Fields of the User class (including ROLE_ADMIN, ROLE_MANAGER): id, username (email), password.

Data Transfer Object (DTO) for communication with the Passenger (client).
Describe here the fields that come from the client to the server (and from the server to the client).
The Team decided that the Entity = DTO in the first phase of development.


Основная сущность Пассажир, клиент авиакомпании.
1) Функционал: Покупает билет на сайте через авторизацию в ЛК, получает информационные сообщения 
(подтверждение покупки, регистрация на рейс, инфо о полёте, начисление миль).
2) Изначально планировали, что будет создан австрактный супер-класс User, от которого пассажир будет наследоваться.
В первой фазе разработки от этого отказались. User - это любой пользователь, включая детей и сотрудников.
3) Поля класса можно условно разделить на две группы: Passenger Details и Contact Details
4) Contact Details / каналы коммуникации для Passenger с ролью MAIN* (на основе предпочтений способа связи при заказе 
билета, отображается в ЛК):

String mobile_num;       //  сountry сode + number (different fields in Figma)
String nick_name;        // direct telegram + авторизация через соц. сети
String username;         // e-mail поле используем (см. выше, секьюрити)

*ROLE_MAIN - это взрослый пассажир, заказывающий билет в приложении на семью, например).

5) class PassengerDto используем для взаимодействия Клиент - Сервер.
На первом этапе разработки команда приняла решения, что набор полей сущности и DTO класса будет идентичен.

7) Роли для User @ManyToMany (Security task)
   ROLE_ADMIN
   ROLE_MANAGER

6) Роли для Passenger (на основе dateOfBirth, Security task)
Можно сделать автоматическую замену роли при наступления события: порог возраста?
   ROLE_ADULT (+12) - все права для ROLE_PASSENGER
   ROLE_CHILD (2-11)  - нет прав, добавляется в билет основного контакта
   ROLE_INFANT (0-2) - нет прав, добавляется в билет основного контакта
   ROLE_MAIN (Main Contact или основной контакт при заказе билета, информирование/коммуникация)

7) Дополнительные фичи/отдельные таски:
   диверсификация перевозок (багаж, животные, oversize вещи/грузы)
   программа лояльности (мили, кэшбэк, акции)
   проверка по STOP листам от МВД/военкомата
