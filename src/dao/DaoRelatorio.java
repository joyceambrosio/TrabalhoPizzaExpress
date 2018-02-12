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
import model.SalarioComissao;

/**
 *
 * @author Natalia
 */
public class DaoRelatorio {

    private ResultSet resultSet;
    private ConexaoBDMySQL conexao;
    private ArrayList<SalarioComissao> semComissao = new ArrayList<>();
    private ArrayList<SalarioComissao> comCossaoFixa = new ArrayList<>();
    private double totalCaixa = 0;

    public ArrayList<SalarioComissao> getComComissao(String dataInicio, String dataFim) throws SQLException {
        conexao = ConexaoBDMySQL.getInstancia();
        try (CallableStatement statement = conexao.getConexao().prepareCall("{call calcFixos(?,?)}")) {

            statement.setString(1, dataInicio);
            statement.setString(2, dataFim);
            statement.execute();
            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String nome = resultSet.getString(2);
                double salario = resultSet.getDouble(3);
                SalarioComissao s = new SalarioComissao(id, nome, salario);
                comCossaoFixa.add(s);
            }

            statement.close();

        }

        return comCossaoFixa;
    }

    public ArrayList<SalarioComissao> getSemComissao(String dataInicio, String dataFim) throws SQLException {
        conexao = ConexaoBDMySQL.getInstancia();
        try (CallableStatement statement = conexao.getConexao().prepareCall("{call calcComissoes(?,?)}")) {

            statement.setString(1, dataInicio);
            statement.setString(2, dataFim);
            statement.execute();
            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String nome = resultSet.getString(2);
                double salario = resultSet.getDouble(3);
                SalarioComissao s = new SalarioComissao(id, nome, salario);
                semComissao.add(s);
            }
            statement.close();

        }

        return semComissao;
    }

    public double getEntradaCaixa(String dataInicio, String dataFim) throws SQLException {
        conexao = ConexaoBDMySQL.getInstancia();
        try (CallableStatement statement = conexao.getConexao().prepareCall("{call calcEntradaCaixa(?,?)}")) {
            statement.setString(1, dataInicio);
            statement.setString(2, dataFim);
            statement.execute();
            resultSet = statement.getResultSet();

            if (resultSet.next()) {
                totalCaixa = resultSet.getDouble(2);
            }
        }
        return totalCaixa;
    }

}
