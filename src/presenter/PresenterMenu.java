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

import view.Menu;

/**
 *
 * @author Aluno
 */
public final class PresenterMenu {

    private static PresenterMenu instancia;
    private Menu view;
    private PresenterCliente abaCliente;
    private PresenterPedido abaPedido;
    private PresenterProduto abaProduto;
    private PresenterFuncionario abaFuncionario;
    
    
    public static PresenterMenu getInstancia(){
        if(instancia == null){
            instancia = new PresenterMenu(new Menu());
        }
        
        return instancia;
    }

    private PresenterMenu(Menu view) {
        this.view = view;

        instanciarAbas();
        

        URL caminhoImagem = this.getClass().getClassLoader().getResource("icones/PizzaIcone.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        view.setIconImage(iconeTitulo);
        view.setTitle("Pizza Express");
        view.setExtendedState(MAXIMIZED_BOTH);
        view.setVisible(true);

    }

    public void instanciarAbas() {
        view.getjTabbedPaneMenu().addChangeListener((ChangeEvent e) -> {

            this.abaPedido = new PresenterPedido(view);
            this.abaCliente = new PresenterCliente(view);
            this.abaProduto = new PresenterProduto(view);
            this.abaFuncionario = new PresenterFuncionario(view);

        });

    }

}
