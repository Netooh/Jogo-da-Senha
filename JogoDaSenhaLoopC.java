package projeto;

import java.util.Random;
import java.util.Scanner;

public class JogoDaSenhaLoopC {

    public static void main(String[] args) {
        // Inicialização do Scanner para entrada de dados e do Random para geração de números aleatórios
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();

        // Variáveis para contar o número de vitórias e derrotas
        int numeroVitorias = 0;
        int numeroDerrotas = 0;

        // Variável para controlar se o jogador quer jogar novamente
        boolean jogarNovamente = true;

        // Exibição do menu inicial
        startMenu(sc);

        // Loop principal do jogo
        while (jogarNovamente) {
            // Arrays para armazenar a senha correta gerada e a senha inserida pelo usuário
            int[] senhaCorreta = new int[4];
            int[] senhaUsuario = new int[4];

            // Variáveis para controlar o número de tentativas e a validação do jogo
            int tentativas = 0;
            int validacao = 0;

            // Loop para permitir até 10 tentativas ou até que o usuário acerte a senha
            while (tentativas != 10 && validacao != -1) {
                // Gera uma nova senha correta
                gerarSenha(senhaCorreta, rd);

                // Mostra a senha gerada (para fins de teste, normalmente não seria mostrada ao jogador)
                mostraSenha(senhaCorreta);

                // Reinicia o contador de tentativas e validação para cada nova tentativa
                tentativas = 0;
                validacao = 0;

                // Loop para cada tentativa do jogador
                while (tentativas < 10 && validacao != -1) {
                    tentativas++;

                    // Solicita que o jogador insira a senha
                    senhaDigitada(senhaUsuario, sc);

                    // Mostra a tentativa atual do jogador
                    tentativa(tentativas, senhaUsuario);

                    // Verifica se o jogador acertou todos os dígitos
                    if (digitosCorretos(senhaCorreta, senhaUsuario, tentativas) == -1) {
                        validacao = -1;
                    }

                    // Mostra quantos dígitos estão deslocados na senha
                    digitosDeslocados(senhaCorreta, senhaUsuario);
                }

                // Se o número de tentativas exceder 10, o jogador perde
                if (tentativas >= 10) {
                    perdedorMenu(senhaCorreta);
                    numeroDerrotas++;
                } else {
                    // Caso contrário, o jogador vence
                    vencedorMenu(senhaCorreta, tentativas);
                    numeroVitorias++;
                }

                // Pergunta se o jogador deseja jogar novamente
                System.out.println("\nDeseja jogar novamente? (S/N)");
                String resposta = sc.next().toUpperCase();
                if (!resposta.equals("S")) {
                    jogarNovamente = false;
                }
            }
        }

        // Ao sair do loop principal, exibe o número de vitórias e derrotas
        System.out.println("\nNúmero de vitórias: " + numeroVitorias);
        System.out.println("Número de derrotas: " + numeroDerrotas);
        System.out.println("Obrigado por jogar!");
    }

    // Método para exibir o menu inicial
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
            System.out.println(""); // Limpa a tela ao iniciar o jogo
        }
    }

    // Método para exibir o menu de vitória
    public static void vencedorMenu(int[] senhaCorreta, int tentativas) {
        System.out.println("");
        System.out.println("Voce Ganhou !! Numero de Tentativas: " + tentativas);
        System.out.print("Senha Gerada: ");
        mostraSenha(senhaCorreta); // Mostra a senha correta após a vitória
    }

    // Método para exibir o menu de derrota
    public static void perdedorMenu(int[] senhaCorreta) {
        System.out.println("");
        System.out.println("Voce Perdeu, o Computador Ganhou!!");
        System.out.print("Senha Gerada: ");
        mostraSenha(senhaCorreta); // Mostra a senha correta após a derrota
    }

    // Método para gerar a senha correta aleatoriamente
    public static void gerarSenha(int[] senhaCorreta, Random rd) {
        for (int i = 0; i < senhaCorreta.length; i++) {
            senhaCorreta[i] = rd.nextInt(6) + 1; // Gera números aleatórios de 1 a 6 para cada posição da senha
        }
    }

    // Método para mostrar a senha (usado para depuração, normalmente não seria mostrado ao jogador)
    public static void mostraSenha(int[] senhaDigitada) {
        for (int i = 0; i < senhaDigitada.length; i++) {
            System.out.print(senhaDigitada[i] + " "); // Exibe cada dígito da senha
        }
        System.out.println();
    }

    // Método para solicitar que o jogador digite a senha
    public static int[] senhaDigitada(int[] senhaUsuario, Scanner sc) {
        System.out.println("");
        System.out.println("Digite a senha de 4 Digitos de 1 a 6");
        for (int i = 0; i < senhaUsuario.length; i++) {
            senhaUsuario[i] = sc.nextInt(); // Lê os dígitos da senha digitada pelo jogador
        }
        return senhaUsuario;
    }

    // Método para exibir a tentativa atual do jogador
    public static void tentativa(int tentativas, int[] senhaDigitada) {
        System.out.println("");
        System.out.print("\033[1mTentativa \033[0m" + tentativas + "\033[1m : \033[0m");
        for (int i = 0; i < senhaDigitada.length; i++) {
            System.out.print(senhaDigitada[i] + " "); // Exibe a tentativa atual do jogador
        }
    }

    // Método para verificar quantos dígitos estão corretos na posição correta
    public static int digitosCorretos(int[] senhaCorreta, int[] senhaUsuario, int validacao) {
        int contador = 0;
        for (int i = 0; i < senhaCorreta.length; i++) {
            if (senhaCorreta[i] == senhaUsuario[i]) {
                contador++; // Conta quantos dígitos estão corretos na posição correta
            }
            if (contador == 4) {
                validacao = -1; // Se todos os dígitos estiverem corretos, define validação como -1 (jogador vence)
            }
        }
        System.out.println("");
        System.out.print("Digitos Corretos: " + contador); // Exibe quantos dígitos estão corretos
        return validacao;
    }

    // Método para verificar quantos dígitos estão corretos, mas na posição errada
    public static void digitosDeslocados(int[] senhaCorreta, int[] senhaUsuario) {
        int[] senhaCorretaClone = new int[senhaCorreta.length];
        int[] senhaUsuarioClone = new int[senhaUsuario.length];
        int contador = 0;
        boolean[] deslocados = new boolean[senhaCorretaClone.length];

        // Clona as senhas correta e do usuário para não alterar as originais
        for (int i = 0; i < senhaCorreta.length; i++) {
            senhaCorretaClone[i] = senhaCorreta[i];
            senhaUsuarioClone[i] = senhaUsuario[i];
        }

        // Marca os dígitos corretos na posição correta como -1 para ignorá-los
        for (int i = 0; i < senhaCorretaClone.length; i++) {
            if (senhaUsuarioClone[i] == senhaCorretaClone[i]) {
                senhaCorretaClone[i] = -1;
                senhaUsuarioClone[i] = -1;
            }
        }

        // Conta quantos dígitos estão corretos, mas na posição errada
        for (int i = 0; i < senhaUsuarioClone.length; i++) {
            if (senhaUsuarioClone[i] != -1

) {
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