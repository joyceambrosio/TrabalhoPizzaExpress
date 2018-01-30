/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;

/**
 *
 * @author Natalia
 */
public interface DAOMysql {
    
    //Chamadas de métodos que depois serão implementadas pelos seus filhos
    // Classe responsável por todo acesso ao Banco refetente a inserção, alteração, remoção e busca.
    
    public void inserir(Object o);
    public void remover(Object o);
    public void alterar(Object o);
    public ResultSet buscar(Object o);
    
}
