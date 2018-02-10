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
 * @author Natalia
 */
public class PresenterModificarProduto {

    private ViewCadastrarProduto view;
    private static PresenterModificarProduto instancia;
    private Produto produto;
    private ArrayList<Insumo> ingredientesTemp;
    String state;

    private PresenterModificarProduto(Produto produto) {

        this.produto = produto;

        this.view = new ViewCadastrarProduto();

        configurarExibicao();
        modificar();

        URL caminhoImagem = this.getClass().getClassLoader().getResource("icones/PizzaIcone.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        view.setIconImage(iconeTitulo);
        view.setTitle("Modificar Produto");

        this.view.addWindowListener(new WindowAdapter() {
            @SuppressWarnings("override")
            public void windowClosing(WindowEvent evt) {
                instancia = null;
                view.dispose();
            }
        });
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    public static PresenterModificarProduto getInstancia(Produto p) {
        if (instancia == null) {
            return instancia = new PresenterModificarProduto(p);
        }
        return instancia;
    }

    public void modificar() {
        view.getjButtonCadastrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getState().equals("Insumo")) {
                    modificarInsumo((Insumo) produto);
                }
                if (getState().equals("Bebida")) {
                    modificarBebida((Bebida) produto);
                }
                if (getState().equals("Pizza")) {
                    modificarPizza((Pizza) produto);
                }

            }
        });
    }

    private void modificarInsumo(Insumo produto) {
        view.getjLabelAvisosNomeVenda().setText("");

        String nome = view.getjTextFieldNomeProduto().getText();

        if (validarCamposInsumo(nome)) {
            try {
                produto.setNome(view.getjTextFieldNomeProduto().getText());
                String precoString = view.getjFormattedTextFielPrecoVenda().getText().replace(',', '.');;
                produto.setPreco(Double.parseDouble(precoString));

                if (Produtos.getInstancia().update(produto)) {
                    JOptionPane.showMessageDialog(null, "Ingrediente modificado com sucesso");
                } else {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro");
                }
            } catch (SQLException e) {
            }
        }
    }

    private void modificarBebida(Bebida produto) {
        String nome = view.getjTextFieldNomeProduto().getText();
        String precoString = view.getjFormattedTextFielPrecoVenda().getText().replace(',', '.');;

        if (validarCamposBebida(nome, precoString)) {
            try {
                produto.setNome(view.getjTextFieldNomeProduto().getText());
                produto.setPreco(Double.parseDouble(precoString));

                if (Produtos.getInstancia().update(produto)) {
                    JOptionPane.showMessageDialog(null, "Bebida modificada com sucesso");
                } else {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro");
                }
            } catch (NumberFormatException | SQLException e) {
            }

        }
    }

    private void modificarPizza(Pizza produto) {
        String nome = view.getjTextFieldNomeProduto().getText();
        String precoString = view.getjFormattedTextFielPrecoVenda().getText().replace(',', '.');
        String receita = view.getjTextAreaReceita().getText();
        Pizza novaPizza;

        System.out.println("entrou na presenter");
        if (validarCamposComida(nome, precoString, receita)) {
            try {
                System.out.println("Entrou no try");

                novaPizza = new Pizza(nome, Double.parseDouble(precoString), receita, ingredientesTemp);

                novaPizza.setId(produto.getId());

                if (Produtos.getInstancia().update(novaPizza)) {
                    JOptionPane.showMessageDialog(null, "Pizza modificada com sucesso");
                } else {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro");
                }
            } catch (NumberFormatException | SQLException e) {
            }

            System.out.println("Passou o try");

        }
    }

    private void configurarExibicao() {

        if (produto.getCategoria().equals("Insumo")) {
            System.out.println(produto.getCategoria());
            estadoInsumo();
            configurarInsumo();
        }
        if (produto.getCategoria().equals("Bebida")) {
            System.out.println(produto.getCategoria());
            estadoBebida();
            configurarBebida();
        }
        if (produto.getCategoria().equals("Pizza")) {
            System.out.println(produto.getCategoria());
            estadoPizza();
            configurarPizza();
        } else {
            instancia = null;
            view.dispose();
        }
    }

    private void configurarInsumo() {
        estadoInsumo();
        view.getjTextFieldNomeProduto().setText(produto.getNome());
    }

    private void configurarBebida() {
        estadoBebida();
        view.getjTextFieldNomeProduto().setText(produto.getNome());
        String precoString = Double.toString(produto.getPreco());
        view.getjFormattedTextFielPrecoVenda().setText(precoString);

    }

    private void configurarPizza() {
        estadoPizza();
        Pizza p = (Pizza) produto;
        ingredientesTemp = p.getIngredientes();

        for (Insumo i : ingredientesTemp) {
            System.out.println("Insumo: " + i.getId() + i.getUnidade() + i.getQuantidade());
        }

        popularInsumosCombo();
        popularTabelaIngredientes();
        view.getjTextFieldNomeProduto().setText(p.getNome());
        String precoString = Double.toString(p.getPreco());
        view.getjFormattedTextFielPrecoVenda().setText(precoString);
        view.getjTextAreaReceita().setText(p.getReceita());
        incluirIngredientes();
        removerIngerdientes();

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

        view.getjRadioButtonInsumo().setEnabled(true);
        view.getjRadioButtonBebida().setEnabled(false);
        view.getjRadioButtonComida().setEnabled(false);

        view.getjTextFieldNomeProduto().setEnabled(true);
        view.getjTextFieldNomeProduto().setEditable(true);
        view.getjFormattedTextFielPrecoVenda().setEnabled(false);
        view.getjFormattedTextFielPrecoVenda().setEditable(false);
        view.getjTextFieldUnidade().setEnabled(false);
        view.getjTextFieldUnidade().setEditable(false);
        view.getjTextFieldQuantidade().setEnabled(false);
        view.getjTextFieldQuantidade().setEditable(false);

        view.getjButtonIncluir().setEnabled(false);
        view.getjButtonExcluir().setEnabled(false);

        view.getjComboBoxIngrediente().setEnabled(false);
        view.getjTableIngredientes().setEnabled(false);
        view.getjTextAreaReceita().setEnabled(false);
        view.getjButtonCadastrar().setText("Salvar");
    }

    public void estadoBebida() {
        setState("Bebida");
        view.getjRadioButtonInsumo().setSelected(false);
        view.getjRadioButtonBebida().setSelected(true);
        view.getjRadioButtonComida().setSelected(false);

        view.getjRadioButtonInsumo().setEnabled(false);
        view.getjRadioButtonBebida().setEnabled(true);
        view.getjRadioButtonComida().setEnabled(false);

        view.getjTextFieldNomeProduto().setEnabled(true);
        view.getjTextFieldNomeProduto().setEditable(true);
        view.getjFormattedTextFielPrecoVenda().setEnabled(true);
        view.getjFormattedTextFielPrecoVenda().setEditable(true);
        view.getjTextFieldUnidade().setEnabled(false);
        view.getjTextFieldUnidade().setEditable(false);
        view.getjTextFieldQuantidade().setEnabled(false);
        view.getjTextFieldQuantidade().setEditable(false);

        view.getjButtonIncluir().setEnabled(false);
        view.getjButtonExcluir().setEnabled(false);

        view.getjComboBoxIngrediente().setEnabled(false);
        view.getjTableIngredientes().setEnabled(false);
        view.getjTextAreaReceita().setEnabled(false);
        view.getjButtonCadastrar().setText("Salvar");
    }

    public void estadoPizza() {
        setState("Pizza");
        view.getjRadioButtonInsumo().setSelected(false);
        view.getjRadioButtonBebida().setSelected(false);
        view.getjRadioButtonComida().setSelected(true);

        view.getjRadioButtonInsumo().setEnabled(false);
        view.getjRadioButtonBebida().setEnabled(false);
        view.getjRadioButtonComida().setEnabled(true);

        view.getjTextFieldNomeProduto().setEnabled(true);
        view.getjTextFieldNomeProduto().setEditable(true);
        view.getjFormattedTextFielPrecoVenda().setEnabled(true);
        view.getjFormattedTextFielPrecoVenda().setEditable(true);
        view.getjTextFieldUnidade().setEnabled(true);
        view.getjTextFieldUnidade().setEditable(true);
        view.getjTextFieldQuantidade().setEnabled(true);
        view.getjTextFieldQuantidade().setEditable(true);

        view.getjButtonIncluir().setEnabled(true);
        view.getjButtonExcluir().setEnabled(true);

        view.getjComboBoxIngrediente().setEnabled(true);
        view.getjTableIngredientes().setEnabled(true);
        view.getjTextAreaReceita().setEnabled(true);
        view.getjButtonCadastrar().setText("Salvar");
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
                if (linha < 0) {
                    JOptionPane.showMessageDialog(view, "Você precisa selecionar um ingrediente na lista antes de remover");
                } else {

                    int id = Integer.parseInt(view.getjTableIngredientes().getValueAt(linha, 0).toString());

                    ArrayList<Insumo> ingredientesIteracao = new ArrayList<>();
                    
                    ingredientesIteracao.addAll(ingredientesTemp);

                    for (Insumo s : ingredientesIteracao) {
                        System.out.println("id lista" + s.getId() + " id " + id);
                        if (s.getId() == id) {
                            ingredientesTemp.remove(s);
                        }
                    }

                    popularTabelaIngredientes();
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

}
