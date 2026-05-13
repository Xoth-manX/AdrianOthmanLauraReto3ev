package app;

import dao.NotaDAO;

public class MainNota {

	public static void main(String[] args) {
		NotaDAO dao = new NotaDAO();
		
		System.out.println(dao.obtenerPorIdAlumno(1));
		System.out.println(dao.insertar());
		System.out.println(dao.obtenerTodos());
	}

}
