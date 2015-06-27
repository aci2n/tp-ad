package svl.viajes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svl.GenericHttpServlet;

@WebServlet("/jsp/Viajes/AgregarCondicionEspecialAViaje")
public class AgregarCondicionEspecialAViaje extends GenericHttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String condicionEspecial = request.getParameter("condicionEspecial");

		try {
			delegate.getInterfaz().agregarCondicionEspecialAViaje(Integer.parseInt(id), condicionEspecial);
			forwardGenerico(request, response, "Condicion especial agregada exitosamente.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			forwardGenerico(request, response, e.getMessage());
		}
	}
}
