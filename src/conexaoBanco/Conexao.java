/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Natalia
 */
public class Conexao {

    private final String USUARIO = "root";
    private final String SENHA = "root";
    private final String URL = "jdbc:mysql://127.0.0.1:3306/PizzaExpress";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private static Conexao instancia;
    
    private Conexao(){
    }
    
    public static Conexao getInstancia(){
        if(instancia == null){
            instancia = new Conexao();
            
        }
        
        return instancia;
    }

    // Conectar ao banco
    public Connection abrir() throws Exception {
        // Registrar o driver
        Class.forName(DRIVER);
        // Capturar a conexão
        conn = DriverManager.getConnection(URL, USUARIO, SENHA);
        // Retorna a conexao aberta
        stmt = conn.createStatement();
        return conn;

    }

    public void fechar() throws Exception {

        conn.close();
        stmt.close();
        rs.close();

    }

}
