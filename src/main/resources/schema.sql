DROP TABLE IF EXISTS COMMUNICATION;

CREATE TABLE ZIP_CODE(
                         ID INTEGER NOT NULL,
                         COM_TYPE VARCHAR(255),
                         STATUS VARCHAR(255) NOT NULL,
                         DESTINATARIO VARCHAR(255) NOT NULL,
                         DATA VARCHAR(255)
);