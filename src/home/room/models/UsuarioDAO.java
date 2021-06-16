package home.room.models;

// @author Telandes Moreira

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
 
public class UsuarioDAO {
    
    public boolean cadastrarUsuario(Usuario usuarioPackEntrada){
        String sql = "INSERT INTO usuario (nomeUsuario, senha, tipoUsuario) VALUES (?,?,?)";
        
            PreparedStatement pst;
            try {
                pst = Conexao.getConexao().prepareStatement(sql);
                pst.setString(1, usuarioPackEntrada.getNomeUsuario());
                pst.setString(2, usuarioPackEntrada.getSenha());
                pst.setString(3, usuarioPackEntrada.getTipoUsuario());
                pst.execute();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("Deu ruin no banco - classe UsuarioDAO - cadastrar\n"+ex);
                //throw new RuntimeException(e); 
                return false;
            }
        return true;
    }
    
    public ArrayList<Usuario> listarUsuarios(){
        String sql = "SELECT * FROM usuario";
        
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs;
        
        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNomeUsuario(rs.getString("nomeUsuario"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipoUsuario(rs.getString("tipoUsuario"));
                listaUsuarios.add(usuario);
            }
            rs.close();
            pst.close();
            
        } catch (SQLException ex) {
            System.out.println("Deu ruin no banco - listarUsuarios - classe UsuarioDAO\n"+ex);
        } 
        return listaUsuarios;  
    }
    
    public String consultarNomeUsuario(String nomeUsuarioEntrada){
        String sql = "SELECT nomeUsuario FROM usuario WHERE nomeUsuario LIKE ?";
        Usuario usuarioPackSaida = new Usuario();
                
        PreparedStatement pst;
        ResultSet rs;

        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setString(1, nomeUsuarioEntrada);
            rs = pst.executeQuery();
            
            while(rs.next()){
                usuarioPackSaida.setNomeUsuario(rs.getString("nomeUsuario"));
            }
            rs.close();
            pst.close();
            
        } catch (SQLException ex) {
            System.out.println("Deu ruin no banco - classe UsuarioDAO - consultarNome\n"+ex);
        }   
        return usuarioPackSaida.getNomeUsuario();
    }    
}