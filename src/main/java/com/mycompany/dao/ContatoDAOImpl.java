package com.mycompany.dao;

import com.mycompany.Model.Contato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAOImpl {
    
    private ConexaoInterface conexao;

    public ContatoDAOImpl() {
        this.conexao = new ConexaoMySQL();
    }

    // --- MÉTODOS AUXILIARES ---

    
    private int obterIdGrupoPorNome(String nomeGrupo, Connection conn) throws Exception {
        if (nomeGrupo == null || nomeGrupo.trim().isEmpty()) {
            throw new Exception("Nome do grupo não pode ser vazio.");
        }
        
        String sqlSelect = "SELECT id FROM Grupo WHERE nome = ?";
        try (PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect)) {
            stmtSelect.setString(1, nomeGrupo);
            try (ResultSet rs = stmtSelect.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        
        String sqlInsert = "INSERT INTO Grupo (nome) VALUES (?)";
        try (PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmtInsert.setString(1, nomeGrupo);
            int rowsAffected = stmtInsert.executeUpdate();
            
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmtInsert.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        System.out.println("Novo grupo criado: " + nomeGrupo);
                        return generatedKeys.getInt(1); 
                    }
                }
            }
        }
        
        throw new Exception("Falha ao obter ou criar ID do Grupo: " + nomeGrupo);
    }
    
    // --- CRUD PRINCIPAL ---

    public void salvar(Contato contato, String nomeGrupo) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        String sql = "INSERT INTO Contato (id_grupo, nome, telefone, email, observacoes, dataAniversario) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            conn = conexao.obterConexao();
            conn.setAutoCommit(false); 
            
            int idGrupo = obterIdGrupoPorNome(nomeGrupo, conn);
            
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, idGrupo); 
            stmt.setString(2, contato.getNome());
            stmt.setString(3, contato.getTelefone());
            stmt.setString(4, contato.getEmail()); 
            stmt.setString(5, contato.getObservacoes());
            stmt.setString(6, contato.getDataAniversario());
            
            stmt.executeUpdate();
            conn.commit(); 
            System.out.println("Contato salvo no banco de dados com sucesso! (Grupo: " + nomeGrupo + ")");
            
        } catch (SQLException e) {
            if (conn != null) conn.rollback(); 
            throw new Exception("Erro ao salvar contato no BD: " + e.getMessage());
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.setAutoCommit(true); 
            conexao.fecharConexao(conn);
        }
    }

   public List<Contato> listarTodos() throws Exception {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Contato> contatos = new ArrayList<>();
    
    String sql = "SELECT c.*, g.nome AS nome_grupo FROM Contato c " +
                 "JOIN Grupo g ON c.id_grupo = g.id " +
                 "ORDER BY c.nome";
    
    try {
        conn = conexao.obterConexao();
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery(); 
        
        while (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            String telefone = rs.getString("telefone");
            String email = rs.getString("email"); 
            String observacoes = rs.getString("observacoes");
            String dataAniversario = rs.getString("dataAniversario");
            int idGrupo = rs.getInt("id_grupo");
            String nomeGrupo = rs.getString("nome_grupo");
            Contato contato = new Contato(id, nome, telefone, email, observacoes, dataAniversario, idGrupo, nomeGrupo);
            contatos.add(contato);
        }
        
        return contatos;
        
    } catch (SQLException e) {
        throw new Exception("Erro ao listar contatos do BD: " + e.getMessage());
    } finally {
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
        conexao.fecharConexao(conn);
    }
   }
    public void atualizar(Contato contato) throws Exception {
    Connection conn = null;
    PreparedStatement stmt = null;
    
    String sql = "UPDATE Contato SET nome = ?, telefone = ?, email = ?, " +
                 "observacoes = ?, dataAniversario = ?, id_grupo = ? " +
                 "WHERE id = ?";
    
    try {
        conn = conexao.obterConexao();
        stmt = conn.prepareStatement(sql);
        
        stmt.setString(1, contato.getNome());
        stmt.setString(2, contato.getTelefone());
        stmt.setString(3, contato.getEmail());
        stmt.setString(4, contato.getObservacoes());
        stmt.setString(5, contato.getDataAniversario());
        stmt.setInt(6, contato.getIdGrupo()); 
        
        stmt.setInt(7, contato.getId()); 
        
        int linhasAfetadas = stmt.executeUpdate();
        
        if (linhasAfetadas > 0) {
            System.out.println("Contato ID " + contato.getId() + " atualizado com sucesso!");
        } else {
            System.out.println("Nenhum contato encontrado com o ID " + contato.getId() + " para atualizar.");
        }
        
    } catch (SQLException e) {
        throw new Exception("Erro ao atualizar contato no BD: " + e.getMessage());
    } finally {
        if (stmt != null) stmt.close();
        conexao.fecharConexao(conn);
    }
}


public void deletar(int id) throws Exception {
    Connection conn = null;
    PreparedStatement stmt = null;
    
    String sql = "DELETE FROM Contato WHERE id = ?";
    
    try {
        conn = conexao.obterConexao();
        stmt = conn.prepareStatement(sql);
        
        stmt.setInt(1, id);
        
        int linhasAfetadas = stmt.executeUpdate();
        
        if (linhasAfetadas > 0) {
            System.out.println("Contato ID " + id + " deletado com sucesso do banco de dados!");
        } else {
            System.out.println("Nenhum contato encontrado com o ID " + id + " para deletar.");
        }
        
    } catch (SQLException e) {
        throw new Exception("Erro ao deletar contato do BD: " + e.getMessage());
    } finally {
        if (stmt != null) stmt.close();
        conexao.fecharConexao(conn);
    }
}
}