package home.room.controllers;

// @author Telandes Moreira

import home.room.views.TelaConsQuartos;
import home.room.views.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class ControleQuartos implements ActionListener{
    private TelaConsQuartos tConsQuartos;
    private RegraQuartos rQuartos;
    
    public ControleQuartos(TelaPrincipal tPrincipal) {
        this.tConsQuartos = new TelaConsQuartos(tPrincipal, true);
        this.rQuartos = new RegraQuartos(tConsQuartos);
        addLitners();
        this.tConsQuartos.setVisible(true);
    }
    
    private void addLitners(){
        this.tConsQuartos.getjButtonPesquisar().addActionListener(this);
        this.tConsQuartos.getjButtonFinalizarReservaHospedagem().addActionListener(this);
        this.tConsQuartos.getjButtonVoltar().addActionListener(this);
    }
       
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.tConsQuartos.getjButtonPesquisar())){
            this.rQuartos.pesquisarQuarto();
        }else if(e.getSource().equals(this.tConsQuartos.getjButtonFinalizarReservaHospedagem())){
            this.rQuartos.finalizarReservaHospedagem();
        }else if(e.getSource().equals(this.tConsQuartos.getjButtonVoltar())){
            this.tConsQuartos.dispose();
        }
    } 
}