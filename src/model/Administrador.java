/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Natalia
 */
public class Administrador extends Funcionario {

//    private static ArrayList<Funcionario> funcionarios;
//    private Relatorio relatorio;
//    private static ArrayList<Produto> produtos;
    public Administrador(int id, String nome, String nomeUsuario, String senha, Cargo cargo) {
        super(id, nome, nomeUsuario, senha, cargo);
    }

    //alterar aqui
//
//    public static ArrayList<Funcionario> getFuncionarios() {
//        return funcionarios;
//    }
//
//    public static void setFuncionarios(ArrayList<Funcionario> funcionarios) {
//        Administrador.funcionarios = funcionarios;
//    }
//
//    public Relatorio getRelatorio() {
//        return relatorio;
//    }
//
//    public void setRelatorio(Relatorio relatorio) {
//        this.relatorio = relatorio;
//    }
//
//    public static ArrayList<Produto> getProdutos() {
//        return produtos;
//    }
//
//    public static void setProdutos(ArrayList<Produto> produtos) {
//        Administrador.produtos = produtos;
//    }
//
//    public void adicionarFuncionario(Funcionario funcionario) {
//        funcionarios.add(funcionario);
//    }
//
//    public void alterarFuncionario(Funcionario funcionario, int i) {
//        funcionarios.add(i, funcionario);
//    }
//
//    public void removerFuncionario(Funcionario funcionario) {
//        funcionarios.remove(funcionario);
//    }
//
//    public void adicionarProduto(Produto produto) {
//        produtos.add(produto);
//    }
//
//    public void alterarProduto(Produto produto, int i) {
//        produtos.add(i, produto);
//    }
//
//    public void removerProduto(Produto produto) {
//        produtos.remove(produto);
//    }
//
//    public void gerarRelatorio(String semana, int mes, int ano) {
//        relatorio.gererRelatorio(semana, mes, ano);
//    }
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
