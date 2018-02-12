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
public class Produto {

    protected int id;
    protected String nome;
    protected double preco;
    private String categoria;

    public Produto() {
    }

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public Produto(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public Produto(String nome) {
        this.nome = nome;
    }

    public void setCategoria() {
        this.categoria = "Produto";
    }

    public void setPreco(double preco) {
        if (preco > 0) {
            this.preco = preco;
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        if (!nome.equals(" ")) {
            this.nome = nome;
        }
    }

    public int getId() {
        return id;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public void imprimir() {
        System.out.println("Produto: " + "nome = " + nome + ", preco = " + preco);
    }

    @Override
    public String toString() {
        return nome + ": R$" + preco;
    }

}
