USE PizzaExpress;


DROP USER administrador;
FLUSH PRIVILEGES;
DROP USER atendente;
FLUSH PRIVILEGES;
DROP USER cozinheiro;
FLUSH PRIVILEGES;


CREATE USER 'administrador'@'localhost' IDENTIFIED BY 'adm';
GRANT ALL ON *.* TO administrador@localhost WITH GRANT OPTION;

CREATE USER 'atendente'@'localhost' IDENTIFIED BY 'atend';

GRANT SELECT, INSERT, UPDATE, DELETE
	ON PizzaExpress.Cliente
	TO atendente@localhost;

GRANT SELECT, INSERT, UPDATE, DELETE
	ON PizzaExpress.Endereco
	TO atendente@localhost;

GRANT SELECT, INSERT, UPDATE, DELETE
	ON PizzaExpress.Produto
	TO atendente@localhost;

GRANT SELECT, INSERT, UPDATE, DELETE
	ON PizzaExpress.Comida
	TO atendente@localhost;

GRANT SELECT, INSERT, UPDATE, DELETE
	ON PizzaExpress.Ingrediente
	TO atendente@localhost;

GRANT SELECT, INSERT, UPDATE, DELETE
	ON PizzaExpress.ComidaIngrediente
	TO atendente@localhost;

GRANT SELECT, INSERT, UPDATE, DELETE
	ON PizzaExpress.Pedido
	TO atendente@localhost;

GRANT SELECT, INSERT, UPDATE, DELETE
	ON PizzaExpress.PedidoProduto
	TO atendente@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.addCliente
	TO atendente@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.updCliente
	TO atendente@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.ativaCliente
	TO atendente@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.addProduto
	TO atendente@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.delProduto
	TO atendente@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.updProduto
	TO atendente@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.addComida
	TO atendente@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.updComida
	TO atendente@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.delComida
	TO atendente@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.addIngrediente
	TO atendente@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.updIngrediente
	TO atendente@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.delIngrediente
	TO atendente@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.addComidaIngrediente
	TO atendente@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.updComidaIngrediente
	TO atendente@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.delComidaIngrediente
	TO atendente@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.addPedido
	TO atendente@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.updPedido
	TO atendente@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.delPedido
	TO atendente@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.addPedidoProduto
	TO atendente@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.updPedidoProduto
	TO atendente@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.delPedidoProduto
	TO atendente@localhost;

CREATE USER 'cozinheiro'@'localhost' IDENTIFIED BY 'cozinh';

GRANT SELECT
	ON PizzaExpress.Cliente
	TO cozinheiro@localhost;

GRANT SELECT
	ON PizzaExpress.Endereco
	TO cozinheiro@localhost;

GRANT SELECT
	ON PizzaExpress.Produto
	TO cozinheiro@localhost;

GRANT SELECT
	ON PizzaExpress.Comida
	TO cozinheiro@localhost;

GRANT SELECT
	ON PizzaExpress.Ingrediente
	TO cozinheiro@localhost;

GRANT SELECT
	ON PizzaExpress.ComidaIngrediente
	TO cozinheiro@localhost;

GRANT SELECT, INSERT, UPDATE, DELETE
	ON PizzaExpress.Pedido
	TO cozinheiro@localhost;

GRANT SELECT, INSERT, UPDATE, DELETE
	ON PizzaExpress.PedidoProduto
	TO cozinheiro@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.addPedido
	TO cozinheiro@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.updPedido
	TO cozinheiro@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.delPedido
	TO cozinheiro@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.addPedidoProduto
	TO cozinheiro@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.updPedidoProduto
	TO cozinheiro@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.delPedidoProduto
	TO cozinheiro@localhost;

CREATE USER 'entregador'@'localhost' IDENTIFIED BY 'entreg';

GRANT SELECT
	ON PizzaExpress.Cliente
	TO entregador@localhost;

GRANT SELECT
	ON PizzaExpress.Endereco
	TO entregador@localhost;

GRANT SELECT
	ON PizzaExpress.Produto
	TO entregador@localhost;

GRANT SELECT
	ON PizzaExpress.Comida
	TO entregador@localhost;

GRANT SELECT
	ON PizzaExpress.Ingrediente
	TO entregador@localhost;

GRANT SELECT
	ON PizzaExpress.ComidaIngrediente
	TO entregador@localhost;

GRANT SELECT, INSERT, UPDATE, DELETE
	ON PizzaExpress.Pedido
	TO entregador@localhost;

GRANT SELECT, INSERT, UPDATE, DELETE
	ON PizzaExpress.PedidoProduto
	TO entregador@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.addPedido
	TO entregador@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.updPedido
	TO entregador@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.delPedido
	TO entregador@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.addPedidoProduto
	TO entregador@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.updPedidoProduto
	TO entregador@localhost;

GRANT EXECUTE 
	ON PROCEDURE PizzaExpress.delPedidoProduto
	TO entregador@localhost;
