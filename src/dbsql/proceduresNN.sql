DROP PROCEDURE IF EXISTS calcFolhaPagamento;
DELIMITER //
CREATE PROCEDURE calcFolhaPagamento(
	IN anoIN INT,
	IN mesIN INT
)
BEGIN

	
END;
//
DELIMITER ;


-- ------------------------------- CATEGORIA - ADD  -----------------------------

DROP PROCEDURE IF EXISTS addCategoria;
DELIMITER //
CREATE PROCEDURE addCategoria(
	IN categoriaIN varchar(45) 

)
BEGIN
    INSERT INTO Categoria(categoria)
	VALUES (categoriaIN);

	SELECT LAST_INSERT_ID();

END;
//
DELIMITER ;

-- ------------------------------- PRODUTO - ADD GET UPDATE DELETE -----------------------------

DROP PROCEDURE IF EXISTS addProduto;
DELIMITER //
CREATE PROCEDURE addProduto(
	IN idCategoriaIN INT,
	IN nomeIN varchar(45), 
	IN precoIN double
)
BEGIN
    INSERT INTO produto(idCategoria, nome, preco)
	VALUES (idCategoriaIN, nomeIN, precoIN);

	SELECT LAST_INSERT_ID();

END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS getProdutosAll;
DELIMITER //
CREATE PROCEDURE getProdutosAll(
)
BEGIN
	SELECT 
    p.idProduto,
    p.nome,
    p.preco,
    com.receita,
    c.idCategoria,
    c.categoria
FROM
	Categoria c JOIN Produto p
		ON p.idCategoria = c.idCategoria
	LEFT JOIN Comida com
		ON p.idProduto = com.idProduto;
END;
//
DELIMITER ;


DROP PROCEDURE IF EXISTS updProduto;
DELIMITER //
CREATE PROCEDURE updProduto(
	IN idProdutoIN INT,
	IN nomeIN varchar(45), 
	IN precoIN double
)
BEGIN
	UPDATE Produto
	SET 
		nome = nomeIN,
		preco = precoIN
	WHERE idProduto = idProdutoIN;
END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS delProduto;
DELIMITER //
CREATE PROCEDURE delProduto(
	IN idProdutoIN INT
)
BEGIN
    DELETE FROM Produto 
    WHERE idProduto = idProdutoIN;
END;
//
DELIMITER ;

-- ------------------------------- COMIDA - ADD GET UPDATE DELETE -----------------------------

DROP PROCEDURE IF EXISTS addIngrediente;
DELIMITER //
CREATE PROCEDURE addIngrediente(
	IN nomeIN varchar(45)
)
BEGIN
    INSERT INTO Ingrediente(nome)
	VALUES (nomeIN);

	SELECT LAST_INSERT_ID();
END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS getIngredientesComida;
DELIMITER //
CREATE PROCEDURE getIngredientesComida(
	IN idComidaIN INT
)
BEGIN
	SELECT 
		i.idIngrediente,
		i.nome,
	    ci.unidade,
	    ci.quantidade
	FROM
		ComidaIngrediente ci INNER JOIN ingrediente i
			on ci.idIngrediente = i.idIngrediente
	WHERE ci.idComida = idComidaIN;
END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS updIngrediente;
DELIMITER //
CREATE PROCEDURE updIngrediente(
	IN idIngredienteIN INT, 
	IN nomeIN varchar(45)
)
BEGIN
	UPDATE Ingrediente
	SET 
		nome = nomeIN
	WHERE idIngrediente = idIngredienteIN;
END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS delIngrediente;
DELIMITER //
CREATE PROCEDURE delIngrediente(
	IN idIngredienteIN INT
)
BEGIN
 	DELETE FROM Ingrediente 
    WHERE idIngrediente = idIngredienteIN;
END;
//
DELIMITER ;


DROP PROCEDURE IF EXISTS addComida;
DELIMITER //
CREATE PROCEDURE addComida(
	IN idCategoriaIN INT,
	IN nomeIN varchar(45), 
	IN precoIN double, 
	IN receitaIN varchar(500)
)
BEGIN
    INSERT INTO produto(idCategoria, nome, preco)
	VALUES (idCategoriaIN, nomeIN, precoIN);

	INSERT INTO comida(idProduto, receita) 
	VALUES (LAST_INSERT_ID(), receitaIN);

	SELECT LAST_INSERT_ID();
END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS updComida;
DELIMITER //
CREATE PROCEDURE updComida(
	IN idProdutoIN INT,
	IN nomeIN varchar(45), 
	IN precoIN double
)
BEGIN
	UPDATE Produto
	SET 
		nome = nomeIN,
		preco = precoIN,
		receita = receitaIN
	WHERE idProduto = idProdutoIN;

	UPDATE Comida 
	SET receita = receitaIN
	WHERE idProduto = idProdutoIN;

	SELECT LAST_INSERT_ID();

END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS delComida;
DELIMITER //
CREATE PROCEDURE delComida(
	IN idProdutoIN INT
)
BEGIN
    DELETE FROM Comida 
    WHERE idProduto = idProdutoIN;

    DELETE FROM ComidaIngrediente
    WHERE idComida = idProdutoIN;
