/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colections;

import dao.DaoFuncionario;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Administrador;
import model.Atendente;
import model.Bebida;
import model.Cargo;
import model.Cliente;
import model.Cozinheiro;
import model.Endereco;
import model.Entregador;
import model.Funcionario;
import model.Insumo;
import model.Login;
import model.Pedido;
import model.Pizza;
import model.Produto;

/**
 *
 * @author Natalia
 */
public class Seed {

//    private Pedidos pedidos = Pedidos.getInstancia();
//    private Produtos produtos = Produtos.getInstancia();
    private Funcionarios funcionarios = Funcionarios.getInstancia();
//    private Clientes clientes = Clientes.getInstancia();
    private static ArrayList<Login> logins;

    public Seed() {
//        this.pedidos = new ArrayList<>();
//        this.produtos = new ArrayList<>();
//        this.funcionarios = new ArrayList<>();
        this.logins = new ArrayList<>();
//        this.clientes = new ArrayList<>();
    }

    public void addSeeds() throws SQLException {
        DaoFuncionario dao = new DaoFuncionario();

        funcionarios.addAll(dao.getFuncionarios());

        //Cadastrando funcionários e logins
//        Funcionario f1 = new Administrador("Administrador", "123456789", new Cargo("Administrador", 8000, false), "Francisco Jaquin");
//        funcionarios.add(f1);
        logins.add(new Login("Administrador", "123456789"));
//
//        Funcionario f2 = new Atendente("carlosAlmeida", "123456", new Cargo("Atendente", 980, false), "Carlos Almeida");
//        funcionarios.add(f2);
//        logins.add(new Login("carlosAlmeida", "123456"));
//
//        Funcionario f3 = new Cozinheiro("chefePedro", "123456", new Cargo("Cozinheiro", 1200, false), "Pedro Cunha");
//        funcionarios.add(f3);
//        logins.add(new Login("chefePedro", "123456"));
//
//        Funcionario f4 = new Entregador("entregadorRoberto", "123456", new Cargo("Entregador", true), "Roberto Augsto");
//        funcionarios.add(f4);
//        logins.add(new Login("entregadorRoberto", "123456"));
//
//        Funcionario f5 = new Cozinheiro("subchefErick", "123456", new Cargo("Cozinheiro", 1200, false), "Erick Ramos");
//        funcionarios.add(f5);
//        logins.add(new Login("subchefErick", "123456"));

//        //Polimorfismo
//        Produto produto1 = new Insumo("Vinho tinto", 3.00);
//        Produto produto2 = new Insumo("Maracujá", 0.5);
//
////        Produto bebida1 = new Bebida("Chopp", 5.50);
////        Produto bebida2 = new Bebida("Cerveja", 4.50);
//        Produto pizza1 = new Pizza("Monta a pizza e assa", "Pizza margarita", 29.90);
//        Produto pizza2 = new Pizza("Monta a pizza e assa", "Pizza de brócolis", 29.90);
//        produtos.add(produto1);
//        produtos.add(produto2);
//        produtos.add(bebida1);
//        produtos.add(bebida2);
//        produtos.add(pizza1);
//        produtos.add(pizza2);
//        produtos.add(new Insumo("Trigo", 2.50));
//
//        Cadastrando clientes no sistema
//        Cliente c1 = new Cliente("José", new Endereco("Rua das palmeiras", "Centro", "Guaçuí", "ES", "29560-000", "123"));
//        clientes.add(c1);
//        Cliente c2 = new Cliente("Laura", new Endereco("Beco dos paulistas", "Jardim da penha", "Guaçuí", "ES", "29560-000", "1"));
//        clientes.add(c2);
//        Cliente c3 = new Cliente("Valentina", new Endereco("Av. José Alexandre", "Centro", "Guaçuí", "ES", "29560-000", "2"));
//        clientes.add(c3);
//        Cliente c4 = new Cliente("Enzo", new Endereco("Rua José Beato", "Quincas Machado", "Guaçuí", "ES", "29560-000", "3"));
//        clientes.add(c4);
//        Cliente c5 = new Cliente("Pedro", new Endereco("Rua Emiliana Emiry ", "Centro", "Guaçuí", "ES", "29560-000", "4"));
//        clientes.add(c5);
//        Cadastrando Pedidos
//        pedidos.add(new Pedido(c1, f2));
//        pedidos.add(new Pedido(c2, f2));
//        pedidos.add(new Pedido(c3, f2));
//        pedidos.add(new Pedido(c4, f2));
//        pedidos.add(new Pedido(c5, f2));
//        System.out.println("Logins cadastrados: " + logins.size());
////        System.out.println("Funcionários cadastrados: " + funcionarios.size());
//        System.out.println("Clientes cadastrados: " + clientes.size());
//        System.out.println("Produtos cadastrados: " + produtos.size());
//        System.out.println("Pedidos realizados: " + pedidos.size());
        //Upcasting
//        System.out.println("UPCAST");
//        //insumo -> produto
//        ((Produto) produto1).imprimir();
//        //Downcast
//        System.out.println("DOWNCAST");
//        //produto -> insumo
//        ((Bebida) bebida2).imprimir();
    }

}
