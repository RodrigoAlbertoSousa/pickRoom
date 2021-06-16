package home.room.models;

// @author Telandes Moreira

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class SistemaDAO {
    
     public Usuario logarSistema(Usuario usuarioPackEntrada){ //aqui tem uma chamada de update
        String sql = "SELECT * FROM usuario WHERE nomeUsuario LIKE ? AND senha LIKE ?";
        Usuario usuarioPackSaida = new Usuario();
        
        PreparedStatement pst;
        ResultSet rs; 

        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setString(1, usuarioPackEntrada.getNomeUsuario());
            pst.setString(2, usuarioPackEntrada.getSenha());
            rs = pst.executeQuery();
        
            while(rs.next()){
                usuarioPackSaida.setIdUsuario(rs.getInt("idUsuario"));
                usuarioPackSaida.setNomeUsuario(rs.getString("nomeUsuario"));
                usuarioPackSaida.setSenha(rs.getString("senha"));
                usuarioPackSaida.setTipoUsuario(rs.getString("tipoUsuario"));
            }
            rs.close();
            pst.close();
            
        } catch (SQLException ex) {
            System.out.println("Deu ruin no banco - classe UsuarioDAO - logar"+ex);
            //throw new RuntimeException(ex);   
        }
        this.atualizarTipoUsuarioLogado(usuarioPackSaida.getTipoUsuario(), 1); //Esse nº "1", poderia ser uma foreign key no banco;
        return usuarioPackSaida;  
    }
    
    public String validarChaveSeguranca(String chaveSegurancaEntrada){      
        String sql = "SELECT chaveSeguranca FROM sistema WHERE chaveSeguranca LIKE ?";   
        String chaveSegurancaSaida = "ERRO!";
        
        PreparedStatement pst;
        ResultSet rs;
        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setString(1, chaveSegurancaEntrada);
            rs = pst.executeQuery();
            
            while(rs.next()){
               chaveSegurancaSaida = rs.getString("chaveSeguranca");
               
            }
            rs.close();
            pst.close();
            
        } catch (SQLException ex) {
            System.out.println("Deu ruin na pesquisa do banco - validarChave - SistemaDAO"+ex);
        }
       return chaveSegurancaSaida; 
    }
    
    public void atualizarTipoUsuarioLogado(String tipoUsuarioLogadoPackEntrada, int idSistema){ //update do tipo usuario logado;
        String sql = "UPDATE sistema SET tipoUsuarioLogado = ? WHERE idSistema = ?";
                    
        PreparedStatement pst;
        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setString(1, tipoUsuarioLogadoPackEntrada);
            pst.setInt(2, idSistema);
            pst.execute();
            pst.close();
       
        } catch (SQLException ex) {
            System.out.println("Deu ruin no banco - classe SitemaDAO - atualizarTipoUsuarioLogado"+ex);
        }
    }
    
    public String consultarTipoUsuarioLogado(){  //consulta do tipo usuario logado; 
        String sql = "SELECT tipoUsuarioLogado FROM sistema";   
        Sistema sistemaPackSaida = new Sistema();
        
        PreparedStatement pst;
        ResultSet rs;
        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
               sistemaPackSaida.setTipoUsuarioLogado(rs.getString("tipoUsuarioLogado"));
            }
            rs.close();
            pst.close();
            
        } catch (SQLException ex) {
            System.out.println("Deu ruin na pesquisa do banco - consultarTipoUsuarioLogado - SistemaDAO"+ex);
        }
       return sistemaPackSaida.getTipoUsuarioLogado(); 
    }
    
    public int consultarIdArquivo(){  //consulta do tipo usuario logado; 
        String sql = "SELECT IdArquivo FROM sistema";   
        int IdArquivo = 0;
        
        PreparedStatement pst;
        ResultSet rs;
        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
               IdArquivo = (rs.getInt("IdArquivo"));
            }
            rs.close();
            pst.close();
            
        } catch (SQLException ex) {
            System.out.println("Deu ruin na pesquisa do banco - consultarIdArquivo - SistemaDAO"+ex);
        }
       return IdArquivo; 
    }
    
    public void atualizarIdArquivo(int idArquivoEntrada, int par){ //update do tipo usuario logado;
        String sql = "UPDATE sistema SET IdArquivo = ? WHERE idSistema = ?";
                    
        PreparedStatement pst;
        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setInt(1, (idArquivoEntrada+(par)));
            pst.setInt(2, 1);
            pst.execute();
            pst.close();
       
        } catch (SQLException ex) {
            System.out.println("Deu ruin no banco - classe SitemaDAO - atualizarIdArquivo"+ex);
        }
    }
}