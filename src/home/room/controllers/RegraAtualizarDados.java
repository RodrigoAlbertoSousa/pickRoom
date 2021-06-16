package home.room.controllers;

// @author Telandes Moreira

import home.room.views.TelaInfoUsuario;
import javax.swing.JOptionPane;

 
public class RegraAtualizarDados {
    private TelaInfoUsuario tInfoUsuario;;

    public RegraAtualizarDados(TelaInfoUsuario tInfoUsuario) {
        this.tInfoUsuario = tInfoUsuario;
    }
    
    public void atualizarDados(){
        //verificar se tudo foi devidamente preenchido nos campos;
        JOptionPane.showMessageDialog(tInfoUsuario, "Dados atualizados com sucesso!");
        this.tInfoUsuario.dispose();
        //limpar campos;
    }
}
