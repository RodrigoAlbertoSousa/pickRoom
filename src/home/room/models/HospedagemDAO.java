package home.room.models;

// @author Telandes Moreira

import java.sql.PreparedStatement;
import java.sql.SQLException;

 
public class HospedagemDAO {
    //falta finalizar...ainda não compreendemos bem quais atributos serão necessários para a hospedagem;
    public boolean cadastrarHospedagem(String cpfCliente){
        String sql = "INSERT INTO hospedagem (cpfCliente) VALUES (?)";
        
        PreparedStatement pst;
        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setString(1, cpfCliente);
            pst.execute();
            pst.close();
            
        } catch (SQLException ex) {
            System.out.println("Deu ruin no banco - classe - cadastrarHospedagem\n"+ex);
            return false;
        }
        return true;
    }
}
