package home.room.models;

// @author Telandes Moreira
 
public class Sistema {
    private int idSistema;
    private String chaveSeguranca;
    private String tipoUsuarioLogado;

    public int getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(int idSistema) {
        this.idSistema = idSistema;
    }
    
    public String getChaveSeguranca() {
        return chaveSeguranca;
    }

    public void setChaveSeguranca(String chaveSeguranca) {
        this.chaveSeguranca = chaveSeguranca;
    }

    public String getTipoUsuarioLogado() {
        return tipoUsuarioLogado;
    }

    public void setTipoUsuarioLogado(String tipoUsuarioLogado) {
        this.tipoUsuarioLogado = tipoUsuarioLogado;
    }  
}