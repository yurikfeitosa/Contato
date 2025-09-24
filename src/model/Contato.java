package model;

public class Contato {
    private String nome;
    private String telefone;
    private String grupo;
    private String endereco;
    private String observacoes;
    private String dataAniversario;

    public Contato(String nome, String telefone, String grupo, String endereco, String observacoes, String dataAniversario) {
        this.nome = nome;
        this.telefone = telefone;
        this.grupo = grupo;
        this.endereco = endereco;
        this.observacoes = observacoes;
        this.dataAniversario = dataAniversario;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getGrupo() { return grupo; }
    public void setGrupo(String grupo) { this.grupo = grupo; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public String getDataAniversario() { return dataAniversario; }
    public void setDataAniversario(String dataAniversario) { this.dataAniversario = dataAniversario; }

    @Override
    public String toString() {
        return "Nome: " + nome + "\nTelefone: " + telefone + "\nGrupo: " + grupo +
                "\nEndereço: " + endereco + "\nObservações: " + observacoes +
                "\nAniversário: " + dataAniversario + "\n";
    }
}
