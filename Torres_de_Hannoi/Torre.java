public class Torre <T> {
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