package ejemplo.modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class S1 {
	public static ArrayList<Veterinario> leer() {
		try (ObjectInputStream in =
                new ObjectInputStream(new FileInputStream("agenda.dat"))) {

           return (ArrayList<Veterinario>) in.readObject();

       } catch (Exception e) {
           e.printStackTrace();
       }
		return null;
   }
	
	
	public static void guardar(List<Veterinario> list) {
		
		try (ObjectOutputStream out = new ObjectOutputStream(new
				FileOutputStream("agenda.dat"))) {
				 out.writeObject(list);
				
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

}