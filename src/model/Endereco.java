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

    private int id;
    private String logradouro, bairro, cidade, estado, cep, pontoDeReferencia, numero;

    public Endereco() {

    }

    public Endereco(String logradouro, String bairro, String cidade, String estado, String cep, String numero) {
        setLogradouro(logradouro);
        setBairro(bairro);
        setCidade(cidade);
        setEstado(estado);
        setCep(cep);
        setNumero(numero);
    }

    public Endereco(String logradouro, String bairro, String cidade, String estado, String cep, String pontoDeReferencia, String numero) {
        setLogradouro(logradouro);
        setBairro(bairro);
        setCidade(cidade);
        setEstado(estado);
        setCep(cep);
        setPontoDeReferencia(pontoDeReferencia);
        setNumero(numero);
    }

    public Endereco(int idEndereco, String logradouro, String bairro, String cidade, String estado, String cep, String pontoDeReferencia, String numero) {
        this.id = idEndereco;
        setLogradouro(logradouro);
        setBairro(bairro);
        setCidade(cidade);
        setEstado(estado);
        setCep(cep);
        setPontoDeReferencia(pontoDeReferencia);
        setNumero(numero);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnderecoCompleto() {
        return logradouro + ", " + numero + ", " + bairro + ", " + pontoDeReferencia;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        if (!numero.equals("")) {
            this.numero = numero;
        }
    }

}
