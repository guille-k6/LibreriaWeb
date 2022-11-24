package Servlets;

import java.io.IOException;
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
		// Traigo mi ID de autor y la opci�n elegida.
		
		String opc = request.getParameter("opcion");
		String elId = request.getParameter("id");
		int id = Integer.parseInt(elId);
		Libro libro = new Libro();
		libro.setIdLibro(id);
		
		LibroLogic liblog = new LibroLogic();
		AutorLogic autlog = new AutorLogic();
		// Cargo la opcion y confirmo si lo quiere eliminar o no.
		if(opc.equals("editar")){
			// Traigo los valores de los inputs
			String isbn = request.getParameter("isbn");
			String titulo = request.getParameter("titulo");
			String editorial = request.getParameter("editorial");
			String fechaEdicion = request.getParameter("fechaEdicion");
			int maxDias = Integer.parseInt(request.getParameter("maxDias"));
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
			libro.setFechaEdicion(fechaEdicion);
			libro.setCantDiasMaxPrestamo(maxDias);
			libro.setAutor(autor);
			
			
			// Updateo el autor con sus nuevos datos (nombre y apellido).
			liblog.update(libro);
			String estado = "Modificacion existosa";
			request.setAttribute("estado", estado);
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMLibros.jsp").forward(request, response);

		}else if(opc.equals("cancelar")) {
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMLibros.jsp").forward(request, response);
		}
	}

}
