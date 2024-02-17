package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Prestamo;
import Logic.PrestamoLogic;

@WebServlet("/verUnPrestamo")
public class verUnPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public verUnPrestamo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idPrestamoString = request.getParameter("ver");
		int idPrestamo = Integer.valueOf(idPrestamoString);
		PrestamoLogic pl = new PrestamoLogic();
		Prestamo prestamo = pl.getOneById(new Prestamo(idPrestamo));
		request.setAttribute("prestamo", prestamo);
		request.getRequestDispatcher("WEB-INF/pages/admin/PrestamoDetallado.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
