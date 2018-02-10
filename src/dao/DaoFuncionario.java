/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexaoBanco.ConexaoBDMySQL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Administrador;
import model.Atendente;
import model.Cargo;
import model.Cozinheiro;
import model.Entregador;
import model.Funcionario;

/**
 *
 * @author joyce
 */
public class DaoFuncionario {

    private ResultSet resultSet;
    private ConexaoBDMySQL conexao;
    private ArrayList<Funcionario> funcionarios;

    public DaoFuncionario() {
   
    }

    public void addFuncionario(Funcionario novoFunc) throws SQLException {
        conexao = ConexaoBDMySQL.getInstancia();

        try (CallableStatement statement = conexao.getConexao().prepareCall("{call addFuncionario(?, ?, ?, ?)}")) {

            statement.setInt(1, novoFunc.getCargo().getIdCargo());
            statement.setString(2, novoFunc.getNomeUsuario());
            statement.setString(3, novoFunc.getNome());
            statement.setString(4, novoFunc.getSenha());

            statement.execute();

            resultSet = statement.getResultSet();

            Funcionario funcionario = null;

            int idCargo = novoFunc.getCargo().getIdCargo();
            String nomeFuncionario = novoFunc.getNome();
            String nomeUsuario = novoFunc.getNomeUsuario();
            String senha = novoFunc.getSenha();

            Cargo cargo = new Cargo(novoFunc.getCargo().getCargo(), novoFunc.getCargo().getSalario(), novoFunc.getCargo().isComissao(), novoFunc.getCargo().getIdCargo());

            if (novoFunc.getCargo().getCargo().equals("Atendente")) {
                funcionario = new Atendente(novoFunc.getCargo().getIdCargo(), nomeFuncionario, nomeUsuario, senha, cargo);
            } else if (novoFunc.getCargo().getCargo().equals("Cozinheiro")) {
                funcionario = new Cozinheiro(idCargo, nomeFuncionario, nomeUsuario, senha, cargo);
            } else if (novoFunc.getCargo().getCargo().equals("Entregador")) {
                funcionario = new Entregador(idCargo, nomeFuncionario, nomeUsuario, senha, cargo);
            } else if (novoFunc.getCargo().getCargo().equals("Administrador")) {
                funcionario = new Administrador(idCargo, nomeFuncionario, nomeUsuario, senha, cargo);
            }

            if (resultSet.next()) {
                funcionario.setId(resultSet.getInt(1));
            }
            
            funcionarios.add(funcionario);

            statement.close();
        }
    }

    public boolean updateFuncionario(Funcionario novoFuncionario) throws SQLException {
        conexao = ConexaoBDMySQL.getInstancia();
        try (CallableStatement statement = conexao.getConexao().prepareCall("{call updFuncionario(?, ?, ?, ?)}")) {

            statement.setInt(1, novoFuncionario.getId());
            statement.setInt(2, novoFuncionario.getCargo().getIdCargo());
            statement.setString(3, novoFuncionario.getNome());
            statement.setString(4, novoFuncionario.getSenha());
            resultSet = statement.getResultSet();

            statement.execute();
            return true;
        }

        
    }

    public ArrayList<Funcionario> getFuncionarios() throws SQLException {
        conexao = ConexaoBDMySQL.getInstancia();
        funcionarios =  new ArrayList<>();
        try (CallableStatement statement = conexao.getConexao().prepareCall("{call getFuncionarios()}")) {
            statement.execute();
            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                int idFuncionario = resultSet.getInt("idFuncionario");
                String nomeFuncionario = resultSet.getString(2);
                String nomeUsuario = resultSet.getString("usuario");
                String senha = resultSet.getString("senha");
                boolean comissao = resultSet.getBoolean("comissao");
                int idCargo = resultSet.getInt("idCargo");
                double salario = resultSet.getDouble("salario");
                String nomeCargo = resultSet.getString(6);
                Cargo cargo = new Cargo(nomeCargo, salario, comissao, idCargo);

                Funcionario funcionario = null;

                if (nomeCargo.equals("Atendente")) {
                    funcionario = new Atendente(idCargo, nomeFuncionario, nomeUsuario, senha, cargo);
                } else if (nomeCargo.equals("Cozinheiro")) {
                    funcionario = new Cozinheiro(idCargo, nomeFuncionario, nomeUsuario, senha, cargo);
                } else if (nomeCargo.equals("Entregador")) {
                    funcionario = new Entregador(idCargo, nomeFuncionario, nomeUsuario, senha, cargo);
                } else if (nomeCargo.equals("Administrador")) {
                    funcionario = new Administrador(idCargo, nomeFuncionario, nomeUsuario, senha, cargo);
                }

                funcionario.setId(idFuncionario);
                funcionarios.add(funcionario);

            }
        }

        return funcionarios;
    }

    
    public void desativaFuncionario(int idFuncionario) throws SQLException{
         conexao = ConexaoBDMySQL.getInstancia();

        try (CallableStatement statement = conexao.getConexao().prepareCall("{call desativaFuncionario(?)}")) {

            statement.setInt(1, idFuncionario);

            statement.execute();

            statement.close();

        }
    }
}
