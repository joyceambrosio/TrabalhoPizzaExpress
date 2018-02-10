DROP TRIGGER IF EXISTS insertProdutoPedido;

DELIMITER //
CREATE TRIGGER insertProdutoPedido 
	AFTER INSERT ON PedidoProduto
	FOR EACH ROW
BEGIN
	DECLARE valorProduto DOUBLE;

	SELECT preco 
    INTO valorProduto 
	FROM Produto
	WHERE idProduto = NEW.idProduto;

	UPDATE Pedido
	SET total = (total + (NEW.quantidade * valorProduto))
	WHERE Pedido.idPedido = NEW.idPedido;
END//
DELIMITER ;

DROP TRIGGER IF EXISTS updateProdutoPedido;

DELIMITER //
CREATE TRIGGER updateProdutoPedido 
	AFTER UPDATE ON PedidoProduto
	FOR EACH ROW
BEGIN
	DECLARE valorProduto DOUBLE;

	SELECT preco 
	INTO valorProduto 
	FROM Produto
	WHERE idProduto = OLD.idProduto;

	UPDATE Pedido
	SET total = (total - (OLD.quantidade * valorProduto))
	WHERE Pedido.idPedido = OLD.idPedido;
	
	UPDATE Pedido
	SET total = (total + (NEW.quantidade * valorProduto))
	WHERE Pedido.idPedido = OLD.idPedido;

END//
DELIMITER ;

DROP TRIGGER IF EXISTS deleteProdutoPedido;

DELIMITER //
	CREATE TRIGGER deleteProdutoPedido 
		BEFORE DELETE ON PedidoProduto
		FOR EACH ROW
	BEGIN
		DECLARE valorProduto DOUBLE;

	SELECT preco
	INTO valorProduto 
	FROM Produto
	WHERE idProduto = OLD.idProduto;

	UPDATE Pedido 
	SET total = (total - (OLD.quantidade * valorProduto))
	WHERE Pedido.idPedido = OLD.idPedido;

END//
DELIMITER ;




