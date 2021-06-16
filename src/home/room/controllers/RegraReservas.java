package home.room.controllers;

// @author Telandes Moreira

import home.room.models.Cliente;
import home.room.models.Quarto;
import home.room.models.QuartoDAO;
import home.room.models.Reserva;
import home.room.models.ReservaDAO;
import home.room.views.TelaCadReservasCliente;
import home.room.views.TelaCadResevas;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;

 
public class RegraReservas {
    private final TelaCadResevas tCadReservas;
    private final TelaCadReservasCliente tCadReservasCliente;
    private final QuartoDAO quartoDao;
    private final ReservaDAO reservaDao;

    public RegraReservas(TelaCadResevas tCadReservas, TelaCadReservasCliente tCadReservasCliente) {
        this.tCadReservas = tCadReservas;
        this.tCadReservasCliente = tCadReservasCliente;
        this.quartoDao = new QuartoDAO();
        this.reservaDao = new ReservaDAO();
    }

    public void listarIdCategoriaQuartos(String statusQuartoEntrada) {
        ArrayList<Quarto> listIdCategoria = this.quartoDao.listarIdCategoriaQuartos(statusQuartoEntrada);
        this.tCadReservas.limparTela();
        
        for(int i=0; i<listIdCategoria.size(); i++){
            this.tCadReservas.adicionarItemTabela(listIdCategoria.get(i).getIdQuarto(), listIdCategoria.get(i).getCategoriaQuarto());     
        }      
    } 
    
