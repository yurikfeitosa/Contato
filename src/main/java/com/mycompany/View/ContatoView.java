package com.mycompany.View;

import com.mycompany.Model.Contato;
import java.util.List;
import java.util.Scanner;

public class ContatoView {
    
    private Scanner scanner = new Scanner(System.in); 

    /**
     * @return Um novo objeto Contato.
     */
    public Contato criarContato() {
        System.out.println("\n--- Cadastro de Novo Contato ---");
        
        System.out.print("Digite o nome do contato: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o telefone: ");
        String telefone = scanner.nextLine();
        
      
        System.out.print("Digite o e-mail: ");
        String email = scanner.nextLine();
        
        System.out.print("Digite o Grupo (ex: Família, Trabalho): ");
        String grupoNome = scanner.nextLine();

        System.out.print("Digite observações: ");
        String observacoes = scanner.nextLine();

        System.out.print("Digite a data de aniversário (dd/mm/yyyy): ");
        String dataAniversario = scanner.nextLine();

        Contato novoContato = new Contato(nome, telefone, email, observacoes, dataAniversario);
        
        String tempObs = observacoes + " [GRUPO_TEMP:" + grupoNome + "]";
        novoContato.setObservacoes(tempObs);
        
        return novoContato; 
    }

    /**
     * @param contato O objeto Contato a ser exibido.
     */
    public void mostrarContato(Contato contato) {
        System.out.println("\nContato processado:");
        System.out.println(contato);
    }

    /**
     * @param contatos A lista de Contatos.
     */
   public void mostrarTodosContatos(List<Contato> contatos) {
        if (contatos.isEmpty()) {
            System.out.println("\nNenhum contato cadastrado no banco de dados.");
            return;
        }

        boolean voltandoAoMenuPrincipal = false;
        
        while (!voltandoAoMenuPrincipal) {
            
            System.out.println("\n--- Lista de Contatos ---");
           
            System.out.printf("| %-3s | %-25s | %-15s | %-30s |%n", "ID", "NOME", "TELEFONE", "E-MAIL");
            System.out.println("---------------------------------------------------------------------------------");

            // LISTAGEM DOS CONTATOS
            for (int i = 0; i < contatos.size(); i++) {
                Contato c = contatos.get(i);
                System.out.printf("| %-3d | %-25s | %-15s | %-30s |%n", 
                                  i + 1, 
                                  c.getNome(), 
                                  c.getTelefone(), 
                                  c.getEmail());
            }
            System.out.println("---------------------------------------------------------------------------------");
            
            // MENU DE OPÇÕES
            System.out.println("\n[DETALHES] Digite o NÚMERO do contato (1 a " + contatos.size() + ").");
            System.out.println("[SAIR] Digite 0 para voltar ao Menu Principal.");
            System.out.print("Escolha: ");
            
            String linha = scanner.nextLine();
            int escolha = 0;
            
            try {
                if (linha.trim().isEmpty()) {
                     System.out.println("Opção inválida. Tente novamente.");
                     continue; 
                }
                escolha = Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                continue; 
            }

            // AVALIA A ESCOLHA DO USUÁRIO
            if (escolha == 0) {
                voltandoAoMenuPrincipal = true; 
            } else if (escolha >= 1 && escolha <= contatos.size()) {
                mostrarDetalhesContato(contatos.get(escolha - 1));
            } else {
                System.out.println("Número de contato inválido. Tente novamente.");
            }
        }
    }
    
    /**
     * @param contato 
     */
    private void mostrarDetalhesContato(Contato contato) {
    System.out.println("\n=============================================");
    System.out.println("          DETALHES DO CONTATO #" + contato.getId());
    System.out.println("=============================================");
    System.out.println("Nome:             " + contato.getNome());
    System.out.println("Telefone:         " + contato.getTelefone());
    System.out.println("E-mail:           " + contato.getEmail());
    System.out.println("Grupo:            " + contato.getNomeGrupo()); 
    System.out.println("Aniversário:      " + contato.getDataAniversario());
    System.out.println("Observações:      " + contato.getObservacoes());
    System.out.println("=============================================");
    
    System.out.print("Pressione ENTER para voltar à lista...");
    scanner.nextLine(); 
}
}

