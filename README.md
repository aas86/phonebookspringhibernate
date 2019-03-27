# README #

Это проект - телефонная книга, реализованная на следующих технологиях

клиентская часть: html, css, jquery, bootstrap, underscore
серверная часть: spring-framework
Связь БД и Java: hibernate
БД: MySQL установленная на компьютероре
сборка: spring + maven
запуск: spring + tomcat

Что умеет делать проет:
отображать сущетсвующие контакты
добавлять новые контакты

Что нужно добавить в проект:
Удаление элемента
Таблицу звонков + апи для получения звонков по контакту

Как запустить проект
Run PhoneBookSpringApplication - данная команда собирает проект и запускает PhoneBookSpringApplication main
Debug PhoneBookSpringApplication - данная команда собирает проект в режиме дебаг и запускает phoneBookSpringApplication main



Реализованные методы API:
GET {host}/phoneBook/rcp/api/v1/getAllContacts - получения списка контактов

POST {host}//phoneBook/rcp/api/v1/addContact - добавление нового контакта в формате
Тело запроса:
{
 "firstName": "Имя",
 "lastName": "Фамилия",
 "phone": "Телефон"
}
POST {host}//phoneBook/rcp/api/v1/deleteContac
