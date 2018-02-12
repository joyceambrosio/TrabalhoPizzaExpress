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
public class Cliente {

    private static int uniqueId = 0;
    private int id;
    private String nome;
    private Endereco endereco;
    private boolean status;
    private int numeroCompra;

    public Cliente() {

    }

    public Cliente(int idCliente, String nome, Endereco endereco, boolean status, int numeroCompra) {
        this.id = idCliente;
        setNome(nome);
        setEndereco(endereco);
        setStatus(status);
        setNumeroCompra(numeroCompra);
    }

    public Cliente(String nome, Endereco endereco, boolean status, int numeroCompra) {
        setNome(nome);
        setEndereco(endereco);
        setStatus(status);
        setNumeroCompra(numeroCompra);
    }

    public Cliente(String nome, Endereco endereco) {
        setNome(nome);
        setEndereco(endereco);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (!nome.equals(" ")) {
            this.nome = nome;
        }
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        if (endereco != null) {
            this.endereco = endereco;
        }
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        if (status != this.status) {
            this.status = status;
        }
    }

    public int getNumeroCompra() {
        return numeroCompra;
    }

    public void setNumeroCompra(int numeroCompra) {
        this.numeroCompra = numeroCompra;
    }

    @Override
    public String toString() {
        return this.nome;
    }

}
