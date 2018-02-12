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
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Funcionario;
import view.ViewLogin;

/**
 *
 * @author Natalia
 */
public class PresenterLogin {

    private ViewLogin view;

    public PresenterLogin(ViewLogin view) {
        this.view = view;

        validarLogin();

        URL caminhoImagem = this.getClass().getClassLoader().getResource("icones/PizzaIcone.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        view.setIconImage(iconeTitulo);

        //Tirar isso depois
        view.getjTextFieldNomeUsuario().setText("Administrador");
        view.getjPasswordFieldSenha().setText("123456789");

        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    public void validarLogin() {
        view.getjButtonEntrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String usuario = view.getjTextFieldNomeUsuario().getText();
                String senha = view.getjPasswordFieldSenha().getText();

                if (usuario.equals("") || senha.equals("")) {
                    if (usuario.equals("")) {
                        JOptionPane.showMessageDialog(view, "Por favor, informar o login do usuário.");
                    }
                    if (senha.equals("")) {
                        JOptionPane.showMessageDialog(view, "Por favor, informar a senha.");
                    }
                } else {
                    try {
                        if (Funcionarios.getInstancia().funcionarioValido(usuario, senha)) {
                            //verifica login e senha e chama a tela main
                            Funcionario funcionario = Funcionarios.getInstancia().getFuncionario(usuario);

                            view.setVisible(false);
                            view.dispose();
                            PresenterMenu.getInstancia(funcionario.getCargo().getCargo());
                            

                        } else {
                            JOptionPane.showMessageDialog(view, "Login ou senha estão incorretos.");
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(PresenterLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        });

    }

}
