package ejemplo.app;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ejemplo.dao.FacturaDAO;
import ejemplo.dao.HistorialDAO;
import ejemplo.dao.MascotaDAO;
import ejemplo.dao.TratamientoDAO;
import ejemplo.dao.VeterinarioDAO;
import ejemplo.modelo.Historial;
import ejemplo.modelo.Mascota;
import ejemplo.modelo.Tratamiento;
import ejemplo.modelo.Veterinario;



public class Main {



	public static void main(String[] args) {

		

		// 1. Muestra los tratamientos que ha realizado el veterinario id=2

        Scanner sc = new Scanner(System.in);
        FacturaDAO facturaDAO = new FacturaDAO();
        MascotaDAO mascotaDAO = new MascotaDAO();
        HistorialDAO historialDAO = new HistorialDAO();
        TratamientoDAO tratamientoDAO = new TratamientoDAO();

   //2. Muestra todos los clientes, selecciona un id y muestras sus mascotas.

        System.out.println("PREGUNTA 1:         === TRATAMIENTOS DEL VETERINARIO ID 2 ===");

        List<Historial> historial = historialDAO.obtenerTodos();

        for (Historial h : historial) {

            if (h.getId_veterinario() == 2) {

                Tratamiento t =
                        tratamientoDAO.obtenerPorId(h.getId_tratamiento());

                System.out.println(
                        "Fecha: " + h.getFecha()
                        + "  Tratamiento: " + t.getNombre()
                        + "  Precio: " + t.getPrecio() + "€");
            }
        }

        //3. Muestra todas las mascotas y selecciona un id y muestra el historial completo de esa mascota.
        System.out.println("PREGUNTA 3:     === LISTADO DE MASCOTAS ===");

        List<Mascota> mascotas = mascotaDAO.obtenerTodos();

        for (Mascota m : mascotas) {

            System.out.println(
                    "ID: " + m.getId_Mascota()
                    + "  Nombre: " + m.getNombre()
                    + "  Especie: " + m.getEspecie());
        }

        System.out.print("Introduce id mascota: ");
        int idMascota = sc.nextInt();

        List<Historial> historialMascota =
                historialDAO.obtenerHistorialPorIdMascota(idMascota);

        System.out.println("=== HISTORIAL DE LA MASCOTA ===");

        for (Historial h : historialMascota) {

            Tratamiento t =
                    tratamientoDAO.obtenerPorId(h.getId_tratamiento());

            System.out.println(
                    "Fecha: " + h.getFecha()
                    + "  Veterinario ID: " + h.getId_veterinario()
                    + "  Tratamiento: " + t.getNombre()
                    + "  Precio: " + t.getPrecio() + "€");
        }

       
    
      //4. Muestra una factura por id junto con todas sus líneas de factura.

facturaDAO.factura4();
    	// 5. Muestra todos los clientes y selecciona uno por id y muestra sus datos,

    	// sus mascotas y sus facturas asociadas.

    	

    	// 6. Añade un veterinario: 12345678Z Pepe Carrera, COL-1005

     // 7. Añadir mascota al cliente id=2
     

    System.out.println("========= PREGUNTA 7 =========");
    Mascota mascota = new Mascota();

    System.out.println("Nombre mascota:");
    mascota.setNombre( "perla");

    System.out.println("Especie:");
    String especie = sc.nextLine();

    System.out.println("Fecha nacimiento: ");
    LocalDate fecha = LocalDate.of(2023,04,14);

    System.out.println("Peso:");
    double peso = Double.parseDouble(sc.nextLine());


    mascota.setId_cliente(2);
    mascota.setEspecie(especie);
    mascota.setFechaNacimiento(fecha);
    mascota.setPeso(peso);

    if (mascotaDAO.insertar(mascota)) {

        System.out.println("Mascota insertada");

    } else {

        System.out.println("Error");
    }

   
    // 8. Tratar mascota: Muestra todas las mascotas, seleccionar una por id.
     

    System.out.println("========= PREGUNTA 8 =========");

    List<Mascota> mascotas1 = mascotaDAO.obtenerTodos();

    for (Mascota m : mascotas1) {

        System.out.println(m);
    }

    System.out.println("Selecciona id mascota:");

    int idMascota1 = Integer.parseInt(sc.nextLine());
    TratamientoDAO TratamientoDAO = new TratamientoDAO();

    List<Tratamiento> tratamientos = TratamientoDAO.obtenerTodos();

    int contador = 1;

    for (Tratamiento t : tratamientos) {

        System.out.println(contador + " " + t);

        contador++;
    }

    System.out.println("Selecciona id tratamiento:");

    int idTratamiento = Integer.parseInt(sc.nextLine());

    Historial historial1 = new Historial();

    historial1.setId_mascota(idMascota1);
    historial1.setId_tratamiento(idTratamiento);
    historial1.setId_veterinario(1);
    historial1.setFecha(LocalDate.now());

    if (historialDAO.insertar(historial1)) {

        System.out.println("Tratamiento añadido");

    } else {

        System.out.println("Error añadiendo tratamiento");
    }

 // 9. Muestra todos los tratamientos disponibles, selecciona uno

 // para ver su precio y cuántas veces aparece en el historial.

    System.out.println("========= PREGUNTA 9 =========");

    List<Tratamiento> listaTratamientos = TratamientoDAO.obtenerTodos();

    int i = 1;

    for (Tratamiento t : listaTratamientos) {

        System.out.println(i + " " + t);

        i++;
    }

    System.out.println("Selecciona id tratamiento:");

    int id = Integer.parseInt(sc.nextLine());

    Tratamiento tratamiento = TratamientoDAO.obtenerPorId(id);

    if (tratamiento != null) {

        System.out.println("Nombre: " + tratamiento.getNombre());

        System.out.println("Precio: " + tratamiento.getPrecio());

        int veces = tratamientoDAO.contarTratamientosHistorial(id);

        System.out.println("Veces en historial: " + veces);
    }
 // 10. Muestra las facturas, pide un número de mes y muestra las facturas

 	// de ese mes y el total facturado.

 	

 	// 11. Muestra los veterinarios y seleccionando un id muestra

 	// las facturas que ha emitido.


 // 12. Muestras las mascotas y actualiza su peso.

    System.out.println("========= PREGUNTA 12 =========");

    List<Mascota> mascotasPeso = mascotaDAO.obtenerTodos();

    for (Mascota m : mascotasPeso) {

        System.out.println(m);
    }

    System.out.println("Selecciona id mascota:");

    int idMascotaPeso = Integer.parseInt(sc.nextLine());

    System.out.println("Nuevo peso:");

    double nuevoPeso = Double.parseDouble(sc.nextLine());

    if (mascotaDAO.actualizarPeso(idMascotaPeso, nuevoPeso)) {

        System.out.println("Peso actualizado");

    } else {

        System.out.println("Error actualizando peso");
    }

 // 13. Muestra las mascotas, selecciona una por id y elimínala

 	// si no tiene facturas asociadas.

    System.out.println("========= PREGUNTA 13 =========");

    List<Mascota> mascotasEliminar = mascotaDAO.obtenerTodos();

    for (Mascota m : mascotasEliminar) {

        System.out.println(m);
    }

    System.out.println("Selecciona id mascota:");

    int idEliminar = Integer.parseInt(sc.nextLine());

    if (mascotaDAO.eliminar(idEliminar)) {

        System.out.println("Mascota eliminada");

    } else {

        System.out.println("No se puede eliminar porque tiene facturas");
    }

    sc.close();
}



	

	// 14. Guarda los veterinarios en el fichero “veterinarios.dat”.

	// Lee ese fichero en una lista y muestra los datos de los veterinarios.

	serial.guardar(veterinarioDAO.obtenerTodos());
	for (Veterinario veterinario : serial.leer()) {
		System.out.println(veterinario);
	}

	// 15. Genera un resumen mensual por veterinario: pide un número de mes

	// y muestra cada veterinario con el número de tratamientos realizados,

	// el número de facturas emitidas y el importe total facturado ese mes

	

	// 16. Duplica una factura. Pide un id de factura, copia su cabecera,

	// copia todas sus líneas de factura en una nueva factura con la fecha

	// actual y muestra la nueva factura generada

	

	// 17. Elimina un tratamiento del historial y rehaz una factura. Selecciona

	// una mascota, muestra su historial de una fecha concreta, elimina uno de

	// los tratamientos de ese día y actualiza la factura asociada

	// recalculando sus líneas, subtotal, IVA y total*/

	

}





