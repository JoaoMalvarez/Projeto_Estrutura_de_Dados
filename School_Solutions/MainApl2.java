//*************************** ATENÇÃO! ****************************
// O método main() deve ser alterado somente nos comentários TODO.
// Todas as outras instruções devem permanecer intactas e o código
// deve funcionar conforme descrito no enunciado da atividade.
//*************************** ATENÇÃO! ****************************
// arquivo: src/MainApl2.java

// Integrantes do Grupo:
// Catarina Silva e Meirelles / RA: 10239324
// Heloisa Martelle / RA: 10738274
// João Pedro Mazzante Alvarez / RA: 10723837

// Referências consultadas:
// TODO: Listar todas as referências consultadas para solucionar a atividade.

import apl2.DLinkedList;
import apl2.LinkedListOriginal;
import apl2.Node;
import apl2.Operation;

import apl2.Data;
import java.util.Scanner; // para ler a opção do menu

public class MainApl2 {

	public static void main(String[] args) {
		LinkedListOriginal list = new LinkedListOriginal();

		// Declaradas antes do while para ficarem acessíveis em todos os cases
		DLinkedList fixedList = new DLinkedList();
		DLinkedList filteredGradedList = new DLinkedList();
		DLinkedList filteredNonGradedList = new DLinkedList();
		DLinkedList aboveAverageList = new DLinkedList();
		float average = 0f;

// ==================================================================================================================================================================
// MENU
		System.out.printf(">>>>>>>>>> Sistema Conversor de Notas <<<<<<<<<<\n" +
			"1) Dados originais: lê arquivo dados.txt e apresenta todos os dados do Sistema de Notas Legado;\n" +
			"2) Dados convertidos: gera arquivo dados.csv e apresenta todos os dados do Sistema de Notas Atualizado;\n" +
			"3) Lista notas filtradas válidas: apresenta os dados somente das notas válidas filtradas;\n" +
			"4) Lista notas filtradas inválidas: apresenta os dados somente das notas filtradas pela \"ausência de notas\";\n" +
			"5) Média de notas válidas: apresenta a média das notas válidas filtradas;\n" +
			"6) Notas acima da média: apresenta os dados para as notas acima da média;\n" +
			"7) Lista mapeada para uma única string: apresenta a String contendo os dados mapeados;\n" +
			"8) Finaliza sistema.\n");
		Scanner scanner = new Scanner(System.in);
		int option = 0;
		while (option != 8) {
			System.out.print("Digite a opção desejada: ");
			option = scanner.nextInt();
			switch (option) {
				case 1:

// ==================================================================================================================================================================

			// TODO: Carregar o conteúdo do arquivo "dados.txt" e adicionar cada linha como um nó na LinkedListOriginal list.
				try {
					String fileContent = Data.loadTextFileToString("dados.txt");
					for (String line : fileContent.split("\\r?\\n|\\r")) {
						if (line.trim().isEmpty()) continue;
						String[] parts = line.split("#");
						int id = Integer.parseInt(parts[0].trim());
						String nome = parts[1].trim();
						int inteiro = Integer.parseInt(parts[2].trim());
						int decimo = Integer.parseInt(parts[3].trim());
						list.append(id, nome, inteiro, decimo);
					}
				} catch (Exception e) {
					System.err.println("Erro ao ler dados.txt: " + e.getMessage());
				}
// ==================================================================================================================================================================

					System.out.println(">>>>>>>>>> Dados originais (sistema legado) >>>>>>>>>>");
					System.out.println(list);
					System.out.println("<<<<<<<<<< Dados originais (sistema legado) <<<<<<<<<<\n");
				break;
			// =========================================================================================
				case 2:
					fixedList = Operation.map(list);
					System.out.println(">>>>>>>>>> Dados convertidos para a nova representação dos dados >>>>>>>>>>");
					System.out.println(fixedList);
					System.out.println("<<<<<<<<<< Dados convertidos para a nova representação dos dados <<<<<<<<<<\n");
				break;
			// =========================================================================================
				case 3:
					filteredGradedList = Operation.filterRemoveNonGraded(fixedList);
					System.out.println(">>>>>>>>>> Lista filtrada (somente notas válidas) >>>>>>>>>>");
					System.out.println(filteredGradedList);
					System.out.println("<<<<<<<<<< Lista filtrada (somente notas válidas) <<<<<<<<<<\n");
				break;
			// =========================================================================================
				case 4:
					filteredNonGradedList = Operation.filterRemoveGraded(fixedList);
					System.out.println(">>>>>>>>>> Lista filtrada (somente 'ausência de nota') >>>>>>>>>>");
					System.out.println(filteredNonGradedList);
					System.out.println("<<<<<<<<<< Lista filtrada (somente 'ausência de nota') <<<<<<<<<<\n");
				break;
			// =========================================================================================
				case 5:
					average = Operation.reduce(filteredGradedList);
					System.out.println(">>>>>>>>>> Média das notas válidas >>>>>>>>>>");
					System.out.println(average);
					System.out.println("<<<<<<<<<< Média das notas válidas <<<<<<<<<<\n");
				break;
			// =========================================================================================
				case 6:
					aboveAverageList = Operation.filterRemoveBelowAverage(filteredGradedList, average);
					System.out.println(">>>>>>>>>> Lista com notas acima da média >>>>>>>>>>");
					System.out.println(aboveAverageList);
					System.out.println("<<<<<<<<<< Lista com notas acima da média <<<<<<<<<<\n");
				break;
			// =========================================================================================
				case 7:
					String contents = Operation.mapToString(fixedList);
					System.out.println(">>>>>>>>>> Lista mapeada para uma única string >>>>>>>>>>");
					System.out.println(contents);
					System.out.println("<<<<<<<<<< Lista mapeada para uma única string <<<<<<<<<<\n");

// ==================================================================================================================================================================

			// TODO: Salvar o conteúdo da String contents em um arquivo chamado "dados.csv".
				try {
					Data.saveStringToTextFile("dados.csv", contents);
				} catch (Exception e) {
					System.err.println("Erro ao salvar dados.csv: " + e.getMessage());
				}
				break;

// ==================================================================================================================================================================

			case 8:
					System.out.println("Finalizando sistema...");
				break;

				default:
					System.out.println("Opção inválida. Digite um número entre 1 e 8.");
				break;
			}
		}
// ==================================================================================================================================================================
		// TESTES
//		Node test1 = fixedList.getNode("23.S1-999");
//		System.out.println(">>>>>>>>>> test1 >>>>>>>>>>\n" + test1 + "\n<<<<<<<<<< test1 <<<<<<<<<<\n");

//		Node test2 = fixedList.removeNode("23.S1-999");
//		System.out.println(">>>>>>>>>> test2 >>>>>>>>>>\n" + test2 + "\n<<<<<<<<<< test2 <<<<<<<<<<\n");

//		Node test3 = fixedList.getNode("23.S1-999");
//		System.out.println(">>>>>>>>>> test3 >>>>>>>>>>\n" + test3 + "\n<<<<<<<<<< test3 <<<<<<<<<<\n");

//		aboveAverageList.clear();
//		System.out.println(">>>>>>>>>> aboveAverageList.clear() >>>>>>>>>>\n" + aboveAverageList + "\n<<<<<<<<<< aboveAverageList.clear() <<<<<<<<<<\n");

//		DLinkedList testList = new DLinkedList();
//		try { testList.insert("ABC", "John Doe", 4.7f); } catch (Exception e) { System.err.println(e.getMessage()); }
//		try { testList.append("XYZ", "Jane Doe", 9.9f); } catch (Exception e) { System.err.println(e.getMessage()); }
//		try { testList.insert("321", "Test", 2.3f); } catch (Exception e) { System.err.println(e.getMessage()); }
//		try { testList.append("Nothing", "Yada yada yada", 99.9f); } catch (Exception e) { System.err.println(e.getMessage()); }

//		System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
//		System.out.println("testList.getHead(): " + testList.getHead());
//		System.out.println("testList.getTail(): " + testList.getTail());
//		System.out.println("testList.removeHead(): " + testList.removeHead());
//		System.out.println("testList.removeTail(): " + testList.removeTail() + '\n');
//		System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
//		System.out.println("testList.getHead(): " + testList.getHead());
//		System.out.println("testList.getTail(): " + testList.getTail());
//		System.out.println("testList.removeNode(\"ABC\"): " + testList.removeNode("ABC") + '\n');
//		System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
//		System.out.println("testList.getHead(): " + testList.getHead());
//		System.out.println("testList.getTail(): " + testList.getTail() + '\n');

//		try { testList.insert("qwerty", "QWERTY", 1.2f); } catch (Exception e) { System.err.println(e.getMessage()); }
//		try { testList.append("WASD", "wasd", 3.4f); } catch (Exception e) { System.err.println(e.getMessage()); }
//		try { testList.insert("ijkl", "IJKL", 5.6f); } catch (Exception e) { System.err.println(e.getMessage()); }
//		try { testList.append("1234", "Um Dois Tres Quatro", 7.8f); } catch (Exception e) { System.err.println(e.getMessage()); }

//		System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
//		testList.clear();
//		System.out.println(">>>>>>>>>> testList.clear() >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList.clear() <<<<<<<<<<\n");
		// =========================================================================================

	}
}