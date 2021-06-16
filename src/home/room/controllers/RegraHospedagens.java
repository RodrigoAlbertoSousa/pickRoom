package home.room.controllers;

// @author Telandes Moreira

import home.room.models.Cliente;
import home.room.models.HospedagemDAO;
import home.room.models.Reserva;
import home.room.models.ReservaDAO;
import home.room.models.SistemaDAO;
import home.room.views.TelaCadHospedagem;
import home.room.views.TelaCadHospedagemCliente;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;

 
public class RegraHospedagens {
    private final TelaCadHospedagem tCadHospedagem;
    private final TelaCadHospedagemCliente tCadHospedagemCliente;
    private ReservaDAO reservaDao;
    private HospedagemDAO hospedagemDao;
    private SistemaDAO sistemaDao;
//    private int cont = -1;

    public RegraHospedagens(TelaCadHospedagem tHospedagem, TelaCadHospedagemCliente tHospedagemCliente) {
        this.tCadHospedagem = tHospedagem;
        this.tCadHospedagemCliente = tHospedagemCliente;
        this.reservaDao = new ReservaDAO();
        this.hospedagemDao = new HospedagemDAO();
        this.sistemaDao = new SistemaDAO();
//        this.cont= (this.cont+1);
//        System.out.println("B"+cont);
    }
    
    public void consultarReseva(){            
        if(this.validarCpf(this.tCadHospedagem.getjFormattedTextFieldPesquisar().getText())){
            
            Cliente clienteSaida = this.reservaDao.consultarRegistroCliente(this.tCadHospedagem.getjFormattedTextFieldPesquisar().getText());

            if(clienteSaida.getCpfCliente()!=null){
                this.tCadHospedagem.getjTextFieldNome().setText(clienteSaida.getNomeCliente());
                this.tCadHospedagem.getjTextFieldCpf().setText(clienteSaida.getCpfCliente());
                this.tCadHospedagem.getjTextFieldTelefone().setText(clienteSaida.getTelefone());
                
                try{
                    ArrayList<Reserva> listReservas = this.reservaDao.consultarReserva(clienteSaida.getCpfCliente());
                    
                    if(listReservas.get(0).getIdReserva()!=0){  //quando chegar aqui pode dar uma exeção;
                        this.tCadHospedagem.limpaTabela();

                        for(int i=0; i<listReservas.size(); i++){
                            this.tCadHospedagem.adicionarItemTabela(listReservas.get(i).getIdQuarto(), listReservas.get(i).getCategoriaQuarto(),
                                    listReservas.get(i).getDataInicioReserva(), listReservas.get(i).getDataFimReserva());     
                        }
                    }
                }catch (IndexOutOfBoundsException ex){
                    System.out.println("Erro: "+ex);
                    JOptionPane.showMessageDialog(tCadHospedagem, "Registro de cliente inativo! Cadastre novas reservas para este cliente.");
                    this.tCadHospedagem.dispose();
                } 
            }else{
                JOptionPane.showMessageDialog(tCadHospedagem, "Reserva de quarto não encontrado para este valor de CPF!");
                this.tCadHospedagem.limparTela();
            }    
        }else{
            JOptionPane.showMessageDialog(tCadHospedagem, "Informe um CPF válido.","Erro: número de CPF inválido!",JOptionPane.ERROR_MESSAGE);
            this.tCadHospedagem.limparTela();
        }
                
    }
    
    public void avancar(){
        if(!this.tCadHospedagem.getjTextFieldCpf().getText().equals("")){
            this.tCadHospedagemCliente.getjTextFieldNome().setText(this.tCadHospedagem.getjTextFieldNome().getText());
            this.tCadHospedagemCliente.getjTextFieldCpf().setText(this.tCadHospedagem.getjTextFieldCpf().getText());
            this.tCadHospedagemCliente.getjTextFieldTelefone().setText(this.tCadHospedagem.getjTextFieldTelefone().getText());
            this.tCadHospedagemCliente.setVisible(true);
        }else JOptionPane.showMessageDialog(tCadHospedagem, "Pesquise por algum registro de reserva existente!");  
    }
    
