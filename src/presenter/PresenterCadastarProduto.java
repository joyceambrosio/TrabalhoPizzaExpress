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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import model.Bebida;
import model.Insumo;
import model.Pizza;
import model.Produto;
import view.ViewCadastrarProduto;

/**
 *
 * @author joyce
 */
public class PresenterCadastarProduto {

    private ArrayList<Insumo> ingredientesTemp;
    private static PresenterCadastarProduto instancia;

    private String state;

    private ViewCadastrarProduto view;

    private PresenterCadastarProduto() {
        this.view = new ViewCadastrarProduto();
        ingredientesTemp = new ArrayList<>();
        EstadoInsumo();
        escutarBotaoInsumo();
        escutarBotaoBebida();
        escutarBotaoComida();
        popularInsumosCombo();
        //incluIngredientes();
        removerIngerdientes();
        fechar();
        cadastrar();

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void EstadoInsumo() {
        setState("Insumo");
        view.getjRadioButtonInsumo().setSelected(true);
        view.getjRadioButtonBebida().setSelected(false);
        view.getjRadioButtonComida().setSelected(false);

        view.getjFormattedTextFielPrecoVenda().setEnabled(false);
        view.getjTextFieldUnidade().setEnabled(false);
        view.getjTextFieldQuantidade().setEnabled(false);

        view.getjButtonIncluir().setEnabled(false);
        view.getjButtonExcluir().setEnabled(false);

        view.getjComboBoxIngrediente().setEnabled(false);
        view.getjTableIngredientes().setEnabled(false);
        view.getjTextAreaReceita().setEnabled(false);
    }

    public void EstadoBebida() {
        setState("Bebida");
        view.getjRadioButtonInsumo().setSelected(false);
        view.getjRadioButtonBebida().setSelected(true);
        view.getjRadioButtonComida().setSelected(false);

        view.getjFormattedTextFielPrecoVenda().setEnabled(true);
        view.getjTextFieldUnidade().setEnabled(false);
        view.getjTextFieldQuantidade().setEnabled(false);

        view.getjButtonIncluir().setEnabled(false);
        view.getjButtonExcluir().setEnabled(false);

        view.getjComboBoxIngrediente().setEnabled(false);
        view.getjTableIngredientes().setEnabled(false);
        view.getjTextAreaReceita().setEnabled(false);
    }

    public void EstadoComida() {
        setState("Comida");
        view.getjRadioButtonInsumo().setSelected(false);
        view.getjRadioButtonBebida().setSelected(false);
        view.getjRadioButtonComida().setSelected(true);

        view.getjFormattedTextFielPrecoVenda().setEnabled(true);
        view.getjTextFieldUnidade().setEnabled(true);
        view.getjTextFieldQuantidade().setEnabled(true);

        view.getjButtonIncluir().setEnabled(true);
        view.getjButtonExcluir().setEnabled(true);

        view.getjComboBoxIngrediente().setEnabled(true);
        view.getjTableIngredientes().setEnabled(true);
        view.getjTextAreaReceita().setEnabled(true);
    }

    public void escutarBotaoInsumo() {
        view.getjRadioButtonInsumo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EstadoInsumo();
            }
        });
    }

    public void escutarBotaoComida() {
        view.getjRadioButtonComida().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EstadoComida();
            }
        });
    }

    public void escutarBotaoBebida() {
        view.getjRadioButtonBebida().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EstadoBebida();
            }
        });
    }

    public void cadastrar() {
        view.getjButtonCadastrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getState().equals("Insumo")) {
                    cadastrarInsumo();
                }
                if (getState().equals("Bebida")) {
                    cadastrarBebida();
                }
                if (getState().equals("Comida")) {
                    cadastrarComida();
                }

            }
        });

    }

    public void cadastrarInsumo() {
        view.getjLabelAvisosNomeVenda().setText("");

        String nome = view.getjTextFieldNomeProduto().getText();

        if (validarCamposInsumo(nome)) {
            try {
                Produto insumo = new Insumo(nome, 0.0);
                if (Produtos.getInstancia().add(insumo)) {
                    JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
                } else {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro");
                }
            } catch (SQLException e) {
            }
        }
    }

    public boolean validarCamposInsumo(String nome) {
        if (nome.equals("")) {
            view.getjLabelAvisosNomeVenda().setText("O nome não pode ser vazio");
            return false;
        }
        return true;
    }

    public void cadastrarBebida() {

        String nome = view.getjTextFieldNomeProduto().getText();
        String precoString = view.getjFormattedTextFielPrecoVenda().getText().replace(',', '.');;
        double preco = 0;

        if (validarCamposBebida(nome, precoString)) {
            try {
                preco = Double.parseDouble(precoString);
                Produto bebida = new Bebida(nome, preco);

                if (Produtos.getInstancia().add(bebida)) {
                    JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
                } else {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro");
                }
            } catch (NumberFormatException | SQLException e) {
            }

        }

    }

    public boolean validarCamposBebida(String nome, String precoString) {
        view.getjLabelAvisosNomeVenda().setText("");

        if (nome.equals("") && precoString.equals("")) {
            view.getjLabelAvisosNomeVenda().setText(view.getjLabelAvisosNomeVenda().getText() + "Os campos não podem ser vazios ");
            return false;
        } else {
            double preco = Double.parseDouble(precoString);
            if (preco <= 0) {
                view.getjLabelAvisosNomeVenda().setText(view.getjLabelAvisosNomeVenda().getText() + "O valor não pode ser inferior a 0");
                return false;
            }
        }
        return true;
    }

    public void cadastrarComida() {
        try {
            Produtos.getInstancia().imprimeLista();
        } catch (SQLException e) {
        }

    }

//    public void cadastrarComida() {
//    }
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
//                        try {
//                            Produtos.getInstancia().add(new Insumo(nome));
//                        } catch (SQLException ex) {
//                            Logger.getLogger(PresenterCadastarProduto.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                        JOptionPane.showMessageDialog(view, "Ingrediente adicionado com sucesso");
//                        view.dispose();
//
//                    }
//
//                    if (view.getjRadioButtonBebida().isSelected()) {
////                        try {
////                            //Produtos.getInstancia().add(new Bebida(nome, preco));
////                        } catch (SQLException ex) {
////                            Logger.getLogger(PresenterCadastarProduto.class.getName()).log(Level.SEVERE, null, ex);
////                        }
//                        JOptionPane.showMessageDialog(view, "Bebida adicionada com sucesso");
//                        instancia = null;
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
//                            try {
//                                Produtos.getInstancia().add(new Pizza(ingredientesTemp, receita, nome, preco));
//                                JOptionPane.showMessageDialog(view, "Pizza adicionada com sucesso");
//                                instancia = null;
//                                view.dispose();
//                                Produtos.getInstancia().imprimeLista();
//                            } catch (SQLException ex) {
//                                Logger.getLogger(PresenterCadastarProduto.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//
//                        }
//                    }
//                }
//            }
//        });
//
//    }
    public void popularInsumosCombo() {
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

    public void popularTabelaIngredientes() {
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
//                    popularTabelaIngredientes();
//
//                } else {
//                    JOptionPane.showMessageDialog(view, "Não foi possível adicionar o ingrediente");
//                }
//
//            }
//        });
//    }

    public void removerIngerdientes() {
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
                        popularTabelaIngredientes();

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
