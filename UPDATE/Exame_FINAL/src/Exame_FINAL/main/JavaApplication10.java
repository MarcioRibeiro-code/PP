/* 
* Nome: Marcio Samuel Santos Ribeiro
* Número: 8200408
* Turma: LEI2T4
* 
* Nome: Hugo Miguel Gomes Alves Ribeiro
* Número: 8200441
* Turma: LEI2T3
*/
package Exame_FINAL.main;

import Exame_FINAL.Classes.Manager;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class JavaApplication10 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int key = -1;
        Manager mn1 = new Manager();

        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("\n--MENU PRINCIPAL--");
            System.out.println("1 - Gerir Construction Site");
            System.out.println("2 - Gerir Equipas");
            System.out.println("3 - Gerir Funcionarios");
            System.out.println("4 - Gerir Equipamentos");
            System.out.println("5 - Gerir Eventos");
            System.out.println("0 - Sair");
            System.out.print("opcao: ");
            key = scan.nextInt();

            switch (key) {
                case 1:
                    mn1.Manage_ConstructionSite();
                    break;
                case 2:
                    mn1.manage_Team();
                    break;
                case 3:
                    mn1.Manage_Employee();
                    break;
                case 4:
                    mn1.ManageEquipment();
                    break;
                case 5: {

                    mn1.Manage_Event();

                }
                break;

            }
        } while (key != 0);

    }

}
