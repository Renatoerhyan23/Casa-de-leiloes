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
    
public ArrayList<ProdutosDTO> listarProdutos() {
    ArrayList<ProdutosDTO> produtos = new ArrayList<ProdutosDTO>();
    
    conn = new conectaDAO().connectDB();
    String sql = "SELECT * FROM produtos";
    try {       
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            while(resultset.next()) {
                ProdutosDTO p = new ProdutosDTO();
                p.setId(resultset.getInt("id"));
                p.setNome(resultset.getString("nome"));
                p.setValor(resultset.getInt("valor"));
                p.setStatus(resultset.getString("status"));
                produtos.add(p);            }
            
        } catch ( SQLException sqle ) {
    System.out.println( "Erro no acesso ao Bando de Dados : "+ sqle.getMessage()); }
    return produtos;    
    }

public void venderProduto(int id){
     try {
   conn = new conectaDAO().connectDB();
   prep = conn.prepareStatement("UPDATE produtos SET status = 'Vendido' WHERE id = ?");   
   prep.setInt(1, id);
   prep.executeUpdate();
   JOptionPane.showMessageDialog(null, "Venda realizada com sucesso!");
}catch (SQLException ex) { JOptionPane.showMessageDialog(null, "Houve um erro na atualização dos dados, tente atualizá-los novamente!");
} }

public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    ArrayList<ProdutosDTO> produtos = new ArrayList<ProdutosDTO>();
    
    conn = new conectaDAO().connectDB();
    String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
    try {       
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();
         while(resultset.next()) {
           ProdutosDTO p = new ProdutosDTO();
            p.setId(resultset.getInt("id"));
            p.setNome(resultset.getString("nome"));
            p.setValor(resultset.getInt("valor"));
            p.setStatus(resultset.getString("status"));
           produtos.add(p);            }
            
        } catch ( SQLException sqle ) {
    System.out.println( "Erro no acesso ao Bando de Dados : "+ sqle.getMessage()); }
    return produtos;
    }
}

