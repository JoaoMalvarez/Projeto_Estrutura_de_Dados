// arquivo: src/apl2/Node.java

// Integrantes do Grupo:
// Catarina Silva e Meirelles / RA: 10239324
// Heloisa Martelle / RA: 10738274
// João Pedro Mazzante Alvarez / RA: 10723837

package apl2;

// -- A classe Node (que pertence ao pacote apl2) deve conter os atributos que
// representam a nova versão dos dados de uma pessoa, conforme descrito no
// enunciado da atividade Apl2.
// -- A classe deve conter os construtores apropriados, assim como os métodos
// getters e setters.
// -- A classe também representa um nó que é usado na implementação da lista
// duplamente encadeada (classe DLinkedList).
// -- A classe deve sobrescrever (override) o método public String toString()
// {...}, retornando uma string com os valores dos atributos da classe.

public class Node {

	// TODO: Implementar a classe conforme o enunciado da atividade Apl2.
	private String id;
	private String nome;
	private float nota;
	private Node prev;
	private Node next;

	public Node() {
		this("", "", 9.99f, null, null);
	}

	public Node(String id, String nome, float nota, Node next, Node prev) {
		this.id = id;
		this.nome = nome;
		this.nota = nota;
		this.next = next;
		this.prev = prev;
	}

	// Construtor que recebe o ID original (int) e as partes inteira/decimal da nota,
	// já convertendo para a nova representação.
	public Node(int idOriginal, String nome, int inteiro, int decimal) {
		setId(idOriginal);
		this.nome = nome;
		setNota(inteiro, decimal);
		this.prev = null;
		this.next = null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setId(int idOriginal) {
		String formatted = String.format("%03d", idOriginal);
		this.id = "23.S1-" + formatted;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		if (nota < 0) {
			this.nota = 99.9f;
		} else {
			this.nota = nota;
		}
	}

	public void setNota(int inteiro, int decimo) {
		if (inteiro < 0 || decimo < 0) {
			this.nota = 99.9f;
		} else {
			this.nota = inteiro + (decimo / 10.0f);
		}
	}

	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	@Override
	public String toString() {
		String prevId = (prev != null) ? prev.getId() : "null";
		String nextId = (next != null) ? next.getId() : "null";
		return prevId + " <- (" + id + "; " + nome + "; " + nota + ") -> " + nextId;
	}
}