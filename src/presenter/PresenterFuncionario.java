/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import colections.Funcionarios;
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
    }
    
     public void populaMenuFuncionarios() {

        Object colunas[] = {"ID", "Nome", "Comissão", "Nome de Usuário", "Cargo"};
        DefaultTableModel tabela = new DefaultTableModel(colunas, 0);

        menu.getjTableFuncionario().setModel(tabela);

        //ta errado esse id
        int i = 0;

        for (Funcionario f : Funcionarios.getInstancia().getFuncionarios()) {
            int id = i++;
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
