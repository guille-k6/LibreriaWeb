package Servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import utils.PasswordEncrypter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Socio;
import Logic.SocioLogic;

/**
 * Servlet implementation class modificarSocio
 */
@WebServlet("/modificarSocio")
public class modificarSocio extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public modificarSocio() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opc = request.getParameter("opcion");
		int id = Integer.parseInt(request.getParameter("id"));
		Socio Socio = new Socio(id);

		SocioLogic soclog = new SocioLogic();
		if (opc.equals("editar")) {
			Socio SocioModificar = soclog.getOneById(Socio);
			request.setAttribute("SocioModificar", SocioModificar);
			String apellido = request.getParameter("apellido");
			String nombre = request.getParameter("nombre");
			String email = request.getParameter("email");
			String domicilio = request.getParameter("domicilio");
			String telefono = request.getParameter("telefono");
			String contrasenia = request.getParameter("contrasenia");

			Socio.setApellido(apellido);
			Socio.setNombre(nombre);
			Socio.setEmail(email);
            Socio.setDomicilio(domicilio);
            Socio.setTelefono(telefono);
			if (contrasenia != null && !contrasenia.isBlank()) {
				Socio.setContrasenia(PasswordEncrypter.sha256(contrasenia));
			}
			try { // Si esta todo bien, ejecuto el update
				soclog.update(Socio);
				request.setAttribute("mensaje", "Modificacion existosa");
				request.getRequestDispatcher("WEB-INF/pages/admin/ABMSocios.jsp").forward(request, response);
			} catch (Exception e) {
				request.setAttribute("mensaje", "Error al modificar el Socio: " + Socio.getIdSocio());
				request.getRequestDispatcher("WEB-INF/pages/admin/ABMSocios.jsp").forward(request, response);
			}

		} else if (opc.equals("cancelar")) {
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMSocios.jsp").forward(request, response);
		}
	}

}
