package br.com.caelum.jdbc.dao;

import br.com.caelum.jdbc.database.ConnectionFactory;
import br.com.caelum.jdbc.modelo.Contato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.List;

public class ContatoDao {
    private Connection connection;
    public ContatoDao() {
        this.connection = new ConnectionFactory().getConnection();
    }
    public void adiciona(Contato contato) {
        String sql = "insert into contatos(nome, email, endereco, dataNascimento) values (?,?,?,?)";
        try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1,contato.getNome());
        stmt.setString(2, contato.getEmail());
        stmt.setString(3, contato.getEndereco());
        stmt.setString(4, new Date(contato.getDataNascimento().getTimeInMillis()).toString());

        stmt.execute();
        stmt.close();
        }catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }

    public List<Contato> getList(){
        List <Contato> contatos = new ArrayList();
        try{
            PreparedStatement stmt = connection.prepareStatement("select * from contatos");
            ResultSet result = stmt.executeQuery();

            while(result.next()) {
                Contato contato = new Contato();
                contato.setNome(result.getString("nome"));
                contato.setEmail(result.getString("email"));
                contato.setEndereco(result.getString("endereco"));

                Calendar data = Calendar.getInstance();
                data.setTime(result.getDate("dataNascimento"));
                contato.setDataNascimento(data);
                contatos.add(contato);
            }

            result.close();
            connection.close();
            return contatos;
        }catch(SQLException err) {
            throw new RuntimeException(err);
        }
    }

    public void atualizaContato(Contato contato) {
        try {
            PreparedStatement stmt = connection.prepareStatement("update contatos set nome=?, email=?, endereco=?, dataNascimento=? where id=?");
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getEmail());
            stmt.setString(3, contato.getEndereco());
            stmt.setString(4, new Date(contato.getDataNascimento().getTimeInMillis()).toString());
            stmt.setLong(5, contato.getId());
            stmt.execute();
            stmt.close();
        }catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }
    public void delete(Contato contato) {
        try {
            PreparedStatement stmt = connection.prepareStatement("delete from contatos where id=?");
            stmt.setLong(1, contato.getId());
            stmt.execute();
            stmt.close();
        }catch(SQLException err) {
            throw new RuntimeException(err);
        }

    }
}
