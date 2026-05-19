package ejemplo.app;

import java.lang.classfile.instruction.SwitchCase;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ejemplo.dao.ClienteDAO;
import ejemplo.dao.FacturaDAO;
import ejemplo.dao.LineaFacturaDAO;
import ejemplo.modelo.Cliente;
import ejemplo.modelo.Factura;
import ejemplo.modelo.LineaFactura;

public class MainLaura {
	public static FacturaDAO facturaDAO;
	public static Scanner sc;
	public static LineaFacturaDAO lineafacturaDAO;
	public static ClienteDAO clienteDAO;

	public static void main(String[] args) {
		int opcion=1;
		sc = new Scanner(System.in);
		System.out.println("ingrese una opcion hasta 0 para salir \n"+".".repeat(50));
		mostrar();
		
		String opc= sc.nextLine();
			opcion = Integer.parseInt(opc);

	
			switch (opcion) {
            case 1: ; break;
            case 2: ; break;
            case 3: ; break;
            case 4: facturaDAO.factura4(); break;
            case 5: ; break;
            case 6: ; break;
            case 7: ; break;
            case 8: ; break;
            case 9: ; break;
            case 10: facturaDAO.FacturaporMes10() ; break;
            case 11: ; break;
            case 12: ; break;
            case 13: ; break;
            case 14: ; break;
            case 15: ; break;
            case 16: facturaDAO.Duplicafactura16() ; break;
            case 17: facturaDAO.eliminaFactHist();; break;
            case 0: System.out.println("Final"); break;
            default: System.out.println("Opción no válida");
        }
		
		


			//lineafacturaDAO = new LineaFacturaDAO();
		//facturaDAO = new FacturaDAO();

		// FacturaporId4();
		// facturaDAO.FacturaporMes10();
		//facturaDAO.Duplicafactura16();
		//facturaDAO.factura4();

		// 1. Muestra los tratamientos que ha realizado el veterinario id=2

		/*
		 * 2. Muestra todos los clientes, selecciona un id y muestras sus mascotas. 3.
		 * Muestra todas las mascotas y selecciona un id y muestra el historial completo
		 * de esa mascota. 4. Muestra una factura por id junto con todas sus líneas de
		 * factura. 5. Muestra todos los clientes y selecciona uno por id y muestra sus
		 * datos, sus mascotas y sus facturas asociadas. 6. Añade un veterinario:
		 * 12345678Z Pepe Carrera, COL-1005 7. Añadir nueva mascota: Añade una mascota
		 * al cliente id=2, pide los datos por teclado. 8. Tratar mascota: Muestra todas
		 * las mascotas, seleccionar una por id. Muestra los tratamientos disponibles y
		 * añade tratamientos seleccionando el id en el historial, hasta introducir 0 y
		 * después crea una factura con esas líneas. Muestra la factura con todos sus
		 * datos. 9. Muestra todos los tratamientos disponibles, selecciona uno para ver
		 * su precio y cuántas veces aparece en el historial. 10. Muestra las facturas,
		 * pide un número de mes y muestra las facturas de ese mes y el total facturado.
		 * 11. Muestra los veterinarios y seleccionando un id muestra las facturas que
		 * ha emitido. 12. Muestras las mascotas y actualiza su peso. 13. Muestra las
		 * mascotas, selecciona una por id y elimínala si no tiene facturas asociadas.
		 * 14. Guarda los veterinarios en el fichero “veterinarios.dat”. Lee ese fichero
		 * en una lista y muestra los datos de los veterinarios. 
		 * 15. Genera un resumen
		 * mensual por veterinario: pide un número de mes y muestra cada veterinario con
		 * el número de tratamientos realizados, el número de facturas emitidas y el
		 * importe total facturado ese mes 16. Duplica una factura. Pide un id de
		 * factura, copia su cabecera, copia todas sus líneas de factura en una nueva
		 * factura con la fecha actual y muestra la nueva factura generada 
		 * 17. Elimina * un tratamiento del historial y rehaz una factura. Selecciona una mascota,
		 * muestra su historial de una fecha concreta, elimina uno de los tratamientos
		 * de ese día y actualiza la factura asociada recalculando sus líneas, subtotal,
		 * IVA y total
		 */
	}

	public static void mostrar() {
		System.out.println("1. Los tratamientos que ha realizado el veterinario id=2");
		System.out.println("2. Todos los clientes, selecciona un id y muestras sus mascotas");
		System.out.println("3. Todas las mascotas y selecciona un id y muestra el historial completo de esa mascota");
		System.out.println("4. Una factura por id junto con todas sus líneas de factura");
		System.out.println("5. Cliente completo: datos, mascotas y facturas");
		System.out.println("6. Añadir veterinario ; Ejemplo 6.	Añade un veterinario: 12345678Z Pepe Carrera, COL-1005");
		System.out.println("7. Añadir mascota al cliente id=2");
		System.out.println("8. Tratar mascota y crear factura");
		System.out.println("9. Buscar tratamiento en historial");
		System.out.println("10. Facturas de un mes y total");
		System.out.println("11. Facturas emitidas por un veterinario");
		System.out.println("12. Actualizar peso de mascota");
		System.out.println("13. Eliminar mascota (sin facturas)");
		System.out.println("14. Guardar veterinarios en fichero");
		System.out.println("15. Resumen mensual por veterinario");
		System.out.println("16. Duplicar una factura");
		System.out.println("17. Eliminar tratamiento y recalcular factura");
		System.out.println("0. Salir");
		System.out.print("Opción: ");
	}

}
