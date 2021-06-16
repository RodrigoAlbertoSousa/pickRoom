package home.room.controllers;

// @author Telandes Moreira

import home.room.models.Criptografar;
import home.room.models.SistemaDAO;
import home.room.models.Usuario;
import home.room.models.UsuarioDAO;
import home.room.views.TelaAcessoAdministrador;
import home.room.views.TelaCadUsuario;
import home.room.views.TelaContUsuarios;
import home.room.views.TelaPrincipal;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class RegraAcessoAdministrador {
    private final TelaAcessoAdministrador tAcessoAdministrador;
    private final TelaContUsuarios tContUsuario;
    private final TelaCadUsuario tCadUsuario;
    private final UsuarioDAO usuarioDao;
    private final SistemaDAO sistemaDao;
    
    public RegraAcessoAdministrador(TelaAcessoAdministrador tAcessoAdministrador, TelaContUsuarios tContUsuario, TelaCadUsuario tCadUsuario) {
        this.tAcessoAdministrador = tAcessoAdministrador;
        this.tContUsuario = tContUsuario;
        this.tCadUsuario = tCadUsuario;
        this.usuarioDao = new UsuarioDAO();
        this.sistemaDao = new SistemaDAO();
    }
     
    public void validarAcessoAdministrador(TelaPrincipal tPrincipal) {
        
        if(this.sistemaDao.consultarTipoUsuarioLogado().equals("Administrador")){
            this.tAcessoAdministrador.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(tPrincipal, "Logue com uma conta de administrativa.","Erro: usuário sem privilégio de acesso!",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cadastrarUsuario(){
        
        if(validarCamposObrigatorios()){
            if(validarChaveSeguranca()){
                if(validarNomeUsuario()){
                
                    Usuario usuarioPackEntrada = new Usuario();
                    usuarioPackEntrada.setNomeUsuario(this.tCadUsuario.getjTextFieldNomeUsuario().getText());
                    usuarioPackEntrada.setSenha(Criptografar.encriptografar(this.tCadUsuario.getjTextFieldSenha().getText()));
                    
                    if(this.tCadUsuario.getjRadioButtonUsuarioAdministrador().isSelected()){
                        usuarioPackEntrada.setTipoUsuario("Administrador");
                    }else{
                        usuarioPackEntrada.setTipoUsuario("Operador");
                    }
                    
                    if(this.usuarioDao.cadastrarUsuario(usuarioPackEntrada)){
                        JOptionPane.showMessageDialog(tCadUsuario, "Usuário cadastrado com sucesso!");
                        this.tCadUsuario.limparCampos();
                        this.tCadUsuario.dispose();                        
                        this.listar();
                    }else{
                        JOptionPane.showMessageDialog(tCadUsuario, "Contate o apoio do sistema.","Erro: falha no cadastro do usuário!",JOptionPane.ERROR_MESSAGE);
                    } 
                }else{
                    JOptionPane.showMessageDialog(tCadUsuario, "Informe um novo nome de usuário.","Erro: nome de usuário já cadastrado!",JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(tCadUsuario, "Informe a chave de segurança do sistema!","Erro: chave de segurança incorreta!",JOptionPane.ERROR_MESSAGE);
            }  
        }else{
            JOptionPane.showMessageDialog(tCadUsuario, "Preencha todos os campos.","Erro: todos os campos são obrigatórios!",JOptionPane.ERROR_MESSAGE);
        }
    } 

    private boolean validarCamposObrigatorios() {
        if(this.tCadUsuario.getjTextFieldNomeUsuario().getText().equals("")){
            return false;
        }else if(this.tCadUsuario.getjTextFieldSenha().getText().equals("")){
            return false;
        }else if(String.valueOf(this.tCadUsuario.getjPasswordFieldChaveSeguranca().getPassword()).equals("")){
            return false;
        }else if(this.tCadUsuario.getjRadioButtonUsuarioAdministrador().isSelected()==false 
                && this.tCadUsuario.getjRadioButtonUsuarioOperador().isSelected()==false){
            return false;
        }else{
            return true;
        }
    }

    private boolean validarChaveSeguranca() {
        String chaveSegurancaEntrada = String.valueOf(this.tCadUsuario.getjPasswordFieldChaveSeguranca().getPassword());    
        return chaveSegurancaEntrada.equals(this.sistemaDao.validarChaveSeguranca(chaveSegurancaEntrada));
    }
    
    private boolean validarNomeUsuario() {
        String nomeUsuarioEntrada = this.tCadUsuario.getjTextFieldNomeUsuario().getText();
        return !nomeUsuarioEntrada.equals(this.usuarioDao.consultarNomeUsuario(nomeUsuarioEntrada));
    }
    
    public void listar() {              //Modificar depois;
        listarUsuarios(usuarioDao.listarUsuarios());
    }
    
    private void listarUsuarios(ArrayList<Usuario> listaPessoas) {    //Modificar depois; 
        this.tContUsuario.limpaTabela();
        for(int i=0;i<listaPessoas.size();i++){
            this.tContUsuario.adicionaItem (listaPessoas.get(i).getIdUsuario(), 
                    listaPessoas.get(i).getNomeUsuario(), listaPessoas.get(i).getTipoUsuario());     
        }      
    }  
}