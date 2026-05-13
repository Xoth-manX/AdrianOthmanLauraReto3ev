package app;

import dao.MunicipioDAO;
import dao.ProvinciaDAO;
import modelo.Municipio;
import modelo.Provincia;

public class MainMunicipio {

	public static void main(String[] args) {
		MunicipioDAO municipioDAO = new MunicipioDAO();
		ProvinciaDAO provinciaDAO = new ProvinciaDAO();
		
		System.out.println("---------------------");
		System.out.println("Mostrar todas las provincias");
		System.out.println("---------------------");
		for (Provincia pro : provinciaDAO.obtenerTodos()) {
			System.out.println(pro);
			for (Municipio mun : municipioDAO.obtenerPorProvincia(pro.getId())) {
				System.out.println(mun);
			}
			System.out.println();
		}
		
		System.out.println("---------------------");
		System.out.println("Mostrar provincia con mas municipios");
		System.out.println("---------------------");
		System.out.println(provinciaDAO.obtenerProvinciaConMasMunicipios());	
		for (Municipio mun : municipioDAO.obtenerPorProvincia(provinciaDAO.obtenerProvinciaConMasMunicipios().getId())) {
				System.out.println(mun);
			}
			System.out.println();
	
		System.out.println("---------------------");
		System.out.println("Añadir nueva provincia");
		System.out.println("---------------------");
		System.out.println(provinciaDAO.insertar("Huesca", "Aragon"));
		//System.out.println(municipioDAO.obtenerPorProvincia(3));
		//System.out.println(municipioDAO.obtenerCapitalPorProvincia(2));
		//System.out.println(municipioDAO.sumaHabitantesPorProvincia(2));
		//System.out.println(municipioDAO.actualizarHabitantesPorNombre("Valencia", 3000000));
		//System.out.println(dao.eliminarPorNombre("Valencia"));
	}

}
