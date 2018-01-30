/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Natalia
 */
public class Login {
    
    private String login;
    private String senha;
    
    public String getLogin() {
        return login;
    }

    public Login(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
    
    
    
    public void setLogin(String login) {
        if (!login.equals(" ")) {
            this.login = login;
        }
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        if (!senha.equals(" ")) {
            this.senha = senha;
        }
    }
    
}
