package com.mycompany.Model;

public class Contato {
    private int id;
    private String nome;
    private String telefone;
    private String email; 
    private String observacoes;
    private String dataAniversario;
    private int idGrupo; 
    private String nomeGrupo;

    // Construtor 1: Para CRIAR um novo Contato
    public Contato(String nome, String telefone, String email, String observacoes, String dataAniversario) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.observacoes = observacoes;
        this.dataAniversario = dataAniversario;
    }
    
    // Construtor 2: Para CARREGAR Contato do Banco de Dados 
    public Contato(int id, String nome, String telefone, String email, String observacoes, String dataAniversario, int idGrupo, String nomeGrupo) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.observacoes = observacoes;
        this.dataAniversario = dataAniversario;
        this.idGrupo = idGrupo;
        this.nomeGrupo = nomeGrupo;
    }
    
    // --- GETTERS E SETTERS ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public String getDataAniversario() { return dataAniversario; }
    public void setDataAniversario(String dataAniversario) { this.dataAniversario = dataAniversario; }
    public int getIdGrupo() { return idGrupo; }
    public void setIdGrupo(int idGrupo) { this.idGrupo = idGrupo; }
    public String getNomeGrupo() { return nomeGrupo; }
    public void setNomeGrupo(String nomeGrupo) { this.nomeGrupo = nomeGrupo; }

    @Override
    public String toString() {
        return "ID: " + id + 
               " | Nome: " + nome + 
               " | Telefone: " + telefone +
               " | E-mail: " + email + 
               " | Data Aniv: " + dataAniversario +
               " | Observações: " + observacoes + 
               " | Grupo: " + nomeGrupo;
    }
}