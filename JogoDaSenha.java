package projeto;

import java.util.Random;
import java.util.Scanner;

public class JogoDaSenha {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();
        
        int[] senhaCorreta = new int[4];
        int[] senhaUsuario = new int[4];
        
        int tentativas = 0;
        int validacao = 0;
        
        gerarSenha(senhaCorreta, rd);
        startMenu(sc);
        //mostraSenha(senhaCorreta);
        while (tentativas != 10 && validacao != -1) {
            tentativas++;
            senhaDigitada(senhaUsuario, sc);
            tentativa(tentativas, senhaUsuario);
            if (digitosCorretos(senhaCorreta, senhaUsuario, tentativas) == -1) {
                validacao = -1;
            }
            digitosDeslocados(senhaCorreta, senhaUsuario);
        }
        
        if (tentativas >= 10) {
            perdedorMenu(senhaCorreta);
        } else {
            vencedorMenu(senhaCorreta, tentativas);
        }
    }
    
    public static void startMenu(Scanner sc) {
    	System.out.println("\033[1mTrabalho Pratico Jogo da Senha\033[0m");
        System.out.println("Disciplina: Logica de Programacao e Algoritmos");
        System.out.println("Prof.: Christianne Orrico Dalforno");
        System.out.println("");
        System.out.println("Integrantes da Equipe:");
        System.out.println("Celso Neto");
        System.out.println("");
        System.out.println("\033[1mJogo da Senha\033[0m");
        System.out.println("Digite os Numeros todos de uma vez, exemplo - 2 2 2 2");
        System.out.println("ou Digite 1 numero de cada vez");
        System.out.println("Digite qualquer tecla para Continuar...");
        sc.next();
        for (int i = 1; i < 5; i++) {
            System.out.println("");
        }
    }
    
    public static void vencedorMenu(int[] senhaCorreta, int tentativas) {
        System.out.println("");
        System.out.println("Voce Ganhou !! Numero de Tentativas: " + tentativas);
        System.out.print("Senha Gerada: ");
        mostraSenha(senhaCorreta);
    }
    
    public static void perdedorMenu(int[] senhaCorreta) {
        System.out.println("");
        System.out.println("Voce Perdeu, o Computador Ganhou!!");
        System.out.print("Senha Gerada: ");
        mostraSenha(senhaCorreta);
    }
    
    public static void gerarSenha(int[] senhaCorreta, Random rd) {
        for (int i = 0; i < senhaCorreta.length; i++) {
            senhaCorreta[i] = rd.nextInt(1, 7);
        }
    }
    
    public static void mostraSenha(int[] senhaDigitada) {
        for (int i = 0; i < senhaDigitada.length; i++) {
            System.out.print(senhaDigitada[i] + " ");
        }
        System.out.println();
    }
    
    public static int[] senhaDigitada(int[] senhaUsuario, Scanner sc) {
        System.out.println("");
        System.out.println("Digite a senha de 4 Digitos de 1 a 6");
        for (int i = 0; i < senhaUsuario.length; i++) {
            senhaUsuario[i] = sc.nextInt();
        }
        return senhaUsuario;
    }
    
    public static void tentativa(int tentativas, int[] senhaDigitada) {
        System.out.println("");
        System.out.print("\033[1mTentativa \033[0m" + tentativas + "\033[1m : \033[0m");
        for (int i = 0; i < senhaDigitada.length; i++) {
            System.out.print(senhaDigitada[i] + " ");
        }
    }
    
    public static int digitosCorretos(int[] senhaCorreta, int[] senhaUsuario, int validacao) {
        int contador = 0;
        for (int i = 0; i < senhaCorreta.length; i++) {
            if (senhaCorreta[i] == senhaUsuario[i]) {
                contador++;
            }
            if (contador == 4) {
                validacao = -1;
            }
        }
        System.out.println("");
        System.out.print("Digitos Corretos: " + contador);
        return validacao;
    }
    
    public static void digitosDeslocados(int[] senhaCorreta, int[] senhaUsuario) {
        int[] senhaCorretaClone = new int[senhaCorreta.length];
        int[] senhaUsuarioClone = new int[senhaUsuario.length];
        int contador = 0;
        boolean[] deslocados = new boolean[senhaCorretaClone.length];

        for (int i = 0; i < senhaCorreta.length; i++) {
            senhaCorretaClone[i] = senhaCorreta[i];
            senhaUsuarioClone[i] = senhaUsuario[i];
        }

        for (int i = 0; i < senhaCorretaClone.length; i++) {
            if (senhaUsuarioClone[i] == senhaCorretaClone[i]) {
                senhaCorretaClone[i] = -1;
                senhaUsuarioClone[i] = -1;
            }
        }

        for (int i = 0; i < senhaUsuarioClone.length; i++) {
            if (senhaUsuarioClone[i] != -1) {
                for (int j = 0; j < senhaCorretaClone.length; j++) {
                    if (senhaUsuarioClone[i] == senhaCorretaClone[j] && !deslocados[j]) {
                        contador++;
                        senhaCorretaClone[j] = -1;
                        deslocados[j] = true;
                    }
                }
            }
        }

        System.out.println("\nDigitos deslocados: " + contador);
    }
}
