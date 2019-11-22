package br.com.caelum.jdbc.teste;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;

import java.util.Calendar;

public class TestaInsere {
    public static void main(String[] args) {
        Contato contato = new Contato();
        contato.setNome("José da Silva");
        contato.setEmail("Jose@email.com");
        contato.setEndereco("Rua Y");
        contato.setDataNascimento(Calendar.getInstance());

        ContatoDao dao = new ContatoDao();
        dao.adiciona(contato);
        System.out.println("Gravado");

    }
}
