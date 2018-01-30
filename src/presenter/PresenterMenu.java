/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import static java.awt.Frame.MAXIMIZED_BOTH;
import view.Menu;

/**
 *
 * @author Aluno
 */
public class PresenterMenu {

    private Menu view;

    public PresenterMenu(Menu view) {
        this.view = view;
        
        
       view.setExtendedState(MAXIMIZED_BOTH);
        view.setVisible(true);
    }
    
    

}
