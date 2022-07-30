package ConsoleTest;
import java.util.LinkedList;
import Entities.*;
import Logic.*;
public class Test {

	public static void main(String[] args) {
		AutorLogic autlog = new AutorLogic();
		
		// PRUEBA GETALL
		/*LinkedList<Autor> losAutores = autlog.getAll();
		for (Autor autor : losAutores) {
			System.out.println(autor.getIdAutor() +" "+ autor.getNombre());
		}*/
		
		// PRUEBA GETONEBYID
		/*Autor miAutor = new Autor();
		miAutor.setIdAutor(2);
		miAutor = autlog.getOneById(miAutor);
		System.out.println(miAutor.getIdAutor() +" "+ miAutor.getNombre());	*/
		
		// PRUEBA ADD
		/*Autor miAutor = new Autor();
		miAutor.setNombre("Julio");
		miAutor.setApellido("Verne");
		autlog.add(miAutor);
		System.out.println(miAutor.getIdAutor() +" "+ miAutor.getNombre());
		*/
		
		// PRUEBA UPDATE
		/*
		Autor miAutor = new Autor();
		miAutor.setIdAutor(2);
		miAutor = autlog.getOneById(miAutor);
		
		miAutor.setNombre("Pedro");
		miAutor.setApellido("Aznar");
		autlog.update(miAutor);
		System.out.println(miAutor.getIdAutor() +" "+ miAutor.getNombre()+" "+ miAutor.getApellido());
		*/
		
		// PRUEBA REMOVE
		/*
		Autor miAutor = new Autor();
		miAutor.setIdAutor(2);
		miAutor = autlog.getOneById(miAutor);
		autlog.remove(miAutor);
		*/
			
		
	}

}
