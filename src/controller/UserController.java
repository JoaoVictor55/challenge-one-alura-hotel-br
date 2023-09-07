package controller;

import connection.ConnectionFactory;
import dao.UserDAO;
import domain.user.User;

public class UserController {

	public UserDAO dao;
	
	public UserController() {
		
		dao = new UserDAO(new ConnectionFactory().conectar());
	}
	
	public User buscarPorNome(String nome) {
		
		return this.dao.buscar(nome);
	}
}
