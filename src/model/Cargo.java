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
public class Cargo {

    private String cargo;
    private double salario;
    private boolean comissao;

    public Cargo(String cargo, double salario, boolean comissao) {
        setCargo(cargo);
        setSalario(salario);
        setComissao(comissao);
    }

    public Cargo() {
    }

    public Cargo(String cargo, double salario) {
        setCargo(cargo);
        setSalario(salario);
    }

    public Cargo(String cargo, boolean comissao) {
        setCargo(cargo);
        setComissao(comissao);
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        if (!cargo.equals(" ")) {
            this.cargo = cargo;
        }
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        if (salario > 0) {
            this.salario = salario;
        }
    }

    public boolean isComissao() {
        return comissao;
    }

    public void setComissao(boolean comissao) {
        if (comissao != this.comissao) {
            this.comissao = comissao;
        }
    }

}
