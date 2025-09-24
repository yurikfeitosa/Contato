package view;

import model.Contato;
import java.util.List;
import java.util.Scanner;

public class ContatoView {
    private Scanner scanner = new Scanner(System.in);

    public Contato criarContato() {
        System.out.println("Digite o nome do contato:");
        String nome = scanner.nextLine();

        System.out.println("Digite o telefone:");
        String telefone = scanner.nextLine();

        System.out.println("Digite o grupo (ex: Família, Amigos, Trabalho):");
        String grupo = scanner.nextLine();

        System.out.println("Digite o endereço:");
        String endereco = scanner.nextLine();

        System.out.println("Digite observações:");
        String observacoes = scanner.nextLine();

        System.out.println("Digite a data de aniversário (dd/mm/yyyy):");
        String dataAniversario = scanner.nextLine();

        return new Contato(nome, telefone, grupo, endereco, observacoes, dataAniversario);
    }

    public void mostrarContato(Contato contato) {
        System.out.println("\nContato cadastrado:");
        System.out.println(contato);
    }

    public void mostrarTodosContatos(List<Contato> contatos) {
        if (contatos.isEmpty()) {
            System.out.println("Nenhum contato cadastrado.");
            return;
        }
        System.out.println("\nLista de contatos:");
        for (int i = 0; i < contatos.size(); i++) {
            System.out.println((i + 1) + ". " + contatos.get(i));
        }
    }
}
