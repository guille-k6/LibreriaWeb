package Logic;

import java.util.LinkedList;

import Data.DataAutor;
import Entities.Autor;

public class AutorLogic {
	private DataAutor dataAutor;

	public AutorLogic(){
		dataAutor = new DataAutor();
	}
	public LinkedList<Autor> getAll(){
		return dataAutor.getAll();
	}
	public Autor getOneById(Autor autor) {
		return dataAutor.getById(autor);
	}
	public void add(Autor autor) {
		dataAutor.add(autor);
	}
	public void update(Autor autor) {
		dataAutor.update(autor);
	}
	public void remove(Autor autor) {
		dataAutor.remove(autor);
	}

	public LinkedList<String> validar(Autor autor){
		LinkedList<String> losErrores = new LinkedList<>();
		if(autor.getNombre().equals("")) {
			losErrores.add("No se puede ingresar un nombre nulo.");
		}if(autor.getApellido().equals("")) {
			losErrores.add("No se puede ingresar un apellido nulo.");
		}
		return losErrores; // si losErrores est� vac�o significa que aprob� la validaci�n
	}
}
