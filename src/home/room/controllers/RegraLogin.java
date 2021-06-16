package home.room.controllers;

// @author Telandes Moreira

import home.room.models.Criptografar;
import home.room.models.SistemaDAO;
import home.room.models.Usuario;
import home.room.views.TelaLogin;
import javax.swing.JOptionPane;

public class RegraLogin {
    
    private final TelaLogin telaLogin;
    private final SistemaDAO sistemaDao;

    public RegraLogin(TelaLogin telaLogin) {
        this.telaLogin = telaLogin;
        this.sistemaDao = new SistemaDAO();
    }
    
    public void logarSistema(){ 
        Usuario usuarioPackEntrada = new Usuario();
        usuarioPackEntrada.setNomeUsuario(this.telaLogin.getjTextFieldUsuario().getText());
        usuarioPackEntrada.setSenha(Criptografar.encriptografar(String.valueOf(this.telaLogin.getjPasswordFieldSenha().getPassword())));
        
        if(this.sistemaDao.logarSistema(usuarioPackEntrada).getIdUsuario()>0){  
            this.telaLogin.dispose();
            ControlePrincipal cPrincipal = new ControlePrincipal();
        }else{
            JOptionPane.showMessageDialog(telaLogin, "Usuário e/ou senha incorreto(s)!","Acesso negado", 0);
            this.telaLogin.limparCampos();
        }
    }
}