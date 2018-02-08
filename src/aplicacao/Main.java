package aplicacao;

import colections.Clientes;
import colections.Produtos;
import colections.Seed;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import model.Bebida;
import model.Cliente;
import model.Endereco;
import model.Funcionario;
import presenter.PresenterCadastrarCliente;
import presenter.PresenterLogin;
import presenter.PresenterMenu;
import view.ViewCadastrarCliente;
import view.ViewLogin;
import view.ViewMenu;

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
    public static void main(String[] args) throws SQLException {

        Seed seed = new Seed();
        seed.addSeeds();

        PresenterLogin login = new PresenterLogin(new ViewLogin());
        //PresenterCadastrarCliente cadastraCliente = new PresenterCadastrarCliente(new ViewCadastrarCliente());

    }

}
