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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Produto;
import view.ViewMenu;
import presenter.*;

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
        } catch (SQLException e) {
        }

        adicionarProduto();

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
}
