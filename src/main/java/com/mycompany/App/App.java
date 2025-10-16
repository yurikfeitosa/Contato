package com.mycompany.App;

import com.mycompany.View.ContatoView;
import com.mycompany.Control.ContatoControl;
import com.mycompany.Model.Contato;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        
        // 1. Inicializa a View 
        ContatoView view = new ContatoView();
        
        // 2. Inicializa o Controller
        ContatoControl control = new ContatoControl();

        Scanner scanner = new Scanner(System.in);

        boolean rodando = true;
        while (rodando) {
            System.out.println("\n=== Sistema de Contatos (PERSISTÊNCIA MYSQL) ===");
            System.out.println("1. Cadastrar contato");
            System.out.println("2. Listar todos os contatos");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = 0;
            try {
                String linha = scanner.nextLine();
                if (linha.isEmpty()) {
                    System.out.println("Opção inválida! Tente novamente.");
                    continue; 
                }
                opcao = Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                 System.out.println("Entrada inválida! Digite um número.");
                 continue;
            }

            switch (opcao) {
                case 1 -> {
                    // Obtém o novo Contato da View
                    Contato contato = view.criarContato();
                    // Salva o contato usando o Controller
                    control.salvar(contato);
                    // Mostra o contato recém-criado 
                    view.mostrarContato(contato);
                }
                case 2 -> {
               
                    view.mostrarTodosContatos(control.listarContatos());
                }
                case 3 -> {
                    System.out.println("Saindo do sistema...");
                    rodando = false;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }
}