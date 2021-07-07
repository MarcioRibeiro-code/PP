/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel;

import Csv.csv;
import Dados_Pessoa.Funcionario;
import Dados_Pessoa.Hospede;
import Dados_Pessoa.Pessoa;
import Dados_Pessoa.d_aux.Fisonomia;
import Exceptions.Divisao_Exception;
import Exceptions.Hotel_Exception;
import Exceptions.Infecao_Exception;
import Exceptions.Movimentos_Exception;
import Exceptions.Pessoa_Exception;
import Exceptions.Zona_Exception;
import Hotel.Divisoes.Movimentos.Movimentos;
import Hotel.Enum.Genero;
import Hotel.Enum.Tipo_Alerta;
import Hotel.Enum.Tipo_Pessoa;
import Hotel.divisao.Divisao;
import Hotel.zonas.Zona;
import java.lang.reflect.Array;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class Hotel1 {

    private String ID;
    private String Name;
    private Funcionario[] funcionarios;
    private Hospede[] hospede;
    private Zona[] zona;
    private Object[] exception;

    public Hotel1(String ID, String Name) {
        this.ID = ID;
        this.Name = Name;
        this.zona = new Zona[4];
        this.exception = new Object[4];
        this.hospede = new Hospede[4];
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
        //this.funcionarios[contador].getFuncionario();
        return true;
    }

    public int get_excep_size() {
        int contador = 0;

        for (Object exception1 : this.exception) {
            if (exception1 != null) {
                contador++;
            }
        }
        
        if(contador +1 >= this.exception.length){
        this.exception = Arrays.copyOf(this.exception,this.exception.length*2);
        
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

        //System.out.println("Zona criada");
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

                //System.out.println("a" + ex.getStr());
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
            //System.out.println("ZONA: " + aux_zona.getName());
            return aux_zona.getDivisoes();
        }

        return null;
    }

    public Divisao getDivisaobyZona(String arg0, String arg1) {
        Divisao[] aux_divisao = this.getDivisoesbyZona(arg0);
        for (Divisao aux_divisao1 : aux_divisao) {
            if (aux_divisao1 != null) {
                //System.out.println("ARG1:" + arg1);
                // System.out.println("Aux_divisao:" + aux_divisao1.getNomeDivisao());
                if (aux_divisao1.getNomeDivisao().equals(arg1)) {
                    // System.out.println("1" + aux_divisao1.getNomeDivisao());
                    return aux_divisao1;
                }
            }
        }
        return null;
    }

    public Hospede adddHospede(String nome, LocalDate data_nascimento, Genero genero, String[] patologias, Fisonomia fisonomia, Tipo_Pessoa tipo) throws Pessoa_Exception {
        Hospede aux_hospede = new Hospede(nome, data_nascimento, genero, patologias, fisonomia, Tipo_Pessoa.Hospede);
        boolean encontrado = false;
        for (Hospede hospede1 : this.hospede) {
            if (hospede1 != null) {
                if (hospede1.equals(nome)) {
                    throw new Pessoa_Exception("Pessoa Repetida");
                }
            }
        }

        return aux_hospede;
    }

    public int get_hospedeContador() {
        int contador = 0;
        for (Hospede hospede1 : this.hospede) {
            if (hospede1 != null) {
                contador++;
            }
        }
        return contador;
    }

    public boolean checkin(String arg0, String arg1, LocalDateTime data, String nome,
            LocalDate data_nascimento, Genero genero, String[] patologias,
            Fisonomia fisonomia) {

        Divisao aux_div = this.getDivisaobyZona(arg0, arg1);

        //System.out.println("Divisao: " + aux_div);
        int contador = 0;
        int hospede_contador = this.get_hospedeContador();

        if (aux_div != null) {
            if (aux_div.getContador() != aux_div.getNumMaxSuporte()) {
                try {
                    this.hospede[hospede_contador] = this.adddHospede(nome, data_nascimento, genero, patologias, fisonomia, Tipo_Pessoa.Hospede);
                    //System.out.println("ID Hospede: " + this.hospede[hospede_contador].getId());
                    this.addMovimentos(arg0, arg1, 37, this.hospede[hospede_contador].getId(), data);
                    Movimentos[] a1 = this.hospede[hospede_contador].getmovimentos();
                    //System.out.println("Movimento: " + a1[0].getZona());
                    //aux_div.incContador();
                    // System.out.println("Contador: " + aux_div.getContador());
                } catch (Pessoa_Exception ex) {
                    System.out.println("Erro: " + ex.getStr());

                    for (Object exception1 : this.exception) {
                        if (exception1 != null) {
                            contador++;
                        }

                    }

                    this.exception[contador] = ex;
                    contador++;

                }

            } else {
                try {
                    throw new Divisao_Exception("Divisao Cheia");
                } catch (Divisao_Exception ex) {
                    this.exception[contador] = ex;
                    contador++;
                }

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

    public boolean verificar_zona_e_divisao(String id_zona, String id_divisao) {
        boolean encontrado_zona = false;
        boolean encontrado_divisao = false;

        for (Zona zona1 : this.zona) {
            if (zona1 != null) {
                if (zona1.getID().equals(id_zona)) {
                    encontrado_zona = true;
                }
                for (Divisao div1 : zona1.getDivisoes()) {
                    if (div1 != null) {
                        if (div1.getNomeDivisao().equals(id_divisao)) {
                            encontrado_divisao = true;
                        }
                    }

                }
            }
        }

        if (encontrado_zona != false && encontrado_divisao != false) {
            return true;
        } else {
            return false;
        }

    }

    public boolean addMovimentos(String nome_zona, String nome_divisao, double body_temp, String id, LocalDateTime data) {

        int contador = this.get_excep_size();

        boolean encontrado_zona = false;
        boolean encontrado_divisao = false;

        //Percorer o array zona ate encontrar um com o id igual
        //Apos percorrer o array divisao nesse array zona anteriormente obtido
        Zona aux_zona = null;
        Pessoa aux_pessoa = null;
        for (Zona zona2 : this.zona) {
            if (zona2 != null) {
                if (zona2.getID().equals(nome_zona)) {
                    aux_zona = zona2;

                    encontrado_zona = true;
                    //throw new Hotel_Exception("Zona Invalida");
                }
                if (aux_zona != null) {
                    for (Divisao div1 : aux_zona.getDivisoes()) {
                        if (div1 != null) {
                            if (div1.getNomeDivisao().equals(nome_divisao)) {
                                encontrado_divisao = true;

                            }
                        }
                    }
                }
            }
        }

        if (!this.verificar_zona_e_divisao(nome_zona, nome_divisao)) {
            try {
                throw new Hotel_Exception("Zona ou divisao Invalida");
            } catch (Hotel_Exception ex) {
                this.exception[contador] = ex;
                contador++;
                System.out.println("EXC: " + ex.getStr());
                return false;
            }
        }

        aux_pessoa = this.getPessoa(id);
        Movimentos[] aux_mov = null;
        aux_mov = aux_pessoa.getmovimentos();

        // System.out.println(
        //   "\n\n\n\n\n\n\nNome Pessoa: " + aux_pessoa.getNome());
        // System.out.println(
        //  "Zona Substring: " + id.substring(0, 2));
        switch (id.substring(
                0, 2)) {

            case "HO":

                if (aux_pessoa != null) {

                    try {

                        if (aux_mov[0] == null) {
                            try {
                                Divisao div = this.getDivisaobyZona(nome_zona, nome_divisao);
                                div.addPessoa(nome_zona, nome_divisao);
                                div.incContador();
                                System.out.println("COntador: " + div.getContador());
                                System.out.println("a");

                                return aux_pessoa.addMovimento(body_temp, data, nome_zona, nome_divisao);
                                /* if (!aux_mov[0].getZona().equals(nome_zona)) {
                    try {
                    throw new Zona_Exception(LocalDateTime.now(), aux_pessoa.getId(), nome_zona + " " + nome_divisao);
                    } catch (Zona_Exception ex) {
                    this.exception[contador] = ex;
                    contador++;
                    System.out.println("Acesso Indevido");*/
                                //csv write_csv = new csv();

                                //write_csv.relatorio_alertas(Tipo_Alerta.ACESSO, ex.getData(), ex.getId(), ex.getDivisao());
                            } catch (Infecao_Exception ex) {
                                csv write_csv = new csv();
                                write_csv.relatorio_alertas(Tipo_Alerta.P_INFECAO, ex.getData(), ex.getId(), ex.getDivisao(), ex.getZona());

                                System.out.println("Movimentos -> Infecao exp: " + ex.getId());
                                this.exception[contador] = ex;
                                contador++;
                            } catch (Pessoa_Exception ex) {
                                this.exception[contador] = ex;
                                System.out.println("Medicao REpetida");
                            } catch (Divisao_Exception ex) {
                                Logger.getLogger(Hotel1.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        if (!aux_mov[0].getZona().equals(nome_zona)) {
                            try {
                                throw new Zona_Exception(data, aux_pessoa.getId(), nome_divisao, nome_zona, "Acesso Invalido");
                            } catch (Zona_Exception ex) {
                                csv write_csv = new csv();
                                this.exception[contador] = ex;
                                contador++;
                                System.out.println("Acesso Invalido");
                                write_csv.relatorio_alertas(Tipo_Alerta.ACESSO, ex.getData(), ex.getId(), ex.getDivisao(), ex.getZona());
                            }

                        }

                        System.out.println("AUX -1 ZONA: " + aux_mov[0].getZona());
                        System.out.println("AUX -1 Divisao: " + aux_mov[0].getDivisao());

                        Divisao aux_div = this.getDivisaobyZona(nome_zona, nome_divisao);
                        aux_div.incContador();
                        Hospede ho = (Hospede) aux_pessoa;

                        aux_div = this.getDivisaobyZona(aux_mov[ho.getMov_cont() - 1].getZona(), aux_mov[ho.getMov_cont() - 1].getDivisao());
                        System.out.println("Nome Divisao: " + aux_div.getNomeDivisao());

                        aux_div.decContador();

                        aux_pessoa.addMovimento(body_temp, data, nome_zona, nome_divisao);

                    } catch (Infecao_Exception ex) {

                        csv write_csv = new csv();

                        System.out.println("Movimentos -> Infecao exp: " + ex.getId());
                        this.exception[contador] = ex;
                        contador++;
                        write_csv.relatorio_alertas(Tipo_Alerta.P_INFECAO, ex.getData(), ex.getId(), ex.getDivisao(), ex.getZona());

                    } catch (Pessoa_Exception ex) {
                        this.exception[contador] = ex;
                        System.out.println("MEDICAO REPETIDO");
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
                        if (aux_mov[0] == null) {
                            try {
                                Divisao div = this.getDivisaobyZona(nome_zona, nome_divisao);
                                div.addPessoa(nome_zona, nome_divisao);
                                div.incContador();
                                System.out.println("COntador: " + div.getContador());
                                System.out.println("B");

                                return aux_pessoa.addMovimento(body_temp, data, nome_zona, nome_divisao);
                                /* if (!aux_mov[0].getZona().equals(nome_zona)) {
                    try {
                    throw new Zona_Exception(LocalDateTime.now(), aux_pessoa.getId(), nome_zona + " " + nome_divisao);
                    } catch (Zona_Exception ex) {
                    this.exception[contador] = ex;
                    contador++;
                    System.out.println("Acesso Indevido");*/
                                //csv write_csv = new csv();

                                //write_csv.relatorio_alertas(Tipo_Alerta.ACESSO, ex.getData(), ex.getId(), ex.getDivisao());
                            } catch (Infecao_Exception ex) {
                                csv write_csv = new csv();
                                write_csv.relatorio_alertas(Tipo_Alerta.P_INFECAO, ex.getData(), ex.getId(), ex.getDivisao(), ex.getZona());

                                System.out.println("Movimentos -> Infecao exp: " + ex.getId());
                                this.exception[contador] = ex;
                                contador++;
                            } catch (Pessoa_Exception ex) {
                                this.exception[contador] = ex;
                                System.out.println("Medicao REpetida");
                            } catch (Divisao_Exception ex) {
                                Logger.getLogger(Hotel1.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        System.out.println("AUX -1 ZONA: " + aux_mov[0].getZona());
                        System.out.println("AUX -1 Divisao: " + aux_mov[0].getDivisao());

                        Divisao aux_div = this.getDivisaobyZona(nome_zona, nome_divisao);
                        aux_div.incContador();
                        Funcionario ho = (Funcionario) aux_pessoa;

                        aux_div = this.getDivisaobyZona(aux_mov[ho.getMov_cont() - 1].getZona(), aux_mov[ho.getMov_cont() - 1].getDivisao());
                        System.out.println("Nome Divisao: " + aux_div.getNomeDivisao());

                        aux_div.decContador();

                        aux_pessoa.addMovimento(body_temp, data, nome_zona, nome_divisao);

                    } catch (Infecao_Exception ex) {

                        csv write_csv = new csv();

                        System.out.println("Movimentos -> Infecao exp: " + ex.getId());
                        this.exception[contador] = ex;
                        contador++;
                        write_csv.relatorio_alertas(Tipo_Alerta.P_INFECAO, ex.getData(), ex.getId(), ex.getDivisao(), ex.getZona());

                    } catch (Pessoa_Exception ex) {
                        this.exception[contador] = ex;
                        System.out.println("MEDICAO REPETIDO");
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

    public Pessoa getPessoa(String id) {

        Pessoa aux_pessoa = null;

        if (id.contains("FU")) {
            for (Pessoa hospede1 : this.funcionarios) {
                if (hospede1 != null) {
                    if (hospede1.getId().equals(id)) {
                        aux_pessoa = hospede1;
                    }
                }
            }
        }

        if (id.contains("HO")) {
            for (Pessoa hospede1 : this.hospede) {
                if (hospede1 != null) {
                    if (hospede1.getId().equals(id)) {
                        aux_pessoa = hospede1;
                    }
                }
            }

        }

        return aux_pessoa;
    }

    public boolean relatorio_movimentos(String ID) {

        Pessoa aux = this.getPessoa(ID);

        System.out.println("ID RELA: " + aux.getId());

        if (aux == null) {
            try {
                throw new Pessoa_Exception("Pessoa nao Encontrada");
            } catch (Pessoa_Exception ex) {
                this.exception[this.get_excep_size()] = ex;
                return false;
            }
        }

        csv c = new csv();
        try {
            c.Monitorizacao_acesso(ID, aux.getmovimentos());
        } catch (Movimentos_Exception ex) {
            this.exception[this.get_excep_size()] = ex;
        }
        return true;
    }

}
