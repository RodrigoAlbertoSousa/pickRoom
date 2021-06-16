package home.room.controllers;

// @author Telandes Moreira

import home.room.views.TelaAcessoAdministrador;
import home.room.views.TelaCadUsuario;
import home.room.views.TelaContUsuarios;
import home.room.views.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class ControleAcessoAdministrador implements ActionListener{

    private final TelaAcessoAdministrador tAcessoAdministrador;
    private final TelaContUsuarios tContUsuario;
    private final TelaCadUsuario tCadUsuario;
    private final RegraAcessoAdministrador regraAcessoAdministrador;
    
    public ControleAcessoAdministrador(TelaPrincipal tPrincipal) {
        this.tAcessoAdministrador = new TelaAcessoAdministrador(tPrincipal, true);
        this.tContUsuario = new TelaContUsuarios(tAcessoAdministrador, true);
        this.tCadUsuario = new TelaCadUsuario(tContUsuario, true);
        this.regraAcessoAdministrador = new RegraAcessoAdministrador(tAcessoAdministrador, tContUsuario, tCadUsuario);
        addListnersTelaAcessoAdministrador();
        addListnersTelaContUsuarios();
        addListnersTelaCadastro();
        this.regraAcessoAdministrador.listar();
        this.regraAcessoAdministrador.validarAcessoAdministrador(tPrincipal);
    }

    private void addListnersTelaAcessoAdministrador() {
        this.tAcessoAdministrador.getjButtonUsuarios().addActionListener(this);
        this.tAcessoAdministrador.getjButtonAdministrativo().addActionListener(this);
        this.tAcessoAdministrador.getjButtonVoltar().addActionListener(this);
    }
    
    private void addListnersTelaContUsuarios() {
        this.tContUsuario.getjButtonAdicionar().addActionListener(this);
        this.tContUsuario.getjButtonDeletar().addActionListener(this);
        this.tContUsuario.getjButtonVoltar().addActionListener(this);
    }
    
    private void addListnersTelaCadastro() {
        this.tCadUsuario.getjButtonCadastrar().addActionListener(this);
        this.tCadUsuario.getjButtonCancelar().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Eventos da TelaAcessoAdministrador;
        if(e.getSource().equals(this.tAcessoAdministrador.getjButtonUsuarios())){
            this.tContUsuario.setVisible(true);
        }
        else if(e.getSource().equals(this.tAcessoAdministrador.getjButtonAdministrativo())){
            System.out.println("Isso aqui só vai ser implementado se der (prioridade baixa)");
        }
        else if(e.getSource().equals(this.tAcessoAdministrador.getjButtonVoltar())){
            this.tAcessoAdministrador.dispose();
        }
        
        //Eventos da telaContUsuarios;
        else if(e.getSource().equals(this.tContUsuario.getjButtonAdicionar())){
            this.tCadUsuario.setVisible(true);
        }else if(e.getSource().equals(this.tContUsuario.getjButtonDeletar())){
            System.out.println("em andamento");
        }else if(e.getSource().equals(this.tContUsuario.getjButtonVoltar())){
            this.tContUsuario.dispose();
        }
        
        //Eventos da TelaCadUsuario;
        if(e.getSource().equals(this.tCadUsuario.getjButtonCadastrar())){
            this.regraAcessoAdministrador.cadastrarUsuario();
        }else if(e.getSource().equals(this.tCadUsuario.getjButtonCancelar())){
            this.tCadUsuario.dispose();
        }
    }
}