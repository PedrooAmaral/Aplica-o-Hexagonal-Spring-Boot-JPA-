-- Este script é executado na inicialização do Spring quando spring.sql.init.mode=always.

CREATE TABLE IF NOT EXISTS produtos (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(60) NOT NULL,
    preco DOUBLE NOT NULL,
    PRIMARY KEY (id)
);
