package home.room.controllers;

// @author Telandes Moreira

import home.room.views.TelaInfoUsuario;
import home.room.views.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleAtualizarDados implements ActionListener{
    private final TelaInfoUsuario tInfoUsuario;
    private RegraAtualizarDados rAtualizarDados;
    
    public ControleAtualizarDados(TelaPrincipal tPrincipal) {
        this.tInfoUsuario = new TelaInfoUsuario(tPrincipal, true);
        this.rAtualizarDados = new RegraAtualizarDados(tInfoUsuario);
        addListners();
        this.tInfoUsuario.setVisible(true);
    }
    
    private void addListners() {
        this.tInfoUsuario.getjButtonAtualizarDados().addActionListener(this);
        this.tInfoUsuario.getjButtonCancelar().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.tInfoUsuario.getjButtonAtualizarDados())){
            this.rAtualizarDados.atualizarDados();
        }else if(e.getSource().equals(this.tInfoUsuario.getjButtonCancelar())){
            //Devo colocar um método específico para cancelar e limpar os campos da classe regra aqui? Ou só dou dipose e limpo aqui msm?
            this.tInfoUsuario.dispose();
        }
    }
}