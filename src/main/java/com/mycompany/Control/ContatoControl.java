package com.mycompany.Control;

import com.mycompany.Model.Contato;
import com.mycompany.dao.ContatoDAOImpl;
import java.util.Collections;
import java.util.List;

/**
 * @author yurikfeitosa
 */
public class ContatoControl {

    private ContatoDAOImpl contatoDAO;
    private static final String GRUPO_MARCA = "[GRUPO_TEMP:";
    private static final String GRUPO_FIM = "]";

    public ContatoControl() {
        this.contatoDAO = new ContatoDAOImpl();
    }

    
    public void salvar(Contato contato) {
        String observacoesOriginais = contato.getObservacoes();
        String nomeGrupo = "Geral";

        if (observacoesOriginais != null && observacoesOriginais.contains(GRUPO_MARCA)) {
            int inicio = observacoesOriginais.indexOf(GRUPO_MARCA);
            int fim = observacoesOriginais.indexOf(GRUPO_FIM, inicio);

            if (inicio != -1 && fim != -1) {
                nomeGrupo = observacoesOriginais.substring(inicio + GRUPO_MARCA.length(), fim).trim();
              
                observacoesOriginais = observacoesOriginais.substring(0, inicio).trim();
                contato.setObservacoes(observacoesOriginais);
            }
        }
      
        try {
            contatoDAO.salvar(contato, nomeGrupo); 
            System.out.println("Operação de salvar concluída no Controller.");
            
        } catch (Exception e) {
            System.err.println("ERRO no Controller ao salvar Contato: " + e.getMessage());
        }
    }

    /**
     * @return 
     */
    public List<Contato> listarContatos() {
        try {
            return contatoDAO.listarTodos();
        } catch (Exception e) {
            System.err.println("ERRO no Controller ao listar Contatos: " + e.getMessage());
            return Collections.emptyList(); 
        }
    }
    public void atualizarContato(Contato contato) {
    try {
        contatoDAO.atualizar(contato);
    } catch (Exception e) {
        System.err.println("ERRO no Controller ao atualizar Contato: " + e.getMessage());
    }
}

public void deletarContato(int id) {
    try {
        contatoDAO.deletar(id);
    } catch (Exception e) {
        System.err.println("ERRO no Controller ao deletar Contato: " + e.getMessage());
    }
}
}