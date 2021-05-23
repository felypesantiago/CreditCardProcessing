CREATE TABLE creditcards
(
    id           SERIAL PRIMARY KEY,
    name         TEXT           NOT NULL,
    number       VARCHAR(19)    NOT NULL,
    limit_amount NUMERIC(20, 2) NOT NULL,
    balance      NUMERIC(20, 2) NOT NULL
);
