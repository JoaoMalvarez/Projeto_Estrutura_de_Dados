package Torres_de_Hannoi;

// joão (parte ja feita) 

import java.util.Comparator;

public class JogoHanoi {
    private Torre<Disco> t1, t2, t3;
    private DequePrint<Disco> print1, print2, print3, aux1, aux2, aux3;
    private int numDiscos; 
    private Comparator<Disco> comp = Comparator.comparingInt(Disco::getTamanho);

    public JogoHanoi(int numDiscos) {
        this.numDiscos = numDiscos;
    }

    private void inicializarJogo() throws Exception {
        t1 = new Torre<>(comp);
        t2 = new Torre<>(comp);
        t3 = new Torre<>(comp);
        print1 = new DequePrint<>(comp);
        print2 = new DequePrint<>(comp);
        print3 = new DequePrint<>(comp);
        aux1 = new DequePrint<>(comp);
        aux2 = new DequePrint<>(comp);
        aux3 = new DequePrint<>(comp);
        for (int i = numDiscos; i >= 1; i--) {
            t1.setupPush(new Disco("*".repeat(i * 2), i));
        }
    }

    public void reiniciar() throws Exception {
        inicializarJogo();
    }

    public void executarMovimento(int de, int para) throws Exception {
        Torre<Disco> origem = selecionar(de);
        Torre<Disco> destino = selecionar(para);

        if (origem.isEmpty()) throw new Exception("Torre de origem está vazia!");

        if (!destino.isEmpty() && origem.topo().getTamanho() > destino.topo().getTamanho()) { //tbm Evita que o Java tente comparar o tamanho de um disco que não existe
        throw new Exception("Movimento inválido: O disco de origem é maior que o disco no destino!");
        }
        //Se chegou aqui, o movimento é 100% seguro
        Disco d = origem.pop();
        destino.push(d);

    }

    private Torre<Disco> selecionar(int n) {
        return (n == 1) ? t1 : (n == 2) ? t2 : t3;
    }

    public boolean verificarVitoriaT3() {
        return t1.isEmpty() && t2.isEmpty() && t3.sizeElements() == numDiscos;
    }

    public void mostrarTorres() throws Exception {
        // 1. Move os discos das torres para os Deques de impressão
        // Usamos dequeue/enqueue para inverter a ordem se necessário, 
        // mas aqui o importante é tirar das torres para poder ler
        while (!t1.isEmpty() || !t2.isEmpty() || !t3.isEmpty()) {
            if (!t1.isEmpty()) print1.enqueueLeft(t1.pop());
            if (!t2.isEmpty()) print2.enqueueLeft(t2.pop());
            if (!t3.isEmpty()) print3.enqueueLeft(t3.pop());
        }

        System.out.println("\n--- ESTADO DAS TORRES ---\n");
        
        // Calculamos quantos espaços vazios existem no topo de cada torre
        int vazios1 = numDiscos - print1.size();
        int vazios2 = numDiscos - print2.size();
        int vazios3 = numDiscos - print3.size();

        // 2. Loop para cada linha (do topo para a base)
        for (int i = 0; i < numDiscos; i++) {
            
            // --- Processamento Torre 1 ---
            if (i < vazios1) {
                // Imprime o eixo central se não houver disco nessa altura
                System.out.print(" ".repeat(numDiscos) + "|" + " ".repeat(numDiscos));
            } else {
                Disco d = print1.dequeueRight(); // Tira APENAS UM disco
                int margem = (numDiscos * 2 - d.getForma().length()) / 2;
                System.out.print(" ".repeat(margem) + "[" + d.getForma() + "]" + " ".repeat(margem));
                aux1.enqueueRight(d); // Devolve para a torre original imediatamente
            }
            System.out.print("  "); // Espaço entre torres

            // --- Processamento Torre 2 ---
            if (i < vazios2) {
                System.out.print(" ".repeat(numDiscos) + "|" + " ".repeat(numDiscos));
            } else {
                Disco d = print2.dequeueRight();
                int margem = (numDiscos * 2 - d.getForma().length()) / 2;
                System.out.print(" ".repeat(margem) + "[" + d.getForma() + "]" + " ".repeat(margem));
                aux2.enqueueRight(d);
            }
            System.out.print("  ");

            // --- Processamento Torre 3 ---
            if (i < vazios3) {
                System.out.print(" ".repeat(numDiscos) + "|" + " ".repeat(numDiscos));
            } else {
                Disco d = print3.dequeueRight();
                int margem = (numDiscos * 2 - d.getForma().length()) / 2;
                System.out.print(" ".repeat(margem) + "[" + d.getForma() + "]" + " ".repeat(margem));
                aux3.enqueueRight(d);
            }

            System.out.println(); // Pula para a próxima linha (andar de baixo)
        }
        // Retorna os discos para as torres originais (já que print é só para mostrar)
        while (!aux1.disEmpty()) t1.push(aux1.dequeueRight());
        while (!aux2.disEmpty()) t2.push(aux2.dequeueRight());
        while (!aux3.disEmpty()) t3.push(aux3.dequeueRight());

        // Base das torres
        String base = "=".repeat(numDiscos * 2 + 2);
        System.out.println(base + "  " + base + "  " + base);
        System.out.println(" ".repeat(numDiscos) + "T1" + " ".repeat(numDiscos + 2) + 
                        " ".repeat(numDiscos) + "T2" + " ".repeat(numDiscos + 2) + 
                        " ".repeat(numDiscos) + "T3");
    }
}