///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package presenter;
//
//import colections.Produtos;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import javax.swing.DefaultComboBoxModel;
//import javax.swing.JOptionPane;
//import javax.swing.table.DefaultTableModel;
//import model.Bebida;
//import model.Insumo;
//import model.Pizza;
//import model.Produto;
//import view.CadastrarProdutoView;
//
///**
// *
// * @author joyce
// */
//public class PresenterCadastarProduto {
//
//    private ArrayList<Insumo> ingredientesTemp;
//
//    private CadastrarProdutoView view;
//
//    public PresenterCadastarProduto() {
//        this.view = new CadastrarProdutoView();
//        ingredientesTemp = new ArrayList<>();
//        EstadoInsumo();
//        EscutaBotaoInsumo();
//        EscutaBotaoBebida();
//        EscutaBotaoComida();
//        populaInsumosCombo();
//        incluIngredientes();
//        removeIngredientes();
//        fechar();
//        Cadastar();
//
//        view.setLocationRelativeTo(null);
//        view.setVisible(true);
//    }
//
//    public void EstadoInsumo() {
//        view.getjRadioButtonInsumo().setSelected(true);
//        view.getjRadioButtonBebida().setSelected(false);
//        view.getjRadioButtonComida().setSelected(false);
//
//        view.getjTextFieldPrecoVenda().setEnabled(false);
//        
//        view.getjButtonIncluir().setEnabled(false);
//        view.getjButtonExcluir().setEnabled(false);
//
//        view.getjComboBoxIngrediente().setEnabled(false);
//        view.getjTableIngredientes().setEnabled(false);
//        view.getjTextAreaReceita().setEnabled(false);
//    }
//
//    public void EstadoBebida() {
//        view.getjRadioButtonInsumo().setSelected(false);
//        view.getjRadioButtonBebida().setSelected(true);
//        view.getjRadioButtonComida().setSelected(false);
//
//        view.getjButtonIncluir().setEnabled(false);
//        view.getjButtonExcluir().setEnabled(false);
//
//        view.getjComboBoxIngrediente().setEnabled(false);
//        view.getjTableIngredientes().setEnabled(false);
//        view.getjTextAreaReceita().setEnabled(false);
//    }
//
//    public void EstadoComida() {
//        view.getjRadioButtonInsumo().setSelected(false);
//        view.getjRadioButtonBebida().setSelected(false);
//        view.getjRadioButtonComida().setSelected(true);
//
//        view.getjButtonIncluir().setEnabled(true);
//        view.getjButtonExcluir().setEnabled(true);
//
//        view.getjComboBoxIngrediente().setEnabled(true);
//        view.getjTableIngredientes().setEnabled(true);
//        view.getjTextAreaReceita().setEnabled(true);
//    }
//
//    public void EscutaBotaoInsumo() {
//        view.getjRadioButtonInsumo().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                EstadoInsumo();
//            }
//        });
//    }
//
//    public void Cadastar() {
//        view.getjButtonCadastrar().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                String nome = view.getjTextFieldNomeProduto().getText();
//                double preco = Double.parseDouble(view.getjTextFieldPrecoVenda().getText());
//
//                if (nome.equals("") || preco <= 0.0) {
//                    if (nome.equals("")) {
//                        JOptionPane.showMessageDialog(view, "O nome não pode ser vazio");
//                    }
//                    if (preco <= 0.0) {
//                        JOptionPane.showMessageDialog(view, "Preencha um valor válido para preço de venda do produto");
//                    }
//                } else {
//
//                    if (view.getjRadioButtonInsumo().isSelected()) {
//                        Produtos.getInstancia().add(new Insumo(nome, preco));
//                        JOptionPane.showMessageDialog(view, "Ingrediente adicionado com sucesso");
//                        view.dispose();
//
//                    }
//
//                    if (view.getjRadioButtonBebida().isSelected()) {
//                        Produtos.getInstancia().add(new Bebida(nome, preco));
//                        JOptionPane.showMessageDialog(view, "Bebida adicionada com sucesso");
//                        view.dispose();
//
//                    }
//
//                    if (view.getjRadioButtonComida().isSelected()) {
//                        String receita = view.getjTextAreaReceita().getText();
//
//                        if (receita.equals("") || ingredientesTemp.size() <= 0) {
//                            if (receita.equals("")) {
//                                JOptionPane.showMessageDialog(view, "Preencha o campo receita");
//                            }
//                            if (ingredientesTemp.size() <= 0) {
//                                JOptionPane.showMessageDialog(view, "Selecione os ingredientes da receita");
//                            }
//                        } else {
//
//                            Produtos.getInstancia().add(new Pizza(ingredientesTemp, receita, nome, preco));
//                            JOptionPane.showMessageDialog(view, "Pizza adicionada com sucesso");
//                            view.dispose();
//                            Produtos.getInstancia().imprimeLista();
//                        }
//                    }
//                }
//            }
//        });
//
//    }
//
//    public void EscutaBotaoBebida() {
//        view.getjRadioButtonBebida().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                EstadoBebida();
//            }
//        });
//    }
//
//    public void EscutaBotaoComida() {
//        view.getjRadioButtonComida().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                EstadoComida();
//            }
//        });
//    }
//
//    public void populaInsumosCombo() {
//        DefaultComboBoxModel produtoComboBox = new DefaultComboBoxModel();
//        view.getjComboBoxIngrediente().setModel(produtoComboBox);
//
//        for (Produto p : Produtos.getInstancia().getLista()) {
//            if (p.getCategoria().equals("Insumo")) {
//                produtoComboBox.addElement(p.getNome());
//            }
//        }
//    }
//
//    public void populaTabelaIngredientes() {
//        Object colunas[] = {"Ingrediente"};
//        DefaultTableModel tabela = new DefaultTableModel(colunas, 0);
//
//        view.getjTableIngredientes().setModel(tabela);
//
//        for (Produto p : ingredientesTemp) {
//            String nome = p.getNome();
//            tabela.addRow(new Object[]{nome});
//        }
//
//    }
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
//
//    public void removeIngredientes() {
//        view.getjButtonExcluir().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int linha = view.getjTableIngredientes().getSelectedRow();
//                if (linha == -1) {
//                    JOptionPane.showMessageDialog(view, "Você precisa selecionar um ingrediente na lista antes de remover");
//                } else {
//                    String produto = view.getjTableIngredientes().getValueAt(linha, 0).toString();
//
//                    Produto p = Produtos.getInstancia().getProdutoByNome(produto);
//                    if (p != null) {
//                        ingredientesTemp.remove(p);
//                        populaTabelaIngredientes();
//
//                    } else {
//                        JOptionPane.showMessageDialog(view, "Não foi possível remover o ingrediente");
//                    }
//                }
//            }
//        });
//    }
//
//    public void fechar() {
//        view.getjButtonFechar().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                view.dispose();
//            }
//        });
//    }
//
//}
