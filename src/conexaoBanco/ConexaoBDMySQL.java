package conexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoBDMySQL {

    private String serverName = "localhost";
    private String mydatabase = "pizzaexpress";
    private String url = "jdbc:mysql://" + serverName + "/" + mydatabase + "?useSSL=false";
    private String usuario = "root";
    private String password = "root";

    private String DRIVER = "com.mysql.jdbc.Driver";
    private Statement stat = null;
    private static ConexaoBDMySQL instance = null;
    private Connection conexao = null;
    private PreparedStatement ps = null;

    private ConexaoBDMySQL() throws SQLException {
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(url, usuario, password);
            stat = con.createStatement();
            conexao = con;
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public static ConexaoBDMySQL getInstancia() throws SQLException {
        if (instance == null) {
            instance = new ConexaoBDMySQL();
        }
        return instance;
    }

    public Connection getConexao() {
        return conexao;
    }

    public Statement getStatment() {
        return stat;
    }

}
