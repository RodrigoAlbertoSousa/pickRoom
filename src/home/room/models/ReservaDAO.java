package home.room.models;

// @author Telandes Moreira

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservaDAO {

    public boolean registrarClienteReserva(Cliente clientePackEntrada) {
        String sql = "INSERT INTO cliente (cpfCliente, nomeCliente, telefone) VALUES (?,?,?)";
        
        PreparedStatement pst;
        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setString(1, clientePackEntrada.getCpfCliente());
            pst.setString(2, clientePackEntrada.getNomeCliente());
            pst.setString(3, clientePackEntrada.getTelefone());
            pst.execute();
            pst.close();
            
        } catch (SQLException ex) {
            System.out.println("Deu ruin no banco - classe ResrevaDAO - registrarClienteReserva\n"+ex);
            return false;
        }
        return true;
    }

    public boolean cadastrarReserva(Reserva reservaPackEntrada) {
        String sql = "INSERT INTO reserva (dataInicioReserva, dataFimReserva, idQuarto, categoriaQuarto, CpfCliente) VALUES (?,?,?,?,?)";
            
        PreparedStatement pst;
        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setString(1, reservaPackEntrada.getDataInicioReserva());
            pst.setString(2, reservaPackEntrada.getDataFimReserva());
            pst.setInt(3, reservaPackEntrada.getIdQuarto());
            pst.setString(4, reservaPackEntrada.getCategoriaQuarto());
            pst.setString(5, reservaPackEntrada.getCpfCliente());
            pst.execute();
            pst.close();
           
        } catch (SQLException ex) {
            System.out.println("Deu ruin no banco - classe ResrevaDAO - cadastrarReserva\n"+ex); 
            return false;
        }
        return true;
    }
    
    public ArrayList<Reserva> consultarReserva(String cpfCliente) {
        String sql = "SELECT * FROM reserva WHERE cpfCliente LIKE ?";
        
        ArrayList<Reserva> listReservas = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs;

        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setString(1, cpfCliente);
            rs = pst.executeQuery();
            
            while(rs.next()){
                Reserva reserva = new Reserva();
                reserva.setIdReserva(rs.getInt("idReserva"));
                reserva.setDataInicioReserva(rs.getString("dataInicioReserva"));
                reserva.setDataFimReserva(rs.getString("dataFimReserva"));
                reserva.setIdQuarto(rs.getInt("idQuarto"));
                reserva.setCategoriaQuarto(rs.getString("categoriaQuarto"));
                listReservas.add(reserva);
            }
            rs.close();
            pst.close();
            
        } catch (SQLException ex) {
            System.out.println("Deu ruin no banco - consultarReserva - classe ReservaDAO\n"+ex);
        }
        return listReservas; 
    }
    
    public Cliente consultarRegistroCliente(String cpfEntrada) {
        String sql = "SELECT cpfCliente, nomeCliente, telefone FROM cliente WHERE cpfCliente LIKE ?";
        Cliente clientePackSaida = new Cliente();    
        
        PreparedStatement pst;
        ResultSet rs;

        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setString(1, cpfEntrada);
            rs = pst.executeQuery();
            
            while(rs.next()){
                clientePackSaida.setCpfCliente(rs.getString("cpfCliente"));
                clientePackSaida.setNomeCliente(rs.getString("nomeCliente"));
                clientePackSaida.setTelefone(rs.getString("telefone"));
            }
            rs.close();
            pst.close();
            
        } catch (SQLException ex) {
            System.out.println("Deu ruin no banco - consultarCpfRegistrado - classe\n"+ex);
        } 
        return clientePackSaida; 
    }

    public boolean atualizarRegistroCliente(Cliente clientePackEntrada) {
        String sql = "UPDATE cliente SET cpfCliente = ?, nomeCliente = ?, telefone = ?  WHERE cpfCliente = ?";
                    
        PreparedStatement pst;
        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setString(1, clientePackEntrada.getCpfCliente());
            pst.setString(2, clientePackEntrada.getNomeCliente());
            pst.setString(3, clientePackEntrada.getTelefone());
            pst.setString(4, clientePackEntrada.getCpfCliente());
            pst.execute();
            pst.close();
       
        } catch (SQLException ex) {
            System.out.println("Deu ruin no banco - classe - atualizarRegistroCliente\n"+ex);
            return false;
        }
    return true;
    }

    public boolean atualizarStatusQuarto(int idQuartoEntrada, String statusQuartoEntrada) {
        String sql = "UPDATE quarto SET statusQuarto = ? WHERE idQuarto = ?";
                    
        PreparedStatement pst;
        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setString(1, statusQuartoEntrada);
            pst.setInt(2, idQuartoEntrada);
            pst.execute();
            pst.close();
       
        } catch (SQLException ex) {
            System.out.println("Deu ruin no banco - classe - atualizarStatusQuarto\n"+ex);
            return false;
        }
        return true;
    }
    
    public boolean atualizarCadastroCliente(Cliente cliente){
        String sql = "UPDATE cliente SET eMail = ?, sexo = ?, dataNascimento = ? WHERE cpfCliente = ?";
                    
        PreparedStatement pst;
        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setString(1, cliente.geteMail());
            pst.setString(2, cliente.getSexo());
            pst.setString(3, cliente.getDataNascimento());
            pst.setString(4, cliente.getCpfCliente());
            pst.execute();
            pst.close();
       
        } catch (SQLException ex) {
            System.out.println("Deu ruin no banco - classe - atualizarCadastroCliente\n"+ex);
            return false;
        }
        return true;
    } 
}