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
public abstract class Funcionario {

    protected int id;
    protected String nome;
    protected String nomeUsuario;
    protected String senha;
    protected Cargo cargo;
    protected Pedido pedido;

    public Funcionario(int id, String nome, String nomeUsuario, String senha, Cargo cargo) {
        this.id = id;
        this.nome = nome;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.cargo = cargo;
        this.pedido = pedido;
    }


    public int getId() {
        return id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public void setNomeUsuario(String nomeUsuario) {
        if (!nomeUsuario.equals(" ")) {
            this.nomeUsuario = nomeUsuario;
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (!senha.equals(" ")) {
            this.senha = senha;
        }
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        if (!cargo.equals(" ")) {
            this.cargo = cargo;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (!nome.equals(" ")) {
            this.nome = nome;
        }
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        if (pedido != null) {
            this.pedido = pedido;
        }
    }

    @Override
    public abstract String toString();

    public abstract void imprimeInformacoesFuncionario();
}
