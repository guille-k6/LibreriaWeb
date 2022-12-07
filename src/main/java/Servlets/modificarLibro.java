package Servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Autor;
import Entities.Libro;
import Logic.AutorLogic;
import Logic.LibroLogic;

/**
 * Servlet implementation class modificarLibro
 */
@WebServlet("/modificarLibro")
public class modificarLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modificarLibro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Traigo mi ID de autor y la opción elegida.
		
		String opc = request.getParameter("opcion");
		String elId = request.getParameter("id");
		int id = Integer.parseInt(elId);
		Libro libro = new Libro();
		libro.setIdLibro(id);
		
		LibroLogic liblog = new LibroLogic();
		AutorLogic autlog = new AutorLogic();
		// Cargo la opcion y confirmo si lo quiere eliminar o no.
		if(opc.equals("editar")){
			// Guardo el atributo autorModificar para que se carguen los datos en la recarga
			Libro libroModificar = liblog.getOneById(libro);
			request.setAttribute("libroModificar", libroModificar);
			
			// Traigo los valores de los inputs
			
			String isbn = request.getParameter("isbn");
			String titulo = request.getParameter("titulo");
			String editorial = request.getParameter("editorial");
			String fechaEdicion = request.getParameter("fechaEdicion");
			String maxDias = request.getParameter("maxDias");
			// Recupero el id de autor y lo inicializo con sus valores
			int idAutor = Integer.parseInt(request.getParameter("autor"));
			Autor autor = new Autor();
			autor.setIdAutor(idAutor);
			autor = autlog.getOneById(autor);
			
			// Lleno los datos del libro a modificar con sus respectivos valores
			libro.setIdLibro(id);
			libro.setIsbn(isbn);
			libro.setTitulo(titulo);
			libro.setEditorial(editorial);
			// Parseo la fecha de edicicon como Date
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// se usa java.util.date porque el parse es exclusivo de este pero luego se transfarma de util.date a sql.date
			java.util.Date parsed = null;
			try {
				parsed = sdf.parse(fechaEdicion);
				} catch (ParseException e1) {
				// TODO Auto-generated catch block
						    e1.printStackTrace();
				}
			java.sql.Date data = new java.sql.Date(parsed.getTime());
			libro.setFechaEdicion(data);
			// Parse int no funciona con un string vacio y un int no puede ser nulo, por default es 0 asi que le asigno 0
			// if(maxDias == "") {maxDias="0";}
			// libro.setCantDiasMaxPrestamo(Integer.parseInt(maxDias));
			libro.setAutor(autor);
			LinkedList<String> errores = liblog.validar(libro,maxDias);
			if(!errores.isEmpty()) { // HAY ERRORES
				request.setAttribute("listaErrores", errores);
				request.getRequestDispatcher("WEB-INF/pages/admin/ModificarLibros.jsp").forward(request, response);		
				return;
			}
			
			// Updateo el autor con sus nuevos datos (nombre y apellido).
			libro.setCantDiasMaxPrestamo(Integer.parseInt(maxDias));
			liblog.update(libro);
			String estado = "Modificacion existosa";
			request.setAttribute("estado", estado);
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMLibros.jsp").forward(request, response);

		}else if(opc.equals("cancelar")) {
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMLibros.jsp").forward(request, response);
		}
	}

}
