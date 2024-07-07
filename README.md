**Trabalho Prático de Lógica de Programação e Algoritmos**

Disciplina: Lógica de Programação e Algoritmos  
Professora: Christianne Orrico Dalforno

**Descrição do Projeto: Jogo da Senha**

O projeto consiste na implementação do jogo da senha, conhecido como Mastermind, criado por Mordechai Meirowitz. Neste jogo de tabuleiro adaptado para interface de texto, uma senha de quatro dígitos, representados por números de 1 a 6, é gerada aleatoriamente pelo programa. O jogador desafiado tem 10 tentativas para descobrir a senha definida pelo jogador desafiador.

A cada tentativa, o programa fornece feedback ao jogador informando quantos dígitos estão corretos e na posição correta, além de quantos dígitos estão corretos mas na posição errada. O jogo continua até que o jogador acerte a senha ou até que se esgotem as 10 tentativas, momento em que o computador é declarado vencedor.

**Implementação Detalhada:**

- A senha é gerada aleatoriamente e armazenada em um vetor de quatro posições.
- O jogador faz tentativas, também armazenadas em um vetor de quatro posições para cada tentativa.
- Após cada tentativa, o programa calcula e exibe a quantidade de números corretos em posição correta e a quantidade de números corretos em posição equivocada.
- Exemplo de interação:
  - Senha correta: 1 1 3 5
  - Tentativa 1: 1 1 1 1
    - Dígitos corretos: 2
    - Dígitos deslocados: 0
  - Tentativa 2: 1 1 2 3
    - Dígitos corretos: 2
    - Dígitos deslocados: 1
  - E assim sucessivamente até a conclusão do jogo.

Este projeto marca o primeiro trabalho prático da faculdade, podendo conter eventuais imperfeições e erros. 

**Tipos de Arquivos:**
- jogoDaSenha - Implementação principal do projeto
