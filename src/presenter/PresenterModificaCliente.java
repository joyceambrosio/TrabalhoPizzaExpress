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
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Endereco;
import view.ViewCadastrarCliente;

/**
 *
 * @author joyce
 */
public class PresenterModificaCliente {

    private Cliente cliente;
    private ViewCadastrarCliente view;
    private static PresenterModificaCliente instancia;

    private PresenterModificaCliente(Cliente c) {

        this.cliente = c;
        this.view = new ViewCadastrarCliente();
        configuraExibicao();
        modificarCliente();
        instancia = null;

        URL caminhoImagem = this.getClass().getClassLoader().getResource("icones/PizzaIcone.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        view.setIconImage(iconeTitulo);
        view.setLocationRelativeTo(null);
        view.setTitle("Modificar Cliente");

        this.view.addWindowListener(new WindowAdapter() {
            @SuppressWarnings("override")
            public void windowClosing(WindowEvent evt) {
                instancia = null;
                view.dispose();
            }
        });

        view.setVisible(true);

    }

    public static PresenterModificaCliente getInstancia(Cliente c) {
        if (instancia == null) {
            instancia = new PresenterModificaCliente(c);
            return instancia;
        }

        return instancia;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    private void configuraExibicao() {
        view.getjTextFieldNome().setText(cliente.getNome());
        view.getjTextFieldLogradouro().setText(cliente.getEndereco().getLogradouro());
        view.getjTextFieldNumero().setText(cliente.getEndereco().getNumero());
        view.getjTextFieldBairro().setText(cliente.getEndereco().getBairro());
        view.getjTextFieldCep().setText(cliente.getEndereco().getCep());
        view.getjTextFieldCidade().setText(cliente.getEndereco().getCidade());
        view.getjTextFieldReferencia().setText(cliente.getEndereco().getPontoDeReferencia());
        view.getjTextFieldEstado().setText(cliente.getEndereco().getEstado());

        view.getjButtonCadastrar().setText("Salvar");
    }

    public void modificarCliente() {
        view.getjButtonCadastrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("nãosei");
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
                        Clientes.getInstancia().modifica(cliente, cli);
                    } catch (SQLException ex) {
                        Logger.getLogger(PresenterModificaCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    JOptionPane.showMessageDialog(view, "Cliente modificado com sucesso");
                    instancia = null;
                    view.dispose();

                }

            }
        });
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
