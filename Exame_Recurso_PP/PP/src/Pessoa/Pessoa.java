/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pessoa;

import Enumeracoes.Genero;
import Enumeracoes.Tipo_Pessoa;
import Exceptions.Infecao_Exception;
import Movimentos.Movimentos;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author PC
 */
public abstract class Pessoa {

    private static int contador = 0;
    private String nome;
    private String id;
    private LocalDate data_nascimento;
    private Genero genero;
    private String[] patologias;
    private Fisonomia fisonomia;
    private Tipo_Pessoa tipo;
    private Movimentos[] movimentos = new Movimentos[2];

    public Pessoa(String nome, LocalDate data_nascimento, Genero genero, String[] patologias, Fisonomia fisonomia, Tipo_Pessoa tipo) {
        System.out.println("CONTADOR PESSOA: " + contador);

        if (this instanceof Hospede) {
            this.id = "HO" + Pessoa.contador;
        }

        if (this instanceof Funcionario) {
            this.id = "FU" + Pessoa.contador;
        }

        Pessoa.contador++;
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.genero = genero;
        this.patologias = patologias;
        this.fisonomia = fisonomia;
        this.tipo = tipo;
    }

    public static int getContador() {
        return contador;
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

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Pessoa) {
            Pessoa aux_pessoa = (Pessoa) obj;

            System.out.println("This: " + this.nome);
            System.out.println("OBJ: " + aux_pessoa.nome);

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
     * @param divisao
     * @return
     * @throws Infecao_Exception
     */
    public abstract boolean addMovimento(double body_temp, LocalDateTime data, String divisao) throws Infecao_Exception;

    public Pessoa get_Pessoa(String id) {

        if (this.id.equals(nome)) {
            return this;
        }
        return null;
    }

}