END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS addComidaIngrediente;
DELIMITER //
CREATE PROCEDURE addComidaIngrediente(
	IN idComidaIN INT,
	IN idIngredienteIN INT,
	IN unidadeIN VARCHAR(25),
	IN quantidadeIN VARCHAR(25)
)
BEGIN
    INSERT INTO ComidaIngrediente(idIngrediente, idComida, unidade, quantidade)
	VALUES (idIngredienteIN, idComidaIN, unidadeIN, quantidadeIN);

	SELECT LAST_INSERT_ID();
END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS updComidaIngrediente;
DELIMITER //
CREATE PROCEDURE updComidaIngrediente(
	IN idComidaIN INT,
	IN idIngredienteIN INT,
	IN unidadeIN VARCHAR(25),
	IN quantidadeIN VARCHAR(25)
)
BEGIN
	UPDATE ComidaIngrediente
	SET 
		unidade = unidadeIN,
		quantidade = quantidadeIN
	WHERE idComida = idComidaIN AND idIngrediente = idIngredienteIN;
END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS delComidaIngrediente;
DELIMITER //
CREATE PROCEDURE delComidaIngrediente(
	IN idComidaIN INT,
	IN idIngredienteIN INT
)
BEGIN
	DELETE FROM ComidaIngrediente
	WHERE idIngrediente = idIngredienteIN AND idComida = idComidaIN;
END;
//
DELIMITER ;

-- ------------------------------ CLIENTE - ADD GET UPDATE ATIVA/DESATIVA -----------------------------

DROP PROCEDURE IF EXISTS addCliente;
DELIMITER //
CREATE PROCEDURE addCliente(
	IN nomeIN VARCHAR(100),
	IN logradouroIN VARCHAR(100),
	IN numeroIN VARCHAR(10),
	IN bairroIN VARCHAR(45),
	IN cidadeIN VARCHAR(45),
	IN estadoIN varchar(45),
	IN cepIN VARCHAR(45),
	IN referenciaIN VARCHAR(45)
)
BEGIN
    INSERT INTO Endereco(logradouro, numero, bairro, cidade, estado, cep, referencia)
    VALUES (logradouroIN, numeroIN, bairroIN, cidadeIN, estadoIN, cepIN, referenciaIN);

    INSERT INTO Cliente(idEndereco, nome) 
    VALUES (LAST_INSERT_ID(), nomeIN);

	SELECT idCliente, idEndereco
    FROM Cliente
    WHERE idCliente = LAST_INSERT_ID();
END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS getClientes;
DELIMITER //
CREATE PROCEDURE getClientes(
)
BEGIN
	SELECT 
		c.idCliente,
		c.idEndereco,
		c.nCompras,
		c.nome,
		e.logradouro,
		e.numero,
		e.cidade,
		e.estado,
		e.bairro,
		e.cep,
		e.referencia
	FROM
		Cliente c
			INNER JOIN
		Endereco e ON e.idEndereco = c.idEndereco
	WHERE
		c.ativo is true;
END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS getClienteByNome;
DELIMITER //
CREATE PROCEDURE getClienteByNome(
	IN nomeIN VARCHAR(100)
)
	BEGIN
	SELECT 
		c.idCliente,
		c.idEndereco,
		c.nCompras,
		c.nome,
		e.logradouro,
		e.numero,
		e.cidade,
		e.estado,
		e.bairro,
		e.cep,
		e.referencia
	FROM
		Cliente c
			INNER JOIN
		Endereco e ON e.idEndereco = c.idEndereco
	WHERE
		c.nome LIKE nomeIN AND c.ativo is true;
END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS updCliente;
DELIMITER //
CREATE PROCEDURE updCliente(
	IN idClienteIN INT,
	IN idEnderecoIN INT,
	IN nComprasIN INT,
	IN nomeIN VARCHAR(100),
	IN logradouroIN VARCHAR(100),
	IN numeroIN VARCHAR(10),
	IN bairroIN VARCHAR(45),
	IN cidadeIN VARCHAR(45),
	IN estadoIN VARCHAR(45),
	IN cepIN VARCHAR(45),
	IN referenciaIN VARCHAR(45)

)
BEGIN
	UPDATE Cliente
	SET 
		nome = nomeIN
	WHERE idCliente = idClienteIN;

	UPDATE Endereco
	SET 
		logradouro = logradouroIN,
		numero = numeroIN,
		bairro = bairroIN,
		cidade = cidadeIN,
		estado = estadoIN,
		cep = cepIN,
		referencia = referenciaIN
	WHERE idEndereco = idEnderecoIN;

END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS desativaCliente;
DELIMITER //
CREATE PROCEDURE desativaCliente(
	IN idClienteIN INT
)
BEGIN

	UPDATE Cliente
	SET ativo = 0 
	WHERE idCliente = idClienteIN;
END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS ativaCliente;
DELIMITER //
CREATE PROCEDURE ativaCliente(
	IN idClienteIN INT
)
BEGIN

	UPDATE Cliente
	SET ativo = 1 
	WHERE idCliente = idClienteIN;
