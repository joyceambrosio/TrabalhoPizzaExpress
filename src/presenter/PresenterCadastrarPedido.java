/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import colections.Clientes;
import colections.Funcionarios;
import colections.Pedidos;
import colections.Produtos;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import model.Funcionario;
import model.Pedido;
import model.Produto;
import view.CadastrarPedido;

/**
 *
 * @author Natalia
 */
public class PresenterCadastrarPedido {

    private CadastrarPedido view;
    private Pedido pedidoTemp;
    protected ArrayList<Produto> produtosPedido;
    private static PresenterCadastrarPedido instancia;

    private PresenterCadastrarPedido() throws SQLException {
        view = new CadastrarPedido();
        produtosPedido = new ArrayList<>();
        fechar();
        populaCliente();
        populaEntregador();
        adicionaProdutoPedido();
        adicionaPedido();

        populaProduto();

        this.view.addWindowListener(new WindowAdapter() {
            @SuppressWarnings("override")
            public void windowClosing(WindowEvent evt) {
                instancia = null;
                view.dispose();
            }
        });

        URL caminhoImagem = this.getClass().getClassLoader().getResource("icones/PizzaIcone.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        view.setIconImage(iconeTitulo);
        view.setTitle("Cadastrar Pedido");
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    public static PresenterCadastrarPedido getInstancia() throws SQLException {
        if (instancia == null) {
            return instancia = new PresenterCadastrarPedido();
        }

        return instancia;
    }

    public void populaCliente() {
        DefaultComboBoxModel clienteComboBox = new DefaultComboBoxModel();
        view.getjComboBoxClientes().setModel(clienteComboBox);
        clienteComboBox.addElement("Selecione um Cliente");
        for (Cliente c : Clientes.getInstancia().getLista()) {
            clienteComboBox.addElement(c.getNome());
        }

    }

    public void populaProduto() throws SQLException {

        DefaultComboBoxModel produtoComboBox = new DefaultComboBoxModel();
        view.getjComboBoxProdutos().setModel(produtoComboBox);

        for (Produto p : Produtos.getInstancia().getLista()) {
            if (!p.getCategoria().equals("Insumo")) {
                produtoComboBox.addElement(p.getNome());
            }
        }

    }

    public void populaEntregador() {

        DefaultComboBoxModel entregadorComboBox = new DefaultComboBoxModel();
        view.getjComboBoxEntregadores().setModel(entregadorComboBox);
        entregadorComboBox.addElement("Casa");
        try {
            for (Funcionario f : Funcionarios.getInstancia().getFuncionarios()) {
                if (f.getCargo().getCargo().equals("Entregador")) {
                    entregadorComboBox.addElement(f.getNome());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PresenterCadastrarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void populaTabelaPedido() {

        Object colunas[] = {"Produto", "Quantidade", "Valor", "Total"};
        DefaultTableModel tabela = new DefaultTableModel(colunas, 0);
        view.getjTableProdutos().setModel(tabela);

        for (Produto p : produtosPedido) {
            //como colocar a quantidade?
            tabela.addRow(new Object[]{p.getNome(), p.getPreco()});
        }

    }

    public void adicionaProdutoPedido() {
        view.getjButtonAdicionar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nomeProduto = (String) view.getjComboBoxProdutos().getSelectedItem();
                try {
                    Produto p = Produtos.getInstancia().getProdutoByNome(nomeProduto);
                    produtosPedido.add(p);
                    populaTabelaPedido();
                    instancia = null;
                    view.dispose();
                } catch (SQLException ex) {
                    Logger.getLogger(PresenterCadastrarPedido.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    public void adicionaPedido() {
//        view.getjButtonCadastrar().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String nomec = view.getjComboBoxClientes().getSelectedItem().toString();
//                String nomef = view.getjComboBoxEntregadores().getSelectedItem().toString();
//                double total = 0;
//
//                Cliente c = Clientes.getInstancia().getClienteByNome(nomec);
//                Funcionario f = Funcionarios.getInstancia().getFuncionarioByNome(nomef);
//
//                if (c == null || c.equals("-1") || produtosPedido.size() <= 0) {
//
//                    if (c == null || c.equals("-1")) {
//                        JOptionPane.showMessageDialog(view, "Você precisa escolher um cliente");
//                    }
//
//                    if (produtosPedido.size() <= 0) {
//                        JOptionPane.showMessageDialog(view, "Você precisa selecionar ao menos um produto para concluir o pedido");
//                    }
//                } else {
//                    pedidoTemp = new Pedido(c, f, produtosPedido, "Aberto");
//                    c.setNumeroCompra(c.getNumeroCompra()+1);
//                    
//                    //dar desconto ou não
//                    
//                    Pedidos.getInstancia().add(pedidoTemp);
//                    System.out.println("Todos os pedidos");
//                    Pedidos.getInstancia().imprimeLista();
//
//                    JOptionPane.showMessageDialog(view, "Pedido cadastrado com sucesso");
//                    view.dispose();
//                }
//            }
//        });
    }

    public void fechar() {
        view.getjButtonFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
    }

}
