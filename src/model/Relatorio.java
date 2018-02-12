/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Natalia
 */
public class Relatorio {

    //alterar aqui
    private String mes;
    private String ano;
    private String semana;
    private Administrador adm;

    public Relatorio(String mes, String ano) {
        this.mes = mes;
        this.ano = ano;

    }

    public Relatorio() {
    }

    public void gererRelatorio(String semana, int mes, int ano) {

    }

}
