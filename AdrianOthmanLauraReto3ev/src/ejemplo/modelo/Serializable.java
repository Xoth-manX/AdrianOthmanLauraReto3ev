package ejemplo.modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Serializable {
	public static void main(String[] args) {
		try (ObjectInputStream in =
                new ObjectInputStream(new FileInputStream("agenda.dat"))) {

           ArrayList<Veterinario> agenda = (ArrayList<Veterinario>) in.readObject();

           for (Veterinario v : agenda) {
               System.out.println(v);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

	}