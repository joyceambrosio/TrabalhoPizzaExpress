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
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
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
 * @author joyce
 */
public class PresenterExibePedido {

    private viewCadastrarPedido view;
    Pedido pedido;

    public PresenterExibePedido(Pedido p) {
        view = new viewCadastrarPedido();
        this.pedido = p;
        fechar();
        configuraExibicao();
        populaTabelaPedido();
        populaCliente();
        populaProduto();
        populaEntregador();

        URL caminhoImagem = this.getClass().getClassLoader().getResource("icones/PizzaIcone.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        view.setIconImage(iconeTitulo);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    public void configuraExibicao() {
        view.getjButtonCadastrar().setEnabled(false);

        view.getjButtonAdicionar().setEnabled(false);

        view.getjButtonRemoverProduto().setEnabled(false);
        view.getjSpinnerQuantidade().setEnabled(false);
    }

    public void populaCliente() {
        DefaultComboBoxModel clienteComboBox = new DefaultComboBoxModel();
        view.getjComboBoxClientes().setModel(clienteComboBox);
        clienteComboBox.addElement(pedido.getCliente().getNome());

    }

    public void populaProduto() {
        DefaultComboBoxModel produtoComboBox = new DefaultComboBoxModel();
        view.getjComboBoxProdutos().setModel(produtoComboBox);
        produtoComboBox.addElement("");

    }

    public void populaEntregador() {
        DefaultComboBoxModel entregadorComboBox = new DefaultComboBoxModel();
        view.getjComboBoxEntregadores().setModel(entregadorComboBox);

        if (pedido.getEntregador() == null) {
            entregadorComboBox.addElement("Casa");
        } else {
            entregadorComboBox.addElement(pedido.getEntregador().getNome());
        }

    }

    public void populaTabelaPedido() {
        Object colunas[] = {"ID", "Produto", "Quantidade", "Valor", "Total"};
        DefaultTableModel tabela = new DefaultTableModel(colunas, 0);
        view.getjTableProdutos().setModel(tabela);

        if (pedido.getProdutos() != null) {
            for (PedidoProduto p : pedido.getProdutos()) {
                tabela.addRow(new Object[]{p.getProduto().getId(), p.getProduto().getNome(), p.getProduto().getPreco(), p.getQuantidade()});
            }
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
