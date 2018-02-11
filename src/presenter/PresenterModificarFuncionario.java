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
public class PresenterModificarFuncionario {

    private static PresenterModificarFuncionario instancia;
    private ViewCadastrarFuncionario view;
    private Funcionario funcionario;

    private PresenterModificarFuncionario(Funcionario f) {
        this.funcionario = f;
        view = new ViewCadastrarFuncionario();

        this.view.addWindowListener(new WindowAdapter() {
            @SuppressWarnings("override")
            public void windowClosing(WindowEvent evt) {
                instancia = null;
                view.dispose();
            }
        });

        view.getjComboBoxCargo().addItem("Entregador");
        view.getjComboBoxCargo().addItem("Antendente");
        view.getjComboBoxCargo().addItem("Cozinheiro");

        configuraCampos();
        modificarFuncionario();

        URL caminhoImagem = this.getClass().getClassLoader().getResource("icones/PizzaIcone.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        view.setIconImage(iconeTitulo);
        view.setTitle("Modificar Funcionário");
        view.getjButtonCadastrar().setText("Salvar");

        view.setLocationRelativeTo(null);
        view.setVisible(true);

    }

    public static PresenterModificarFuncionario getInstancia(Funcionario f) {
        if (instancia == null) {
            return instancia = new PresenterModificarFuncionario(f);
        }

        return instancia;
    }

    public void modificarFuncionario() {
        Funcionario funcionarioAntigo = funcionario;

        view.getjButtonCadastrar().addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                if (validaCampos()) {
                    if (view.getjComboBoxCargo().getSelectedItem().equals("Entregador")) {
                        funcionario = new Entregador(view.getjTextFieldNome().getText(), view.getjTextFieldUsuario().getText(), view.getjPasswordFieldSenha1().getText(), new Cargo("Entregador", 0, true, 4));
                    }
                    if (view.getjComboBoxCargo().getSelectedItem().equals("Atendente")) {
                        funcionario = new Entregador(view.getjTextFieldNome().getText(), view.getjTextFieldUsuario().getText(), view.getjPasswordFieldSenha1().getText(), new Cargo("Atendente", 1200, false, 1));
                    }
                    if (view.getjComboBoxCargo().getSelectedItem().equals("Cozinheiro")) {
                        funcionario = new Entregador(view.getjTextFieldNome().getText(), view.getjTextFieldUsuario().getText(), view.getjPasswordFieldSenha1().getText(), new Cargo("Cozinheiro", 1800, false, 3));

                    }

                    funcionario.setId(funcionarioAntigo.getId());

                    try {
                        Funcionarios.getInstancia().updtFuncionario(funcionario);
                        JOptionPane.showMessageDialog(view, "Funcionário alterado com sucesso!");
                        PresenterMenu.getInstancia().populaMenuFuncionarios();
                        instancia = null;
                        view.dispose();

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
        } else if (view.getjTextFieldUsuario().getText().equals("")) {
            JOptionPane.showMessageDialog(view, "Informe um nome de usuário");
            return false;
        } else if (view.getjPasswordFieldSenha1().getText().equals("") || view.getjPasswordFieldSenha2().getText().equals("")) {
            JOptionPane.showMessageDialog(view, "Preencher o campo senha");
            return false;
        } else if (view.getjPasswordFieldSenha1().getText().equals(view.getjPasswordFieldSenha2().getText())) {
            return true;
        }

        JOptionPane.showMessageDialog(view, "As senhas não são compatíveis");
        view.getjPasswordFieldSenha1().setText("");
        view.getjPasswordFieldSenha2().setText("");
        return false;
    }

    public void configuraCampos() {
        view.getjTextFieldNome().setText(funcionario.getNome());
        view.getjTextFieldUsuario().setText(funcionario.getNomeUsuario());
        view.getjTextFieldUsuario().setEnabled(false);
        view.getjTextFieldUsuario().setEnabled(false);
        view.getjPasswordFieldSenha1().setText(funcionario.getSenha());
        view.getjPasswordFieldSenha2().setText(funcionario.getSenha());
        view.getjComboBoxCargo().setSelectedItem(funcionario.getCargo().getCargo());

    }

}
