/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel;

import Csv.csv;
import Divisoes.Divisao;
import Divisoes.Zona;
import Enumeracoes.Genero;
import Enumeracoes.Tipo_Alerta;
import Enumeracoes.Tipo_Pessoa;
import Exceptions.Divisao_Exception;
import Exceptions.Hotel_Exception;
import Exceptions.Infecao_Exception;
import Exceptions.Movimentos_Exception;
import Exceptions.Pessoa_Exception;
import Exceptions.Zona_Exception;
import Interface.core.IHotelStatistics;
import Pessoa.Fisonomia;
import Pessoa.Funcionario;
import Pessoa.Pessoa;
import java.time.LocalDateTime;
import Pessoa.Hospede;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class Hotel implements IHotelStatistics {

    private String ID;
    private String Name;
    private Funcionario[] funcionarios;
    private Zona[] zona;
    private Object[] exception;

    public Hotel(String ID, String Name) {
        this.ID = ID;
        this.Name = Name;
        this.zona = new Zona[4];
        this.exception = new Object[4];
        this.funcionarios = new Funcionario[4];
    }

    public boolean addFuncionario(Funcionario func) {

        int contador = 0;
        for (Funcionario func1 : this.funcionarios) {
            if (func1 != null) {
                contador++;
            }
        }

        this.funcionarios[contador] = func;
        this.funcionarios[contador].getFuncionario();
        return true;
    }

    public int get_excep_size() {
        int contador = 0;

        for (Object exception1 : this.exception) {
            if (exception1 != null) {
                contador++;
            }
        }
        return contador;
    }

    public boolean addZona(String ID, String name) {

        int contador = this.get_excep_size();

        if ((ID == null || ID.isEmpty()) && (name == null || name.isEmpty())) {
            try {
                throw new Hotel_Exception("Dados Invalidos");
            } catch (Hotel_Exception ex) {

                this.exception[contador] = ex;
            }
        }

        Zona aux_zona = new Zona(ID, name);
        contador = 0;

        for (Zona zona1 : this.zona) {
            if (zona1 != null) {
                contador++;
                if (zona1.equals(this)) {
                    return false;

                }
            }

        }

        System.out.println("Zona criada");
        this.zona[contador] = aux_zona;
        return true;
    }

    public boolean addDivisao(String arg0, int arg1, String arg2) {

        Zona aux_zona = this.getZona(arg0);

        int contador = this.get_excep_size();

        boolean retun = true;

        if (aux_zona != null) {
            try {
                aux_zona.addDivisao(arg1, arg2);
            } catch (Zona_Exception ex) {

                System.out.println(ex.getStr());

                this.exception[contador] = ex;
                retun = false;
            }
        } else {
            System.out.println("Zona não encontrada");
        }

        return retun;
    }

    public Zona[] getZonas() {
        return this.zona;
    }

    public Zona getZona(String arg0) {

        for (Zona zona1 : this.zona) {
            if (zona1 != null) {
                if (zona1.getID().equals(arg0)) {
                    return zona1;
                }
            }

        }

        return null;
    }

    public Divisao[] getDivisoesbyZona(String arg0) {

        if (this.getZona(arg0) != null) {
            Zona aux_zona = (Zona) this.getZona(arg0);
            return aux_zona.getDivisoes();
        }

        return null;
    }

    public Divisao getDivisaobyZona(String arg0, String arg1) {
        Divisao[] aux_divisao = this.getDivisoesbyZona(arg0);

        for (Divisao aux_divisao1 : aux_divisao) {
            if (aux_divisao1 != null) {

                if (aux_divisao1.getId().equals(arg1)) {
                    return aux_divisao1;
                }
            }
        }
        return null;
    }

    @Override
    public Pessoa[] getPessoasByDivisao(String arg0, String arg1) {
        Divisao aux_divisao = this.getDivisaobyZona(arg0, arg1);

        if (aux_divisao != null) {
            return aux_divisao.getPessoasDivisao();
        }

        return null;
    }

    @Override
    public Pessoa getPessoaByDivisao(String arg0, String arg1, String id) {
        Pessoa[] aux_pessoas = this.getPessoasByDivisao(arg0, arg1);

        if (aux_pessoas != null) {
            for (Pessoa aux_pessoa : aux_pessoas) {
                if (aux_pessoa != null) {
                    if (aux_pessoa.getId().equals(id)) {
                        return aux_pessoa;
                    }
                }
            }
        }
        return null;
    }

    public boolean checkin(String arg0, String arg1, String nome,
            LocalDate data_nascimento, Genero genero, String[] patologias,
            Fisonomia fisonomia) {

        Divisao aux_div = this.getDivisaobyZona(arg0, arg1);

        System.out.println("Divisao: " + aux_div);

        Hospede aux_hospede = new Hospede(nome, data_nascimento, genero,
                patologias, fisonomia, Tipo_Pessoa.Hospede);

        System.out.println(aux_hospede.getId());

        int contador = 0;

        if (aux_div != null) {
            try {
                aux_div.addHospede( aux_hospede, Tipo_Pessoa.Hospede);
            } catch (Pessoa_Exception ex) {
                System.out.println("Erro: " + ex.getStr());

                for (Object exception1 : this.exception) {
                    if (exception1 != null) {
                        contador++;
                    }

                }

                this.exception[contador] = ex;
                contador++;
            } catch (Divisao_Exception ex) {
                System.out.println("Divisao cheia");
                this.exception[contador] = ex;
            }
        } else {
            try {
                throw new Divisao_Exception("Divisao Inexistente");
            } catch (Divisao_Exception ex) {
                contador = this.get_excep_size();
                this.exception[contador] = ex;
            }
        }

        return true;
    }

    public boolean addMovimentos(String nome_zona, String nome_divisao, double body_temp, String id) {

        int contador = this.get_excep_size();

        boolean encontrado_zona = false;
        boolean encontrado_divisao = false;

        //Percorer o array zona ate encontrar um com o id igual
        //Apos percorrer o array divisao nesse array zona anteriormente obtido
        Zona aux_zona = null;

        for (Zona zona2 : this.zona) {
            if (zona2 != null) {
                if (zona2.getID().equals(nome_zona)) {
                    aux_zona = zona2;

                    System.out.println("sadas");
                    encontrado_zona = true;
                    //throw new Hotel_Exception("Zona Invalida");
                }
                if (aux_zona != null) {
                    for (Divisao div1 : aux_zona.getDivisoes()) {
                        if (div1 != null) {
                            if (div1.getId().equals(nome_divisao)) {
                                encontrado_divisao = true;
                            }
                        }
                    }
                }
            }
        }

        if (encontrado_zona == false) {
            try {
                throw new Hotel_Exception("Zona Invalida");
            } catch (Hotel_Exception ex) {
                System.out.println("EXC: " + ex.getStr());
                return false;
            }

        }

        if (encontrado_divisao == false) {
            try {
                throw new Hotel_Exception("Divisao Invalida");
            } catch (Hotel_Exception ex) {
                System.out.println("EXC: " + ex.getStr());
                return false;
            }

        }

        for (int i = 0; i < this.zona.length; i++) {
        }

        System.out.println("Zona Substring: " + id.substring(0, 2));

        switch (id.substring(0, 2)) {

            case "HO":

                for (Zona zona1 : this.zona) {
                    if (zona1 != null) {
                        try {
                            System.out.println("ZONA NAME: " + zona1.getName());
                            return zona1.addMovimento(nome_zona, nome_divisao, id, body_temp, LocalDateTime.now());
                        } catch (Pessoa_Exception ex) {
                            System.out.println("Movimentos -> Pessoa exp: " + ex.getStr());
                            this.exception[contador] = ex;

                        } catch (Movimentos_Exception ex) {
                            System.out.println("Movimentos -> Movimento exp: " + ex.getStr());
                            this.exception[contador] = ex;

                        } catch (Infecao_Exception | Zona_Exception ex) {

                            csv write_csv = new csv();
                            if (ex instanceof Infecao_Exception) {
                                Infecao_Exception aux_ex = (Infecao_Exception) ex;

                                System.out.println("Movimentos -> Infecao exp: " + aux_ex.getId());
                                this.exception[contador] = ex;
                                write_csv.relatorio_alertas(Tipo_Alerta.P_INFECAO, aux_ex.getData(), aux_ex.getId(), aux_ex.getDivisao());
                            } else {
                                Zona_Exception aux_ex = (Zona_Exception) ex;
                                System.out.println("Movimentos -> Zona exp: Acesso Indevido" + aux_ex.getData() + aux_ex.getId() + aux_ex.getDivisao());
                                this.exception[contador] = ex;

                                write_csv.relatorio_alertas(Tipo_Alerta.P_INFECAO, aux_ex.getData(), aux_ex.getId(), aux_ex.getDivisao());

                            }

                        }
                    }

                }

                break;

            case "FU":

                Funcionario aux_func = null;

                for (Funcionario func1 : this.funcionarios) {
                    if (func1 != null) {
                        if (func1.getId().equals(id)) {
                            aux_func = func1;
                        }
                    }

                }

                if (aux_func == null) {
                    try {
                        throw new Hotel_Exception("ID funcionario inexistente");
                    } catch (Hotel_Exception ex1) {
                        contador = this.get_excep_size();

                        this.exception[contador] = ex1.getStr();
                        System.out.println("EX ID func: " + this.exception[contador]);
                    }

                } else {
                    try {
                        aux_func.addMovimento(body_temp, LocalDateTime.now(), nome_zona.concat(" " + nome_divisao));
                    } catch (Infecao_Exception ex) {
                        System.out.println("Movimentos -> Infecao exp: " + ex.getDivisao());

                        csv write_csv = new csv();

                        write_csv.relatorio_alertas(Tipo_Alerta.P_INFECAO, ex.getData(), ex.getId(), ex.getDivisao());

                        this.exception[contador] = ex;
                    }

                }

                break;

        }

        return false;
    }

    public void Exception_toStr() {

        System.out.println("Tamnho EXp: " + this.exception.length);

        for (Object exception1 : this.exception) {
            if (exception1 != null) {

                Exception aux_ex = (Exception) exception1;

                System.out.println(aux_ex.toString());

            }
        }
    }

}