    public void salvarConfirmarHospedagem(){
        if(validarCamposObrigatorios()){    //precisa ter mais uma validação de cpf existente;
            
            Cliente cliente = new Cliente();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            cliente.setCpfCliente(this.tCadHospedagemCliente.getjTextFieldCpf().getText());
            cliente.setNomeCliente(this.tCadHospedagemCliente.getjTextFieldNome().getText());
//            cliente.setTelefone(this.tCadHospedagemCliente.getjTextFieldTelefone().getText());
            cliente.seteMail(this.tCadHospedagemCliente.getjTextFieldEmail().getText());
            cliente.setSexo(this.tCadHospedagemCliente.getjTextFieldSexo().getText());
            cliente.setDataNascimento(sdf.format(this.tCadHospedagemCliente.getjDateChooserDataNascimento().getDate()));
            
            this.reservaDao.atualizarCadastroCliente(cliente);
            for (int i = 0; i < this.tCadHospedagem.getjTableReservas().getRowCount(); i++) {
                this.reservaDao.atualizarStatusQuarto((int) this.tCadHospedagem.getjTableReservas().getValueAt(i, 0), "Ocupado");
            }
            
            if(this.hospedagemDao.cadastrarHospedagem(cliente.getCpfCliente())){
                JOptionPane.showMessageDialog(tCadHospedagemCliente, "Dados salvos! Hospedagem confirmada!");
                this.tCadHospedagemCliente.limparTela();
                this.tCadHospedagemCliente.dispose();
                this.tCadHospedagem.dispose(); 
                this.gerarArquivoÚtil("** Arquivo útil da hospedagem **\n\nNome do cliente: "+cliente.getNomeCliente()+"\nCPF: "+cliente.getCpfCliente());
                
                for (int i = 0; i < this.tCadHospedagem.getjTableReservas().getRowCount(); i++) {
                    this.sistemaDao.atualizarIdArquivo(this.sistemaDao.consultarIdArquivo(), -1);
                    this.gerarArquivoÚtil("\nNúmero do quarto "+(i+1)+": "+this.tCadHospedagem.getjTableReservas().getValueAt(i, 0));
                }
                JOptionPane.showMessageDialog(null, "O arquivo de texto correspondente à esta reserva foi criado no diretório raiz do usuário logado na máquina.");
            
            }else{
                JOptionPane.showMessageDialog(tCadHospedagemCliente, "Contate o apoio do sistema.","Erro: falha no cadastro da hospedagem!",JOptionPane.ERROR_MESSAGE);
                this.tCadHospedagemCliente.limparTela();
                this.tCadHospedagemCliente.dispose();
                this.tCadHospedagem.dispose();
            }  
        }else JOptionPane.showMessageDialog(tCadHospedagemCliente, "Preencha todos os campos.","Erro: todos os campos são obrigatórios!",JOptionPane.ERROR_MESSAGE);
        //aqui tem q ter aquele treco de "informações úteis" dos requisistos (pode ser um arquivo de texto gerado para dowload);  
    }
    
    private boolean validarCamposObrigatorios() {
       if(this.tCadHospedagemCliente.getjTextFieldEmail().getText().equals("")){
            return false;
        }else if(this.tCadHospedagemCliente.getjTextFieldSexo().getText().equals("")){
            return false;
        }else return this.tCadHospedagemCliente.getjDateChooserDataNascimento().getDate() != null;
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
    
    public void gerarArquivoÚtil(String msg) {
        FileWriter arquivo;
	String caminho = System.getProperty("user.home");
        this.sistemaDao.atualizarIdArquivo(this.sistemaDao.consultarIdArquivo(), 1);
        
	try {
            arquivo = new FileWriter(new File(caminho+"/Arquivo da hospedagem("+this.sistemaDao.consultarIdArquivo()+").txt"), true);
            arquivo.write(msg);
            arquivo.close();
	} catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Contate o apoio do sistema.","Erro: falha no processo de gerar o arquivo da hospedagem!",JOptionPane.ERROR_MESSAGE);
            System.out.println("Erro no processo de gerar o arquivo: "+ex);
	} catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Contate o apoio do sistema.","Erro: falha no processo de gerar o arquivo da hospedagem!",JOptionPane.ERROR_MESSAGE);
            System.out.println("Erro no processo de gerar o arquivo: "+ex);
	}
    } 
}