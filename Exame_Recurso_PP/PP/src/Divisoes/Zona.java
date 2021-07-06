/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Divisoes;

import Csv.csv;
import Enumeracoes.Tipo_Alerta;
import Enumeracoes.Tipo_Pessoa;
import Exceptions.Divisao_Exception;
import Exceptions.Infecao_Exception;
import Exceptions.Movimentos_Exception;
import Exceptions.Pessoa_Exception;
import Exceptions.Zona_Exception;
import Pessoa.Pessoa;
import java.time.LocalDateTime;

/**
 *
 * @author PC
 */
public class Zona {

    private String ID;
    private String Name;
    private Divisao[] divisao = new Divisao[2];

    public Zona(String ID, String Name) {
        this.ID = ID;
        this.Name = Name;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public Divisao[] getDivisoes() {
        return divisao;
    }

    public boolean addDivisao(int num_max_suporte, String identificador) throws Zona_Exception {

        int contador_div = 0;

        System.out.println("Zona: " + num_max_suporte);

        if (num_max_suporte <= 0) {
            throw new Zona_Exception("Valor invalido -> Numero max da divisao inferior ou igual a 0");
        }

        for (Divisao divisao1 : this.divisao) {
            if (divisao1 != null) {

                if (divisao1.getId().equals(identificador)) {
                    throw new Zona_Exception("Tentar adicionar uma divisao que já esta introduzida");
                }

                contador_div++;
            }

        }

        System.out.println("Identificador Zona: " + identificador);

        this.divisao[contador_div] = new Divisao(num_max_suporte, identificador);

        return true;
    }


    public boolean addMovimento(String zona, String divisao, String id_pessoa, double body_temp, LocalDateTime data) throws Pessoa_Exception, Movimentos_Exception, Infecao_Exception, Zona_Exception {

        System.out.println("Paraemtro Name: " + zona);
        System.out.println("Zona Name: " + this.Name);

        if (id_pessoa.isEmpty()) {
            throw new Pessoa_Exception("ID invalido");
        }

        if (body_temp <= 35 || data == null) {
            throw new Movimentos_Exception("Temperatura ou data invalida");
        }

        int id;

        id = Integer.parseInt(id_pessoa.replaceAll("[a-zA-Z]", ""));

        //Caso hospede
        for (Divisao divisao1 : this.divisao) {
            if (divisao1 != null && id <= Pessoa.getContador()) {
                for (Pessoa pessoasDivisao : divisao1.getPessoasDivisao()) {
                    if (pessoasDivisao != null) {
                        if (pessoasDivisao.getId().equals(id_pessoa)) {
                            System.out.println("Pessoa encontrada: " + id_pessoa);
                            divisao1.addMovimento(id_pessoa, body_temp, data, zona+" "+divisao);
                            if (!(zona.equals(this.ID))) {
                                try {
                                    throw new Zona_Exception(LocalDateTime.now(), id_pessoa, zona + " " + divisao);
                                } catch (Zona_Exception ex) {
                                    csv write_csv = new csv();

                                    write_csv.relatorio_alertas(Tipo_Alerta.ACESSO, ex.getData(), ex.getId(), ex.getDivisao());
                                }

                            }
                            return true;
                        } else {
                            throw new Pessoa_Exception("Pessoa errada, não tem essa divisao lhe atribuida");
                        }
                    }
                }
            }
        }

        return false;
    }

    public Zona getZona(String zona, String id_pessoa, String divisao) {

        if (this.getID().equals(zona)) {
            for (Divisao divisao1 : this.divisao) {
                if (divisao1 != null) {
                    if (divisao1.get_divisao(id_pessoa) != null) {
                        return this;
                    }
                }
            }
        }

        return null;
    }

    @Override
    public boolean equals(Object arg0) {

        if (!(arg0 instanceof Zona)) {
            return false;
        }

        Zona aux_zona = (Zona) arg0;

        if (!this.ID.equals(aux_zona.ID)
                && !this.Name.equals(aux_zona.Name)) {
            return false;
        }
        return true;
    }
}
