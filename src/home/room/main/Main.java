package home.room.main;

//@author Telandes Moreira

import home.room.controllers.ControleLogin;
import home.room.models.Conexao;
import java.sql.Connection;


public class Main {
    public static void main(String[] args) {
        Connection conexao = Conexao.getConexao();               
        ControleLogin cLogin = new ControleLogin();
    }
}