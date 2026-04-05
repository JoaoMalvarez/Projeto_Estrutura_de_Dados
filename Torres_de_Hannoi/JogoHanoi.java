package Torres_de_Hannoi;

// joão (parte ja feita) 

import java.util.Comparator;

public class JogoHanoi {
    private Torre<Disco> t1, t2, t3;
    private int numDiscos;
    private Comparator<Disco> comp = Comparator.comparingInt(Disco::getTamanho);

    public JogoHanoi(int numDiscos) throws Exception {
        this.numDiscos = numDiscos;
        inicializarJogo();
    }

    private void inicializarJogo() throws Exception {
        t1 = new Torre<>(comp);
        t2 = new Torre<>(comp);
        t3 = new Torre<>(comp);
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

    public boolean verificarVitoria() {
        return t1.isEmpty() && t2.isEmpty() && t3.sizeElements() == numDiscos;
    }

    // Métodos de visualização (Getters para o Main usar)
    public Torre<Disco> getTorre(int n) { return selecionar(n); }
    public int getNumDiscos() { return numDiscos; }
}