/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colections;

import dao.DaoProduto;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Produto;

/**
 *
 * @author Natalia
 */
public class Produtos {

    private static Produtos instancia = null;
    private static ArrayList<Produto> produtos = new ArrayList<>();

    private Produtos() {

    }

    public static Produtos getInstancia() throws SQLException {
        if (instancia == null) {
            return new Produtos();
        }
        return instancia;
    }

    public ArrayList<Produto> getProdutos() throws SQLException {
        DaoProduto dao = new DaoProduto();
        return produtos = dao.getProdutos();
    }

    public ArrayList<Produto> getLista() {
        return produtos;
    }

    private boolean isProduto(Produto produto) {
        return produtos.indexOf(produto) != -1;

    }

    public boolean add(Produto produto) throws SQLException {
        DaoProduto dao = new DaoProduto();
        if (dao.addProduto(produto)) {
            return produtos.add(produto);
        }
        return false;
    }

    public Produto getProdutoByNome(String nome) {
        for (Produto p : produtos) {
            if (p.getNome().equals(nome)) {
                return p;
            }
        }
        return null;
    }

    public Produto getProdutosbyID(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id && !p.getCategoria().equals("Insumo")) {
                return p;
            }
        }
        return null;
    }

    public Produto getInsumosbyID(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id && p.getCategoria().equals("Insumo")) {
                return p;
            }
        }
        return null;
    }

    public boolean remove(Produto produto) throws SQLException {
        DaoProduto dao = new DaoProduto();   
        if (isProduto(produto)) {
            dao.removeProduto(produto);
            return true;
        } else {
            return false;
        }
    }

    public void imprimeLista() {
        for (Produto p : produtos) {
            System.out.println(p.toString());;
        }
    }

    public boolean update(Produto old) throws SQLException {
        DaoProduto dao = new DaoProduto();
        System.out.println("entrou na collection");
        int x = -1;
        for (Produto p : produtos) {
            x++;
            if (p.getId() == old.getId()) {
                dao.updateProduto(old);
                produtos.set(x, old);
                return true;
            }
        }
        return false;
    }

}
