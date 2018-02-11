/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import colections.Clientes;
import colections.Funcionarios;
import colections.Produtos;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import model.Funcionario;
import model.Produto;

import view.ViewMenu;

/**
 *
 * @author Aluno
 */
public final class PresenterMenu {

    private static PresenterMenu instancia;
    private ViewMenu view;
    private PresenterCliente abaCliente;
    private PresenterPedido abaPedido;
    private PresenterProduto abaProduto;
    private PresenterFuncionario abaFuncionario;

    public static PresenterMenu getInstancia(String cargo) {
        if (instancia == null) {
            instancia = new PresenterMenu(new ViewMenu(), cargo);
        }

        return instancia;
    }
    
    public static PresenterMenu getInstancia(){
          if (instancia == null) {
            instancia = new PresenterMenu(new ViewMenu());
        }

        return instancia;
    }

    private PresenterMenu(ViewMenu view, String cargo) {
        this.view = view;

        URL caminhoImagem = this.getClass().getClassLoader().getResource("icones/PizzaIcone.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        view.setIconImage(iconeTitulo);
        view.setTitle("Pizza Express");
        view.setExtendedState(MAXIMIZED_BOTH);
        view.setVisible(true);
        instanciarAbas(cargo);

    }

    private PresenterMenu(ViewMenu viewMenu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void instanciarAbas(String cargo) {

        if (cargo.equals("Administrador")) {
            this.abaPedido = new PresenterPedido(view);
            this.abaCliente = new PresenterCliente(view);
            this.abaProduto = new PresenterProduto(view);
            this.abaFuncionario = new PresenterFuncionario(view);


        } else if (cargo.equals("Entregador")) {
            view.getjTabbedPaneMenu().setEnabledAt(1, false);
            this.abaPedido = new PresenterPedido(view);
            view.getjTabbedPaneMenu().setEnabledAt(2, false);
            view.getjTabbedPaneMenu().setEnabledAt(3, false);
            view.getjTabbedPaneMenu().setEnabledAt(4, false);
        } else if (cargo.equals("Cozinheiro")) {
            this.abaPedido = new PresenterPedido(view);
            this.abaProduto = new PresenterProduto(view);

            view.getjTabbedPaneMenu().setEnabledAt(1, false);
            view.getjTabbedPaneMenu().setEnabledAt(3, false);
            view.getjTabbedPaneMenu().setEnabledAt(4, false);

        } else if (cargo.equals("Atendente")) {
            this.abaPedido = new PresenterPedido(view);
            this.abaCliente = new PresenterCliente(view);
            view.getjTabbedPaneMenu().setEnabledAt(2, false);
            view.getjTabbedPaneMenu().setEnabledAt(3, false);
            view.getjTabbedPaneMenu().setEnabledAt(4, false);
        }

    }

    public ViewMenu getView() {
        return view;
    }
    
      public void populaMenuClientes() {

        Object colunas[] = {"ID", "Nome do Cliente", "Endereço", "Quantidade de Compras"};
        DefaultTableModel tabela = new DefaultTableModel(colunas, 0);

        view.getjTableCliente().setModel(tabela);

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
      
       
        public void populaMenuFuncionarios() {

        Object colunas[] = {"ID", "Nome", "Comissão", "Nome de Usuário", "Cargo"};
        DefaultTableModel tabela = new DefaultTableModel(colunas, 0);
        view.getjTableFuncionario().setModel(tabela);


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
        
        
         public void populaMenuProdutos() throws SQLException {
        Object colunas[] = {"ID", "Nome", "Categoria", "Preço"};
        DefaultTableModel tabela = new DefaultTableModel(colunas, 0);

        view.getjTableProduto().setModel(tabela);

        if (Produtos.getInstancia().getProdutos() == null) {
            System.out.println("produtos null");
        }

        for (Produto p : Produtos.getInstancia().getProdutos()) {
            if (!p.getCategoria().equals("Insumo")) {
                int id = p.getId();
                String nome = p.getNome();
                String categoria = p.getCategoria();
                double preco = p.getPreco();
                tabela.addRow(new Object[]{id, nome, categoria, preco});
            }
        }
    }

}
