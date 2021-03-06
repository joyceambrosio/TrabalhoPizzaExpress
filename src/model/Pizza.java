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

    private ArrayList<Insumo> ingredientes;
    private String receita;
    private String categoria;

    public Pizza(String receita, String nome, double preco) {
        super(nome, preco);
        setReceita(receita);
        setCategoria();
    }

    public Pizza(int id, String nome, double preco, String receita) {
        super(id, nome, preco);
        this.receita = receita;
        this.ingredientes = null;
        setCategoria();
    }

    public Pizza(String nome, double preco, String receita, ArrayList<Insumo> ingredientes) {
        super(nome, preco);
        setReceita(receita);
        setIngredientes(ingredientes);
        setCategoria();
    }

    public Pizza(ArrayList<Insumo> ingredientes, String receita, String categoria, int id, String nome, double preco) {
        super(id, nome, preco);
        this.ingredientes = ingredientes;
        this.receita = receita;
        this.categoria = categoria;
    }

    public Pizza() {
    }

    @Override
    public void setCategoria() {
        this.categoria = "Pizza";
    }

    @Override
    public String getCategoria() {
        return categoria;
    }

    public ArrayList<Insumo> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<Insumo> ingredientes) {
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

    @Override
    public String toString() {
        return nome + ": R$" + preco;
    }

}
