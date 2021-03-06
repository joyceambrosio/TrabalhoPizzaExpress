/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import colections.Produtos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Produto;
import view.ViewMenu;

/**
 *
 * @author Natalia
 */
public class PresenterProduto {

    private ViewMenu view;

    public PresenterProduto(ViewMenu view) {
        this.view = view;
        try {
            modificarProduto();
            populaMenuProdutos();
            excluirProduto();
        } catch (SQLException e) {
        }

        adicionarProduto();

        pesquisaProduto();

    }

    // Coisas de Produto
    public void populaMenuProdutos() throws SQLException {
        Object colunas[] = {"ID", "Nome", "Categoria", "Preço"};
        DefaultTableModel tabela = new DefaultTableModel(colunas, 0);

        view.getjTableProduto().setModel(tabela);

        if (Produtos.getInstancia().getProdutos() == null) {
            System.out.println("produtos null");
        }

        for (Produto p : Produtos.getInstancia().getProdutos()) {
            if (!p.getCategoria().equals("Insumo")) {
                int id = p.getId();
                String nome = p.getNome();
                String categoria = p.getCategoria();
                double preco = p.getPreco();
                tabela.addRow(new Object[]{id, nome, categoria, preco});
            }
        }
    }

    public void adicionarProduto() {
        view.getjButtonNovoProduto().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PresenterCadastarProduto presenterCadastroProduto = PresenterCadastarProduto.getInstancia();
            }
        });
    }

    public void modificarProduto() {
        view.getjButtonModificarProduto().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linha = view.getjTableProduto().getSelectedRow();
                if (linha == -1) {
                    JOptionPane.showMessageDialog(view, "Selecione um produto para realizar as modificações");
                } else if (linha >= 0) {

                    try {
                        int idProduto = Integer.parseInt(view.getjTableProduto().getValueAt(linha, 0).toString());
                        Produto p = Produtos.getInstancia().getProdutosbyID(idProduto);

                        if (p != null) {
                            PresenterModificarProduto presenterModificaProduto = PresenterModificarProduto.getInstancia(p);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PresenterProduto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    public void excluirProduto() {
        view.getjButtonExcluirProduto().addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                int linha = view.getjTableProduto().getSelectedRow();

                if (linha == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione um produto na lista para remove-lo");
                } else if (linha >= 0) {
                    int id = Integer.parseInt(view.getjTableProduto().getValueAt(linha, 0).toString());
                    try {
                        int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que quer remover esse produto?");
                        if (confirmacao == 0) {

                            Produto p = Produtos.getInstancia().getProdutosbyID(id);
                            System.out.println(p.getId());
                            Produtos.getInstancia().remove(p);
                            JOptionPane.showMessageDialog(null, "O produto foi removido com sucesso");
                            PresenterMenu.getInstancia().populaMenuProdutos();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PresenterMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        });
    }

    public void pesquisaProduto() {
        view.getjButtonPesquisarProduto().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTextFieldBuscarProduto().getText().equals("")) {
                    try {
                        populaMenuProdutos();
                    } catch (SQLException ex) {
                        Logger.getLogger(PresenterProduto.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {

                    ArrayList<Produto> pesquisa = null;
                    try {
                        pesquisa = Produtos.getInstancia().pesquisaProduto(view.getjTextFieldBuscarProduto().getText());
                        Object colunas[] = {"ID", "Nome", "Categoria", "Preço"};
                        DefaultTableModel tabela = new DefaultTableModel(colunas, 0);

                        view.getjTableProduto().setModel(tabela);

                        for (Produto p : pesquisa) {
                            if (!p.getCategoria().equals("Insumo")) {
                                int id = p.getId();
                                String nome = p.getNome();
                                String categoria = p.getCategoria();
                                double preco = p.getPreco();
                                tabela.addRow(new Object[]{id, nome, categoria, preco});
                            }
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(view, "Não foi possível realizar a pesquisa");
                    }

                }
            }
        });
    }

}
