/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import colections.Clientes;
import colections.Funcionarios;
import conexaoBanco.ConexaoBDMySQL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cliente;
import model.Funcionario;
import model.Insumo;
import model.Pedido;
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
    private ArrayList<Produto> produtos = new ArrayList<>();
    private ArrayList<Insumo> ingredientes = null;

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
                    funcionario = Funcionarios.getInstancia().getFuncionarioByID(1);
                } else {
                    funcionario = Funcionarios.getInstancia().getFuncionarioByID(idFuncionario);
                }

                double total = resultSet.getDouble(6);
                int desconto = resultSet.getInt(7);
                String status = resultSet.getString(8);

                Cliente cliente = Clientes.getInstancia().getClienteById(idCliente);

                Pedido pedido = new Pedido(idPedido, cliente, funcionario, status);

                System.out.println(pedido);

                pedidos.add(pedido);

            }
            statement.close();
        }

        return pedidos;

    }

}
