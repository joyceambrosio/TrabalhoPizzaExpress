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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import model.Funcionario;
import model.Pedido;
import model.PedidoProduto;
import model.Produto;
import view.viewCadastrarPedido;

/**
 *
 * @author Natalia
 */
public class PresenterCadastrarPedido {

    private viewCadastrarPedido view;
    private Pedido pedidoTemp;
    protected ArrayList<PedidoProduto> produtosPedido;
//    protected ArrayList<PedidoProduto> produtosPedidoTemp;
    private static PresenterCadastrarPedido instancia;
    private Cliente clientePedido = null;
    private Funcionario funcionarioPedido = null;

    private PresenterCadastrarPedido() throws SQLException {
        view = new viewCadastrarPedido();
        produtosPedido = new ArrayList<>();
        fechar();
        populaCliente();
        populaEntregador();
        adicionaProdutoPedido();
        removerProdutoPedido();
        adicionaPedido();
        escutaCliente();

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

    public void populaCliente() throws SQLException {
        DefaultComboBoxModel clienteComboBox = new DefaultComboBoxModel();
        view.getjComboBoxClientes().setModel(clienteComboBox);
//        clienteComboBox.addElement("Selecione um Cliente");
        for (Cliente c : Clientes.getInstancia().getLista()) {
            clienteComboBox.addElement(c);
        }

    }

    public void escutaCliente() {
        view.getjComboBoxClientes().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populaTabelaPedido();
            }
        });
    }

    public void populaProduto() throws SQLException {

        DefaultComboBoxModel produtoComboBox = new DefaultComboBoxModel();
        view.getjComboBoxProdutos().setModel(produtoComboBox);

        for (Produto p : Produtos.getInstancia().getLista()) {
            if (!p.getCategoria().equals("Insumo")) {
                produtoComboBox.addElement(p);
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
                    entregadorComboBox.addElement(f);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PresenterCadastrarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void populaTabelaPedido() {

        Object colunas[] = {"Produto", "Preço", "Quantidade", "Total"};
        DefaultTableModel tabela = new DefaultTableModel(colunas, 0);
        view.getjTableProdutos().setModel(tabela);

        double total = 0.0;
        double totallinha = 0.0;

        for (PedidoProduto p : produtosPedido) {
            totallinha = (p.getProduto().getPreco() * p.getQuantidade());
            tabela.addRow(new Object[]{p, p.getProduto().getPreco(), p.getQuantidade(), totallinha});
            total = total + totallinha;
        }

        if (view.getjComboBoxClientes().getSelectedItem().equals("Selecione um Cliente")) {
            String labeltotal = "R$ " + total;
            view.getjLabelSubtotal().setText(labeltotal);
            view.getjLabelDesconto().setText("R$ 0.00");
            view.getjLabelTotalPedido().setText(labeltotal);
        } else {
            clientePedido = (Cliente) view.getjComboBoxClientes().getSelectedItem();
            if (clientePedido.getNumeroCompra() == 9) {
                String labeltotal = "R$ " + total;
                String labelDesconto = "R$" + (total * 0.5);
                view.getjLabelSubtotal().setText(labeltotal);
                view.getjLabelDesconto().setText(labelDesconto);
                view.getjLabelTotalPedido().setText(labelDesconto);
            } else {
                String labeltotal = "R$ " + total;
                String labelDesconto = "R$" + (total * 1);
                view.getjLabelSubtotal().setText(labeltotal);
                view.getjLabelDesconto().setText("R$ 0.00");
                view.getjLabelTotalPedido().setText(labeltotal);

            }
        }

    }

    public void adicionaProdutoPedido() {
        view.getjButtonAdicionar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Produto produto = (Produto) view.getjComboBoxProdutos().getSelectedItem();
                System.out.println(produto.getCategoria());
                int qtd = (Integer) view.getjSpinnerQuantidade().getValue();

                if (qtd > 0) {
                    PedidoProduto nPedidoProduto = new PedidoProduto(produto, qtd);
                    produtosPedido.add(nPedidoProduto);
                    populaTabelaPedido();
                } else {
                    view.getjLabelAvisosProduto().setText("Escolha uma quantidade para adicionar o produto");
                }
            }
        }
        );
    }

    public void removerProdutoPedido() {
        view.getjButtonRemoverProduto().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linha = view.getjTableProdutos().getSelectedRow();
                if (linha == -1) {
                    JOptionPane.showMessageDialog(view, "Você precisa selecionar um produto na lista antes de remover");
                } else {

                    PedidoProduto pedidoProduto = (PedidoProduto) view.getjTableProdutos().getValueAt(linha, 0);

                    produtosPedido.remove(pedidoProduto);
                    populaTabelaPedido();

                }
            }
        }
        );
    }

    public void adicionaPedido() {
        view.getjButtonCadastrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (validaCampos()) {
                    pedidoTemp = new Pedido(clientePedido, funcionarioPedido, produtosPedido, "Aberto");

                    try {
                        Pedidos.getInstancia().add(pedidoTemp);
                    } catch (SQLException ex) {
                        Logger.getLogger(PresenterCadastrarPedido.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    JOptionPane.showMessageDialog(view, "Pedido cadastrado com sucesso");
                    instancia = null;
                    view.dispose();
                }

            }
        });

    }

    public boolean validaCampos() {

        String controleCliente = view.getjComboBoxClientes().getSelectedItem().toString();
        String controleFuncionario = view.getjComboBoxEntregadores().getSelectedItem().toString();

        if (controleFuncionario.equals("Casa")) {
            funcionarioPedido = null;
        } else {
            Funcionario f = (Funcionario) view.getjComboBoxEntregadores().getSelectedItem();
            funcionarioPedido = f;
        }

        if (controleCliente.equals("Selecione um cliente")) {
            view.getjLabelAvisosCliente().setText("Selecione um cliente");
            return false;
        } else {
            clientePedido = (Cliente) view.getjComboBoxClientes().getSelectedItem();
        }

        if (produtosPedido.isEmpty()) {
            view.getjLabelAvisosProduto().setText("Adicione um produto ao pedido");
            return false;
        } else {
            return true;
        }

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
