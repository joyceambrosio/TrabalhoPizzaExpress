/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import colections.Clientes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import view.ViewMenu;

/**
 *
 * @author Natalia
 */
public class PresenterCliente {

    private ViewMenu menu;

    public PresenterCliente(ViewMenu menu) {
        this.menu = menu;

        adicionarCliente();
        populaMenuClientes();
        modificarCliente();
        buscarCliente();
        excluirCliente();

    }

    public void populaMenuClientes() {

        Object colunas[] = {"ID", "Nome do Cliente", "Endereço", "Quantidade de Compras"};
        DefaultTableModel tabela = new DefaultTableModel(colunas, 0);

        menu.getjTableCliente().setModel(tabela);

        try {
            for (Cliente c : Clientes.getInstancia().getClientes()) {
                int id = c.getId();

                String nome = c.getNome();
                String end = c.getEndereco().getEnderecoCompleto();
                int compras = c.getNumeroCompra();

                tabela.addRow(new Object[]{id, nome, end, compras});
            }
        } catch (SQLException ex) {
            Logger.getLogger(PresenterMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void adicionarCliente() {

        menu.getjButtonNovoCliente().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PresenterCadastrarCliente cliente = PresenterCadastrarCliente.getInstancia();
            }
        });

    }

    public void modificarCliente() {
        menu.getjButtonModificarCliente().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int linha = menu.getjTableCliente().getSelectedRow();

                if (linha == -1) {
                    JOptionPane.showMessageDialog(menu, "Selecione um cliente para realizar as modificações");
                } else if (linha >= 0) {

                    int idCliente = Integer.parseInt(menu.getjTableCliente().getValueAt(linha, 0).toString());
                    Cliente c = null;
                    try {
                        c = Clientes.getInstancia().getClienteById(idCliente);
                    } catch (SQLException ex) {
                        Logger.getLogger(PresenterCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (c != null) {
                        PresenterModificarCliente presenterModificaCliente = PresenterModificarCliente.getInstancia(c);

                    }

                }

            }
        });
    }

    public void buscarCliente() {
        menu.getjButtonPesquisarCliente().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buscar = menu.getjTextFieldBuscarCliente().getText();

                Object colunas[] = {"ID", "Nome do Cliente", "Endereço", "Quantidade de Compras"};
                DefaultTableModel tabela = new DefaultTableModel(colunas, 0);

                menu.getjTableCliente().setModel(tabela);

                try {
                    for (Cliente c : Clientes.getInstancia().getClienteByNome(buscar)) {
                        int id = c.getId();
                        String nome = c.getNome();
                        String end = c.getEndereco().getEnderecoCompleto();
                        int compras = c.getNumeroCompra();

                        tabela.addRow(new Object[]{id, nome, end, compras});
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(PresenterMenu.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }

    public void excluirCliente() {

        menu.getjButtonExcluirCliente().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linha = menu.getjTableCliente().getSelectedRow();

                if (linha == -1) {
                    JOptionPane.showMessageDialog(menu, "Selecione um cliente na lista para remove-lo");
                } else if (linha >= 0) {
                    int id = Integer.parseInt(menu.getjTableCliente().getValueAt(linha, 0).toString());
                    try {

                        int confirmacao = JOptionPane.showConfirmDialog(menu, "Tem certeza que quer remover esse cliente?");
                        if (confirmacao == 0) {
                            Clientes.getInstancia().desativaCliente(id);
                            PresenterMenu.getInstancia().populaMenuClientes();
                            JOptionPane.showMessageDialog(menu, "O cliente foi removido com sucesso");

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PresenterMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        );

    }

}
