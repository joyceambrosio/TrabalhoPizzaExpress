/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.CallableStatement;
import java.sql.*;
import java.util.ArrayList;
import model.Cliente;
import model.Endereco;
import conexaoBanco.*;

/**
 *
 * @author joyce
 */
public class DaoCliente {

    private ResultSet resultSet;
    ConexaoBDMySQL conexao;
    ArrayList<Integer> ids;
    ArrayList<Cliente> clientes = new ArrayList<>();

    public DaoCliente() {

    }

    public ArrayList<Integer> addCliente(Cliente cliente) throws SQLException {
        this.ids = new ArrayList<Integer>();
        conexao = ConexaoBDMySQL.getInstancia();

        try (CallableStatement statement = conexao.getConexao().prepareCall("{call addCliente(?, ?, ?, ?, ?, ?, ?, ?)}")) {

            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getEndereco().getLogradouro());
            statement.setString(3, cliente.getEndereco().getNumero());
            statement.setString(4, cliente.getEndereco().getBairro());
            statement.setString(5, cliente.getEndereco().getCidade());
            statement.setString(6, cliente.getEndereco().getEstado());
            statement.setString(7, cliente.getEndereco().getCep());
            statement.setString(8, cliente.getEndereco().getPontoDeReferencia());

            statement.execute();
            resultSet = statement.getResultSet();

            if (resultSet.next()) {
                
                ids.add(resultSet.getInt(1));
                ids.add(resultSet.getInt(2));

                statement.close();

                return ids;

            } else {
                return null;
            }

        }
    }

    public ArrayList<Cliente> getClientes() throws SQLException {

        conexao = ConexaoBDMySQL.getInstancia();
        try (CallableStatement statement = conexao.getConexao().prepareCall("{call getClientes()}")) {
            statement.execute();
            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                int idCliente = resultSet.getInt(1);
                int idEndereco = resultSet.getInt(2);
                int nCompras = resultSet.getInt(3);
                String nome = resultSet.getString(4);
                String logradouro = resultSet.getString(5);
                String numero = resultSet.getString(6);
                String bairro = resultSet.getString(7);
                String cidade = resultSet.getString(8);
                String estado = resultSet.getString(9);
                String cep = resultSet.getString(10);
                String pontoDeReferencia = resultSet.getString(11);

                Endereco end = new Endereco(idEndereco, logradouro, bairro, cidade, estado, cep, pontoDeReferencia, numero);
                Cliente cli = new Cliente(idCliente, nome, end, true, nCompras);

                clientes.add(cli);
            }

            System.out.println("Chamada de inserção de cliente feita com sucesso!");

            statement.close();

            return clientes;

        }
    }

    public void updCliente(Cliente cliente) throws SQLException {

        this.ids = new ArrayList<Integer>();
        conexao = ConexaoBDMySQL.getInstancia();

        try (CallableStatement statement = conexao.getConexao().prepareCall("{call updCliente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {

            statement.setInt(1, cliente.getId());
            statement.setInt(2, cliente.getEndereco().getId());
            statement.setInt(3, cliente.getNumeroCompra());
            statement.setString(4, cliente.getNome());
            statement.setString(5, cliente.getEndereco().getLogradouro());
            statement.setString(6, cliente.getEndereco().getNumero());
            statement.setString(7, cliente.getEndereco().getBairro());
            statement.setString(8, cliente.getEndereco().getCidade());
            statement.setString(9, cliente.getEndereco().getEstado());
            statement.setString(10, cliente.getEndereco().getCep());
            statement.setString(11, cliente.getEndereco().getPontoDeReferencia());

            statement.execute();
            resultSet = statement.getResultSet();

            statement.close();

        }
    }

    public ArrayList<Cliente> getClienteByNome(String nomeCliente) throws SQLException {
        conexao = ConexaoBDMySQL.getInstancia();
        try (CallableStatement statement = conexao.getConexao().prepareCall("{call getClienteByNome(?)}")) {
            nomeCliente = ("%" + nomeCliente + "%");
            statement.setString(1, nomeCliente);
            statement.execute();
            resultSet = statement.getResultSet();

            while (resultSet.next()) {

                int idCliente = resultSet.getInt(1);
                int idEndereco = resultSet.getInt(2);
                int nCompras = resultSet.getInt(3);
                String nome = resultSet.getString(4);
                String logradouro = resultSet.getString(5);
                String numero = resultSet.getString(6);
                String bairro = resultSet.getString(7);
                String cidade = resultSet.getString(8);
                String estado = resultSet.getString(9);
                String cep = resultSet.getString(10);
                String pontoDeReferencia = resultSet.getString(11);

                Endereco end = new Endereco(idEndereco, logradouro, bairro, cidade, estado, cep, pontoDeReferencia, numero);
                Cliente cli = new Cliente(idCliente, nome, end, true, nCompras);

                clientes.add(cli);
                System.out.println(cli.getNome());
            }

            System.out.println("Chamada de busca por id feita com sucesso!");

            statement.close();

            return clientes;

        }

    }

    public void desativaCliente(int idCliente) throws SQLException {
        conexao = ConexaoBDMySQL.getInstancia();

        try (CallableStatement statement = conexao.getConexao().prepareCall("{call desativaCliente(?)}")) {

            statement.setInt(1, idCliente);

            statement.execute();

            System.out.println("Cliente desativado com sucesso!");

            statement.close();

        }

    }

}
