// arquivo: src/apl2/DLinkedList.java

// Integrantes do Grupo:
// Catarina Silva e Meirelles / RA: 10239324
// Heloisa Martelle / RA: 10738274
// João Pedro Mazzante Alvarez / RA: 10723837

package apl2;

// -- A classe DLinkedList (que pertence ao pacote apl2) deve implementar uma
// lista duplamente encadeada. Os nós dessa lista são do tipo [da classe] Node.
// -- A classe deve possuir dois nós especiais, head e tail, que são
// referências para o primeiro e último nó da lista, respectivamente.
// -- A classe deve possuir um contador de quantos nós existem na lista.
// -- A classe deve sobrescrever (override) o método public String toString()
// {...}, retornando uma string com o conteúdo da lista.
// -- A classe deve implementar as operações a seguir, respeitando o
// comportamento descrito em cada operação.

public class DLinkedList {

	private Node head;
	private Node tail;
	private int count;

// =======================================================================================

// OPERAÇÃO:		Método construtor
// COMPORTAMENTO:	Cria uma lista vazia.
	public DLinkedList() {
		head = tail = null;
		count = 0;
	}

// =======================================================================================

// OPERAÇÃO:		insert(<dados da pessoa>)
// COMPORTAMENTO:	Aloca um Node que contém os <dados da pessoa> e insere o
//					novo nó no início da lista.
	public void insert(String id, String nome, float nota) {
		Node aux = new Node(id, nome, nota, null, null);
		if (isEmpty()) {
			head = tail = aux;
		} else {
			aux.setNext(head);
			head.setPrev(aux);
			head = aux;
		}
		count++;
	}

// =======================================================================================

// OPERAÇÃO:		append(<dados da pessoa>)
// COMPORTAMENTO:	Aloca um Node que contém os <dados da pessoa> e insere o
//					novo nó no final da lista.
	public void append(String id, String nome, float nota) {
		Node aux = new Node(id, nome, nota, null, null);
		if (isEmpty()) {
			head = tail = aux;
		} else {
			tail.setNext(aux);
			aux.setPrev(tail);
			tail = aux;
		}
		count++;
	}

// =======================================================================================

// OPERAÇÃO: 		removeHead()
// COMPORTAMENTO:	Remove o nó do início da lista e retorna a referência do
//					nó removido.
//					Ou retorna null caso a lista esteja vazia.
	public Node removeHead() {
		if (isEmpty()) return null;
		else {
			Node pAux = head;
			if (count == 1) {
				head = tail = null;
			} else {
				head = head.getNext();
				head.setPrev(null);
			}
			pAux.setNext(null);
			pAux.setPrev(null);
			count--;
			return pAux;
		}
	}

// =======================================================================================

// OPERAÇÃO:		removeTail()
// COMPORTAMENTO:	Remove o nó do final da lista e retorna a referência do
//					nó removido.
//					Ou retorna null caso a lista esteja vazia.
	public Node removeTail() {
		if (isEmpty()) return null;
		else {
			Node pAux = tail;
			if (count == 1) {
				head = tail = null;
			} else {
				tail = tail.getPrev();
				tail.setNext(null);
			}
			pAux.setNext(null);
			pAux.setPrev(null);
			count--;
			return pAux;
		}
	}

// =======================================================================================

// OPERAÇÃO:		removeNode(<ID da pessoa>)
// COMPORTAMENTO:	Remove o nó que contém o <ID da pessoa> da lista e retorna
//					a referência do nó removido.
//					Ou retorna null caso não exista um nó com <ID da pessoa>.
	public Node removeNode(String id) {
		if (isEmpty()) return null;
		Node pAnda = head;
		while (pAnda != null) {
			if (pAnda.getId().equals(id)) {
				if (pAnda == head) return removeHead();
				if (pAnda == tail) return removeTail();
				pAnda.getPrev().setNext(pAnda.getNext());
				pAnda.getNext().setPrev(pAnda.getPrev());
				pAnda.setNext(null);
				pAnda.setPrev(null);
				count--;
				return pAnda;
			}
			pAnda = pAnda.getNext();
		}
		return null;
	}

// =======================================================================================

// OPERAÇÃO:		getHead()
// COMPORTAMENTO:	Retorna uma referência para o nó do início da lista.
//					Ou retorna null caso a lista esteja vazia.
	public Node getHead() {
		return head;
	}

// =======================================================================================

// OPERAÇÃO:		getTail()
// COMPORTAMENTO:	Retorna uma referência para o nó do final da lista.
//					Ou retorna null caso a lista esteja vazia.
	public Node getTail() {
		return tail;
	}

// =======================================================================================

// OPERAÇÃO:		getNode(<ID da pessoa>)
// COMPORTAMENTO:	Retorna uma referência para o nó que contém o <ID da pessoa>
//					da lista.
//					Ou retorna null caso não exista um nó com <ID da pessoa>.
	public Node getNode(String id) {
		Node pAnda = head;
		while (pAnda != null) {
			if (pAnda.getId().equals(id)) {
				return pAnda;
			}
			pAnda = pAnda.getNext();
		}
		return null;
	}

// =======================================================================================

// OPERAÇÃO:		count()
// COMPORTAMENTO:	Retorna a quantidade de nós da lista.
	public int count() {
		return count;
	}

// =======================================================================================

// OPERAÇÃO:		isEmpty()
// COMPORTAMENTO:	Retorna true se a lista estiver vazia ou false, caso contrário.
	public boolean isEmpty() {
		return head == null;
	}

// =======================================================================================

// OPERAÇÃO:		clear()
// COMPORTAMENTO:	Esvazia a lista, liberando a memória de todos os nós da lista.
	public void clear() {
		while (!isEmpty()) {
			removeHead();
		}
	}

// =======================================================================================

// OPERAÇÃO:		toString()
// COMPORTAMENTO:	Retorna uma string com o conteúdo da lista (caso queira, use o
//					exemplo do método toString() da classe LinkedListOriginal).
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("(" + count + ") \n");

		Node node = head;
		while (node != null) {
			String prevId = (node.getPrev() != null) ? node.getPrev().getId() : "null";
			String nextId = (node.getNext() != null) ? node.getNext().getId() : "null";
			sb.append(prevId)
			  .append(" <- (")
			  .append(node.getId())
			  .append("; ")
			  .append(node.getNome())
			  .append("; ")
			  .append(node.getNota())
			  .append(") -> ")
			  .append(nextId)
			  .append("\n");
			node = node.getNext();
		}

		return sb.toString();
	}

}