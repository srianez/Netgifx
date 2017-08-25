package br.com.fiap.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*CREATE TABLE netgif.usuario (
	       nome VARCHAR(200) not null,
	       login varchar(20) not null,
	       senha VARCHAR(10) not null);*/

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "NOME", nullable=false, length=200)
	private String nome;
	
	@Id
	@Column(name = "LOGIN", nullable=false, length=20)
	private String login;

	@Column(name = "SENHA", nullable=false, length=10)
	private String senha;


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


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