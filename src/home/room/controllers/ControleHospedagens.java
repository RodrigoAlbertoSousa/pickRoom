package home.room.controllers;

// @author Telandes Moreira

import home.room.views.TelaCadHospedagem;
import home.room.views.TelaCadHospedagemCliente;
import home.room.views.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 
public class ControleHospedagens implements ActionListener{

    private TelaCadHospedagem tCadHospedagem;
    private TelaCadHospedagemCliente tCadHospedagemCliente;
    private RegraHospedagens rHospedagens;
    
    public ControleHospedagens(TelaPrincipal tPrincipal) {
        this.tCadHospedagem = new TelaCadHospedagem(tPrincipal, true);
        this.tCadHospedagemCliente = new TelaCadHospedagemCliente(tCadHospedagem, true);
        this.rHospedagens = new RegraHospedagens(tCadHospedagem, tCadHospedagemCliente);
        addListnersTelaCadHospedagens();
        addListnersTelaCadHospedagemCliente();
        this.tCadHospedagem.setVisible(true);
    }
    
    private void addListnersTelaCadHospedagens() {
        this.tCadHospedagem.getjButtonPesquisar().addActionListener(this);
        this.tCadHospedagem.getjButtonAvancar().addActionListener(this);
        this.tCadHospedagem.getjButtonVoltar().addActionListener(this);
    }

    private void addListnersTelaCadHospedagemCliente() {
        this.tCadHospedagemCliente.getjButtonSalvarEconfirmar().addActionListener(this);
        this.tCadHospedagemCliente.getjButtonCancelar().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //eventos da tCadHospedagem;
        if(e.getSource().equals(this.tCadHospedagem.getjButtonPesquisar())){
            this.rHospedagens.consultarReseva();
        }else if(e.getSource().equals(this.tCadHospedagem.getjButtonAvancar())){
            this.rHospedagens.avancar();
        }else if(e.getSource().equals(this.tCadHospedagem.getjButtonVoltar())){
            this.tCadHospedagem.dispose();
        }
        
        //eventos da tCadHospedagemCliente;
        if(e.getSource().equals(this.tCadHospedagemCliente.getjButtonSalvarEconfirmar())){
            this.rHospedagens.salvarConfirmarHospedagem();
        }else if(e.getSource().equals(this.tCadHospedagemCliente.getjButtonCancelar())){
            this.tCadHospedagemCliente.limparTela();
            this.tCadHospedagemCliente.dispose();
            this.tCadHospedagem.dispose();
        }
    }
}
