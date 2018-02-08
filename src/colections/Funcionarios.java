/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colections;

import java.util.ArrayList;
import model.Funcionario;

/**
 *
 * @author Natalia
 */
public class Funcionarios {

    private static Funcionarios instancia = null;
    private static ArrayList<Funcionario> funcionarios = new ArrayList<>();

    public Funcionarios() {
    }

    public static Funcionarios getInstancia() {
        if (instancia == null) {
            instancia = new Funcionarios();
        }
        return instancia;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return this.funcionarios;
    }

    public boolean isFuncionario(Funcionario funcionario) {
        for (Funcionario f : funcionarios) {
            if (f.getNomeUsuario().equals(funcionario.getNomeUsuario())) {
                return true;
            }
        }
        return false;
    }

    public boolean add(Funcionario f) {
        if (!isFuncionario(f)) {
            funcionarios.add(f);
            return true;
        }
        return false;
    }

    public boolean funcionarioValido(String usuario) {
        for (Funcionario f : funcionarios) {
            return f.getNomeUsuario().equals(usuario);
        }
        return false;
    }

    public Funcionario getFuncionario(String usuario) {
        for (Funcionario f : funcionarios) {
            if (f.getNomeUsuario().equals(usuario)) {
                return f;
            } else {
                return null;
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

    public void addAll(ArrayList<Funcionario> atualizar){
       funcionarios.addAll(atualizar);
    }
    
}
