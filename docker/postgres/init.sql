CREATE TABLE IF NOT EXISTS books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) NOT NULL,
    description VARCHAR(1000),
    price DECIMAL(10,2) NOT NULL,
    quantity INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    book_id INTEGER REFERENCES books(id),
    quantity INTEGER NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    order_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO books (title, author, isbn, description, price, quantity) VALUES
    ('Война и мир', 'Лев Толстой', '978-5-17-084444-1', 'Роман-эпопея, описывающий события войны 1812 года', 1500.00, 5),
    ('Преступление и наказание', 'Федор Достоевский', '978-5-04-096744-2', 'Социально-психологический и социально-философский роман', 1200.00, 8),
    ('Анна Каренина', 'Лев Толстой', '978-5-17-084444-3', 'Роман о трагической любви замужней дамы', 1300.00, 6),
    ('Мастер и Маргарита', 'Михаил Булгаков', '978-5-17-084444-4', 'Роман о дьяволе, посетившем Москву', 1100.00, 10),
    ('Евгений Онегин', 'Александр Пушкин', '978-5-17-084444-5', 'Роман в стихах', 900.00, 7),
    ('Дубровский', 'Александр Пушкин', '978-5-17-084444-6', 'Роман о благородном разбойнике', 800.00, 9),
    ('Герой нашего времени', 'Михаил Лермонтов', '978-5-17-084444-7', 'Первый психологический роман в русской прозе', 1000.00, 4); 