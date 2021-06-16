package home.room.models;

// @author Telandes Moreira
 
public class Quarto {
    private int idQuarto;
    private String statusQuarto;
    private String categoriaQuarto;
    private int qtdCamas;
    private String tipoCama;
    private String anotacoes;

    public int getIdQuarto() {
        return idQuarto;
    }

    public void setIdQuarto(int idQuarto) {
        this.idQuarto = idQuarto;
    }

    public String getStatusQuarto() {
        return statusQuarto;
    }

    public void setStatusQuarto(String statusQuarto) {
        this.statusQuarto = statusQuarto;
    }

    public String getCategoriaQuarto() {
        return categoriaQuarto;
    }

    public void setCategoriaQuarto(String categoriaQuarto) {
        this.categoriaQuarto = categoriaQuarto;
    }

    public int getQtdCamas() {
        return qtdCamas;
    }

    public void setQtdCamas(int qtdCamas) {
        this.qtdCamas = qtdCamas;
    }

    public String getTipoCama() {
        return tipoCama;
    }

    public void setTipoCama(String tipoCama) {
        this.tipoCama = tipoCama;
    }

    public String getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(String anotacoes) {
        this.anotacoes = anotacoes;
    }   
}
