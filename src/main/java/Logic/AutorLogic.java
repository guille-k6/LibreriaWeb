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
}
