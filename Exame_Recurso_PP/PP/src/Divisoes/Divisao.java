/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Divisoes;

import Enumeracoes.Tipo_Pessoa;
import Exceptions.Divisao_Exception;
import Exceptions.Infecao_Exception;
import Exceptions.Movimentos_Exception;
import Exceptions.Pessoa_Exception;
import Interface.core.IDivisoes;
import Movimentos.Movimentos;
import Pessoa.Hospede;
import Pessoa.NoQuarto;
import Pessoa.Pessoa;
import java.time.LocalDateTime;

/**
 *
 * @author PC
 */
public class Divisao implements IDivisoes {

    private final int num_max_suporte;
    private String identificador;
    private NoQuarto[] no_quarto;
    private Pessoa[] pessoas_na_divisao;

    public Divisao(int num_max_suporte, String identificador) {
        this.num_max_suporte = num_max_suporte;
        this.identificador = identificador;
        this.pessoas_na_divisao = new Pessoa[this.num_max_suporte];
        this.no_quarto = new NoQuarto[this.num_max_suporte];
    }

    @Override
    public String getId() {
        return this.identificador;
    }

    @Override
    public int getNumMaxSuporte() {
        return this.num_max_suporte;
    }

    public int getCont_NoQuarto() {
        int contador = 0;
        for (NoQuarto no_quarto1 : this.no_quarto) {
            if (no_quarto1 != null) {
                contador++;
            }
        }
        return contador;
    }

    public boolean addHospede(Hospede pess, Tipo_Pessoa arg1) throws Pessoa_Exception, Divisao_Exception {

        int contador_pessoa = 0;

        for (Pessoa pessoas_na_divisao1 : this.pessoas_na_divisao) {
            if (pessoas_na_divisao1 != null) {
                if (pessoas_na_divisao1.equals(pess)) {
                    throw new Pessoa_Exception("Pessoa repetida");
                }

                contador_pessoa++;
            }

        }

        if (contador_pessoa + 1 > this.pessoas_na_divisao.length) {
            throw new Divisao_Exception("Divisao Cheia");
        }

        pess.setTipo(arg1);

        System.out.println("Contador: " + (contador_pessoa));
        this.pessoas_na_divisao[contador_pessoa] = pess;
        this.no_quarto[this.getCont_NoQuarto()] = new NoQuarto(this.pessoas_na_divisao[contador_pessoa].getId());

        return true;
    }

    public Divisao get_divisao(String id) {

        for (Pessoa pess1 : this.getPessoasDivisao()) {
            if (pess1 != null) {
                if (pess1.get_Pessoa(id) != null) {
                    return this;
                }
            }
        }
        return null;
    }

    @Override
    public Pessoa[] getPessoasDivisao() {
        return this.pessoas_na_divisao;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Divisao)) {
            return false;
        }
        Divisao aux_div = (Divisao) obj;

        if (this.identificador.equals(aux_div.identificador)) {
            return true;
        }
        return false;
    }

    public boolean addMovimento(String id_pessoa, double body_temp, LocalDateTime data, String Zona) throws Movimentos_Exception, Infecao_Exception {

        System.out.println("Zona DIVISAO"+Zona);
        
        for (Pessoa pessoas_na_divisao1 : this.pessoas_na_divisao) {

            if (pessoas_na_divisao1 != null) {
                if (pessoas_na_divisao1.getId().equals(id_pessoa)) {
                    pessoas_na_divisao1.addMovimento(body_temp, data, Zona);
                    for (NoQuarto no_quarto1 : this.no_quarto) {
                        if (no_quarto1 != null) {
                            if (no_quarto1.getId().equals(id_pessoa)) {

                            }
                        }
                    }
                    System.out.println(pessoas_na_divisao1.getNome());
                    Movimentos[] aux = pessoas_na_divisao1.getmovimentos();

                    System.out.println("ID Zona: " + aux[0].getZona());

                }
            }

        }

        return false;
    }

}
