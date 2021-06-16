package home.room.controllers;

// @author Telandes Moreira

import home.room.views.TelaConsQuartos;
import javax.swing.JOptionPane;

 
public class RegraQuartos {
    private TelaConsQuartos tConsQuartos;

    public RegraQuartos(TelaConsQuartos tConsQuartos) {
        this.tConsQuartos = tConsQuartos;
    }
    
    public void pesquisarQuarto(){
        //se a pesquisa for encontrada, ele seta as informações na tela, se não ele apresenta msg de erro;
    }
    
    public void finalizarReservaHospedagem(){
        JOptionPane.showMessageDialog(tConsQuartos, "Reserva ou hospedagem finalizada com sucesso!");
    }
}
