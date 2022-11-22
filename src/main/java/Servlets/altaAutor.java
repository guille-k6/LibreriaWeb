package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Autor;
import Entities.Socio;
import Logic.AutorLogic;

/**
 * Servlet implementation class altaAutor
 */
@WebServlet("/altaAutor")
public class altaAutor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public altaAutor() {
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
		// Recupero el usuario y veo que opcion eligió
		Socio socio = new Socio();		
		socio = (Socio)request.getSession().getAttribute("usuario");		
		String opc = request.getParameter("opcion");
		
		switch(opc) {
		case("crearAutor"):
			// Recupero el nombre y el apellido del form.
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			// Creo un autor y lo cargo a la DB.
			AutorLogic autlog = new AutorLogic();
			Autor autor = new Autor();
			autor.setNombre(nombre);
			autor.setApellido(apellido);
			try {
				autlog.add(autor);
			}catch (Exception e) {
	            e.printStackTrace();
			}
			String estado = "Alta existosa";
			request.setAttribute("estado", estado);			
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMAutores.jsp").forward(request, response);
			break;
		case("cancelar"):
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMAutores.jsp").forward(request, response);
			break;
		}
	}

}
