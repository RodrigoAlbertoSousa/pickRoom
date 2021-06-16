package home.room.controllers;

// @author Telandes Moreira

import home.room.views.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlePrincipal implements ActionListener{
    private final TelaPrincipal tPrincipal;

    public ControlePrincipal() {
        this.tPrincipal = new TelaPrincipal();
        this.addListeners();
        this.tPrincipal.setVisible(true);
    }
    
    private void addListeners(){
        this.tPrincipal.getjMenuItemInfoUsuario().addActionListener(this);
        this.tPrincipal.getjMenuItemCadReservas().addActionListener(this);
        this.tPrincipal.getjMenuItemCadHospedagem().addActionListener(this);
        this.tPrincipal.getjMenuItemConsQuartos().addActionListener(this);
        //aqui talvez tenha o item de menu referente às contas;
        this.tPrincipal.getjMenuItemAcessoAdministrador().addActionListener(this);
        //Como escutar o evento proveniente do menu sair?
        this.tPrincipal.getjMenuItemFazerLogOut().addActionListener(this);
        this.tPrincipal.getjMenuItemSair().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.tPrincipal.getjMenuItemInfoUsuario())){
            ControleAtualizarDados cAtualizarDados = new ControleAtualizarDados(tPrincipal);
        }else if(e.getSource().equals(this.tPrincipal.getjMenuItemCadReservas())){
            ControleReservas cReservas = new ControleReservas(tPrincipal);
        }else if(e.getSource().equals(this.tPrincipal.getjMenuItemCadHospedagem())){
            ControleHospedagens cHospedagens = new ControleHospedagens(tPrincipal);
        }else if(e.getSource().equals(this.tPrincipal.getjMenuItemConsQuartos())){
            ControleQuartos cQuartos = new ControleQuartos(tPrincipal);
        }else if(e.getSource().equals(this.tPrincipal.getjMenuItemAcessoAdministrador())){
            ControleAcessoAdministrador cAcessoAdministrador = new ControleAcessoAdministrador(tPrincipal);
        }else if(e.getSource().equals(this.tPrincipal.getjMenuItemFazerLogOut())){
            this.tPrincipal.dispose();
            ControleLogin cLogin = new ControleLogin();
        }else if(e.getSource().equals(this.tPrincipal.getjMenuItemSair())){
            System.exit(0);
        }
    }
}