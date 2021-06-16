package home.room.models;

// @author Telandes Moreira
 
public class Reserva {
    private int idReserva;
    private String dataInicioReserva;
    private String dataFimReserva;
//    private Date dataInicioReserva;
//    private Date dataFimReserva;
    private int idQuarto;
    private String categoriaQuarto;
    private String cpfCliente;

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }
    
    public String getDataInicioReserva() {
        return dataInicioReserva;
    }

    public void setDataInicioReserva(String dataInicioReserva) {
        this.dataInicioReserva = dataInicioReserva;
    }

    public String getDataFimReserva() {
        return dataFimReserva;
    }

    public void setDataFimReserva(String dataFimReserva) {
        this.dataFimReserva = dataFimReserva;
    }

    public int getIdQuarto() {
        return idQuarto;
    }

    public void setIdQuarto(int idQuarto) {
        this.idQuarto = idQuarto;
    }

    public String getCategoriaQuarto() {
        return categoriaQuarto;
    }

    public void setCategoriaQuarto(String categoriaQuarto) {
        this.categoriaQuarto = categoriaQuarto;
    }
    
    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    } 
}