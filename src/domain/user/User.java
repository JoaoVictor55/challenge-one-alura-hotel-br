package domain.user;

public class User {
	
	private Integer indice;
	private String userName;
	private String senha;
	
	
	
	public User(Integer indice, String userName, String senha) {
		super();
		this.indice = indice;
		this.userName = userName;
		this.senha = senha;
	}
	public User() {
		this.indice = null;
		this.userName = null;
		this.senha = null;
	}
	public String getUserName() {
		return userName;
	}
	
	
	
	public Integer getIndice() {
		return indice;
	}
	public void setIndice(Integer indice) {
		this.indice = indice;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
