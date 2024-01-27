CREATE TABLE pessoa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE debito (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idpessoa INT NOT NULL,
    datalancamento DATETIME NOT NULL,
    FOREIGN KEY (idpessoa) REFERENCES pessoa(id)
);

CREATE TABLE debitoparcela (
    id INT AUTO_INCREMENT PRIMARY KEY,
    iddebito INT NOT NULL,
    numero INT NOT NULL,
    datavencimento DATETIME NOT NULL,
    valor FLOAT NOT NULL,
    situacao CHAR(1) NOT NULL,
    FOREIGN KEY (iddebito) REFERENCES debito(id),
    UNIQUE KEY (iddebito, numero)
);
