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
import model.Produto;
import view.ViewCadastrarProduto;

/**
 *
 * @author Natalia
 */
public class PresenterModificarProduto {

    private ViewCadastrarProduto view;
    private static PresenterModificarProduto instancia;
    private Produto produto;

    private PresenterModificarProduto(Produto  produto) {
        
        this.produto = produto;
        
        
        
        
        
        
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
        view.setTitle("Modificar Produto");
        view = new ViewCadastrarProduto();
    }
    
    
    public static PresenterModificarProduto getInstancia(Produto p){
        if (instancia == null){
            return instancia = new PresenterModificarProduto(p);
        }
        
        return instancia;
    }
    
    

}
