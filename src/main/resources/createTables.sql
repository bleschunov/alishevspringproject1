DROP TABLE IF EXISTS person CASCADE;
DROP TABLE IF EXISTS book;

CREATE TABLE person(
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    full_name VARCHAR(60) UNIQUE,
    year_of_birth INT
);

CREATE TABLE book(
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    title VARCHAR(30),
    author VARCHAR(30),
    publishing_year int,
    person_id int REFERENCES person(id) ON DELETE SET NULL
);

INSERT INTO person(full_name, year_of_birth)
VALUES
    ('Иванов Иван Иванович', 1970),
    ('Петров Петр Петрович', 1960),
    ('Алексеев Алексей Алексеевич', 1989),
    ('Познер Владимир Владимирович', 1944),
    ('Федоров Мирон Янович', 1985);

INSERT INTO book(title, author, publishing_year, person_id)
VALUES
    ('Над пропостью во ржи', 'Джером Сэлинджер', 1951, 1);

INSERT INTO book(title, author, publishing_year)
VALUES
    ('День опричника', 'Владимир Сорокин', 2006),
    ('Тайные виды на гору Фудзи', 'Владимир Пелевин', 2018),
    ('Философия Java', 'Брюс Эккель', 2018),
    ('Психопатология обыденной жизни', 'Фрейд Зигмунд', 1904),
    ('Бытие и время', 'Мартин Хайдеггер', 1927);
