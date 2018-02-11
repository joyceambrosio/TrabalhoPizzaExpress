USE PizzaExpress;

-- adicionando algumas categorias

CALL addCategoria("Insumo");
CALL addCategoria("Pizza");
CALL addCategoria("Bebida");

-- adicionando alguns ingredientes
CALL addIngrediente("Farinha de trigo");
CALL addIngrediente("Ovos");
CALL addIngrediente("Leite");
CALL addIngrediente("Queijo brie");
CALL addIngrediente("Queijo parmesão");
CALL addIngrediente("Queijo provolone");
CALL addIngrediente("Queijo catupiri");
CALL addIngrediente("Manjericão");
CALL addIngrediente("Tomate");
CALL addIngrediente("Brócolis");
CALL addIngrediente("Água");
CALL addIngrediente("Batata");
CALL addIngrediente("Milho");
CALL addIngrediente("Pimenta calabreza");
CALL addIngrediente("Pimenta do reino");

-- TEstando del e upd
-- CALL delIngrediente(15);
-- CALL updIngrediente(14, "Pimenta calabresa");

-- adicionando algumas bebidas
CALL addProduto(3, "Coca cola 3L", 10);
CALL addProduto(3, "Coca cola 2L", 8.50);
CALL addProduto(3, "Coca cola 1,5L", 7.50);
CALL addProduto(3, "Suco de frutas 500ml", 5.00);
CALL addProduto(3, "Suco de frutas 350ml", 4.00);
-- CALL addProduto(3, "Suco de tamarindo", 15.00);
-- Testando upd e del
CALL updProduto(5, "Suco de frutas 350ml", 3.50);
-- CALL delProduto(6);

-- adicionando algumas pizzas
CALL addComida(2, "Pizza de 4 queijos", 25.99, "Fazer a massa, colocar qualquer queijo que tiver e assar");
CALL addComida(2, "Pizza marguerita", 25.99, "Fazer a massa, colocar queijo, manjericão e tomate e assar");
CALL addComida(2, "Pizza de brócolis", 30.99, "Fazer a massa, colocar brócolis e assar");
CALL addComida(2, "Pizza portuguesa", 30.99, "Fazer a massa, colocar tudo que tiver e assar");
CALL addComida(2, "Pizza baiana", 30.99, "Fazer a massa, colocar todas as pimentas e assar");
-- CALL addComida(2, "Pizza teste", 30.99, "Fazer a massa, colocar todas as pimentas e assar");

-- Testando del e upd
-- CALL updComida("Pizza Bahiana", 35.99, "Fazer a massa, colocar todas as pimentas e assar");
-- CALL delComida(6);


-- adicionando ingredientes às pizzas

-- pizza 1
CALL addComidaIngrediente(6, 1, "Copos" ,"2/5");
CALL addComidaIngrediente(6, 2, "unidades" ,"2");
CALL addComidaIngrediente(6, 11, "Copos", "2/5");
CALL addComidaIngrediente(6, 4, "gramas", "100");
CALL addComidaIngrediente(6, 5, "gramas", "100");
CALL addComidaIngrediente(6, 6, "gramas" ,"100");
CALL addComidaIngrediente(6, 7, "gramas", "100");
-- pizza 2
CALL addComidaIngrediente(7, 1, "Copos", "2/5");
CALL addComidaIngrediente(7, 2, "unidades", "2");
CALL addComidaIngrediente(7, 11, "Copos", "2/5");
CALL addComidaIngrediente(7, 9, "unidades", "2");
CALL addComidaIngrediente(7, 4, "gramas", "200");
CALL addComidaIngrediente(7, 5, "1gramas", "100");
-- pizza 3
CALL addComidaIngrediente(8, 1, "Copos", "2/5");
CALL addComidaIngrediente(8, 2, "unidades", "2");
CALL addComidaIngrediente(8, 11, "Copos", "2/5");
CALL addComidaIngrediente(8, 9, "unidades", "2");
CALL addComidaIngrediente(8, 4, "gramas", "100 ");
CALL addComidaIngrediente(8, 5, " gramas", "100");
CALL addComidaIngrediente(8, 10, "gramas", "100 ");
-- pizza 4
CALL addComidaIngrediente(9, 1, "Copos", "2/5");
CALL addComidaIngrediente(9, 2, " unidades", "2");
CALL addComidaIngrediente(9, 11, "Copos", "2/5");
CALL addComidaIngrediente(9, 9, "unidades", "2 ");
CALL addComidaIngrediente(9, 4, "gramas", "100");
CALL addComidaIngrediente(9, 5, " gramas", "100");
CALL addComidaIngrediente(9, 12, "gramas", "100");
CALL addComidaIngrediente(9, 13, "gramas", "100");
CALL addComidaIngrediente(9, 14, "gramas", "100");
-- pizza 5
CALL addComidaIngrediente(10, 1, "Copos", "2/5");
CALL addComidaIngrediente(10, 2, "unidades", "2");
CALL addComidaIngrediente(10, 11, "2/5 Copos", "2/5");
CALL addComidaIngrediente(10, 9, "unidades", "2");
CALL addComidaIngrediente(10, 4, "gramas", "100");
CALL addComidaIngrediente(10, 14, "gramas", "100");
CALL addComidaIngrediente(10, 10, "gramas", "100");
-- testando upd e del
-- CALL updComidaIngrediente(4, 9, "unidade", "1");
-- CALL delComidaIngrediente(3, 10);

