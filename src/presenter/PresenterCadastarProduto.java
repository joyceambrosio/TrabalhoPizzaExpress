/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

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
import model.Bebida;
import model.Insumo;
import model.Pizza;
import model.Produto;
import view.CadastrarProduto;

/**
 *
 * @author joyce
 */
public class PresenterCadastarProduto {

    private ArrayList<Insumo> ingredientesTemp;
    private static PresenterCadastarProduto instancia;

    private CadastrarProduto view;

    private PresenterCadastarProduto() {
        this.view = new CadastrarProduto();
        ingredientesTemp = new ArrayList<>();
        EstadoInsumo();
        EscutaBotaoInsumo();
        EscutaBotaoBebida();
        EscutaBotaoComida();
        populaInsumosCombo();
        //incluIngredientes();
        removeIngredientes();
        fechar();
        Cadastar();

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
        view.setTitle("Cadastrar Produto");

        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    public static PresenterCadastarProduto getInstancia() {
        if (instancia == null) {
            return instancia = new PresenterCadastarProduto();
        }
        return instancia;
    }

    public void EstadoInsumo() {
        view.getjRadioButtonInsumo().setSelected(true);
        view.getjRadioButtonBebida().setSelected(false);
        view.getjRadioButtonComida().setSelected(false);

        view.getjTextFieldPrecoVenda().setEnabled(false);

        view.getjButtonIncluir().setEnabled(false);
        view.getjButtonExcluir().setEnabled(false);

        view.getjComboBoxIngrediente().setEnabled(false);
        view.getjTableIngredientes().setEnabled(false);
        view.getjTextAreaReceita().setEnabled(false);
    }

    public void EstadoBebida() {
        view.getjRadioButtonInsumo().setSelected(false);
        view.getjRadioButtonBebida().setSelected(true);
        view.getjRadioButtonComida().setSelected(false);

        view.getjButtonIncluir().setEnabled(false);
        view.getjButtonExcluir().setEnabled(false);

        view.getjComboBoxIngrediente().setEnabled(false);
        view.getjTableIngredientes().setEnabled(false);
        view.getjTextAreaReceita().setEnabled(false);
    }

    public void EstadoComida() {
        view.getjRadioButtonInsumo().setSelected(false);
        view.getjRadioButtonBebida().setSelected(false);
        view.getjRadioButtonComida().setSelected(true);

        view.getjButtonIncluir().setEnabled(true);
        view.getjButtonExcluir().setEnabled(true);

        view.getjComboBoxIngrediente().setEnabled(true);
        view.getjTableIngredientes().setEnabled(true);
        view.getjTextAreaReceita().setEnabled(true);
    }

    public void EscutaBotaoInsumo() {
        view.getjRadioButtonInsumo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EstadoInsumo();
            }
        });
    }

    public void Cadastar() {
        view.getjButtonCadastrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nome = view.getjTextFieldNomeProduto().getText();
                double preco = Double.parseDouble(view.getjTextFieldPrecoVenda().getText());

                if (nome.equals("") || preco <= 0.0) {
                    if (nome.equals("")) {
                        JOptionPane.showMessageDialog(view, "O nome não pode ser vazio");
                    }
                    if (preco <= 0.0) {
                        JOptionPane.showMessageDialog(view, "Preencha um valor válido para preço de venda do produto");
                    }
                } else {

                    if (view.getjRadioButtonInsumo().isSelected()) {
                        try {
                            Produtos.getInstancia().add(new Insumo(nome, preco));
                        } catch (SQLException ex) {
                            Logger.getLogger(PresenterCadastarProduto.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        JOptionPane.showMessageDialog(view, "Ingrediente adicionado com sucesso");
                        view.dispose();

                    }

                    if (view.getjRadioButtonBebida().isSelected()) {
//                        try {
//                            //Produtos.getInstancia().add(new Bebida(nome, preco));
//                        } catch (SQLException ex) {
//                            Logger.getLogger(PresenterCadastarProduto.class.getName()).log(Level.SEVERE, null, ex);
//                        }
                        JOptionPane.showMessageDialog(view, "Bebida adicionada com sucesso");
                        instancia = null;
                        view.dispose();

                    }

                    if (view.getjRadioButtonComida().isSelected()) {
                        String receita = view.getjTextAreaReceita().getText();

                        if (receita.equals("") || ingredientesTemp.size() <= 0) {
                            if (receita.equals("")) {
                                JOptionPane.showMessageDialog(view, "Preencha o campo receita");
                            }
                            if (ingredientesTemp.size() <= 0) {
                                JOptionPane.showMessageDialog(view, "Selecione os ingredientes da receita");
                            }
                        } else {

                            try {
                                Produtos.getInstancia().add(new Pizza(ingredientesTemp, receita, nome, preco));
                                JOptionPane.showMessageDialog(view, "Pizza adicionada com sucesso");
                                instancia = null;
                                view.dispose();
                                Produtos.getInstancia().imprimeLista();
                            } catch (SQLException ex) {
                                Logger.getLogger(PresenterCadastarProduto.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    }
                }
            }
        });

    }

    public void EscutaBotaoBebida() {
        view.getjRadioButtonBebida().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EstadoBebida();
            }
        });
    }

    public void EscutaBotaoComida() {
        view.getjRadioButtonComida().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EstadoComida();
            }
        });
    }

    public void populaInsumosCombo() {
        DefaultComboBoxModel produtoComboBox = new DefaultComboBoxModel();
        view.getjComboBoxIngrediente().setModel(produtoComboBox);

        try {
            for (Produto p : Produtos.getInstancia().getLista()) {
                if (p.getCategoria().equals("Insumo")) {
                    produtoComboBox.addElement(p.getNome());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PresenterCadastarProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void populaTabelaIngredientes() {
        Object colunas[] = {"Ingrediente"};
        DefaultTableModel tabela = new DefaultTableModel(colunas, 0);

        view.getjTableIngredientes().setModel(tabela);

        for (Produto p : ingredientesTemp) {
            String nome = p.getNome();
            tabela.addRow(new Object[]{nome});
        }

    }
//
//    public void incluIngredientes() {
//        view.getjButtonIncluir().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String produto = view.getjComboBoxIngrediente().getSelectedItem().toString();
//                Produto p = Produtos.getInstancia().getProdutoByNome(produto);
//
//                if (p != null) {
//                    ingredientesTemp.add(p);
//                    populaTabelaIngredientes();
//
//                } else {
//                    JOptionPane.showMessageDialog(view, "Não foi possível adicionar o ingrediente");
//                }
//
//            }
//        });
//    }

    public void removeIngredientes() {
        view.getjButtonExcluir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linha = view.getjTableIngredientes().getSelectedRow();
                if (linha == -1) {
                    JOptionPane.showMessageDialog(view, "Você precisa selecionar um ingrediente na lista antes de remover");
                } else {
                    String produto = view.getjTableIngredientes().getValueAt(linha, 0).toString();

                    Produto p = null;
                    try {
                        p = Produtos.getInstancia().getProdutoByNome(produto);
                    } catch (SQLException ex) {
                        Logger.getLogger(PresenterCadastarProduto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (p != null) {
                        ingredientesTemp.remove(p);
                        populaTabelaIngredientes();

                    } else {
                        JOptionPane.showMessageDialog(view, "Não foi possível remover o ingrediente");
                    }
                }
            }
        });
    }

    public void fechar() {
        view.getjButtonFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instancia = null;
                view.dispose();
            }
        });
    }

}
