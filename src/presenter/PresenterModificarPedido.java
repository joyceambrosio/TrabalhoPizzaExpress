/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import model.Pedido;
import view.viewCadastrarPedido;

/**
 *
 * @author Natalia
 */
public class PresenterModificarPedido {

    private viewCadastrarPedido view;
    private static PresenterModificarPedido instancia;
    private Pedido pedido;

    public PresenterModificarPedido(Pedido p) {
        this.pedido = p;
        view = new viewCadastrarPedido();
        
               
         this.view.addWindowListener(new WindowAdapter() {
            @SuppressWarnings("override")
            public void windowClosing(WindowEvent evt) {
                instancia = null;
                view.dispose();
            }
        });

        URL caminhoImagem = this.getClass().getClassLoader().getResource("icones/PizzaIcone.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        view.setIconImage(iconeTitulo);
        view.setTitle("Modificar Pedido");
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
    
    
    public static PresenterModificarPedido getInstancia(Pedido p){
        if(instancia == null){
            return instancia = new PresenterModificarPedido(p);
        }
        return instancia;
    }
    
    
   
    
    

}
