package Torres_de_Hannoi;

import java.util.Comparator;

public class DequePrint <T> {
    private Deque<T> print;

    public DequePrint(Comparator<T> comparator) {
        this.print = new Deque<>();
    }

    public void enqueueLeft(T elemento) throws Exception {
        print.enqueueLeft(elemento);
    }

    public void enqueueRight(T elemento) throws Exception {
        print.enqueueRight(elemento);
    }

    public T dequeueLeft() throws Exception {
        return print.dequeueLeft();
    }

    public T dequeueRight() throws Exception {
        return print.dequeueRight();
    }

    public boolean disEmpty() {
        return print.isEmpty();
    }
    
    public boolean disFull() { 
        return print.isEmpty(); 
    }
    
    public int size() { 
        return print.size(); 
    }
    
    public T getLeft() throws Exception {
        return print.getLeft();
    }
    
    public T getRight() throws Exception { 
        return print.getRight(); 
    }

}
