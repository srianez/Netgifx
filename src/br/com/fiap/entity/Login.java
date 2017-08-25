package br.com.fiap.entity;


//Classe utilizada como auxiliar no loggin.
//Quando utilizei a classe Usuario, após o cadastro os campos da tela de login ficavam preenchidos por usar
//a mesma classe
public class Login {

	private String login;
	private String senha;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
