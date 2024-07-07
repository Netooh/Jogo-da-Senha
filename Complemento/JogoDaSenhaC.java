package projeto;

import java.util.Random;
import java.util.Scanner;

public class JogoDaSenhaC {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Inicializa o Scanner para entrada de dados
        Random rd = new Random(); // Inicializa o gerador de números aleatórios
        
        // Arrays para armazenar a senha correta e a senha digitada pelo usuário
        int[] senhaCorreta = new int[4];
        int[] senhaUsuario = new int[4];
        
        int tentativas = 0; // Contador de tentativas
        int validacao = 0; // Variável de validação
        
        gerarSenha(senhaCorreta, rd); // Gera a senha correta
        startMenu(sc); // Exibe o menu inicial
        
        // Loop principal do jogo
        while (tentativas != 10 && validacao != -1) {
            tentativas++; // Incrementa o número de tentativas
            senhaDigitada(senhaUsuario, sc); // Solicita e armazena a senha digitada pelo usuário
            tentativa(tentativas, senhaUsuario); // Mostra a tentativa atual
            // Verifica se todos os dígitos estão corretos
            if (digitosCorretos(senhaCorreta, senhaUsuario, tentativas) == -1) {
                validacao = -1; // Define a validação como -1 para indicar que o jogo terminou
            }
            digitosDeslocados(senhaCorreta, senhaUsuario); // Calcula e mostra os dígitos deslocados
        }
        
        // Verifica o resultado do jogo e exibe o menu correspondente
        if (tentativas >= 10) {
            perdedorMenu(senhaCorreta); // Exibe menu de perdedor
        } else {
            vencedorMenu(senhaCorreta, tentativas); // Exibe menu de vencedor
        }
    }
    
    // Método para exibir o menu inicial
    public static void startMenu(Scanner sc) {
    	System.out.println("\033[1mTrabalho Pratico Jogo da Senha\033[0m"); // Título do trabalho prático
        System.out.println("Disciplina: Logica de Programacao e Algoritmos"); // Nome da disciplina
        System.out.println("Prof.: Christianne Orrico Dalforno"); // Nome da professora
        System.out.println("");
        System.out.println("Integrantes da Equipe:");
        System.out.println("Celso Neto"); // Nome do integrante da equipe
        System.out.println("");
        System.out.println("\033[1mJogo da Senha\033[0m"); // Título do jogo
        System.out.println("Digite os Numeros todos de uma vez, exemplo - 2 2 2 2"); // Instruções para entrada de senha
        System.out.println("ou Digite 1 numero de cada vez"); // Instruções alternativas para entrada de senha
        System.out.println("Digite qualquer tecla para Continuar..."); // Mensagem para continuar após ler as instruções
        sc.next(); // Aguarda a entrada do usuário para continuar
        for (int i = 1; i < 5; i++) {
            System.out.println(""); // Pula algumas linhas para limpar a tela
        }
    }
    
    // Método para exibir o menu de vencedor
    public static void vencedorMenu(int[] senhaCorreta, int tentativas) {
        System.out.println("");
        System.out.println("Voce Ganhou !! Numero de Tentativas: " + tentativas); // Mensagem de vitória com número de tentativas
        System.out.print("Senha Gerada: ");
        mostraSenha(senhaCorreta); // Mostra a senha correta gerada
    }
    
    // Método para exibir o menu de perdedor
    public static void perdedorMenu(int[] senhaCorreta) {
        System.out.println("");
        System.out.println("Voce Perdeu, o Computador Ganhou!!"); // Mensagem de derrota
        System.out.print("Senha Gerada: ");
        mostraSenha(senhaCorreta); // Mostra a senha correta gerada
    }
    
    // Método para gerar a senha correta
    public static void gerarSenha(int[] senhaCorreta, Random rd) {
        for (int i = 0; i < senhaCorreta.length; i++) {
            senhaCorreta[i] = rd.nextInt(1, 7); // Gera números aleatórios de 1 a 6 para cada posição da senha
        }
    }
    
    // Método para mostrar a senha digitada
    public static void mostraSenha(int[] senhaDigitada) {
        for (int i = 0; i < senhaDigitada.length; i++) {
            System.out.print(senhaDigitada[i] + " "); // Exibe cada número da senha separado por espaço
        }
        System.out.println(); // Pula uma linha no final
    }
    
    // Método para solicitar e armazenar a senha digitada pelo usuário
    public static int[] senhaDigitada(int[] senhaUsuario, Scanner sc) {
        System.out.println("");
        System.out.println("Digite a senha de 4 Digitos de 1 a 6"); // Solicitação para o usuário digitar a senha
        for (int i = 0; i < senhaUsuario.length; i++) {
            senhaUsuario[i] = sc.nextInt(); // Lê e armazena cada número da senha digitada pelo usuário
        }
        return senhaUsuario; // Retorna a senha digitada pelo usuário
    }
    
    // Método para mostrar a tentativa atual
    public static void tentativa(int tentativas, int[] senhaDigitada) {
        System.out.println("");
        System.out.print("\033[1mTentativa \033[0m" + tentativas + "\033[1m : \033[0m"); // Mostra o número da tentativa
        for (int i = 0; i < senhaDigitada.length; i++) {
            System.out.print(senhaDigitada[i] + " "); // Exibe cada número da tentativa separado por espaço
        }
    }
    
    // Método para verificar quantos dígitos estão corretos
    public static int digitosCorretos(int[] senhaCorreta, int[] senhaUsuario, int validacao) {
        int contador = 0;
        for (int i = 0; i < senhaCorreta.length; i++) {
            if (senhaCorreta[i] == senhaUsuario[i]) {
                contador++; // Incrementa o contador se o dígito na mesma posição estiver correto
            }
            if (contador == 4) {
                validacao = -1; // Define a validação como -1 se todos os dígitos estiverem corretos
            }
        }
        System.out.println("");
        System.out.print("Digitos Corretos: " + contador); // Exibe quantos dígitos estão corretos
        return validacao; // Retorna a validação atualizada
    }
    
    // Método para verificar quantos dígitos estão deslocados
    public static void digitosDeslocados(int[] senhaCorreta, int[] senhaUsuario) {
        int[] senhaCorretaClone = new int[senhaCorreta.length];
        int[] senhaUsuarioClone = new int[senhaUsuario.length];
        int contador = 0;
        boolean[] deslocados = new boolean[senhaCorretaClone.length];

        // Clona as senhas correta e digitada para manipulação
        for (int i = 0; i < senhaCorreta.length; i++) {
            senhaCorretaClone[i] = senhaCorreta[i];
            senhaUsuarioClone[i] = senhaUsuario[i];
        }

        // Marca os dígitos corretos para evitar contagem dupla
        for (int i = 0; i < senhaCorretaClone.length; i++) {
            if (senhaUsuarioClone[i] == senhaCorretaClone[i]) {
                senhaCorretaClone[i] = -1;
                senhaUsuarioClone[i] = -1;
            }
        }

        // Verifica e conta os dígitos deslocados
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

        System.out.println("\nDigitos deslocados: " + contador); // Exibe quantos dígitos estão deslocados
    }
}
