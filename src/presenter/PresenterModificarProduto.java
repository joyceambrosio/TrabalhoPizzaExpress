/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
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
    String state;

    private PresenterModificarProduto(Produto produto) {

        this.produto = produto;

        this.view = new ViewCadastrarProduto();

        configurarExibicao();
        estadoInsumo();
        instancia = null;
        URL caminhoImagem = this.getClass().getClassLoader().getResource("icones/PizzaIcone.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        view.setIconImage(iconeTitulo);
        view.setTitle("Modificar Produto");
        view = new ViewCadastrarProduto();

        this.view.addWindowListener(new WindowAdapter() {
            @SuppressWarnings("override")
            public void windowClosing(WindowEvent evt) {
                instancia = null;
                view.dispose();
            }
        });

        view.setVisible(true);
    }

    public static PresenterModificarProduto getInstancia(Produto p) {
        if (instancia == null) {
            return instancia = new PresenterModificarProduto(p);
        }
        return instancia;
    }

    private void configurarExibicao() {
        if (produto.getCategoria().equals("Insumo")) {
            estadoInsumo();
            configurarInsumo();
        }
        if (produto.getCategoria().equals("Bebida")) {
            estadoBebida();
            configurarBebida();
        }
        if (produto.getCategoria().equals("Pizza")) {
            estadoPìzza();
            configurarPizza();
        } else {
            view.dispose();
        }
    }

    private void configurarInsumo() {
        estadoInsumo();
        System.out.println(produto.getNome());
        view.getjTextFieldNomeProduto().setText(produto.getNome());
    }

    private void configurarBebida() {
        estadoBebida();
        System.out.println(produto.getNome());
        view.getjTextFieldNomeProduto().setText(produto.getNome());
    }

    private void configurarPizza() {
        estadoPìzza();
        System.out.println(produto.getNome());
        view.getjTextFieldNomeProduto().setText(produto.getNome());
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

        view.getjFormattedTextFielPrecoVenda().setEnabled(false);
        view.getjTextFieldUnidade().setEnabled(false);
        view.getjTextFieldQuantidade().setEnabled(false);

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

        view.getjFormattedTextFielPrecoVenda().setEnabled(true);
        view.getjTextFieldUnidade().setEnabled(false);
        view.getjTextFieldQuantidade().setEnabled(false);

        view.getjButtonIncluir().setEnabled(false);
        view.getjButtonExcluir().setEnabled(false);

        view.getjComboBoxIngrediente().setEnabled(false);
        view.getjTableIngredientes().setEnabled(false);
        view.getjTextAreaReceita().setEnabled(false);
        view.getjButtonCadastrar().setText("Salvar");
    }

    public void estadoPìzza() {
        setState("Pizza");
        view.getjRadioButtonInsumo().setSelected(false);
        view.getjRadioButtonBebida().setSelected(false);
        view.getjRadioButtonComida().setSelected(true);

        view.getjRadioButtonInsumo().setEnabled(false);
        view.getjRadioButtonBebida().setEnabled(false);
        view.getjRadioButtonComida().setEnabled(true);

        view.getjFormattedTextFielPrecoVenda().setEnabled(true);
        view.getjTextFieldUnidade().setEnabled(true);
        view.getjTextFieldQuantidade().setEnabled(true);

        view.getjButtonIncluir().setEnabled(true);
        view.getjButtonExcluir().setEnabled(true);

        view.getjComboBoxIngrediente().setEnabled(true);
        view.getjTableIngredientes().setEnabled(true);
        view.getjTextAreaReceita().setEnabled(true);
        view.getjButtonCadastrar().setText("Salvar");
    }

}
