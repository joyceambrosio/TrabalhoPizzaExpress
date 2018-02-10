/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.event.ChangeEvent;

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

}
