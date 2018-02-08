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
public class Pedido {

    private static int uniqueId = 0;
    private int id;
    private Cliente cliente;
    private Funcionario entregador;
    private ArrayList<Produto> produtos;
    private String statusPedido;

    //atributo estático em pedido
    private static int numTotalPedido;

    public Pedido() {
    }

    public Pedido(Cliente cliente, Funcionario entregador) {
        id = uniqueId;
        uniqueId++;
        setCliente(cliente);
        alterarEntregador(entregador);
        setStatusPedido("Aberto");
    }

    public Pedido(Cliente cliente, Funcionario entregador, ArrayList<Produto> produtos, String statusPedido) {
        id = uniqueId;
        uniqueId++;
        System.out.println(uniqueId);
        System.out.println(uniqueId);
        setCliente(cliente);
        alterarEntregador(entregador);
        setProdutos(produtos);
        setStatusPedido(statusPedido);
    }

    public int getIdPedido() {
        return this.id;
    }

    public void setEntregador(Funcionario entregador) {
        this.entregador = entregador;
    }

    public static int getNumTotalPedido() {
        return numTotalPedido;
    }

    public static void setNumTotalPedido(int numTotalPedido) {
        Pedido.numTotalPedido = numTotalPedido;
    }

    public void addProduto(Produto produto) {
        produtos.add(produto);
        numTotalPedido++;
    }

    public void removerProduto(Produto produto) {
        produtos.remove(produto);
    }

    public void alterarProduto(Produto produto, int i) {
        produtos.set(i, produto);
    }

    //Aberto > Em Produção > Em  Entrega > Concluido
    public boolean nextStatusPedido() {
        if (this.statusPedido.equals(" ")) {
            setStatusPedido("Aberto");
            return true;
        } else if (this.statusPedido.equals("Aberto")) {
            setStatusPedido("Em produção");
            return true;
        } else if (this.statusPedido.equals("Em produção")) {
            setStatusPedido("Em entrega");
            return true;
        } else if (this.statusPedido.equals("Em entrega")) {
            setStatusPedido("Concluído");
            return true;
        }
        return false;
    }

    public boolean lastStatusPedido() {
        if (this.statusPedido.equals(" ")) {
            setStatusPedido("Aberto");
            return true;
        } else if (this.statusPedido.equals("Concluído")) {
            setStatusPedido("Em entrega");
            return true;
        } else if (this.statusPedido.equals("Em entrega")) {
            setStatusPedido("Em produção");
            return true;
        } else if (this.statusPedido.equals("Em produção")) {
            setStatusPedido("Aberto");
            return true;
        } else if (this.statusPedido.equals("Aberto")) {
            return false;
        }
        return false;
    }

    public void statusDoPedido() {
        System.out.println("Status do pedido: " + statusPedido);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        if (cliente != null) {
            this.cliente = cliente;
        }
    }

    //método estático para verificar a quantidade de número de pedidos
    public static void consultarNumeroTotalDePedidos() {
        System.out.println(numTotalPedido);
    }

    public Funcionario getEntregador() {
        return entregador;
    }

    public void alterarEntregador(Funcionario entregador) {
        if (entregador != null) {
            this.entregador = entregador;

        }
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        if (produtos != null) {
            this.produtos = produtos;
        }
    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        if (!statusPedido.equals(" ")) {
            this.statusPedido = statusPedido;
        } else {
            this.statusPedido = "Aberto";
        }
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", cliente=" + cliente + ", entregador=" + entregador + ", produtos=" + produtos + ", statusPedido=" + statusPedido + '}';
    }

}
