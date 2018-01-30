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
public class Endereco {

    private String logradouro, bairro, cidade, estado, cep, pontoDeReferencia;
    private int numero;

    public Endereco() {

    }

    public Endereco(String logradouro, String bairro, String cidade, String estado, String cep, int numero) {
        setLogradouro(logradouro); 
        setBairro(bairro);
        setCidade(cidade);
        setEstado(estado);
        setCep(cep);
        setNumero(numero);
    }

    public Endereco(String logradouro, String bairro, String cidade, String estado, String cep, String pontoDeReferencia, int numero) {
        setLogradouro(logradouro);
        setBairro(bairro);
        setCidade(cidade);
        setEstado(estado);
        setCep(cep);
        setPontoDeReferencia(pontoDeReferencia);
        setNumero(numero);
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        if (!logradouro.equals(" ")) {
            this.logradouro = logradouro;
        }
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        if (!bairro.equals(" ")) {
            this.bairro = bairro;
        }
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        if (!cidade.equals(" ")) {
            this.cidade = cidade;
        }
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (!estado.equals(" ")) {
            this.estado = estado;
        }
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        if (!cep.equals(" ")) {
            this.cep = cep;
        }
    }

    public String getPontoDeReferencia() {
        return pontoDeReferencia;
    }

    public void setPontoDeReferencia(String pontoDeReferencia) {
        if (!pontoDeReferencia.equals(" ")) {
            this.pontoDeReferencia = pontoDeReferencia;
        }
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        if (numero >= 0) {
            this.numero = numero;
        }
    }

}
