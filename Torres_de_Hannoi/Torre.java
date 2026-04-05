<<<<<<< HEAD
<<<<<<<< HEAD:Torres_de_Hannoi/Torre.java
public class Torre <T> {
========
package Torres_de_Hannoi;

// joão (já foi feito) 

public class Pilha <T> {
>>>>>>>> d662bf4 (parte do joão concluida):Torres_de_Hannoi/Pilha.java
    // Atributos:
    private static final int TAM = 100;
    private int topoTorre;
    private T elementos[];
    // Construtores:

    public Torre(int tamanho) {
        this.elementos = (T[]) new Object[tamanho];
        this.topoTorre = -1; 
    }

    public Torre() {
        this(TAM);
    }
    // Métodos:

    public boolean isEmpty() {
        return this.topoTorre == -1;
    }

    public boolean isFull() {
        return this.topoTorre == elementos.length -1;
    }

    public void push(T e) throws Exception {
        if(!this.isFull()) {
            topoTorre++;
            this.elementos[topoTorre] = e;
        }       
        else {
            throw new Exception("Overflow: Torre está cheia"); //talvez mude
        }
    }

    public T pop() throws Exception{
        if(!this.isEmpty()) {
            T temp = this.elementos[topoTorre];
            topoTorre--;
            return temp;
        }
        else {
            throw new Exception("Underflow: Torre já está vazia"); //talvez mude
        }
    }

    public T topo() throws Exception {
        if (!this.isEmpty()) {
            return this.elementos[topoTorre];
        }
        else {
            throw new Exception("Underflow: A Torre está vazia");
        }
    }

    public int sizeElements() {
        return topoTorre + 1;
    }
}
=======
package Torres_de_Hannoi;

// joao (já foi feito) 

import java.util.Comparator;

public class Torre <T> {
    private Pilha<T> torre;
    private Comparator<T> comparator;

    public Torre(Comparator<T> comparator) {
        this.torre = new Pilha<>();
        this.comparator = comparator;
    }

    // Regra do jogo: só entra se for menor que o topo
    public void push(T elemento) throws Exception {
        if (torre.isEmpty() || comparator.compare(elemento, torre.topo()) < 0) {
            torre.push(elemento);
        } else {
            throw new Exception("Movimento Proibido: Disco maior sobre disco menor!");
        }
    }

    // Usado apenas para criar o jogo no início
    public void setupPush(T elemento) throws Exception {
        torre.push(elemento);
    }

    public T pop() throws Exception { 
        return torre.pop(); 
    }
    
    public boolean isEmpty() { 
        return torre.isEmpty(); 
    }
    
    public int sizeElements() { 
        return torre.sizeElements(); 
    }
    
    public T topo() throws Exception { 
        return torre.topo(); 
    }
}
>>>>>>> d662bf4 (parte do joão concluida)
