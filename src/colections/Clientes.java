/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colections;

import dao.DaoCliente;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cliente;

/**
 *
 * @author Natalia
 */
public class Clientes {

    private static Clientes instancia = null;
    private static ArrayList<Cliente> clientes = new ArrayList<>();

    private Clientes() {

    }

    public static Clientes getInstancia() {
        if (instancia == null) {
            return new Clientes();
        }
        return instancia;
    }

    public Iterable<Cliente> getClientes() throws SQLException {
        DaoCliente dao = new DaoCliente();
        this.clientes = dao.getClientes();
        return clientes;
    }

    public ArrayList<Cliente> getLista() {
        return clientes;
    }

    public ArrayList<Cliente> getClienteByNome(String nome) throws SQLException {
        DaoCliente dao = new DaoCliente();
        return dao.getClienteByNome(nome);
    }

    public Cliente getClienteById(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    private boolean isCliente(Cliente cliente) {
        return clientes.indexOf(cliente) != -1;

    }

    public void desativaCliente(int idCliente) throws SQLException {
        DaoCliente dao = new DaoCliente();
        dao.desativaCliente(idCliente);
    }

    public boolean modifica(Cliente clienteOld, Cliente clienteNew) throws SQLException {
        // O que eu to fazendo aqui é pegar o cliente velho e um cliente novo com as novas variáveis
        // setar os ids do cliente velho no cliente novo e passar pra dao

        int index = clientes.indexOf(clienteOld);

        if (index > -1) {
            clientes.set(index, clienteNew);

            clienteNew.getEndereco().setId(clienteOld.getEndereco().getId());
            clienteNew.setId(clienteOld.getId());
            clienteNew.setNumeroCompra(clienteOld.getNumeroCompra());

            DaoCliente dao = new DaoCliente();
            dao.updCliente(clienteNew);
            return true;
        }

        return false;
    }

    public boolean add(Cliente cliente) throws SQLException {
        DaoCliente dao = new DaoCliente();
        
        ArrayList<Integer> idsCliente = dao.addCliente(cliente);

        cliente.setId(idsCliente.get(0));
        cliente.getEndereco().setId(idsCliente.get(1));

        return clientes.add(cliente);
    }

    public boolean remove(Cliente cliente) {
        if (isCliente(cliente)) {
            clientes.remove(cliente);
            return true;
        } else {
            return false;
        }
    }

    public void imprimeLista() {
        for (Cliente c : clientes) {
            System.out.println(c.toString());
        }
    }

}
