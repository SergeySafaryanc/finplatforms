# java-finplatforms


## Задача 1
Имеется корневая папка. В этой папке могут находиться текстовые файлы, а
также другие папки. В других папках также могут находится текстовые файлы и
папки (уровень вложенности может оказаться любым).
Найти все текстовые файлы, отсортировать их по имени и склеить содержимое в
один текстовый файл.

### [Реализация](file-collector/README.md)

## Задача 2
Имеется embedded реляционная база данных (h2, hsqldb, sqlite и т.д. - любая sql
embedded БД). Завести в БД таблицу данных о студентах, которая будет
содержать: имя, фамилия, отчество, дата рождения, группа, уникальный номер.
Реализовать консольный или графический пользовательский интерфейс, с
помощью которого можно: добавить студента, удалить студента по уникальному
номеру, вывести список студентов.

### [Реализация](student/README.md)