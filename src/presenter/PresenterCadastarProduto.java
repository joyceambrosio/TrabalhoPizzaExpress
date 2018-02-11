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
        estadoInsumo();
        escutarBotaoInsumo();
        escutarBotaoBebida();
        escutarBotaoComida();
        popularInsumosCombo();
        incluirIngredientes();
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

    public void estadoInsumo() {
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

    public void estadoBebida() {
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

    public void estadoPizza() {
        setState("Pizza");
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
                estadoInsumo();
            }
        });
    }

    public void escutarBotaoComida() {
        view.getjRadioButtonComida().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estadoPizza();
            }
        });
    }

    public void escutarBotaoBebida() {
        view.getjRadioButtonBebida().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estadoBebida();
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
                if (getState().equals("Pizza")) {
                    cadastrarPizza();
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
                    PresenterMenu.getInstancia().populaMenuProdutos();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro");
                }
            } catch (SQLException e) {
            }
        }
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
                    PresenterMenu.getInstancia().populaMenuProdutos();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro");
                }
            } catch (NumberFormatException | SQLException e) {
            }

        }

    }

    public void cadastrarPizza() {
        String nome = view.getjTextFieldNomeProduto().getText();
        String precoString = view.getjFormattedTextFielPrecoVenda().getText().replace(',', '.');;
        String receita = view.getjTextAreaReceita().getText();
        double preco = 0;

        if (validarCamposComida(nome, precoString, receita)) {
            try {
                preco = Double.parseDouble(precoString);
                Produto comida = new Pizza(nome, preco, receita, ingredientesTemp);
                comida.imprimir();
                if (Produtos.getInstancia().add(comida)) {
                    JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
                    PresenterMenu.getInstancia().populaMenuProdutos();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro");
                }
            } catch (NumberFormatException | SQLException e) {
            }

        }
    }

    public void popularInsumosCombo() {
        DefaultComboBoxModel produtoComboBox = new DefaultComboBoxModel();
        view.getjComboBoxIngrediente().setModel(produtoComboBox);

        try {
            for (Produto p : Produtos.getInstancia().getLista()) {
                if (p.getCategoria().equals("Insumo")) {
                    produtoComboBox.addElement(p);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PresenterCadastarProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void popularTabelaIngredientes() {
        Object colunas[] = {"ID", "Ingrediente", "Unidade", "Quantidade"};
        DefaultTableModel tabela = new DefaultTableModel(colunas, 0);

        view.getjTableIngredientes().setModel(tabela);

        for (Insumo produto : ingredientesTemp) {
            tabela.addRow(new Object[]{produto.getId(), produto.getNome(), produto.getUnidade(), produto.getQuantidade()});
        }

    }

    public boolean ingredienteUnico(Produto produto) {
        for (Produto p : ingredientesTemp) {
            if (p.getId() == produto.getId()) {
                return false;
            }
        }
        return true;
    }

    public void incluirIngredientes() {
        view.getjButtonIncluir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Insumo produto = (Insumo) view.getjComboBoxIngrediente().getSelectedItem();
                String und = view.getjTextFieldUnidade().getText();
                String qtd = view.getjTextFieldQuantidade().getText();

                if (validarCamposIngredientes(und, qtd)) {
                    produto.setUnidade(und);
                    produto.setQuantidade(qtd);
                    if (produto != null) {
                        if (ingredienteUnico(produto)) {
                            ingredientesTemp.add(produto);
                            popularTabelaIngredientes();
                        } else {
                            JOptionPane.showMessageDialog(view, "O ingrediente já está na lista");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "Não foi possível adicionar o ingrediente");
                }
            }
        }
        );
    }

    public void removerIngerdientes() {
        view.getjButtonExcluir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linha = view.getjTableIngredientes().getSelectedRow();
                if (linha == -1) {
                    JOptionPane.showMessageDialog(view, "Você precisa selecionar um ingrediente na lista antes de remover");
                } else {

                    int id = Integer.parseInt(view.getjTableIngredientes().getValueAt(linha, 0).toString());

                    Produto p;
                    try {
                        p = Produtos.getInstancia().getInsumosbyID(id);
                        if (p != null) {
                            ingredientesTemp.remove(p);
                            popularTabelaIngredientes();

                        } else {
                            JOptionPane.showMessageDialog(view, "Não foi possível remover o ingrediente");

                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(PresenterCadastarProduto.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
    }

    public boolean validarCamposInsumo(String nome) {
        if (nome.equals("")) {
            view.getjLabelAvisosNomeVenda().setText("O campo nome não pode ser vazio");
            return false;
        }
        return true;
    }

    public boolean validarCamposIngredientes(String unidade, String quantidade) {
        view.getjLabelAvisosIngrediente().setText("");
        if (unidade.equals("") && quantidade.equals("")) {
            view.getjLabelAvisosIngrediente().setText(view.getjLabelAvisosIngrediente().getText() + "Os campos unidade e quantidade não podem ser vazios ");
            return false;
        }
        return true;
    }

    public boolean validarCamposBebida(String nome, String precoString) {
        view.getjLabelAvisosNomeVenda().setText("");

        if (nome.equals("") && precoString.equals("")) {
            view.getjLabelAvisosNomeVenda().setText(view.getjLabelAvisosNomeVenda().getText() + "Os campos nome e preço não podem ser vazios ");
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

    public boolean validarCamposComida(String nome, String preco, String receita) {
        view.getjLabelAvisosReceita().setText(" ");
        validarCamposBebida(nome, preco);

        if (ingredientesTemp.isEmpty()) {
            view.getjLabelAvisosIngrediente().setText(view.getjLabelAvisosIngrediente().getText() + "Você precisa adicionar ao menos um ingrediente à receita ");
        }

        if (receita.equals("")) {
            view.getjLabelAvisosReceita().setText("O campo receita não pode ser vazio");
            return false;
        }
        return true;
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
