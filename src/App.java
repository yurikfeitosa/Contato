import view.ContatoView;
import controller.ContatoController;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ContatoView view = new ContatoView();
        ContatoController controller = new ContatoController(view);
        Scanner scanner = new Scanner(System.in);

        boolean rodando = true;
        while (rodando) {
            System.out.println("\n=== Sistema de Contatos ===");
            System.out.println("1. Cadastrar contato");
            System.out.println("2. Listar todos os contatos");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> controller.cadastrarContato();
                case 2 -> controller.listarContatos();
                case 3 -> {
                    System.out.println("Saindo...");
                    rodando = false;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}
