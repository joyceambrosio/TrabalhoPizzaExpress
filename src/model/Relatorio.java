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
    
    private int mes;
    private int ano;
    private String semana;
    private Administrador adm;

    public Relatorio(int mes, int ano) {
        this.mes = mes;
        this.ano = ano;
        adm = new Administrador();
    }

    public Relatorio() {
    }
    
    
    public void gererRelatorio(String semana, int mes, int ano){
       // System.out.println("Relatório referente a semana "+semana+" do mês: " + mes + " ano:" + ano);
       // System.out.println("Em construção.... aguarde em breve");
    }
    
   
    
    
    
    
    
    
}
