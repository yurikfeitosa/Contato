package com.mycompany.App;

import com.mycompany.View.ContatoView;
import com.mycompany.Control.ContatoControl;
import com.mycompany.Model.Contato;
import java.util.Scanner;
import java.util.List; 

public class App {
    public static void main(String[] args) {
        
        
        ContatoView view = new ContatoView();
        
        
        ContatoControl control = new ContatoControl();

        Scanner scanner = new Scanner(System.in);

        boolean rodando = true;
        while (rodando) {
            System.out.println("\n=== Sistema de Contatos ===");
            System.out.println("1. Cadastrar novo contato");
            System.out.println("2. Listar todos os contatos");
            System.out.println("3. Atualizar contato"); 
            System.out.println("4. Excluir contato"); 
            System.out.println("5. Sair"); 
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
                    Contato contato = view.criarContato();
                    control.salvar(contato);
                    view.mostrarContato(contato);
                }
                case 2 -> {
                    List<Contato> contatos = control.listarContatos();
                    view.mostrarTodosContatos(contatos);
                }
                case 3 -> {
                    List<Contato> contatos = control.listarContatos();
                    if (contatos.isEmpty()) break;
                    
                    int idParaAtualizar = view.solicitarID("atualizar");
                    if (idParaAtualizar == -1) break;

                    Contato contatoAtualizado = view.criarContatoParaAtualizacao(idParaAtualizar);
                    
                    control.atualizarContato(contatoAtualizado);
                }
                case 4 -> {
                    List<Contato> contatos = control.listarContatos();
                    if (contatos.isEmpty()) break;
                    
                    int idParaDeletar = view.solicitarID("deletar");
                    if (idParaDeletar == -1) break;
                    
                    control.deletarContato(idParaDeletar);
                }
                case 5 -> { 
                    System.out.println("Saindo do sistema...");
                    rodando = false;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
        scanner.close(); 
    }
}