    public void listarInformacoesQuarto(){
        int itemSelecionado = this.tCadReservas.getjTableIdCategoria().getSelectedRow();
        
        if(itemSelecionado>=0){
            Quarto quarto = this.quartoDao.listarInformacoesQuarto((int) this.tCadReservas.getModelo().getValueAt(itemSelecionado, 0));               
            
            String msg = " Id do quarto: "+quarto.getIdQuarto()
                +"\n Status de ocupação: "+quarto.getStatusQuarto()
                +"\n\n Categoria: "+quarto.getCategoriaQuarto()
                +"\n Quantidade de camas: "+quarto.getQtdCamas()
                +"\n Tipo da(s) cama(s): "+quarto.getTipoCama()
                +"\n\n Anotações e observações: "+quarto.getAnotacoes();
            this.tCadReservas.adicionarInformacoesQuarto(msg);
        
        }else{
            JOptionPane.showMessageDialog(tCadReservas, "Selecione algum item da tabela.","Erro: nehum item selecionado!",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void consultarQuarto(){        
        try {
            ArrayList<Quarto> listIdCategoria = this.quartoDao.consultarQuartos(Integer.parseInt(this.tCadReservas.getjTextFieldPesquisar().getText()));
             
            if(listIdCategoria.get(0).getIdQuarto() == Integer.parseInt(this.tCadReservas.getjTextFieldPesquisar().getText())){
                //this.tCadReservas.limparTela();
                this.tCadReservas.limpaTabela();
                //precisaria adicionar na tela apenas se n já estiver adicionado
                for(int i=0; i<listIdCategoria.size(); i++){
                    this.tCadReservas.adicionarItemTabela(listIdCategoria.get(i).getIdQuarto(), listIdCategoria.get(i).getCategoriaQuarto());     
                }

                this.setStausQuartoConsultado(listIdCategoria);
            
            }else if(listIdCategoria.get(0).getIdQuarto() == 0){
                JOptionPane.showMessageDialog(tCadReservas, "Informe um valor de id existente.","Erro: id do quarto não encontardo!",JOptionPane.ERROR_MESSAGE);
            }
        }catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(tCadReservas, "Informe um valor válido para a consulta.","Erro: valor inválido para consulta!",JOptionPane.ERROR_MESSAGE);
            this.tCadReservas.limparTela();
            this.tCadReservas.limparSelecaoRadioButtons();
            System.out.println("  Erro de formato de dado: Classe RegraReservas - Método consultarQuarto\n"+ex);
        }catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(tCadReservas, "Informe um valor válido, compatível com os parâmetros existentes disponíveis.","Erro: id de quarto não encontrado!",JOptionPane.ERROR_MESSAGE);
            this.tCadReservas.limparTela();
            this.tCadReservas.limparSelecaoRadioButtons();
            System.out.println("  Erro de formato de limite de dado: Classe RegraReservas - Método consultarQuarto\n"+ex);
        }    
    }
    
    public void setStausQuartoConsultado(ArrayList<Quarto> listIdCategoria){ //lembrar de habilitar e desabilitar o botão de reserva;
        switch (listIdCategoria.get(0).getStatusQuarto()) {
            case "Livre":
                this.tCadReservas.getjRadioButtonLivres().setSelected(true);
                this.tCadReservas.getjButtonReservar().setEnabled(true);   
                break;
            case "Ocupado":
                this.tCadReservas.getjRadioButtonOcupados().setSelected(true);
                this.tCadReservas.getjButtonReservar().setEnabled(false); 
                break;
            case "Reservado":
                this.tCadReservas.getjRadioButtonReservados().setSelected(true);
                this.tCadReservas.getjButtonReservar().setEnabled(false); 
                break;
            case "Em manutenção":
                this.tCadReservas.getjRadioButtonEmManutencao().setSelected(true);
                this.tCadReservas.getjButtonReservar().setEnabled(false); 
                break;
            default:
                break;
        }
    }
    
    public void atualizarLimiteTempoHospedagem(){
        //aqui tem q ter aquela forma de atualizar com base na função administrativa de limite;
    }
    
    public void associarClienteReserva(){
        int itemSelecionado = this.tCadReservas.getjTableIdCategoria().getSelectedRow();
        
        if(itemSelecionado>=0){
            this.tCadReservasCliente.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(tCadReservas, "Selecione algum item da tabela.","Erro: nehum item selecionado!",JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    public void salvarConfirmarReserva(){
        //System.out.println("AHAH"+this.tCadReservasCliente.getjDateChooserDataFinal().getDate().toString());
        if(validarCamposObrigatorios()){
            if(validarCpf(this.tCadReservasCliente.getjFormattedTextFieldCpf().getText())){
                //não isso
                
                Cliente clientePackEntrada = new Cliente();
                //id talvez;
                clientePackEntrada.setCpfCliente(this.tCadReservasCliente.getjFormattedTextFieldCpf().getText());
                clientePackEntrada.setNomeCliente(this.tCadReservasCliente.getjTextFieldNomeCliente().getText());
                clientePackEntrada.setTelefone(this.tCadReservasCliente.getjFormattedTextFieldTelefone().getText());     
                
                if(validarCpfExistente()==false){
                    if(this.reservaDao.registrarClienteReserva(clientePackEntrada)==false){
                        JOptionPane.showMessageDialog(tCadReservasCliente, "Contate o apoio do sistema.","Erro: falha no cadastro dos dados do cliente!",JOptionPane.ERROR_MESSAGE);
                        this.tCadReservasCliente.limparTela();
                        this.tCadReservasCliente.dispose();
                        this.tCadReservas.limparTela();
                        this.tCadReservas.limparSelecaoRadioButtons();
                        this.tCadReservas.dispose();
                    }
                }else{
                    //atualizar registro
                    if(this.reservaDao.atualizarRegistroCliente(clientePackEntrada)){
                        JOptionPane.showMessageDialog(tCadReservasCliente, "Registro de cliente identificado.\nDados atualizados com sucesso!");
                    }else{
                        JOptionPane.showMessageDialog(tCadReservasCliente, "Contate o apoio do sistema.","Erro: falha na atualização dos dados do cliente!",JOptionPane.ERROR_MESSAGE);
                        this.tCadReservasCliente.limparTela();
                        this.tCadReservasCliente.dispose();
                        this.tCadReservas.limparTela();
                        this.tCadReservas.limparSelecaoRadioButtons();
                        this.tCadReservas.dispose();
                    }
                }
                Reserva reservaPackEntrada = new Reserva();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                      
                reservaPackEntrada.setDataInicioReserva(sdf.format(this.tCadReservasCliente.getjDateChooserDataInicial().getDate()));
                reservaPackEntrada.setDataFimReserva(sdf.format(this.tCadReservasCliente.getjDateChooserDataFinal().getDate()));
                reservaPackEntrada.setIdQuarto((int) this.tCadReservas.getjTableIdCategoria().getValueAt(this.tCadReservas.getjTableIdCategoria().getSelectedRow(), 0));
                reservaPackEntrada.setCategoriaQuarto((String) this.tCadReservas.getjTableIdCategoria().getValueAt(this.tCadReservas.getjTableIdCategoria().getSelectedRow(), 1));
                reservaPackEntrada.setCpfCliente(this.tCadReservasCliente.getjFormattedTextFieldCpf().getText());
                    
                if(this.reservaDao.cadastrarReserva(reservaPackEntrada)){
                    JOptionPane.showMessageDialog(this.tCadReservasCliente, "Dados salvos! Reserva confirmada!");
                    this.tCadReservasCliente.limparTela();
                    this.tCadReservasCliente.dispose();
                    this.tCadReservas.limparTela();
                    this.tCadReservas.limparSelecaoRadioButtons();
                    this.tCadReservas.dispose();
                    
                    this.atualizarStatusQuarto(reservaPackEntrada.getIdQuarto(), "Reservado");   //atualizar;
                
                }else{
                    JOptionPane.showMessageDialog(tCadReservasCliente, "Contate o apoio do sistema.\nDados do cliente registrados.\n Falha de registro da reserva!","Erro: falha no cadastro dos dados da reserva!",JOptionPane.ERROR_MESSAGE);
                    this.tCadReservasCliente.limparTela();
                    this.tCadReservasCliente.dispose();
                    this.tCadReservas.limparTela();
                    this.tCadReservas.limparSelecaoRadioButtons();
                    this.tCadReservas.dispose();
                }      
            }else{
                JOptionPane.showMessageDialog(tCadReservasCliente, "Informe um CPF válido.","Erro: número de CPF inválido!",JOptionPane.ERROR_MESSAGE);
            } 
        }else{
            JOptionPane.showMessageDialog(tCadReservasCliente, "Preencha todos os campos.","Erro: todos os campos são obrigatórios!",JOptionPane.ERROR_MESSAGE);
        }   
    }
    
    private boolean validarCpfExistente() {
        Cliente clienteSaida = this.reservaDao.consultarRegistroCliente(this.tCadReservasCliente.getjFormattedTextFieldCpf().getText());
        return clienteSaida.getCpfCliente()!=null;  
    }
    
    public void consultarRegistroCliente(){
        if(this.validarCpf(this.tCadReservasCliente.getjFormattedTextFieldPesquisar().getText())){
            
            Cliente clienteSaida = this.reservaDao.consultarRegistroCliente(this.tCadReservasCliente.getjFormattedTextFieldPesquisar().getText());

            if(clienteSaida.getCpfCliente()!=null){
                this.tCadReservasCliente.desabilitarCamposCliente();
                this.tCadReservasCliente.getjTextFieldNomeCliente().setText(clienteSaida.getNomeCliente());
                this.tCadReservasCliente.getjFormattedTextFieldCpf().setText(clienteSaida.getCpfCliente());
                this.tCadReservasCliente.getjFormattedTextFieldTelefone().setText(clienteSaida.getTelefone());
            }else{
                JOptionPane.showMessageDialog(tCadReservasCliente, "Registro de cliente não encontrado para este valor de CPF!");
                this.tCadReservasCliente.limparTela();
            }    
        }else{
            JOptionPane.showMessageDialog(tCadReservasCliente, "Informe um CPF válido.","Erro: número de CPF inválido!",JOptionPane.ERROR_MESSAGE);
            this.tCadReservasCliente.limparTela();
        }
    }

    private boolean validarCamposObrigatorios() {
       if(this.tCadReservasCliente.getjTextFieldNomeCliente().getText().equals("")){
            return false;
        }else if(this.tCadReservasCliente.getjFormattedTextFieldCpf().getText().equals("   .   .   -  ")){
            return false;
        }else if(this.tCadReservasCliente.getjFormattedTextFieldTelefone().getText().equals("(  )       -    ")){
            return false;
        }else if(this.tCadReservasCliente.getjDateChooserDataInicial().getDate() == null){
            return false;
        }else return this.tCadReservasCliente.getjDateChooserDataFinal().getDate() != null;
    }

    private void validarFormatoCampos() {
        //validar cpf, validar data...tempo limite, validar telefone;
        //return this.validarCpf();
    }

    public boolean  validarCpf(String cpf){

        int cont=0;
        
        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");

        for (int i = 0; i < 10; i++) {
            if(cpf.charAt(i) == cpf.charAt(i+1)){
                cont = cont+1;
            }
        }
        
        if(cont==10 || cpf.length()!=11){
            return false;
        }
        
        char dig10, dig11;
        int sm, i, r, num, peso;
        
        try {
        //cálculo do 1º dígito verificador;
            sm = 0;
            peso = 10;
            
            for (i=0; i<9; i++) {
                num = (int)(cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            
            if ((r == 10) || (r == 11)){
                dig10 = '0';
            }else{ 
                dig10 = (char)(r + 48);
            }
        
        //cálculo do 2º dígito verificador;
            sm = 0;
            peso = 11;
            
            for(i=0; i<10; i++) {
                num = (int)(cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
        
            if((r == 10) || (r == 11)){
                dig11 = '0';
            }else{
                dig11 = (char)(r + 48);
            }
        
            //verifica se os digitos calculados conferem com os digitos informados.
            return (dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10));

        }catch (InputMismatchException ex) {
            System.out.println("Deu ruin na verificação do cpf - Classe RegraReservas - Método "+ex);
            return false;
        }
    }
    
    public void cancelarReserva() {
        this.tCadReservas.limparSelecaoRadioButtons();
        this.tCadReservas.limparTela();
        this.tCadReservasCliente.limparTela();
        this.tCadReservasCliente.dispose();
    }

    private void atualizarStatusQuarto(int idQuartoEntrada, String statusQuartoEntrada) {
       if(this.reservaDao.atualizarStatusQuarto(idQuartoEntrada, statusQuartoEntrada)==false){
           JOptionPane.showMessageDialog(tCadReservasCliente, "Contate o apoio do sistema.","Erro: falha na atualização do status do quarto reserva!",JOptionPane.ERROR_MESSAGE);
            this.tCadReservasCliente.limparTela();
            this.tCadReservasCliente.dispose();
            this.tCadReservas.limparTela();
            this.tCadReservas.limparSelecaoRadioButtons();
            this.tCadReservas.dispose();
       }
    }
}