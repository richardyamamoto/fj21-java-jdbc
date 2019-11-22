package br.com.caelum.jdbc.teste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import br.com.caelum.jdbc.database.ConnectionFactory;
public class TestaConexao {
    public static void main(String[] args) throws SQLException {
//        Cria conexão;
        Connection connection = new ConnectionFactory().getConnection();
//        Query para inserir dados;
        String sql = "insert into contatos(nome, email, endereco, dataNascimento) values (?,?,?,?)";
//        Prepara a query;
        PreparedStatement stmt = connection.prepareStatement(sql);
//        Altera os pontos de interrogação pelos dados (Começa em 1);
        stmt.setString(1, "Richard");
        stmt.setString(2, "rybolteri@gmail.com");
        stmt.setString(3, "Rua X");
//        Retorna a data atual em milissegundos;
        stmt.setDate(4, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
//        Executa a query;
        stmt.execute();
        stmt.close();
        System.out.println("Gravado no banco de dados");
        connection.close();
    }
}
