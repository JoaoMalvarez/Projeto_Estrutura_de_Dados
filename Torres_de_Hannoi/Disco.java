package Torres_de_Hannoi;

// helô

public class Disco {
    private String forma; // define a forma do disco
    private int tamanho; //define o tamanho do disco

    public Disco(String forma, int tamanho) { //construtor: cria o objeto (disco) com os valores inicias, ja com o tamanho
        this.forma = forma; //define a forma do disco 
        this.tamanho = tamanho; //define o tamanho do disco na criação
    }

    public String getForma() {
        return forma;
    }

    // Getter
    public int getTamanho() { //pega o valor do disco e mostra
        return tamanho;
    }

    // Método toString (opcional, mas ajuda no print)
    public String toString() { //coverte o numero em texto, ou seja, mostra o valor final
        return String.valueOf(tamanho);
    }
    
}