END;
//
DELIMITER ;

-- ------------------------------ FUNCIONARIO - ADD UPDATE ATIVA/DESATIVA -----------------------------

DROP PROCEDURE IF EXISTS addFuncionario;
DELIMITER //
CREATE PROCEDURE addFuncionario(
	IN idCargoIN INT, 
	IN usuarioIN varchar(45), 
	IN senhaIN varchar(45)
)
BEGIN
	INSERT INTO Funcionario (idCargo, usuario, senha) 
	VALUES(idCargoIN, usuarioIN, senhaIN);

	SELECT LAST_INSERT_ID();

END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS updFuncionario;
DELIMITER //
CREATE PROCEDURE updFuncionario(
	IN idFuncionarioIN INT,
	IN idCargoIN INT, 
	IN usuarioIN varchar(45), 
	IN senhaIN varchar(45)
)
BEGIN
	UPDATE Funcionario
	SET 
		idCargo = idCargoIN,
		usuario = usuarioIN,
		senha = senhaIN
	WHERE idFuncionario = idFuncionarioIN;
END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS desativaFuncionario;
DELIMITER //
CREATE PROCEDURE desativaFuncionario(
	IN idFuncionarioIN INT
)
BEGIN
	UPDATE Funcionario
	SET ativo = 0 
	WHERE idFuncionario = idFuncionarioIN;
END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS ativaFuncionario;
DELIMITER //
CREATE PROCEDURE ativaFuncionario(
	IN idFuncionarioIN INT
)
BEGIN

	UPDATE Funcionario
	SET ativo = 1 
	WHERE idFuncionario = idFuncionarioIN;
END;
//
DELIMITER ;

-- ------------------------------ PEDIDO - ADD UPDATE DELETE -----------------------------

DROP PROCEDURE IF EXISTS addPedido;
DELIMITER //
CREATE PROCEDURE addPedido(
	IN idClienteIN INT,
	IN idFuncionarioIN INT
)
BEGIN
	INSERT INTO 
	Pedido (idCliente, idFuncionario)
	VALUES (idClienteIN, idFuncionarioIN);

	SELECT LAST_INSERT_ID();
END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS delPedido;
DELIMITER //
CREATE PROCEDURE delPedido(
	IN idPedidoIN INT
)
BEGIN
	DELETE FROM Pedido
	WHERE idPedido = idPedidoIN;
END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS updPedido;
DELIMITER //
CREATE PROCEDURE updPedido(
	IN idPedidoIN INT,
	IN idFuncionarioIN INT,
	IN idClienteIN INT
)
BEGIN
	UPDATE Pedido
	SET 
		idCliente = idClienteIN,
		idFuncionario = idFuncionarioIN 
	WHERE idPedido = idPedidoIN;
END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS addPedidoProduto;
DELIMITER //
CREATE PROCEDURE addPedidoProduto(
	IN idPedidoIN INT,
	IN idProdutoIN INT,
	IN quantidadeIN INT
)
BEGIN
	INSERT INTO 
	PedidoProduto (idPedido, idProduto, quantidade)
	VALUES (idPedidoIN, idProdutoIN, quantidadeIN);

	SELECT LAST_INSERT_ID();
END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS delPedidoProduto;
DELIMITER //
CREATE PROCEDURE delPedidoProduto(
	IN idPedidoIN INT,
	IN idProdutoIN INT
)
BEGIN
	DELETE FROM PedidoProduto
	WHERE idPedido = idPedidoIN AND idProduto = idProdutoIN;
END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS updPedidoProduto;
DELIMITER //
CREATE PROCEDURE updPedidoProduto(
	IN idPedidoIN INT,
	IN idProdutoIN INT,
	IN quantidadeIN INT
)
BEGIN
	UPDATE PedidoProduto
	SET quantidade = quantidadeIN
	WHERE idProduto = idProdutoIN AND  idPedido = idPedidoIN;
END;
//
DELIMITER ;

-- ------------------------------ CARGO - ADD UPDATE DELETE -----------------------------

DROP PROCEDURE IF EXISTS addCargo;
DELIMITER //
CREATE PROCEDURE addCargo(
	IN nomeIN VARCHAR(45),
	IN salarioIN double,
	IN comissaoIN CHAR(1)
)
BEGIN
	INSERT INTO 
	Cargo (nome, salario, comissao)
	VALUES (nomeIN, salarioIN, comissaoIN);

	SELECT LAST_INSERT_ID();
END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS delCargo;
DELIMITER //
CREATE PROCEDURE delCargo(
	IN idCargoIN INT
)
BEGIN
	DELETE FROM Cargo
	WHERE idCargo = idCargoIN;
END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS updCargo;
DELIMITER //
CREATE PROCEDURE updCargo(
	IN idCargoIN INT,
	IN nomeIN VARCHAR(45),
	IN salarioIN double, 
	IN comissaoIN CHAR(1)
)
BEGIN
	UPDATE Pedido
	SET 
		nome = nomeIN,
		salario = salarioIN,
		comissao = comissaoIN
	WHERE idCargo = idCargoIN;
END;
//
DELIMITER ;

