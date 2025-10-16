package com.mycompany.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL implements ConexaoInterface{
    private static final String URL = "jdbc:mysql://localhost:3306/agenda_contatos";
    private static final String USUARIO = "root";
    private static final String SENHA = "yurik";
    
    @Override
    public Connection obterConexao() throws Exception{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexao com o banco de dados estabelecida com sucesso!");
            return conexao;
            
        } catch (ClassNotFoundException e){
            
            throw new Exception("Driver JDBC do MySQL não encontrado: " + e.getMessage());
            
        } catch ( SQLException e){
            
            throw new Exception("Erro ao conectar ao banco de dados: " + e.getMessage());
            
        } 
    }
    
    @Override
    public void fecharConexao(Connection conexao) throws Exception{
        if (conexao != null) {
            try{
                conexao.close();
                System.out.println("Conexao com o banco de dados fechada com sucesso!");
                
            } catch (SQLException e){
                
                throw new Exception("Erro ao fechar banco de dados: " + e.getMessage());
                
            } 
        } 	
    }
}