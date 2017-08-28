package br.fiap.com.managedbeans;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.entity.Login;
import br.com.fiap.entity.Usuario;
import br.com.fiap.utils.PrimeFaceMessage;

@ManagedBean(name = "UsuarioMB")
public class UsuarioManagedBean {

	private Usuario usuario = new Usuario();
	private Login login = new Login();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private PrimeFaceMessage PfMessage = new PrimeFaceMessage();
	private Usuario existeUsuario;
	
	public String cadastraUsuario() throws SQLException {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Netgifx");
		EntityManager em = emf.createEntityManager();

		if (usuario.getNome().isEmpty()) {
			PfMessage.addErrorMessage("Favor prencher o nome do usuário!");
			return null;
		} else if (usuario.getLogin().isEmpty()) {
			PfMessage.addErrorMessage("Favor prencher o login do usuário!");
			return null;
		} else if (usuario.getSenha().isEmpty()) {
			PfMessage.addErrorMessage("Favor prencher a senha do usuário!");
			return null;
		}

		existeUsuario = usuarioDAO.existeUsuario(usuario.getLogin(), usuario.getNome());
		
		if (existeUsuario != null) {
			PfMessage.addErrorMessage("Usuário já cadastrado!");
			return null;			
		}
		
		try {

			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();

			PfMessage.addInfoMessage("Usuário cadastrado com sucesso!");

		} catch (Exception e) {
			throw e;
		} finally {
			em.close();
		}

		return null;
	}

	public String enviar() {

		if (!login.getLogin().isEmpty() && !login.getSenha().isEmpty()) {

			usuario = usuarioDAO.getUsuario(login.getLogin(), login.getSenha());

			if (usuario == null) {
				PfMessage.addErrorMessage("Usuário não encontrado!");
				return null;
			} else {
				return "/menubar";
			}
		} else {
			PfMessage.addErrorMessage("Favor prencher os campos Usuário e Senha!");
			return null;
		}
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String novo() {
		return "/cadUsuario";
	}

	public String login() {
		return "/login";
	}

}