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
 * Servlet implementation class altaSocio
 */
@WebServlet("/altaSocio")
public class altaSocio extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public altaSocio() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SocioLogic soclog = new SocioLogic();
		String opc = request.getParameter("opcion");
		if (opc.equals("crearSocio")) {
			String apellido = request.getParameter("apellido");
			String nombre = request.getParameter("nombre");
			String email = request.getParameter("email");
			String domicilio = request.getParameter("domicilio");
			String telefono = request.getParameter("telefono");
			String contrasenia = request.getParameter("contrasenia");
			String repetirContrasenia = request.getParameter("repetirContrasenia");
			String isAdmin = request.getParameter("isAdmin");
			boolean isAdminBool = false;
			if (isAdmin!=null) {
                isAdminBool = true;
            }
			String usuario = request.getParameter("usuario");
			Socio Socio = new Socio();
			Socio.setApellido(apellido);
			Socio.setNombre(nombre);
			Socio.setEmail(email);
			Socio.setDomicilio(domicilio);
			Socio.setTelefono(telefono);
			Socio.setAdmin(isAdminBool);
			Socio.setUsuario(email);
			Socio.setUsuario(usuario);
			if (!contrasenia.equals(repetirContrasenia)) {
				request.setAttribute("listaErrores", new LinkedList<String>(List.of("Las contraseñas no coinciden")));
				request.getRequestDispatcher("WEB-INF/pages/admin/AltaSocios.jsp").forward(request, response);
				return;
			}
            Socio.setContrasenia(contrasenia);
			try { // Si esta todo bien, ejecuto el update
				String hashedPassword = PasswordEncrypter.sha256(Socio.getContrasenia());
				Socio.setContrasenia(hashedPassword);
				soclog.add(Socio);
				request.setAttribute("mensaje", "Alta existosa");
				request.getRequestDispatcher("WEB-INF/pages/admin/ABMSocios.jsp").forward(request, response);
			} catch (Exception e) {
				request.setAttribute("mensaje", "Error al dar de alta el Socio");
				request.getRequestDispatcher("WEB-INF/pages/admin/ABMSocios.jsp").forward(request, response);
			}
		} else if (opc.equals("cancelar")) {
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMSocios.jsp").forward(request, response);
		}
	}

}
