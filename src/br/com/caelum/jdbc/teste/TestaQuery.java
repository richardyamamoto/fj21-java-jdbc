package br.com.caelum.jdbc.teste;

import br.com.caelum.jdbc.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaQuery {
    public static void main(String[] args) throws SQLException {
        Connection con = new ConnectionFactory().getConnection();
        PreparedStatement stmt = con.prepareStatement("select * from contatos");
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            String id = rs.getString("id");
            String nome = rs.getString("nome");
            String email = rs.getString("email");
            String endereco = rs.getString("endereco");
            System.out.println("Id: " + id + "Nome: " + nome + " Email: " + email + "Endereço: " + endereco);
        }
        rs.close();
        stmt.close();
        con.close();
    }
}
