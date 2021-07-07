/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.zonas;

import Csv.csv;
import Dados_Pessoa.Hospede;
import Dados_Pessoa.Pessoa;
import Exceptions.Divisao_Exception;
import Exceptions.Infecao_Exception;
import Exceptions.Movimentos_Exception;
import Exceptions.Pessoa_Exception;
import Exceptions.Zona_Exception;
import Hotel.Divisoes.Movimentos.Movimentos;
import Hotel.Enum.Tipo_Alerta;
import Hotel.divisao.Divisao;
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

       // System.out.println("Zona: " + num_max_suporte);

        if (num_max_suporte <= 0) {
            throw new Zona_Exception("Valor invalido -> Numero max da divisao inferior ou igual a 0");
        }

        for (Divisao divisao1 : this.divisao) {
            if (divisao1 != null) {

                if (divisao1.getNomeDivisao().equals(identificador)) {
                    throw new Zona_Exception("Tentar adicionar uma divisao que já esta introduzida");
                }

                contador_div++;
            }

        }

       // System.out.println("Identificador Zona: " + identificador);

        this.divisao[contador_div] = new Divisao(num_max_suporte, identificador);

        return true;
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
