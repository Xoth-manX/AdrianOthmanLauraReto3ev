package app;

import dao.ProvinciaDAO;

public class MainProvincia {

	public static void main(String[] args) {
		ProvinciaDAO dao = new ProvinciaDAO();
		
		System.out.println(dao.obtenerProvinciaConMasMunicipios());
	}

}
