package br.com.caelum.jdbc.dao;

import br.com.caelum.jdbc.database.ConnectionFactory;
import br.com.caelum.jdbc.modelo.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao {
    private Connection connection;
    public FuncionarioDao() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void adiciona(Funcionario funcionario) {
        try {
            String sql = "insert into funcionarios (usuario, senha, nome) values (?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,funcionario.getNome());
            stmt.setString(2,funcionario.getSenha());
            stmt.setString(3,funcionario.getNome());
            stmt.execute();
            stmt.close();
        } catch(SQLException err) {
            throw new RuntimeException(err);
        }
    }

    public List<Funcionario> getList() {
        List<Funcionario> funcionarios = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from funcionarios");
            ResultSet result = stmt.executeQuery();
            while(result.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(result.getLong("id"));
                funcionario.setUsuario(result.getString("usuario"));
                funcionario.setSenha(result.getString("senha"));
                funcionario.setNome(result.getString("nome"));

                funcionarios.add(funcionario);
            }
            result.close();
            connection.close();
            return funcionarios;
        }catch(SQLException err) {
            throw new RuntimeException(err);
        }
    }

    public void atualiza(Funcionario funcionario) {
        String sql = "update funcionarios set usuario=?, senha=?, nome=? where id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, funcionario.getUsuario());
            stmt.setString(2, funcionario.getSenha());
            stmt.setString(3, funcionario.getNome());
            stmt.setLong(4, funcionario.getId());
            stmt.execute();
            stmt.close();
            connection.close();
        }catch(SQLException err) {
            throw new RuntimeException(err);
        }
    }

    public void deleta(Funcionario funcionario) {
        String sql = "delete form funcionarios where id=?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, funcionario.getId());
            stmt.execute();
            stmt.close();
            connection.close();
        }catch(SQLException err) {
            throw new RuntimeException(err);
        }
    }
}
