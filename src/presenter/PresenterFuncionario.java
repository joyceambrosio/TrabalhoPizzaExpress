/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import colections.Funcionarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Funcionario;
import view.ViewMenu;

/**
 *
 * @author Natalia
 */
public class PresenterFuncionario {

    private ViewMenu menu;

    public PresenterFuncionario(ViewMenu menu) {
        this.menu = menu;

        populaMenuFuncionarios();
        cadastrarNovoFuncionario();
        modificarFuncionario();
        excluirFuncionario();
        pesquisaFuncionario();
    }

    public void cadastrarNovoFuncionario() {
        menu.getjButtonNovoFuncionario().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PresenterCadastrarFuncionario presenterFuncionarioCad = PresenterCadastrarFuncionario.getInstancia();
            }
        });

    }

    public void modificarFuncionario() {
        menu.getjButtonModificarFuncionario().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int linha = menu.getjTableFuncionario().getSelectedRow();

                if (linha == -1) {
                    JOptionPane.showMessageDialog(menu, "Selecione um Funcionário para realizar as modificações");
                } else if (linha >= 0) {

                    int idFuncionario = Integer.parseInt(menu.getjTableFuncionario().getValueAt(linha, 0).toString());
                    Funcionario f = Funcionarios.getInstancia().getFuncionarioByID(idFuncionario);

                    if (f != null) {
                        PresenterModificarFuncionario presenterFuncionarioMod = PresenterModificarFuncionario.getInstancia(f);

                    }
                }

            }
        });

    }

    public void populaMenuFuncionarios() {

        Object colunas[] = {"ID", "Nome", "Comissão", "Nome de Usuário", "Cargo"};
        DefaultTableModel tabela = new DefaultTableModel(colunas, 0);
        menu.getjTableFuncionario().setModel(tabela);

        try {
            for (Funcionario f : Funcionarios.getInstancia().getFuncionarios()) {
                int id = f.getId();
                String nome = f.getNome();
                String usuario = f.getNomeUsuario();
                String cargo = f.getCargo().getCargo();
                String comissao;
                if (f.getCargo().isComissao()) {
                    comissao = "Sim";
                } else {
                    comissao = "Não";
                }
                tabela.addRow(new Object[]{id, nome, comissao, usuario, cargo});
            }
        } catch (SQLException ex) {
            Logger.getLogger(PresenterFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void excluirFuncionario() {
        menu.getjButtonExcluirFuncionario().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linha = menu.getjTableFuncionario().getSelectedRow();

                if (linha == -1) {
                    JOptionPane.showMessageDialog(menu, "Selecione um funcionário na lista para remove-lo");
                } else if (linha >= 0) {
                    int id = Integer.parseInt(menu.getjTableFuncionario().getValueAt(linha, 0).toString());
                    try {

                        int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que quer remover esse funcionário?");
                        if (confirmacao == 0) {
                            Funcionarios.getInstancia().desativaFuncionario(id);
                            JOptionPane.showMessageDialog(null, "O funcionário foi removido com sucesso");
                            PresenterMenu.getInstancia().populaMenuFuncionarios();

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PresenterMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    public void pesquisaFuncionario() {
        menu.getjButtonPesquisarFuncionario().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menu.getjTextFieldBuscarFuncionario().getText().equals("")) {
                    populaMenuFuncionarios();
                } else {
                    Object colunas[] = {"ID", "Nome", "Comissão", "Nome de Usuário", "Cargo"};
                    DefaultTableModel tabela = new DefaultTableModel(colunas, 0);
                    menu.getjTableFuncionario().setModel(tabela);

                    System.out.println("Pesquisa: " + menu.getjTextFieldBuscarFuncionario().getText());
                    ArrayList<Funcionario> pesquisa = Funcionarios.getInstancia().pesquisa(menu.getjTextFieldBuscarFuncionario().getText());
                    for (Funcionario f : pesquisa) {
                        int id = f.getId();
                        String nome = f.getNome();
                        String usuario = f.getNomeUsuario();
                        String cargo = f.getCargo().getCargo();
                        String comissao;
                        if (f.getCargo().isComissao()) {
                            comissao = "Sim";
                        } else {
                            comissao = "Não";
                        }
                        tabela.addRow(new Object[]{id, nome, comissao, usuario, cargo});

                    }

                }
            }
        });
    }

}
