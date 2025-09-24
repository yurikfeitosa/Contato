package controller;

import model.Contato;
import view.ContatoView;
import java.util.ArrayList;
import java.util.List;

public class ContatoController {
    private ContatoView view;
    private List<Contato> contatos = new ArrayList<>(); // Lista dinâmica

    public ContatoController(ContatoView view) {
        this.view = view;
    }

    public void cadastrarContato() {
        Contato contato = view.criarContato();
        contatos.add(contato);
        view.mostrarContato(contato);
    }

    public void listarContatos() {
        view.mostrarTodosContatos(contatos);
    }
}
