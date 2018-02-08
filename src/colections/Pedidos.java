/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colections;

import java.util.ArrayList;
import model.Cliente;
import model.Pedido;
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

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public ArrayList<Pedido> getLista() {
        return pedidos;
    }

    public Pedido getPedidoById(int id) {
        for (Pedido p : pedidos) {
            if (p.getIdPedido() == id) {
                return p;
            }
        }
        return null;
    }

    private boolean isPedido(Pedido pedido) {
        return pedidos.indexOf(pedido) != -1;

    }

    public boolean add(Pedido pedido) {
        return pedidos.add(pedido);
    }

    public boolean remove(Pedido pedido) {
        if (isPedido(pedido)) {
            pedidos.remove(pedido);
            return true;
        } else {
            return false;
        }
    }

    public void imprimeLista() {
        for (Pedido p : pedidos) {
            System.out.println(p.toString());;
        }
    }
}
