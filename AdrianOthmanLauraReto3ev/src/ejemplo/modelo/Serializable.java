package ejemplo.modelo;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Serializable {
	public static void main(String[] args) {
			ArrayList<Veterinario> agenda = new ArrayList<>();
			agenda.add(new Veterinario("Ana", "600111222", "ana@correo.es"));
			agenda.add(new Veterinario("Luis", "611222333", "luis@correo.es"));

			try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("agenda.dat"))) {
				out.writeObject(agenda);
				System.out.println("Agenda guardada.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}