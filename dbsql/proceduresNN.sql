

DROP PROCEDURE IF EXISTS calcComissaoEntregadores;
DELIMITER //
CREATE PROCEDURE calcComissaoEntregadores(
	IN dataInicio VARCHAR(15),
    IN dataFim VARCHAR(15)
)
BEGIN
	SELECT 
		p.idFuncionario, 
		f.nome, 
		sum(total * 0.15) as comissao
	FROM
		Pedido p
			INNER JOIN
		Funcionario f ON f.idFuncionario = p.idFuncionario
			INNER JOIN
		cargo c ON c.idCargo = f.idCargo
	WHERE
		data BETWEEN CAST(dataInicio AS DATE) AND CAST(dataFim AS DATE)
		AND p.status LIKE 'Concluído'
		AND f.idCargo = 4
    GROUP BY idFuncionario;
END;
//
DELIMITER ;


DROP PROCEDURE IF EXISTS calcComissaoFixos;
DELIMITER //
CREATE PROCEDURE calcComissaoFixos(
	IN dataInicio VARCHAR(15),
    IN dataFim VARCHAR(15)
)
BEGIN
	SELECT 
	    f.idFuncionario as id,
	    f.nome as nome,
	    c.salario as salario
	FROM
	    Funcionario f
	        INNER JOIN
	    cargo c ON c.idCargo = f.idCargo
	WHERE
	    f.dataAdmissao BETWEEN CAST(dataInicio AS DATE) AND CAST(dataFim AS DATE)
	    AND c.comissao = 0
	    GROUP BY idFuncionario;
END;
//
DELIMITER ;


DROP PROCEDURE IF EXISTS calcFluxodeCaixa
DELIMITER //
CREATE PROCEDURE calcComissaoFixos(
	IN dataInicio VARCHAR(15),
    IN dataFim VARCHAR(15)
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

DROP PROCEDURE IF EXISTS getProdutos;
DELIMITER //
CREATE PROCEDURE getProdutos(
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

DROP PROCEDURE IF EXISTS getIngredientes;
DELIMITER //
CREATE PROCEDURE getIngredientes()
BEGIN
	SELECT 
		idIngrediente,
		nome
	FROM
		Ingrediente;
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
	IN precoIN double, 
	IN receitaIN varchar(500)
)
BEGIN
	UPDATE Produto
	SET 
		nome = nomeIN,
		preco = precoIN
	WHERE idProduto = idProdutoIN;

	UPDATE Comida 
	SET receita = receitaIN
	WHERE idProduto = idProdutoIN;

END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS delComida;
DELIMITER //
CREATE PROCEDURE delComida(
	IN idProdutoIN INT
)
BEGIN

    DELETE FROM ComidaIngrediente
    WHERE idComida = idProdutoIN;
    
    DELETE FROM Comida 
    WHERE idProduto = idProdutoIN;

    DELETE FROM Produto 
    WHERE idProduto = idProdutoIN;

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

DROP PROCEDURE IF EXISTS delComidaIngredientes;
DELIMITER //
CREATE PROCEDURE delComidaIngredientes(
	IN idComidaIN INT
)
BEGIN
	DELETE FROM ComidaIngrediente
	WHERE idComida = idComidaIN;
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
	IN nomeIN varchar(45),
	IN senhaIN varchar(45)
)
BEGIN
	INSERT INTO Funcionario (idCargo, nome, usuario, senha) 
	VALUES(idCargoIN, nomeIN, usuarioIN, senhaIN);

	SELECT LAST_INSERT_ID();

END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS updFuncionario;
DELIMITER //
CREATE PROCEDURE updFuncionario(
	IN idFuncionarioIN INT,
	IN idCargoIN INT, 
	IN nomeIN varchar(45), 
	IN senhaIN varchar(45)
)
BEGIN
	UPDATE Funcionario
	SET 
		idCargo = idCargoIN,
		nome = nomeIN,
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
	SET 
		ativo = 0,
		dataDemissao = CURRENT_TIMESTAMP
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

DROP PROCEDURE IF EXISTS getFuncionarios;
DELIMITER //
CREATE PROCEDURE getFuncionarios(
)
BEGIN
	SELECT 
    f.idFuncionario,
    f.nome,
    f.usuario,
    f.senha,
    c.idCargo,
    c.nome, 
    c.salario,
    c.comissao
FROM Funcionario f INNER JOIN Cargo c
	ON f.idCargo = c.idCargo
WHERE f.ativo = 1;
END;
//
DELIMITER ;

-- ------------------------------ PEDIDO - ADD GET UPDATE DELETE -----------------------------

DROP PROCEDURE IF EXISTS addPedido;
DELIMITER //
CREATE PROCEDURE addPedido(
	IN idClienteIN INT,
	IN idFuncionarioIN INT
)
BEGIN
	DECLARE conta INT;
    
	INSERT INTO 
	Pedido (idCliente, idFuncionario)
	VALUES (idClienteIN, idFuncionarioIN);

	UPDATE Cliente
	SET ncompras = (ncompras + 1)
	WHERE Cliente.idCliente = idClienteIN;

	SELECT nCompras
    INTO conta
    FROM Cliente 
    WHERE idCliente = idClienteIN;
    
    IF (conta > 9)
		THEN
			UPDATE Pedido
            SET desconto = 0.5
            WHERE idPedido = LAST_INSERT_ID();
            
        	UPDATE Cliente
			SET ncompras = 0
			WHERE Cliente.idCliente = idClienteIN; 
	
    END IF;
        IF (conta <= 9)
		THEN
			UPDATE Pedido
            SET desconto = 1
            WHERE idPedido = LAST_INSERT_ID();
	END IF;
	SELECT LAST_INSERT_ID();
END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS getPedidos;
DELIMITER //
CREATE PROCEDURE getPedidos(
)
BEGIN
	SELECT 
	    Pedido.idPedido,
	    Pedido.idCliente,
	    Cliente.nome,
	    Pedido.idFuncionario,
	    Funcionario.nome,
	    Pedido.total,
	    Pedido.desconto,
	    Pedido.status
	FROM
	    Pedido
	        INNER JOIN
	    Cliente ON Cliente.idCliente = Pedido.idCliente
	        LEFT JOIN
	    Funcionario ON Pedido.idFuncionario = Funcionario.idFuncionario;
END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS delPedido;
DELIMITER //
CREATE PROCEDURE delPedido(
	IN idPedidoIN INT
)
BEGIN

	UPDATE Cliente
	SET ncompras = (ncompras - 1)
	WHERE Cliente.idCliente IN 
		(
			SELECT idCliente
			FROM Pedido
			WHERE idPedido = idPedidoIN

		);

	DELETE FROM PedidoProduto
	WHERE idPedido = idPedidoIN;

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
	IN idClienteIN INT,
	IN statusIN VARCHAR(25)
)
BEGIN
	UPDATE Pedido
	SET 
		idCliente = idClienteIN,
		idFuncionario = idFuncionarioIN,
		status = statusIN
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

END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS getPedidoProdutos;
DELIMITER //
CREATE PROCEDURE getPedidoProdutos(
	IN idPedidoIN INT
)
	BEGIN
		SELECT 
	    idProduto,
	    quantidade
	FROM
	    pedidoProduto
	WHERE idPedido = idPedidoIN;
END;
//
DELIMITER ;

DROP PROCEDURE IF EXISTS delPedidoProdutos;
DELIMITER //
CREATE PROCEDURE delPedidoProdutos(
	IN idPedidoIN INT
)
BEGIN
	DELETE FROM PedidoProduto
	WHERE idPedido = idPedidoIN;
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

