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
import javax.swing.table.DefaultTableModel;
import model.Produto;
import view.Menu;
import presenter.*;

/**
 *
 * @author Natalia
 */
public class PresenterProduto {
    
    private Menu view;

    public PresenterProduto(Menu view) {
        this.view = view;
        
        try {
            populaMenuProdutos();
        } catch (SQLException ex) {
            Logger.getLogger(PresenterProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
         novoProduto();

    }
    
    
  
    
    
    // Coisas de Produto
    public void populaMenuProdutos() throws SQLException, SQLException {
        Object colunas[] = {"ID", "Nome", "Categoria", "Preço"};
        DefaultTableModel tabela = new DefaultTableModel(colunas, 0);

        view.getjTableProduto().setModel(tabela);

        if (Produtos.getInstancia().getProdutos() == null) {
            System.out.println("produtos null");
        }

        for (Produto p : Produtos.getInstancia().getProdutos()) {
            int id = p.getId();
            String nome = p.getNome();
            String categoria = p.getCategoria();
            double preco = p.getPreco();
            tabela.addRow(new Object[]{id, nome, categoria, preco});
        }

    }

    public void novoProduto() {
        view.getjButtonNovoProduto().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PresenterCadastarProduto presenterCadastroProduto = new PresenterCadastarProduto();
            }
        });
    }

    
}
