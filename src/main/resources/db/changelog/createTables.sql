CREATE SEQUENCE users_id_seq;

CREATE SEQUENCE chat_id_seq;

CREATE TABLE users (
    id integer PRIMARY KEY DEFAULT nextval('users_id_seq') NOT NULL,
    login VARCHAR(255),
    password VARCHAR(255)
);

ALTER SEQUENCE users_id_seq
OWNED BY users.id;

CREATE TABLE chat (
    id integer PRIMARY KEY DEFAULT nextval('chat_id_seq') NOT NULL ,
    name VARCHAR(255),
    message VARCHAR(255),
    date VARCHAR(255),
    idUser INT,
    FOREIGN KEY (idUser) REFERENCES users(ID)
);
ALTER SEQUENCE chat_id_seq
OWNED BY chat.id;