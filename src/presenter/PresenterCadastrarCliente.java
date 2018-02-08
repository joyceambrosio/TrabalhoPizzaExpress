/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import colections.Clientes;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Endereco;
import view.CadastrarCliente;

/**
 *
 * @author joyce
 */
public class PresenterCadastrarCliente {

    private CadastrarCliente view;
    private static PresenterCadastrarCliente instancia;

    private PresenterCadastrarCliente() {
        this.view = new CadastrarCliente();
               
        cadastrarCliente();
        configurarTela();
  
        this.view.addWindowListener(new WindowAdapter() {
            @SuppressWarnings("override")
            public void windowClosing(WindowEvent evt) {
                instancia = null;
                view.dispose();
            }
        });
    }

    public static PresenterCadastrarCliente getInstancia() {
        if (instancia == null) {
            instancia = new PresenterCadastrarCliente();
        }

        return instancia;
    }

    private void cadastrarCliente() {
       
        view.getjButtonCadastrar().addActionListener((ActionEvent e) -> {
            if (validarCampos()) {
                
                String nome = view.getjTextFieldNome().getText();
                String logradouro = view.getjTextFieldLogradouro().getText();
                String numero = view.getjTextFieldNumero().getText();
                String bairro = view.getjTextFieldBairro().getText();
                String cidade = view.getjTextFieldCidade().getText();
                String estado = view.getjTextFieldEstado().getText();
                String pontoDeReferencia = view.getjTextFieldReferencia().getText();
                String cep = view.getjTextFieldCep().getText();
                
                Endereco end = new Endereco(logradouro, bairro, cidade, estado, cep, pontoDeReferencia, numero);
                
                Cliente cli = new Cliente(nome, end, true, 0);
                try {
                    Clientes.getInstancia().add(cli);
                } catch (SQLException ex) {
                    Logger.getLogger(PresenterCadastrarCliente.class.getName()).log(Level.SEVERE, null, ex);
                }

                JOptionPane.showMessageDialog(view, "Cliente cadastrado com sucesso");
                instancia = null;
                view.dispose();
            }
        });
    }

    private void configurarTela() {
        URL caminhoImagem = this.getClass().getClassLoader().getResource("icones/PizzaIcone.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        view.setIconImage(iconeTitulo);
        view.setTitle("Cadastrar Cliente");
        view.setLocationRelativeTo(null);
        view.setVisible(true);

    }

 

    public boolean validarCampos() {

        String avisos = null;

        String nome = view.getjTextFieldNome().getText();
        String logradouro = view.getjTextFieldLogradouro().getText();
        String numero = view.getjTextFieldNumero().getText();
        String bairro = view.getjTextFieldBairro().getText();
        String cidade = view.getjTextFieldCidade().getText();
        String estado = view.getjTextFieldEstado().getText();
        String pontoDeReferencia = view.getjTextFieldReferencia().getText();
        String cep = view.getjTextFieldCep().getText();

        boolean vazio = (nome.equals("") || logradouro.equals("") || bairro.equals("") || cidade.equals("") || estado.equals("") || cep.equals("") || numero.equals("") || pontoDeReferencia.equals(""));
        System.out.println(vazio);
        if (vazio == true) {
            view.getjLabelAvisos().setText("Os campos não podem ser vazios");
            return false;
        }

        view.getjLabelAvisos().setText("");
        return true;

    }

}
