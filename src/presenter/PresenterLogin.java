/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.Login;
import view.Menu;

/**
 *
 * @author Natalia
 */
public class PresenterLogin {

    private Login view;

    public PresenterLogin(Login view) {
        this.view = view;

        validarLogin();

        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    public void validarLogin() {
        view.getjButtonEntrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTextFieldNomeUsuario().getText().equals("")) {
                    JOptionPane.showMessageDialog(view, "Por favor, informar o login do usuário.");
                } else if (view.getjPasswordFieldSenha().getText().equals("")) {
                    JOptionPane.showMessageDialog(view, "Por favor, informar a senha.");
                } else {
                    JOptionPane.showMessageDialog(view, "Login ou senha estão incorretos.");
                    
                }

//Controle de acesso
                    view.setVisible(false);
                    view.dispose();

                    PresenterMenu menu = new PresenterMenu(new Menu());
                }
            }
        );

    }

}