-- adicionando clientes

CALL addCliente("Joyce Ambrosio", "Rua Pref. Antonio Lemos Jr", 217, "Colina", "Alegre", "ES", "29500-000", "Prox Fabrica de Manilha");
CALL addCliente("Natalia Soares", "Avenida esqueci", 217, "Colina", "Guacui", "ES","29540-000", "Ai");
CALL addCliente("Enzo Vasconcelos", "Avenida esqueci", 217, "Colina", "Alegre", "ES", "29540-000", "Perto da ufes");
CALL addCliente("Erick Ambrosio", "Avenida esqueci", 217, "Guararema", "Alegre","ES","29540-000", "Rua sem fim");
CALL addCliente("Caio Soares", "Avenida esqueci", 217, "Guararema", "Alegre", "ES","29540-000", " ");
CALL addCliente("Valentina Guimaraes", "Avenida esqueci", 217, "Guararema", "Alegre", "ES","29540-000", " ");


CALL addCargo("Administrador", 1200.00, 0);
CALL addCargo("Atendente", 1200.00, 0);
CALL addCargo("Cozinheiro", 1800.00, 0);
CALL addCargo("Entregador", 0.00, 1);

CALL addFuncionario(1, "Administrador", "Administrador", "123456789");
CALL addFuncionario(2, "Joyce", "Joy", "123");
CALL addFuncionario(3, "Natalia", "NatyCristal", "123");
CALL addFuncionario(4, "Carlos Alberto", "Carlos", "123");

CALL addPedido(1, 3);
CALL addPedido(2, 3);
CALL addPedido(3, 3);
CALL addPedido(4, 3);
CALL addPedido(5, 3);

CALL addPedido(6, null);
CALL addPedido(6, 3);
CALL addPedido(6, 3);
CALL addPedido(6, 3);
CALL addPedido(6, null);

CALL addPedido(6, 3);
CALL addPedido(6, null);
CALL addPedido(6, 3);
CALL addPedido(2, 3);
CALL addPedido(4, 3);

CALL addPedido(4, 3);
CALL addPedido(5, 3);
CALL addPedido(6, null);
CALL addPedido(1, null);
CALL addPedido(2, 3);


CALL addPedidoProduto(1, 1, 1);
CALL addPedidoProduto(1, 3, 1);
CALL addPedidoProduto(2, 2, 2);
CALL addPedidoProduto(2, 3, 1);
CALL addPedidoProduto(2, 4, 1);
CALL updPedidoProduto(2, 4, 6);

CALL addPedidoProduto(3, 1, 1);
CALL addPedidoProduto(3, 3, 1);
CALL addPedidoProduto(3, 2, 2);
CALL addPedidoProduto(4, 3, 1);
CALL addPedidoProduto(4, 4, 1);
CALL updPedidoProduto(4, 4, 6);

CALL addPedidoProduto(5, 1, 1);
CALL addPedidoProduto(6, 3, 1);
CALL addPedidoProduto(7, 2, 2);
CALL addPedidoProduto(7, 3, 1);
CALL addPedidoProduto(8, 4, 1);
CALL updPedidoProduto(8, 4, 6);

CALL addPedidoProduto(9, 1, 1);
CALL addPedidoProduto(10, 3, 1);
CALL addPedidoProduto(11, 2, 2);
CALL addPedidoProduto(12, 3, 1);
CALL addPedidoProduto(13, 4, 1);
CALL updPedidoProduto(15, 4, 6);

CALL addPedidoProduto(15, 1, 1);
CALL addPedidoProduto(16, 3, 1);
CALL addPedidoProduto(17, 2, 2);
CALL addPedidoProduto(18, 3, 1);
CALL addPedidoProduto(19, 4, 1);
CALL updPedidoProduto(20, 4, 6);

-- select * from Produto;
-- 
-- 
-- SELECT * FROM Pedido;
-- SELECT * FROM PedidoProduto;
-- select * from comida;
-- select * from cliente;
-- select * from endereco;
-- select * from cliente inner join endereco on endereco.idEndereco = cliente.idEndereco;
-- select * from Produto INNER JOIN Comida on Produto.idProduto = Comida.idProduto;
-- select Cliente.nome, Produto.nome, Pedido.Total from Pedido inner join PedidoProduto ON Pedido.idPedido = PedidoProduto.idPedido INNER JOIN Produto on PedidoProduto.idProduto = Produto.idProduto INNER JOIN Cliente on Cliente.idCliente = Pedido.idCliente LEFT JOIN Funcionario on Pedido.idFuncionario = Funcionario.idFuncionario;
-- 
-- 


