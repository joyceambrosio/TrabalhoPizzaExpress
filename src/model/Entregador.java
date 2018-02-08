/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Natalia
 */
public class Entregador extends Funcionario {

    public Entregador(int id, String nome, String nomeUsuario, String senha, Cargo cargo) {
        super(id, nome, nomeUsuario, senha, cargo);
    }

  
    
    @Override
    public void imprimeInformacoesFuncionario() {
        System.out.println("Nome Funcionario: " + super.getNome() + " Usuário: " + getNomeUsuario() + " Senha: " + super.getSenha()
                + "\nCargo: " + super.getCargo().getCargo() + " Salário: " + super.getCargo().getSalario() + " Comissão: Não comissionado");

    }

    @Override
    public String toString() {
        return this.getNome() + " " + this.getNomeUsuario() + " " + this.cargo;
    }

}
