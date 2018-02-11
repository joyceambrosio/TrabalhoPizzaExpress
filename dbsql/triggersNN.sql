DROP TRIGGER IF EXISTS insertProdutoPedido;

DELIMITER //
CREATE TRIGGER insertProdutoPedido 
	AFTER INSERT ON PedidoProduto
	FOR EACH ROW
BEGIN
	DECLARE valorProduto DOUBLE;
	DECLARE valorDesconto DOUBLE;

	SELECT preco 
    INTO valorProduto 
	FROM Produto
	WHERE idProduto = NEW.idProduto;

	SELECT desconto
    INTO valorDesconto 
	FROM Pedido
	WHERE idPedido = NEW.idPedido;

	UPDATE Pedido
	SET total = (total + (( NEW.quantidade * valorProduto ) * valordesconto ))
	WHERE Pedido.idPedido = NEW.idPedido;
END//
DELIMITER ;


DROP TRIGGER IF EXISTS deleteProdutoPedido;

DELIMITER //
	CREATE TRIGGER deleteProdutoPedido 
		BEFORE DELETE ON PedidoProduto
		FOR EACH ROW
	BEGIN
		DECLARE valorProduto DOUBLE;
		DECLARE valorDesconto DOUBLE;

	SELECT preco
	INTO valorProduto 
	FROM Produto
	WHERE idProduto = OLD.idProduto;

	SELECT desconto
    INTO valorDesconto 
	FROM Pedido
	WHERE idPedido = OLD.idPedido;

	UPDATE Pedido 
	SET total = (total - (( OLD.quantidade * valorProduto ) * valordesconto ))
	WHERE Pedido.idPedido = OLD.idPedido;

END//
DELIMITER ;




