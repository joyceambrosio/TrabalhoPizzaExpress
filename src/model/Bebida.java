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
public class Bebida extends Produto {

    private String categoria;

    public Bebida() {
    }

    public Bebida(String nome) {
        super(nome);
    }

    public Bebida(int id, String nome, double preco) {
        super(id, nome, preco);
        setCategoria();
    }

    public void setCategoria() {
        this.categoria = "Bebida";
    }

    @Override
    public String getCategoria() {
        return categoria;
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
        System.out.println("Bebida: " + " nome = " + nome + ", preco = " + preco);
    }

    @Override
    public String toString() {
        return "Bebida{" + "categoria=" + categoria + '}';
    }

}
