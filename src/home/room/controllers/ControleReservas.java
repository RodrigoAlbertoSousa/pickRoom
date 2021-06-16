package home.room.controllers;

// @author Telandes Moreira

import home.room.views.TelaPrincipal;
import home.room.views.TelaCadReservasCliente;
import home.room.views.TelaCadResevas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
 
public class ControleReservas implements ActionListener, MouseListener{

    private final TelaCadResevas tCadReservas;
    private final TelaCadReservasCliente tCadReservasCliente;
    private final RegraReservas rReservas;

    public ControleReservas(TelaPrincipal tPrincipal) {
        this.tCadReservas = new TelaCadResevas(tPrincipal, true);
        this.tCadReservasCliente = new TelaCadReservasCliente(this.tCadReservas, true);
        this.rReservas = new RegraReservas(tCadReservas, tCadReservasCliente);
        addListnersTelaCadResevas();
        addListnersTelaCadReservasCliente();
        this.tCadReservas.setVisible(true);
    }
    
    private void addListnersTelaCadResevas() {
        this.tCadReservas.getjButtonPesquisar().addActionListener(this);
       this.tCadReservas.getjButtonReservar().addActionListener(this);
       this.tCadReservas.getjButtonVoltar().addActionListener(this);
       this.tCadReservas.getjRadioButtonLivres().addActionListener(this);
       this.tCadReservas.getjRadioButtonOcupados().addActionListener(this);
       this.tCadReservas.getjRadioButtonReservados().addActionListener(this);
       this.tCadReservas.getjRadioButtonEmManutencao().addActionListener(this);
       this.tCadReservas.getjTableIdCategoria().addMouseListener(this);
    }

    private void addListnersTelaCadReservasCliente() {
       this.tCadReservasCliente.getjButtonPesquisar().addActionListener(this);
       this.tCadReservasCliente.getjButtonSalvarConfirmar().addActionListener(this);
       this.tCadReservasCliente.getjButtonCancelar().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {  
        
       //eventos da TelaCadResevas;
       if(e.getSource().equals(this.tCadReservas.getjButtonPesquisar())){
           this.rReservas.consultarQuarto();
       }else if(e.getSource().equals(this.tCadReservas.getjButtonReservar())){     
           this.rReservas.associarClienteReserva();
       }else if(e.getSource().equals(this.tCadReservas.getjButtonVoltar())){
           this.tCadReservas.dispose();
       }else if(e.getSource().equals(this.tCadReservas.getjRadioButtonLivres())){
           this.rReservas.listarIdCategoriaQuartos("Livre"); 
       }else if(e.getSource().equals(this.tCadReservas.getjRadioButtonOcupados())){
           this.rReservas.listarIdCategoriaQuartos("Ocupado");
       }else if(e.getSource().equals(this.tCadReservas.getjRadioButtonReservados())){
           this.rReservas.listarIdCategoriaQuartos("Reservado");
       }else if(e.getSource().equals(this.tCadReservas.getjRadioButtonEmManutencao())){
           this.rReservas.listarIdCategoriaQuartos("Em manutenção");
       }
       
       //eventos da TelaCadReservasCliente;
       if(e.getSource().equals(this.tCadReservasCliente.getjButtonPesquisar())){
           this.rReservas.consultarRegistroCliente();
       }else if(e.getSource().equals(this.tCadReservasCliente.getjButtonSalvarConfirmar())){
           this.rReservas.salvarConfirmarReserva();
       }else if(e.getSource().equals(this.tCadReservasCliente.getjButtonCancelar())){
          this.rReservas.cancelarReserva();
       }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.rReservas.listarInformacoesQuarto();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("bNot supported yet."); //To body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("cNot supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("dNot supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("eNot supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}