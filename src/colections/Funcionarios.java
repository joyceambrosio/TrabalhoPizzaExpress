/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colections;

import dao.DaoFuncionario;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Funcionario;

/**
 *
 * @author Natalia
 */
public class Funcionarios {

    private static Funcionarios instancia = null;
    private static ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private DaoFuncionario dao;

    private Funcionarios() {
        dao = new DaoFuncionario();
    }

    public static Funcionarios getInstancia() {
        if (instancia == null) {
            instancia = new Funcionarios();
        }
        return instancia;
    }
   
    public ArrayList<Funcionario> getFuncionarios() throws SQLException {
        return funcionarios = dao.getFuncionarios();
    }

    public boolean isFuncionario(Funcionario funcionario) {
        for (Funcionario f : funcionarios) {
            if (f.getNomeUsuario().equals(funcionario.getNomeUsuario())) {
                return true;
            }
        }
        return false;
    }

    public boolean add(Funcionario f) throws SQLException {
        if (!isFuncionario(f)) {
            dao.addFuncionario(f);
            funcionarios.add(f);
            return true;
        }
        return false;
    }

    public boolean funcionarioValido(String usuario, String senha) throws SQLException {
        for (Funcionario f : getFuncionarios()) {
            if(f.getNomeUsuario().equals(usuario) && f.getSenha().equals(senha)){
                return true;
            }
           
        }
        return false;
    }

    public Funcionario getFuncionario(String usuario) throws SQLException {
        for (Funcionario f : dao.getFuncionarios()) {
            if (f.getNomeUsuario().equals(usuario)) {
                return f;
            }
        }
        return null;
    }

    public void updtFuncionario(Funcionario funcionario) throws SQLException {

        int x =-1;
        for(Funcionario f: funcionarios){
             x ++;
            if(f.getId() ==  funcionario.getId()){
                dao.updateFuncionario(funcionario);
                funcionarios.set(x, funcionario);
            }
        }
        
    }

    public Funcionario getFuncionarioByID(int id) {
        for (Funcionario f : funcionarios) {
            if (f.getId() == id) {

                return f;
            }
        }
        return null;
    }

    public Funcionario getFuncionarioByNome(String nome) {
        for (Funcionario f : funcionarios) {
            if (f.getNome().equals(nome)) {
                return f;
            }
        }
        return null;
    }

    public void imprimeFuncionarios() {
        for (Funcionario f : funcionarios) {
            System.out.println(f.toString());
        }
    }
    
    public void desativaFuncionario(int idFuncionario) throws SQLException{
        dao.desativaFuncionario(idFuncionario);
    }

    public void addAll(ArrayList<Funcionario> atualizar) {
        funcionarios.addAll(atualizar);
    }
    
    public ArrayList<Funcionario> pesquisa(String nome){
        ArrayList<Funcionario> funcTemp = new ArrayList<>();
        for(Funcionario f: funcionarios){
            if(f.getNome().toLowerCase().contains(nome.toLowerCase()) ){
                funcTemp.add(f);
            }
        }
      
        return funcTemp;
    }

}
