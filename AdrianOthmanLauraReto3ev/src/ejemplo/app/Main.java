package ejemplo.app;

import ejemplo.dao.PersonaDAO;

public class Main {

	public static void main(String[] args) {
		PersonaDAO personaDAO = new PersonaDAO();
		// 1. Muestra los tratamientos que ha realizado el veterinario id=2
		
		/*2. Muestra todos los clientes, selecciona un id y muestras sus mascotas.
		3. Muestra todas las mascotas y selecciona un id y muestra el historial completo de esa mascota.
		4. Muestra una factura por id junto con todas sus líneas de factura.
		5. Muestra todos los clientes y selecciona uno por id y muestra sus datos, sus mascotas y sus facturas
		asociadas.
		6. Añade un veterinario: 12345678Z Pepe Carrera, COL-1005
		7. Añadir nueva mascota: Añade una mascota al cliente id=2, pide los datos por teclado.
		8. Tratar mascota: Muestra todas las mascotas, seleccionar una por id. Muestra los tratamientos
		disponibles y añade tratamientos seleccionando el id en el historial, hasta introducir 0 y después crea
		una factura con esas líneas. Muestra la factura con todos sus datos.
		9. Muestra todos los tratamientos disponibles, selecciona uno para ver su precio y cuántas veces aparece
		en el historial.
		10. Muestra las facturas, pide un número de mes y muestra las facturas de ese mes y el total facturado.
		11. Muestra los veterinarios y seleccionando un id muestra las facturas que ha emitido.
		12. Muestras las mascotas y actualiza su peso.
		13. Muestra las mascotas, selecciona una por id y elimínala si no tiene facturas asociadas.
		14. Guarda los veterinarios en el fichero “veterinarios.dat”. Lee ese fichero en una lista y muestra los
		datos de los veterinarios.
		15. Genera un resumen mensual por veterinario: pide un número de mes y muestra cada veterinario con
		el número de tratamientos realizados, el número de facturas emitidas y el importe total facturado ese
		mes
		16. Duplica una factura. Pide un id de factura, copia su cabecera, copia todas sus líneas de factura en una
		nueva factura con la fecha actual y muestra la nueva factura generada
		17. Elimina un tratamiento del historial y rehaz una factura. Selecciona una mascota, muestra su historial
		de una fecha concreta, elimina uno de los tratamientos de ese día y actualiza la factura asociada
		recalculando sus líneas, subtotal, IVA y total*/
	}

}
