package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Autor;
import Entities.Libro;
import Entities.Socio;
import Logic.AutorLogic;
import Logic.LibroLogic;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Servlet implementation class altaLibro
 */
@WebServlet("/altaLibro")
public class altaLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public altaLibro() {
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
		Socio socio = new Socio();		
		socio = (Socio)request.getSession().getAttribute("usuario");		
		String opc = request.getParameter("opcion");
		
		switch(opc) {
		case("crearLibro"):
			// Recupero los datos del libro del form.
			String isbn = request.getParameter("isbn");
			String titulo = request.getParameter("titulo");
			String editorial = request.getParameter("editorial");
			String fechaEdicion = request.getParameter("fechaEdicion");
			String maxDias = request.getParameter("maxDias");
			String idAutor = request.getParameter("autor");
			// Recupero el autor.
			AutorLogic autlog = new AutorLogic();
			Autor autor = new Autor();
			autor.setIdAutor(Integer.parseInt(idAutor));
			autor = autlog.getOneById(autor);
			// Creo un libro con los datos del form.
			LibroLogic liblog = new LibroLogic();
			Libro libro = new Libro();
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
			libro.setCantDiasMaxPrestamo(Integer.parseInt(maxDias));
			libro.setAutor(autor);			
			
			try {
				liblog.add(libro);
				String estado = "Alta existosa";
				request.setAttribute("estado", estado);	
			}catch (Exception e) {
	            e.printStackTrace();
			}		
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMLibros.jsp").forward(request, response);
			break;
		case("cancelar"):
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMLibros.jsp").forward(request, response);
			break;
		}
	}

}
