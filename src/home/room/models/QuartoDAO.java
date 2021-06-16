package home.room.models;

// @author Telandes Moreira

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
 
public class QuartoDAO {
    
    public ArrayList<Quarto> listarIdCategoriaQuartos(String statusQuartoEntrada){
        String sql = "SELECT idQuarto, categoriaQuarto FROM quarto WHERE statusQuarto LIKE ?";
        
        ArrayList<Quarto> listIdCategoria = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs;

        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setString(1, statusQuartoEntrada);
            rs = pst.executeQuery();
            
            while(rs.next()){
                Quarto quarto = new Quarto();
                quarto.setIdQuarto(rs.getInt("idQuarto"));
                quarto.setCategoriaQuarto(rs.getString("categoriaQuarto"));
                listIdCategoria.add(quarto);
            }
            rs.close();
            pst.close();
            
        } catch (SQLException ex) {
            System.out.println("Deu ruin no banco - listarIdCategoriaQuartos - classe QuartoDAO\n"+ex);
        } 
        return listIdCategoria;  
    }
    
    public Quarto listarInformacoesQuarto(int idQuartoEntrada){  //retornar um objeto de quarto;
        String sql = "SELECT * FROM quarto WHERE idQuarto LIKE ?";
        
        Quarto quarto = new Quarto();
        PreparedStatement pst;
        ResultSet rs;
        
        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setInt(1, idQuartoEntrada);
            rs = pst.executeQuery();
            
            while(rs.next()){   //nem precisa do while;
                quarto.setIdQuarto(rs.getInt("idQuarto"));
                quarto.setStatusQuarto(rs.getString("statusQuarto"));
                quarto.setCategoriaQuarto(rs.getString("categoriaQuarto"));
                quarto.setQtdCamas(rs.getInt("qtdCamas"));
                quarto.setTipoCama(rs.getString("tipoCama"));
                quarto.setAnotacoes(rs.getString("anotacoes"));  
            }
            rs.close();
            pst.close();
            
        } catch (SQLException ex) {
            System.out.println("Deu ruin no banco - listarIdCategoriaQuartos - classe QuartoDAO\n"+ex);
        } 
        return quarto; 
    }
    
    public ArrayList<Quarto> consultarQuartos(int idQuartoEntrada){ //consulta de quartos por id;
        String sql = "SELECT idQuarto, statusQuarto, categoriaQuarto FROM quarto WHERE idQuarto LIKE ?";
        
        ArrayList<Quarto> listIdCategoria = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs;

        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setInt(1, idQuartoEntrada);
            rs = pst.executeQuery();
            
            while(rs.next()){
                Quarto quarto = new Quarto();
                quarto.setIdQuarto(rs.getInt("idQuarto"));
                quarto.setStatusQuarto(rs.getString("statusQuarto"));
                quarto.setCategoriaQuarto(rs.getString("categoriaQuarto"));
                listIdCategoria.add(quarto);
            }
            rs.close();
            pst.close();
            
        } catch (SQLException ex) {
            System.out.println("Deu ruin no banco - listarIdCategoriaQuartos - classe QuartoDAO\n"+ex);
        } 
        System.out.println("aaa"+listIdCategoria.get(0).getIdQuarto());
        return listIdCategoria;  
    }
}