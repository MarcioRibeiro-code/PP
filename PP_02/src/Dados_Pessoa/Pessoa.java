/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados_Pessoa;

import Dados_Pessoa.d_aux.Fisonomia;
import Exceptions.Infecao_Exception;
import Exceptions.Pessoa_Exception;
import Hotel.Divisoes.Movimentos.Movimentos;
import Hotel.Enum.Genero;
import Hotel.Enum.Tipo_Pessoa;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author PC
 */
public abstract class Pessoa {

    private String nome;
    private String id;
    private LocalDate data_nascimento;
    private Genero genero;
    private String[] patologias;
    private Fisonomia fisonomia;
    private Tipo_Pessoa tipo;
    private Movimentos[] movimentos = new Movimentos[4];

    public Pessoa(String nome, LocalDate data_nascimento, Genero genero, String[] patologias, Fisonomia fisonomia, Tipo_Pessoa tipo, int contador) {

        if (this instanceof Hospede) {
            this.id = "HO" + contador;
        }

        if (this instanceof Funcionario) {
            this.id = "FU" + contador;
        }

        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.genero = genero;
        this.patologias = patologias;
        this.fisonomia = fisonomia;
        this.tipo = tipo;
    }

    public void setTipo(Tipo_Pessoa tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return this.id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public Genero getGenero() {
        return genero;
    }

    public String[] getPatologias() {
        return patologias;
    }

    public Fisonomia getFisonomia() {
        return fisonomia;
    }

    public Tipo_Pessoa getTipo() {
        return tipo;
    }

    public Movimentos[] getmovimentos() {
        return this.movimentos;
    }

    public int numMovimentos() {
        int contador = 0;
        for (Movimentos mov1 : this.movimentos) {
            if (mov1 != null) {
                contador++;
            }
        }
        return contador;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Pessoa) {
            Pessoa aux_pessoa = (Pessoa) obj;

            //System.out.println("This: " + this.nome);
            //System.out.println("OBJ: " + aux_pessoa.nome);

            if (this.nome.equals(aux_pessoa.nome) && this.fisonomia.equals(aux_pessoa.fisonomia)) {
                System.out.println("IGUAIS");
                return true;
            }

        }
        return false;
    }

    /**
     *
     * @param body_temp
     * @param data
     * @param Zona
     * @param divisao
     * @return
     * @throws Infecao_Exception
     * @throws Exceptions.Pessoa_Exception
     */
    public abstract boolean addMovimento(double body_temp, LocalDateTime data, String Zona, String divisao) throws Infecao_Exception, Pessoa_Exception;

    public Pessoa get_Pessoa(String id) {

        if (this.id.equals(nome)) {
            return this;
        }
        return null;
    }

    public int getMov_cont() {
        int contador = 0;
        for (Movimentos mov1 : this.getmovimentos()) {
            if (mov1 != null) {
                contador++;
            }
        }
        return contador;
    }

}
