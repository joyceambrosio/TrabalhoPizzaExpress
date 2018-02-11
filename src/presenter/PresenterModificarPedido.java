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
public class PresenterModificarPedido {

    private viewCadastrarPedido view;
    private static PresenterModificarPedido instancia;


    protected ArrayList<PedidoProduto> produtosPedidoTemp;
    private Pedido pedido;

    private Cliente clientePedido = null;
    private Funcionario funcionarioPedido = null;

    public PresenterModificarPedido(Pedido p) throws SQLException { 
        this.pedido = p;
        view = new viewCadastrarPedido();

        this.view.addWindowListener(new WindowAdapter() {
            @SuppressWarnings("override")
            public void windowClosing(WindowEvent evt) {
                instancia = null;
                view.dispose();
            }
        });

        configurarExibicao();

        URL caminhoImagem = this.getClass().getClassLoader().getResource("icones/PizzaIcone.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        view.setIconImage(iconeTitulo);
        view.setTitle("Modificar Pedido");
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    public static PresenterModificarPedido getInstancia(Pedido p) throws SQLException {
        if (instancia == null) {
            return instancia = new PresenterModificarPedido(p);
        }
        return instancia;
    }

    private void configurarExibicao() throws SQLException {

        view.getjButtonCadastrar().setText("Salvar");
        view.getjComboBoxClientes().setEnabled(false);
        produtosPedidoTemp = pedido.getProdutos();

        populaCliente();
        populaTabelaPedido();
        populaEntregador();
        populaProduto();
        removerProdutoPedido();
        adicionaProdutoPedido();
        modificaPedido();

    }

    public void populaCliente() throws SQLException {
        DefaultComboBoxModel clienteComboBox = new DefaultComboBoxModel();
        view.getjComboBoxClientes().setModel(clienteComboBox);
        clienteComboBox.addElement(pedido.getCliente());

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
        if (pedido.getEntregador() == null) {
            entregadorComboBox.addElement("Casa");
        } else {
            entregadorComboBox.addElement(pedido.getEntregador());
        }
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

        for (PedidoProduto p : produtosPedidoTemp) {
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
                int qtd = (Integer) view.getjSpinnerQuantidade().getValue();

                if (validaCampos()) {
                    PedidoProduto nPedidoProduto = new PedidoProduto(produto, qtd);
                    
                    if(produtoUnico(nPedidoProduto)) {
                        produtosPedidoTemp.add(nPedidoProduto);
                        populaTabelaPedido();
                    } else {
                        view.getjLabelAvisosProduto().setText("Produto já adicionado. Remova e adicione a nova quantidade");
                    }

                } else {
                    view.getjLabelAvisosProduto().setText("Escolha uma quantidade para adicionar o produto");
                }
            }
        }
        );
    }
    
     public boolean produtoUnico(PedidoProduto nPedidoProduto) {
        for (PedidoProduto p : produtosPedidoTemp) {
            if (p.getProduto().getId() == nPedidoProduto.getProduto().getId()) {
                return false;
            }
        }
        return true;
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

                    ArrayList<PedidoProduto> pedidoProdutosIteracao = new ArrayList<>();

                    pedidoProdutosIteracao.addAll(produtosPedidoTemp);

                    for (PedidoProduto s : pedidoProdutosIteracao) {
                        if (s.getProduto().getId() == pedidoProduto.getProduto().getId()) {
                            produtosPedidoTemp.remove(s);
                        }
                    }
                    populaTabelaPedido();

                }
            }
        }
        );
    }

    public void modificaPedido() {
        view.getjButtonCadastrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (validaCampos()) {

                    pedido.setEntregador(funcionarioPedido);
                    pedido.setProdutos(produtosPedidoTemp);

                    try {
                        Pedidos.getInstancia().updatePedido(pedido);
                    } catch (SQLException ex) {
                        Logger.getLogger(PresenterCadastrarPedido.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    JOptionPane.showMessageDialog(view, "Pedido modificado com sucesso");
                    instancia = null;
                    view.dispose();
                }

            }
        });

    }

    public boolean validaCampos() {

        String controleFuncionario = view.getjComboBoxEntregadores().getSelectedItem().toString();

        if (controleFuncionario.equals("Casa")) {
            funcionarioPedido = null;
        } else {
            Funcionario f = (Funcionario) view.getjComboBoxEntregadores().getSelectedItem();
            funcionarioPedido = f;
        } 

        if (produtosPedidoTemp.isEmpty()) {
            view.getjLabelAvisosProduto().setText("Adicione um produto ao pedido");
            return false;
        } else {
            return true;
        }

    }

}
