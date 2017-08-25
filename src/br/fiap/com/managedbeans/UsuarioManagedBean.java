package br.fiap.com.managedbeans;

import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.fiap.entity.Login;
import br.com.fiap.entity.Usuario;
import br.com.fiap.dao.UsuarioDAO;

@ManagedBean(name = "UsuarioMB")
public class UsuarioManagedBean {

	private Usuario usuario = new Usuario();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	private Login login = new Login();

	public String cadastraUsuario() throws SQLException {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Netgifx");
		EntityManager em = emf.createEntityManager();
		
		try {
			
			if (usuario.getNome().isEmpty()) {
				addMessage("Favor prencher o nome do usuário!");
				return null;					
			}

			if (usuario.getLogin().isEmpty()) {
				addMessage("Favor prencher o login do usuário!");
				return null;					
			}
			
			if (usuario.getSenha().isEmpty()) {
				addMessage("Favor prencher a senha do usuário!");
				return null;					
			}
			
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
			addMessage("Usuário cadastrado com sucesso!");
		} catch (Exception e) {
			throw e;
		} finally {
		  em.close();
		}

		return "";
	}

	public String enviar() {
		
		if ( !login.getLogin().isEmpty() && !login.getSenha().isEmpty() ) {		
		
			usuario = usuarioDAO.getUsuario(login.getLogin(), login.getSenha());
	
			if (usuario == null) {
				addMessage("Usuário não encontrado!");
				return null;
			} else {
				return "/menubar";
			}
		} else {
			addMessage("Favor prencher os campos Usuário e Senha!");
			return null;		
		}
	}

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
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