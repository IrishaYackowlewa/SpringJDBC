SpringJDBC
Для запуска требуется предварительно создать БД H2 с именем test в папке пользователя и выполнить создание таблиц:

CREATE TABLE Recipes(id int not null auto_increment, description varchar not null, recipe varchar, primary key(id) );
