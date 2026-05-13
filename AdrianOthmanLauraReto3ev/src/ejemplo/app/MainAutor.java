package app;

import dao.AutorDAO;

public class MainAutor {

	public static void main(String[] args) {
		AutorDAO dao = new AutorDAO();
		System.out.println(dao.autorConMasLibros());

	}

}
