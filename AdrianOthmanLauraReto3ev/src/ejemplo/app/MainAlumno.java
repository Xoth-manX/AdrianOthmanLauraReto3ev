package app;

import dao.AlumnoDAO;
import modelo.Alumno;

public class MainAlumno {
	public static void main(String[] args) {
		AlumnoDAO dao = new AlumnoDAO();

		
		Alumno a1 = new Alumno("paco", 30, "ESO", false);
		System.out.println(dao.insertar(a1));
		a1.setEdad(13);
		System.out.println(dao.actualizar(a1));
		System.out.println(a1);
		
		for (Alumno a : dao.obtenerTodos()) {
			System.out.println(a);
		}
		
	}
}
