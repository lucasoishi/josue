CREATE TABLE book (
    id UUID NOT NULL,
    name TEXT NOT NULL,
    author VARCHAR(256),
    publisher VARCHAR(256),
    pages INT,
    price float,
    publication_date TIMESTAMP,
    published BOOL,
    cover_image TEXT,
    book_language VARCHAR(100)
);
