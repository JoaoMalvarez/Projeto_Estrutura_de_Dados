package Torres_de_Hannoi;

import java.util.Scanner;

public class Main {

    // Parte do Menu e Inicialização
    public static void main(String[] args) { //funçao principal do programa com os parametros

        Scanner scanner = new Scanner(System.in);

        int opcao; //guarda a escolha do menu
        int qntdDiscos; //guarda a quantidade de discos que o usuario escolheu

        // Entrada inicial
        System.out.println("--- TORRE DE HANOI ---");
        System.out.print("Informe a quantidade de discos: "); //pede a quantidade de discos para o usuario
        qntdDiscos = scanner.nextInt(); //le o numero que o usuario digitou e guarda ele em qntdDiscos

        // ver como implementar essa parte do jogo!!!
        JogoHanoi jogo = new JogoHanoi(qntdDiscos); 
        try {
            jogo.reiniciar();
        } catch (Exception e) {
            System.out.println("Erro ao iniciar o jogo: " + e.getMessage());
            scanner.close();
            return; // Encerra o programa se não for possível iniciar o jogo
        }

        do {
            // Menu
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Mover disco");
            System.out.println("2 - Mostrar torres");
            System.out.println("3 - Reiniciar jogo");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    int origem, destino; //guarda a escolha da torre de origem (a que os discos vao ser tirados) e a torre de destino (o que os discos vão ir)

                    System.out.print("Informe a torre de origem (1, 2 ou 3): "); //usuario digita qual a torre ele quer tirar os discos
                    origem = scanner.nextInt();

                    System.out.print("Informe a torre de destino (1, 2 ou 3): "); //usuario digita qual a torre ele quer que va os discos
                    destino = scanner.nextInt();
                    try { 
                        jogo.executarMovimento(origem, destino); //move os discos de uma torre para outra
                        if (jogo.verificarVitoriaT3() == true) {
                             System.out.println("Você ganhou!");
                         }
                         break;
                    
                    } catch (Exception e) {
                        System.out.println("Erro ao mover disco: " + e.getMessage());
                    }
                    break;
                    
                    //ADICIONAR A CONDIÇÃO DE SE É POSSIVEL QUE OS DISCOS DE UMA TORRE SEJAM TIRADOS E COLOCADOS EM OUTRA, DE ACORDO COM A PG 3 DO PROJETO

                case 2:
                    try {
                        jogo.mostrarTorres(); //exibe as torres na tela
                    } catch (Exception e) {
                        System.out.println("Erro ao mostrar torres: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        jogo.reiniciar(); //reinicia o jogo, ou seja, volta os discos para a torre 1
                        System.out.println("Jogo reiniciado!");
                    } catch (Exception e) {
                        System.out.println("Erro ao reiniciar o jogo: " + e.getMessage());
                    }   
                    break;

                case 4:
                    System.out.println("Saindo..."); //encerra o programa
                    break;

                default:
                    System.out.println("Opção inválida!"); //caso o usuario digite algo errado, isso aparece; tipo um else em if else -> se o usuario digita algo que nao e compativel com os outros cases, ele pula para o default
            }

        } while (opcao != 4); //o menu se repete ate o usuario escolher a opcao 4, que no caso é sair do jogo

        scanner.close(); 
    }
}
