package home.room.controllers;

// @author Telandes Moreira

import home.room.views.TelaLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class ControleLogin implements ActionListener{

    private final TelaLogin tLogin; 
    private final RegraLogin rLogin;

    public ControleLogin() {
        this.tLogin = new TelaLogin(null, true);
        this.rLogin = new RegraLogin(tLogin);       
        addListeners();
        this.tLogin.setVisible(true);
    }
    
    private void addListeners() {
        this.tLogin.getjButtonEntrar().addActionListener(this);
        this.tLogin.getjButtonSair().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.tLogin.getjButtonEntrar())){
            this.rLogin.logarSistema(); 
        }else if(e.getSource().equals(this.tLogin.getjButtonSair())){
            System.exit(0);
        }
    }
}
