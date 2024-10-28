Create Table books(
id SERIAL PRIMARY KEY,
name VARCHAR(50) NOT NULL,
author VARCHAR(100) NOT NULL,
publishedDate date not null,
isbn Varchar(50) not null
)