/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import colections.Produtos;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Bebida;
import conexaoBanco.*;
import model.Endereco;
import model.Insumo;
import model.Pizza;
import model.Produto;

/**
 *
 * @author joyce
 */
public class DaoProduto {

    private ResultSet resultSet;
    ConexaoBDMySQL conexao;
    int idComida;
    ArrayList<Integer> ids;
    ArrayList<Produto> produtos = new ArrayList<>();
    ArrayList<Insumo> ingredientes = new ArrayList<>();

    public boolean addProduto(Produto produto) throws SQLException {

        if (produto.getCategoria().equals("Insumo")) {
            addInsumo((Insumo) produto);
            produto.setId(idComida);
            return true;
        }

        if (produto.getCategoria().equals("Bebida")) {
            addBebida((Bebida) produto);
            produto.setId(idComida);
            return true;
        }
        
        if (produto.getCategoria().equals("Comida")) {
            addComida((Pizza) produto);
            produto.setId(idComida);
            return true;
        }

        return false;
    }

    public void addInsumo(Insumo insumo) throws SQLException {

        conexao = ConexaoBDMySQL.getInstancia();

        try (CallableStatement statement = conexao.getConexao().prepareCall("{call addIngrediente(?)}")) {
            statement.setString(1, insumo.getNome());
            statement.execute();
            resultSet = statement.getResultSet();
            if (resultSet.next()) {
                idComida = resultSet.getInt(1);
            }
            statement.close();
        }

    }

    public int addBebida(Bebida bebida) throws SQLException {

        conexao = ConexaoBDMySQL.getInstancia();

        try (CallableStatement statement = conexao.getConexao().prepareCall("{call addProduto(?, ?, ?)}")) {

            statement.setInt(1, 3);
            statement.setString(2, bebida.getNome());
            statement.setDouble(3, bebida.getPreco());

            statement.execute();
            resultSet = statement.getResultSet();

            if (resultSet.next()) {
                idComida = resultSet.getInt(1);
            }
            statement.close();
        }
        return idComida;

    }

    public int addComida(Pizza produto) throws SQLException {
        conexao = ConexaoBDMySQL.getInstancia();

        try (CallableStatement statement = conexao.getConexao().prepareCall("{call addComida(?, ?, ?)}")) {

            statement.setString(1, produto.getNome());
            statement.setDouble(2, produto.getPreco());
            statement.setString(3, produto.getReceita());

            statement.execute();
            resultSet = statement.getResultSet();

            if (resultSet.next()) {
                //System.out.println("Add Pizza chamado com sucesso");
                idComida = resultSet.getInt(1);
            }
            statement.close();
        }
        //eu podia ter feito outra função mas são 4 am

        for (Insumo p : produto.getIngredientes()) {
            try (CallableStatement statement = conexao.getConexao().prepareCall("{call addComidaIngrediente(?, ?, ?, ?)}")) {

                statement.setInt(1, idComida);
                statement.setInt(2, p.getId());
                statement.setString(3, p.getUnidade());
                statement.setString(4, p.getQuantidade());

                statement.execute();
                resultSet = statement.getResultSet();

                statement.close();
            }
        }
        return idComida;
    }

    public ArrayList<Produto> getProdutos() throws SQLException {

        conexao = ConexaoBDMySQL.getInstancia();

        try (CallableStatement statement = conexao.getConexao().prepareCall("{call getProdutos}")) {

            statement.execute();

            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                int idProduto = resultSet.getInt(1);
                String nome = resultSet.getString(2);
                double preco = resultSet.getDouble(3);
                String receita = resultSet.getString(4);
                int idCategoria = resultSet.getInt(5);
                String categoria = resultSet.getString(6);

                
                if (categoria.equals("Bebida")) {
                    Bebida novaBebida = new Bebida(idProduto, nome, preco);
                    produtos.add(novaBebida);
                } else if (categoria.equals("Pizza")) {
                    Pizza novaPizza = new Pizza(idProduto, nome, preco, receita);
                    produtos.add(novaPizza);
                }
            }
            statement.close();
        }
        getIngredientes();

        return produtos;

    }

    public void getIngredientes() throws SQLException {
        conexao = ConexaoBDMySQL.getInstancia();

        for (Produto p : this.produtos) {

            if (p.getCategoria().equals("Pizza")) {

                try (CallableStatement statement = conexao.getConexao().prepareCall("{call getIngredientesComida(?)}")) {
                    ingredientes.clear();

                    Pizza pizza = (Pizza) p;

                    statement.setInt(1, pizza.getId());
                    statement.execute();

                    resultSet = statement.getResultSet();

                    while (resultSet.next()) {

                        int idIngrediente = resultSet.getInt(1);
                        String nome = resultSet.getString(2);
                        String unidade = resultSet.getString(3);
                        String quantidade = resultSet.getString(4);

                        Insumo ingrediente = new Insumo(idIngrediente, nome, 0.0, unidade, quantidade);

                        ingredientes.add(ingrediente);

                    }

                    ((Pizza) p).setIngredientes(ingredientes);

                    statement.close();
                }

            }
        }

        //System.out.println("Chamada de inserção de get ingredientes feita com sucesso!");
    }
}
