/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import colections.Funcionarios;
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
import model.Cargo;
import model.Entregador;
import model.Funcionario;
import view.ViewCadastrarFuncionario;

/**
 *
 * @author Natalia
 */
public class PresenterCadastrarFuncionario {

    private ViewCadastrarFuncionario view;
    private static PresenterCadastrarFuncionario instancia;

    private PresenterCadastrarFuncionario() {
        view = new ViewCadastrarFuncionario();

        this.view.addWindowListener(new WindowAdapter() {
            @SuppressWarnings("override")
            public void windowClosing(WindowEvent evt) {
                instancia = null;
                view.dispose();
            }
        });

        view.getjComboBoxCargo().addItem("Entregador");
        view.getjComboBoxCargo().addItem("Atendente");
        view.getjComboBoxCargo().addItem("Cozinheiro");

        URL caminhoImagem = this.getClass().getClassLoader().getResource("icones/PizzaIcone.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        view.setIconImage(iconeTitulo);
        view.setTitle("Cadastrar Funcionário");

        cadastrarFuncionario();

        view.setLocationRelativeTo(null);
        view.setVisible(true);

    }

    public static PresenterCadastrarFuncionario getInstancia() {
        if (instancia == null) {
            return instancia = new PresenterCadastrarFuncionario();
        }

        return instancia;
    }

    public void cadastrarFuncionario() {
        view.getjButtonCadastrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validaCampos()) {
                    Funcionario funcionario = null;
                    if (view.getjComboBoxCargo().getSelectedItem().equals("Entregador")) {
                        funcionario = new Entregador(view.getjTextFieldNome().getText(), view.getjTextFieldUsuario().getText(), view.getjPasswordFieldSenha1().getText(), new Cargo("Entregador", 0, true, 4));
                    } else if (view.getjComboBoxCargo().getSelectedItem().equals("Atendente")) {
                        funcionario = new Entregador(view.getjTextFieldNome().getText(), view.getjTextFieldUsuario().getText(), view.getjPasswordFieldSenha1().getText(), new Cargo("Atendente", 1200, false, 1));

                    } else if (view.getjComboBoxCargo().getSelectedItem().equals("Cozinheiro")) {
                        funcionario = new Entregador(view.getjTextFieldNome().getText(), view.getjTextFieldUsuario().getText(), view.getjPasswordFieldSenha1().getText(), new Cargo("Cozinheiro", 1800, false, 3));

                    }

                    try {
                        if (Funcionarios.getInstancia().add(funcionario)) {

                            JOptionPane.showMessageDialog(view, "Funcionário cadastrado com sucesso!");
                            PresenterMenu.getInstancia().populaMenuFuncionarios();
                            instancia = null;
                            view.dispose();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PresenterCadastrarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
    }

    public boolean validaCampos() {

        if (view.getjTextFieldNome().equals("")) {
            JOptionPane.showMessageDialog(view, "Informe o nome");
            return false;
        }
        if (view.getjTextFieldUsuario().getText().equals("")) {
            JOptionPane.showMessageDialog(view, "Informe um nome de usuário");
            return false;
        }

        if (!view.getjTextFieldUsuario().getText().equals("")) {
            try {
                for (Funcionario f : Funcionarios.getInstancia().getFuncionarios()) {
                    if (f.getNomeUsuario().equalsIgnoreCase(view.getjTextFieldUsuario().getText())) {
                        JOptionPane.showMessageDialog(view, "Nome de usuário existente!");
                        return false;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(PresenterCadastrarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (view.getjPasswordFieldSenha1().getText().equals("") || view.getjPasswordFieldSenha2().getText().equals("")) {
            JOptionPane.showMessageDialog(view, "Preencher o campo senha");
            return false;
        }

        if (!view.getjPasswordFieldSenha1().getText().equals(view.getjPasswordFieldSenha2().getText())) {
            JOptionPane.showMessageDialog(view, "As senhas não são compatíveis");
            view.getjPasswordFieldSenha1().setText("");
            view.getjPasswordFieldSenha2().setText("");
            return false;
        }

        return true;
    }

}
