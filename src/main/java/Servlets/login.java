package Servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.util.StringUtils;

import Data.PasswordEncrypter;
import Entities.*;
import Logic.Login;

/**
 * Servlet implementation class login
 */
@WebServlet({ "/login", "/Login", "/LogIn", "/LOGIN" }) // 
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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
		// TODO Auto-generated method stub
		Socio socio = new Socio();
		Login login = new Login();
		
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		
		// validar user y pasword
		socio.setUsuario(usuario);
		socio.setContrasenia(password);
		
		String error = null;
		if(StringUtils.isNullOrEmpty(usuario.trim()) || StringUtils.isNullOrEmpty(password.trim())) {
			error = "Debe completar usuario y contraseña.";
			request.setAttribute("loginError", error);
			return;
		}
//		LinkedList<String> errores = login.validar(socio); // errores tiene la cantidad de cosas que no se validaron
		socio = login.validate(socio); // este me devuelve el socio
		
		
		if(socio!=null) {
			request.getSession().setAttribute("usuario", socio);
			if(socio.getAdmin()) {
				request.getRequestDispatcher("WEB-INF/pages/menuAdmin.jsp").forward(request, response);
			}	
			else{
				request.getRequestDispatcher("WEB-INF/pages/menuUser.jsp").forward(request, response);						
			}
		}else {
			response.getWriter().append("No se encontró usuario.");				
		}

	}
}
