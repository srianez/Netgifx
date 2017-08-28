package br.com.fiap.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import br.com.fiap.entity.Usuario;


public class UsuarioDAO {

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Netgifx");
	private EntityManager em = entityManagerFactory.createEntityManager();


	public Usuario getUsuario(String login, String senha) {

		try {
			Usuario usuario = (Usuario) em
					.createQuery("SELECT u from Usuario u where u.login = :login and u.senha = :senha")
					.setParameter("login", login).setParameter("senha", senha).getSingleResult();
			return usuario;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Usuario existeUsuario(String login, String nome) {
		try {
			Usuario usuario = (Usuario) em
					.createQuery("SELECT u from Usuario u where u.login = :login and u.nome = :nome")
					.setParameter("login", login).setParameter("nome", nome).getSingleResult();
			return usuario;
		} catch (NoResultException e) {
			return null;
		}
	}

}