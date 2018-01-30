package aplicacao;

import colections.Seed;
import conexaoBanco.Conexao;
import java.util.logging.Level;
import java.util.logging.Logger;
import presenter.PresenterLogin;
import view.Login;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aluno
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //PresenterLogin login = new PresenterLogin(new Login() );
        Seed seed = new Seed();
        seed.addSeeds();
       
    }
    
}
