package home.room.models;

// @author Telandes Moreira

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

public class Conexao {
    private static Connection conexao;
    
    public static Connection getConexao(){
        FileReader reader; 
        Properties p = new Properties();
        
        try{
            reader = new FileReader("db.properties");
            p.load(reader);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Falha: "+e);
        }
        
        
        if(conexao == null){
            try {
                conexao = DriverManager.getConnection(p.getProperty("URL_DB"),p.getProperty("USER_DB"), p.getProperty("PASSWORD_DB"));
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados!");
                throw new RuntimeException(e);
            }
        }
        return conexao;
    } 
}
