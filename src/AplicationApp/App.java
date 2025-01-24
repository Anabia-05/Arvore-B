package AplicationApp;
import java.util.Scanner;

import arvore.TreeB;

public class App {
    public static void main (String[] args) {

        TreeB<Integer> arvore = new TreeB<Integer>(5);
        int op,num;
        Integer[] valores = {110, 100, 90, 80, 60, 50, 40,130,120, 70, 150, 30, 200, 280, 500, 170,105,75, 190,145};

        for (Integer valor : valores) {
            arvore.inserir(valor);
        }


        Scanner input = new Scanner(System.in);
    
        do {
            exibirOpcoes();
            op = input.nextInt();
            input.nextLine();
            switch(op) {
                case 1: System.out.print("Insira valor: ");
                    num = input.nextInt();
                    arvore.inserir(num);
                    
                    break;
                case 2: arvore.mostrarMaior();
                    break;
                case 3: arvore.mostrarMenor();      
                    break;
                case 4: System.out.println("A A1ltura da arvore é: " + arvore.altura());         
                    break;
                case 5: System.out.print("Insira valor a ser buscado: ");
                    num = input.nextInt();
                    arvore.busca(num);       
                    break;
                case 6:arvore.porNivel();
                    break;
                case 7:arvore.emOrdem();
                    break;
                case 8: System.out.print("Remova valor: ");
                    num = input.nextInt();
                    arvore.remocao(num);
                    arvore.porNivel();
                    break;
                case 0:
                    System.out.println("Bye bye!");
                    break;                    
            }
        } while (op != 0);
        input.close();
    }
    public static void exibirOpcoes () {
        System.out.println("Opções");
        System.out.println("1 - Inserir valor");
        System.out.println("2 - Exibir Maior");
        System.out.println("3 - Exibir Menor");
        System.out.println("4 - Altura");
        System.out.println("5 - Busca");
        System.out.println("6 - Exibir por nivel");
        System.out.println("7 - Exibir em ordem");
        System.out.println("8 - Remover valor");
        System.out.println("0 - Encerrar programa");
        System.out.print("Informe a opção: ");
    }
}