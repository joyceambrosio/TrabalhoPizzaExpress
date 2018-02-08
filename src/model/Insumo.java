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
public class Insumo extends Produto {

    private String categoria;
    private String unidade;
    private String quantidade;

    public Insumo(String nome, double preco) {
        super(nome, preco);
        setCategoria();
    }

    public Insumo(int id, String nome, double preco, String unidade, String quantidade) {
        super(id, nome, preco);
        this.quantidade = quantidade;
        this.unidade = unidade;
        setCategoria();
    }

    public void setCategoria() {
        this.categoria = "Insumo";
    }

    public String getUnidade() {
        return unidade;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public Insumo() {
    }

    public Insumo(String nome) {
        super(nome);
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
        System.out.println("Insumo: " + "nome = " + nome + ", preco = " + preco);
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " Preço: " + preco;
    }

}
