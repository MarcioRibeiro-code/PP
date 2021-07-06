/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pessoa;

/**
 *
 * @author PC
 */
public class NoQuarto {

    private boolean flag = false;
    private final String Id;

    public NoQuarto(String Id) {
        this.Id = Id;
        this.flag = true;
    }

    public void noQuarto() {
        this.flag = true;
    }

    public void saiuQuarto() {
        this.flag = true;
    }

    public boolean isFlag() {
        return flag;
    }

    public String getId() {
        return Id;
    }

}
