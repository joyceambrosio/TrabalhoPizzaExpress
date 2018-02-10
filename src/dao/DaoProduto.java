/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Bebida;
import conexaoBanco.*;
import model.Insumo;
import model.Pizza;
import model.Produto;

/**
 *
 * @author joyce
 */
public class DaoProduto {

    private ResultSet resultSet;
    private ConexaoBDMySQL conexao;
    private int idComida;
    private ArrayList<Integer> ids;
    private ArrayList<Produto> produtos = new ArrayList<>();
    private ArrayList<Insumo> ingredientes = null;

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

        if (produto.getCategoria().equals("Pizza")) {
            addComida((Pizza) produto);
            produto.setId(idComida);
            return true;
        }

        return false;
    }

    public boolean removeProduto(Produto produto) throws SQLException {
        if (produto.getCategoria().equals("Insumo")) {
            delInsumo((Insumo) produto);
            return true;
        }

        if (produto.getCategoria().equals("Bebida")) {
            delBebida((Bebida) produto);
            return true;
        }

        if (produto.getCategoria().equals("Pizza")) {
            delComida((Pizza) produto);
            return true;
        }

        return false;
    }

    public boolean updateProduto(Produto produto) throws SQLException {

        if (produto.getCategoria().equals("Insumo")) {
            updInsumo((Insumo) produto);
            return true;
        }

        if (produto.getCategoria().equals("Bebida")) {
            updBebida((Bebida) produto);
            return true;
        }

        if (produto.getCategoria().equals("Pizza")) {
            updComida((Pizza) produto);
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

        try (CallableStatement statement = conexao.getConexao().prepareCall("{call addComida(?, ?, ?, ?)}")) {

            statement.setInt(1, 2);
            statement.setString(2, produto.getNome());
            statement.setDouble(3, produto.getPreco());
            statement.setString(4, produto.getReceita());

            statement.execute();

            resultSet = statement.getResultSet();

            if (resultSet.next()) {

                idComida = resultSet.getInt(1);
            }
            statement.close();
        }

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
        getIngredientesComida();
        getIngredientes();

        return produtos;

    }

    public void getIngredientesComida() throws SQLException {
        conexao = ConexaoBDMySQL.getInstancia();

        for (Produto p : this.produtos) {

            if (p.getCategoria().equals("Pizza")) {
                ingredientes = new ArrayList<>();

                try (CallableStatement statement = conexao.getConexao().prepareCall("{call getIngredientesComida(?)}")) {

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

    }

    public void getIngredientes() throws SQLException {
        conexao = ConexaoBDMySQL.getInstancia();
        try (CallableStatement statement = conexao.getConexao().prepareCall("{call getIngredientes}")) {
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()) {
                int idProduto = resultSet.getInt(1);
                String nome = resultSet.getString(2);

                Produto novoInsumo = new Insumo(idProduto, nome, 0.0);
                produtos.add(novoInsumo);
            }
            statement.close();
        }

    }

    private void updInsumo(Insumo insumo) throws SQLException {

        conexao = ConexaoBDMySQL.getInstancia();

        try (CallableStatement statement = conexao.getConexao().prepareCall("{call updIngrediente(?, ?)}")) {
            statement.setInt(1, insumo.getId());
            statement.setString(2, insumo.getNome());
            statement.execute();
            resultSet = statement.getResultSet();

            statement.close();
        }
    }

    private void updBebida(Bebida bebida) throws SQLException {

        conexao = ConexaoBDMySQL.getInstancia();

        try (CallableStatement statement = conexao.getConexao().prepareCall("{call updProduto(?, ?, ?)}")) {

            statement.setInt(1, bebida.getId());
            statement.setString(2, bebida.getNome());
            statement.setDouble(3, bebida.getPreco());

            statement.execute();

            resultSet = statement.getResultSet();

            statement.close();

        }

    }

    private void updComida(Pizza pizza) throws SQLException {
        conexao = ConexaoBDMySQL.getInstancia();

        try (CallableStatement statement = conexao.getConexao().prepareCall("{call updComida(?, ?, ?, ?)}")) {

            statement.setInt(1, pizza.getId());
            statement.setString(2, pizza.getNome());
            statement.setDouble(3, pizza.getPreco());
            statement.setString(4, pizza.getReceita());

            statement.execute();

            resultSet = statement.getResultSet();

            statement.close();

        }

        try (CallableStatement statement = conexao.getConexao().prepareCall("{delComidaIngredientes(?)}")) {
            statement.setInt(1, pizza.getId());

        } catch (Exception e) {
        }

        for (Insumo p : pizza.getIngredientes()) {

            try (CallableStatement statement = conexao.getConexao().prepareCall("{call addComidaIngrediente(?, ?, ?, ?)}")) {

                statement.setInt(1, idComida);
                statement.setInt(2, p.getId());
                statement.setString(3, p.getUnidade());
                statement.setString(4, p.getQuantidade());
                statement.execute();

                resultSet = statement.getResultSet();

                statement.close();
            } catch (Exception e) {
            }
        }

    }

    private void delInsumo(Insumo insumo) throws SQLException {
        conexao = ConexaoBDMySQL.getInstancia();

        try (CallableStatement statement = conexao.getConexao().prepareCall("{call delIngrediente(?)}")) {
            statement.setInt(1, insumo.getId());
            statement.execute();
            resultSet = statement.getResultSet();
            statement.close();
        }
    }

    private void delBebida(Bebida bebida) throws SQLException {
        conexao = ConexaoBDMySQL.getInstancia();
        try (CallableStatement statement = conexao.getConexao().prepareCall("{call delProduto(?)}")) {
            statement.setInt(1, bebida.getId());
            statement.execute();
            resultSet = statement.getResultSet();
            statement.close();
        }

    }

    private void delComida(Pizza pizza) throws SQLException {
        conexao = ConexaoBDMySQL.getInstancia();
        try (CallableStatement statement = conexao.getConexao().prepareCall("{call delComida(?)}")) {
            statement.setInt(1, pizza.getId());
            statement.execute();
            resultSet = statement.getResultSet();
            statement.close();
        }
    }
}
