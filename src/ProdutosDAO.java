/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){        
        conn = new conectaDAO().connectDB();
 try {
 String sql = "insert into produtos (nome, valor, status) values (?,?,?)";
 
    prep = conn.prepareStatement(sql);
    prep.setString(1, produto.getNome());
    prep.setString(2, produto.getValor().toString()) ;
    prep.setString(3, produto.getStatus());

    prep.executeUpdate();
    JOptionPane.showMessageDialog(null, "Dados cadastrados com sucesso!");    
} catch ( SQLException sqle ) {
    JOptionPane.showMessageDialog(null, "Erro ao inserir os dados!"); }
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

