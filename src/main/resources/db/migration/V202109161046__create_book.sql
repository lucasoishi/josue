CREATE TABLE book (
    id UUID NOT NULL,
    name TEXT NOT NULL,
    author CHAR(256),
    publisher CHAR(256),
    pages INT,
    price float,
    publication_date TIMESTAMP,
    published BOOL,
    cover_image TEXT,
    book_language CHAR(100)
);
