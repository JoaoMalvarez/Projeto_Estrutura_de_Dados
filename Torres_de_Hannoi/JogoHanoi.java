package Torres_de_Hannoi;

// joão (parte ja feita) 

import java.util.Comparator;

public class JogoHanoi {
    private Torre<Disco> t1, t2, t3;
    private DequePrint<Disco> print1, print2, print3;
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
        Disco numO = origem.topo();
        Disco numD = destino.topo();

        if (!origem.isEmpty() && numO.getTamanho() > numD.getTamanho()) throw new Exception("Jogada impossível!");
        
        if (origem.isEmpty()) throw new Exception("Torre de origem está vazia!");
        
        Disco d = origem.pop();
        try { 
            destino.push(d); 
        } catch (Exception e) { 
            origem.setupPush(d); // Devolve o disco se o movimento for inválido
            throw e; 
        }
    }

    private Torre<Disco> selecionar(int n) {
        return (n == 1) ? t1 : (n == 2) ? t2 : t3;
    }

    public boolean verificarVitoriaT3() {
        return t1.isEmpty() && t2.isEmpty() && t3.sizeElements() == numDiscos;
    }

    public boolean verificarVitoriaT2() {
        return t1.isEmpty() && t2.sizeElements() == numDiscos && t3.isEmpty();
   }

    public void mostrarTorres() throws Exception {
        while (!t1.isEmpty() && !t2.isEmpty() && !t3.isEmpty()) {
            print1.enqueueLeft(t1.pop());
            print2.enqueueLeft(t2.pop());
            print3.enqueueLeft(t3.pop());
        }

        System.out.println("\n--- Torres ---\n");
        int espacosVazios1 = numDiscos - print1.size();
        int espacosVazios2 = numDiscos - print2.size();
        int espacosVazios3 = numDiscos - print3.size();

        for (int i = 0; i > numDiscos; i++) {
            if (i < espacosVazios1) {
                System.out.print(" ".repeat(numDiscos) + "|" + " ".repeat(numDiscos) + " ");
            } else {
                // Desempilha até chegar no disco desta linha
                while (print1.size() > (numDiscos - i)) t1.push(print1.dequeueLeft());
                Disco d1 = t1.topo();
                int margem = (numDiscos * 2 - d1.getForma().length()) / 2;
                System.out.print(" ".repeat(margem) + "[" + d1.getForma() + "]" + " ".repeat(margem) + " ");
            }

            if (i < espacosVazios2) {
                System.out.print(" ".repeat(numDiscos) + "|" + " ".repeat(numDiscos) + " ");
            } else {
                // Desempilha até chegar no disco desta linha
                while (print2.size() > (numDiscos - i)) t2.push(print2.dequeueLeft());
                Disco d2 = t2.topo();
                int margem = (numDiscos * 2 - d2.getForma().length()) / 2;
                System.out.print(" ".repeat(margem) + "[" + d2.getForma() + "]" + " ".repeat(margem) + " ");
            }

            if (i < espacosVazios3) {
                System.out.print(" ".repeat(numDiscos) + "|" + " ".repeat(numDiscos) + " ");
            } else {
                // Desempilha até chegar no disco desta linha
                while (print3.size() > (numDiscos - i)) t3.push(print3.dequeueLeft());
                Disco d3 = t3.topo();
                int margem = (numDiscos * 2 - d3.getForma().length()) / 2;
                System.out.print(" ".repeat(margem) + "[" + d3.getForma() + "]" + " ".repeat(margem) + " ");
            }
        }
        System.out.println("=".repeat(numDiscos * 8));
        System.out.println("Torre 1 " + " ".repeat(numDiscos) + "Torre 2 " + " ".repeat(numDiscos) + "Torre 3");
    }

    // Métodos de visualização (Getters para o Main usar)
    public Torre<Disco> getTorre(int n) { return selecionar(n); }
    public int getNumDiscos() { return numDiscos; }
}