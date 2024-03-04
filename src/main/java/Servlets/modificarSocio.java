package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Socio;
import Logic.SocioLogic;
import utils.PasswordEncrypter;

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
		Socio socio = new Socio(id);

		SocioLogic soclog = new SocioLogic();
		if (opc.equals("editar")) {
			Socio socioModificar = soclog.getOneById(socio);
			request.setAttribute("SocioModificar", socioModificar);
			String apellido = request.getParameter("apellido");
			String nombre = request.getParameter("nombre");
			String email = request.getParameter("email");
			String domicilio = request.getParameter("domicilio");
			String telefono = request.getParameter("telefono");
			String contrasenia = request.getParameter("contrasenia");
			String isAdminString = request.getParameter("isadmin");
			String usuario = request.getParameter("usuario");

			socio.setApellido(apellido);
			socio.setNombre(nombre);
			socio.setEmail(email);
			socio.setDomicilio(domicilio);
			socio.setTelefono(telefono);
			if (soclog.isUsernameAvailable(usuario) || socioModificar.getUsuario().equals(usuario)) {
				socio.setUsuario(usuario);
			} else {
				request.setAttribute("mensaje", "El usuario: " + usuario + ". No esta disponible");
				request.getRequestDispatcher("WEB-INF/pages/admin/ABMSocios.jsp").forward(request, response);
			}
			boolean isAdmin = false;
			if (isAdminString != null) {
				isAdmin = true;
			}
			socio.setAdmin(isAdmin);
			try { // Si esta todo bien, ejecuto el update
				if (contrasenia != null && !contrasenia.isBlank()) {
					socio.setContrasenia(PasswordEncrypter.sha256(contrasenia));
					soclog.update(socio);
				} else {
					soclog.updateWithoutPassword(socio);
				}
				request.setAttribute("mensaje", "Modificacion existosa");
				request.getRequestDispatcher("WEB-INF/pages/admin/ABMSocios.jsp").forward(request, response);
			} catch (Exception e) {
				request.setAttribute("mensaje", "Error al modificar el Socio: " + socio.getIdSocio());
				request.getRequestDispatcher("WEB-INF/pages/admin/ABMSocios.jsp").forward(request, response);
			}

		} else if (opc.equals("cancelar")) {
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMSocios.jsp").forward(request, response);
		}
	}

}
