SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP DATABASE IF EXISTS PizzaExpress;

CREATE DATABASE IF NOT EXISTS PizzaExpress DEFAULT CHARACTER SET utf8 ;

USE PizzaExpress ;

CREATE TABLE IF NOT EXISTS Cargo (
    idCargo INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    salario DOUBLE NOT NULL,
    comissao CHAR(1) NOT NULL,
    CONSTRAINT pkCargo PRIMARY KEY (idCargo)
)  ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS Funcionario (
    idFuncionario INT NOT NULL AUTO_INCREMENT,
    idCargo INT NOT NULL,
    nome VARCHAR(45) NOT NULL,
    usuario VARCHAR(45) NOT NULL,
    senha VARCHAR(45) NOT NULL,
    ativo CHAR(1) NOT NULL DEFAULT 1,
    dataAdmissao DATETIME DEFAULT CURRENT_TIMESTAMP,
    dataDemissao DATETIME NULL,
    CONSTRAINT pkFuncionario PRIMARY KEY (idFuncionario),
    CONSTRAINT fkFuncionarioCargo FOREIGN KEY (idCargo)
        REFERENCES Cargo (idCargo)
)  ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS FolhaPagamento (
    idFolhaPagamento INT NOT NULL AUTO_INCREMENT,
    idFuncionario INT NOT NULL,
    data DATETIME NOT NULL,
    totalSalario DOUBLE NOT NULL,
    CONSTRAINT pkFolhaPagamento PRIMARY KEY (idFolhaPagamento),
    CONSTRAINT fkFolhaPagamentoFuncionario FOREIGN KEY (idFuncionario)
        REFERENCES Funcionario (idFuncionario)
)  ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS Endereco (
    idEndereco INT NOT NULL AUTO_INCREMENT,
    logradouro VARCHAR(100) NOT NULL,
    numero VARCHAR(10) NULL DEFAULT 'sn',
    bairro VARCHAR(45) NOT NULL,
    cidade VARCHAR(45) NOT NULL,
    estado VARCHAR(45) NOT NULL,
    cep VARCHAR(45) NOT NULL,
    referencia VARCHAR(45) NULL,
    CONSTRAINT pkEndereco PRIMARY KEY (idEndereco)
)  ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS Cliente (
    idCliente INT NOT NULL AUTO_INCREMENT,
    idEndereco INT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    ativo CHAR(1) NOT NULL DEFAULT 1,
    nCompras INT NOT NULL DEFAULT 0,
    CONSTRAINT pkCliente PRIMARY KEY (idCliente),
    CONSTRAINT fkClienteEndereco FOREIGN KEY (idEndereco)
        REFERENCES Endereco (idEndereco)
        ON DELETE CASCADE ON UPDATE CASCADE
)  ENGINE=INNODB;


CREATE TABLE IF NOT EXISTS Categoria (
    idCategoria INT NOT NULL AUTO_INCREMENT,
    categoria VARCHAR(45) NOT NULL,
    PRIMARY KEY (idCategoria)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS Produto (
    idProduto INT NOT NULL AUTO_INCREMENT,
    idCategoria INT NOT NULL,
    nome VARCHAR(45) NOT NULL,
    preco DOUBLE NOT NULL,
    PRIMARY KEY (idProduto),
    CONSTRAINT fkProdutoCategoria FOREIGN KEY (idCategoria)
        REFERENCES Categoria(idCategoria)
)  ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS Comida (
    idProduto INT NOT NULL,
    receita VARCHAR(500) NOT NULL,
    CONSTRAINT pkComida PRIMARY KEY (idProduto),
    CONSTRAINT fkComidaProduto FOREIGN KEY (idProduto)
        REFERENCES Produto (idProduto)
)  ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS Ingrediente (
    idIngrediente INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(25) NOT NULL,
    CONSTRAINT pkIngrediente PRIMARY KEY (idIngrediente)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS ComidaIngrediente (
    idComida INT NOT NULL,
    idIngrediente INT NOT NULL,
    unidade VARCHAR(25) NOT NULL,
    quantidade VARCHAR(25) NOT NULL,
    CONSTRAINT pkComidaIngrediente PRIMARY KEY (idIngrediente , idComida),
    CONSTRAINT fkComidaIngredienteIngrediente FOREIGN KEY (idIngrediente)
        REFERENCES Ingrediente(idIngrediente)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fkComidaIngredienteComida FOREIGN KEY (idComida)
        REFERENCES Produto (idProduto)
)  ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS Pedido (
    idPedido INT NOT NULL AUTO_INCREMENT,
    idFuncionario INT NULL DEFAULT NULL, # força o null pra default
    idCliente INT NOT NULL,
    total DOUBLE DEFAULT 0.0,
    desconto DOUBLE DEFAULT 1,
    status VARCHAR(25) DEFAULT 'Aberto',
    data DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pkPedido PRIMARY KEY (idPedido),
    CONSTRAINT fkPedidoCliente FOREIGN KEY (idCliente)
        REFERENCES Cliente (idCliente),
    CONSTRAINT fkPedidoFuncionario FOREIGN KEY (idFuncionario)
        REFERENCES Funcionario (idFuncionario)
)  ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS PedidoProduto (
    idProduto INT NOT NULL,
    idPedido INT NOT NULL,
    quantidade INT NOT NULL,
    CONSTRAINT fkPedidoProdutoProduto FOREIGN KEY (idProduto)
        REFERENCES Produto (idProduto),
    CONSTRAINT fkPedidoProdutoPedido FOREIGN KEY (idPedido)
        REFERENCES Pedido (idPedido)
)  ENGINE=INNODB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

