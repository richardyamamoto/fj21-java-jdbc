package br.com.caelum.jdbc.teste;

import br.com.caelum.jdbc.dao.FuncionarioDao;
import br.com.caelum.jdbc.modelo.Funcionario;

public class TestaFuncionario {
    public static void main(String[] args) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Funcionario 01");
        funcionario.setUsuario("func01");
        funcionario.setSenha("123456");

        FuncionarioDao dao = new FuncionarioDao();
        dao.adiciona(funcionario);
        dao.getList();
    }
}
