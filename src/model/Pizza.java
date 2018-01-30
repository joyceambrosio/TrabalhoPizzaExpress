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
public class Pizza extends Produto {

    private ArrayList<Produto> ingredientes;
    private String receita;

    public Pizza(String receita, String nome, double preco) {
        super(nome, preco);
        setReceita(receita);
    }

    public Pizza(ArrayList<Produto> ingredientes, String receita, String nome, double preco) {
        super(nome, preco);
        setIngredientes(ingredientes);
        setReceita(receita);
    }

    public Pizza() {
    }

    public ArrayList<Produto> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<Produto> ingredientes) {
        if (ingredientes != null) {
            this.ingredientes = ingredientes;
        }
    }

    public String getReceita() {
        return receita;
    }

    public void setReceita(String receita) {
        if (!receita.equals(" ")) {
            this.receita = receita;
        }
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        super.setNome(nome);
    }

    @Override
    public double getPreco() {
        return preco;
    }

    @Override
    public void setPreco(double preco) {
        super.setPreco(preco);
    }

    @Override
    public void imprimir() {
        System.out.println("Pizza: " + " nome = " + nome + ", preco = " + preco);
    }

    //Testar
    @Override
    public String toString() {
        return "Nome Pizza: " + nome + " Preço: " + preco + "\nReceita: " + receita + " Produtos: " + ingredientes.toString();

    }

}
