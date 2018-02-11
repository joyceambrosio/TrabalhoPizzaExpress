/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colections;

import dao.DaoPedido;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cliente;
import model.Pedido;
import model.PedidoProduto;
import model.Produto;

/**
 *
 * @author Natalia
 */
public class Pedidos {

    private static Pedidos instancia = null;
    private static ArrayList<Pedido> pedidos = new ArrayList<>();

    private Pedidos() {

    }

    public static Pedidos getInstancia() {
        if (instancia == null) {
            return new Pedidos();
        }
        return instancia;
    }

    public ArrayList<Pedido> getPedidos() throws SQLException {
        DaoPedido dao = new DaoPedido();
        return dao.getPedidos();
    }

    public void setPedidos() throws SQLException {
        DaoPedido dao = new DaoPedido();
        this.pedidos = dao.getPedidos();
    }

    public ArrayList<Pedido> getLista() {
        return pedidos;
    }

    public Pedido getPedidoById(int id) throws SQLException {
        setPedidos();
        for (Pedido p : pedidos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    private boolean isPedido(Pedido pedido) {
        return pedidos.indexOf(pedido) != -1;

    }

    public boolean add(Pedido pedido) throws SQLException {
        DaoPedido dao = new DaoPedido();
        dao.addPedido(pedido);
        return pedidos.add(pedido);
    }

    public boolean remove(Pedido pedido) throws SQLException {
        DaoPedido dao = new DaoPedido();
        dao.delPedido(pedido);
        return pedidos.remove(pedido);

    }

    public void imprimeLista() {
        for (Pedido p : pedidos) {
            System.out.println(p.toString());;
        }
    }

    public void updatePedido(Pedido p) throws SQLException {
        DaoPedido dao = new DaoPedido();
        System.out.println("Dentro de update na collections");
        System.out.println(p.getId());
        
        for (PedidoProduto g : p.getProdutos()){
            System.out.println(g.getProduto().getId() +  " " + g.getProduto().getNome() + " "+ g.getQuantidade());
        }

        dao.updPedido(p);
        setPedidos();
    }
}
