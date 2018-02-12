/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import colections.Clientes;
import colections.Funcionarios;
import colections.Produtos;
import conexaoBanco.ConexaoBDMySQL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Cliente;
import model.Funcionario;
import model.Insumo;
import model.Pedido;
import model.PedidoProduto;
import model.Pizza;
import model.Produto;

/**
 *
 * @author joyce
 */
public class DaoPedido {

    private ResultSet resultSet;
    private ConexaoBDMySQL conexao;
    private int idPedido;
    private ArrayList<Integer> ids;
    private ArrayList<Pedido> pedidos;
    private ArrayList<PedidoProduto> produtos = new ArrayList<>();

    private Cliente cliente;
    private Funcionario funcionario;

    public ArrayList<Pedido> getPedidos() throws SQLException {
        conexao = ConexaoBDMySQL.getInstancia();
        pedidos = new ArrayList<Pedido>();

        try (CallableStatement statement = conexao.getConexao().prepareCall("{call getPedidos}")) {

            statement.execute();

            resultSet = statement.getResultSet();

            while (resultSet.next()) {

                int idPedido = resultSet.getInt(1);
                int idCliente = resultSet.getInt(2);
                String nome = resultSet.getString(3);
                int idFuncionario = resultSet.getInt(4);

                if (resultSet.wasNull()) {
                    funcionario = null;
                } else {
                    funcionario = Funcionarios.getInstancia().getFuncionarioByID(idFuncionario);
                }

                double total = resultSet.getDouble(6);
                int desconto = resultSet.getInt(7);
                String status = resultSet.getString(8);

                Cliente cliente = Clientes.getInstancia().getClienteById(idCliente);

                Pedido pedido = new Pedido(idPedido, cliente, funcionario, status);
                pedido.setTotalPedido(total);

                pedidos.add(pedido);

            }
            statement.close();
        }
        getPedidosProdutos();
        return pedidos;

    }

    public void getPedidosProdutos() throws SQLException {
        conexao = ConexaoBDMySQL.getInstancia();

        for (Pedido p : pedidos) {

            produtos = new ArrayList<>();

            try (CallableStatement statement = conexao.getConexao().prepareCall("{call getPedidoProdutos(?)}")) {

                statement.setInt(1, p.getId());
                statement.execute();

                resultSet = statement.getResultSet();

                while (resultSet.next()) {
                    int idProduto = resultSet.getInt(1);
                    int quantidade = resultSet.getInt(2);

                    Produto produto = Produtos.getInstancia().getProdutosbyID(idProduto);
                    PedidoProduto pedidoProduto = new PedidoProduto(produto, quantidade);
                    produtos.add(pedidoProduto);
                }

                statement.close();
            }
            p.setProdutos(produtos);

        }

    }

    public void delPedido(Pedido pedido) throws SQLException {
        conexao = ConexaoBDMySQL.getInstancia();
        try (CallableStatement statement = conexao.getConexao().prepareCall("{call delPedido(?)}")) {
            statement.setInt(1, pedido.getId());
            statement.execute();
            resultSet = statement.getResultSet();
            statement.close();
        }
    }

    public void updPedido(Pedido pedido) throws SQLException {
        System.out.println("Entrou em update pedido na dao");
        System.out.println(pedido.getId());
        for (PedidoProduto p : pedido.getProdutos()) {
            System.out.println(p.getProduto().getId() + p.getProduto().getId() + " " + p.getQuantidade() + " ");
        }
        conexao = ConexaoBDMySQL.getInstancia();

        try (CallableStatement statement = conexao.getConexao().prepareCall("{call updPedido(?, ?, ?, ?)}")) {

            statement.setInt(1, pedido.getId());
            if (pedido.getEntregador() == null) {

                statement.setNull(2, java.sql.Types.INTEGER);
            } else {
                statement.setInt(2, pedido.getEntregador().getId());
            }
            statement.setInt(3, pedido.getCliente().getId());
            statement.setString(4, pedido.getStatusPedido());

            statement.execute();

            statement.close();

        }

        try (CallableStatement statement = conexao.getConexao().prepareCall("{call delPedidoProdutos(?)}")) {
            statement.setInt(1, pedido.getId());
            statement.execute();
            statement.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        for (PedidoProduto p : pedido.getProdutos()) {

            try (CallableStatement statement = conexao.getConexao().prepareCall("{call addPedidoProduto(?, ?, ?)}")) {

                statement.setInt(1, pedido.getId());
                statement.setInt(2, p.getProduto().getId());
                statement.setInt(3, p.getQuantidade());

                statement.execute();

                resultSet = statement.getResultSet();

                statement.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    public int addPedido(Pedido pedido) throws SQLException {

        conexao = ConexaoBDMySQL.getInstancia();
        System.out.println("id e nome: " + pedido.getCliente().getId() + "  " + pedido.getCliente().getNome());

        try (CallableStatement statement = conexao.getConexao().prepareCall("{call addPedido(?, ?)}")) {

            statement.setInt(1, pedido.getCliente().getId());
            if (pedido.getEntregador() == null) {
                statement.setNull(2, java.sql.Types.INTEGER);
            } else {
                statement.setInt(2, pedido.getEntregador().getId());
            }
            statement.execute();

            resultSet = statement.getResultSet();

            if (resultSet.next()) {
                idPedido = resultSet.getInt(1);
            }
            statement.close();
        }

        pedido.setId(idPedido);

        for (PedidoProduto p : pedido.getProdutos()) {

            try (CallableStatement statement = conexao.getConexao().prepareCall("{call addPedidoProduto(?, ?, ?)}")) {

                statement.setInt(1, idPedido);
                statement.setInt(2, p.getProduto().getId());
                statement.setInt(3, p.getQuantidade());

                statement.execute();

                resultSet = statement.getResultSet();

                statement.close();
            }
        }
        return idPedido;
    }

}
