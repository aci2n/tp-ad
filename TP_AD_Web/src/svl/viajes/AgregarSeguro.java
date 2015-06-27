package svl.viajes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svl.GenericHttpServlet;
import views.viajes.SeguroView;
import controllers.ControladorPrincipal;

@WebServlet("/jsp/Viajes/AgregarSeguro")
public class AgregarSeguro extends GenericHttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String tipo = request.getParameter("tipo");
		String tarifa = request.getParameter("tarifa");

		try {
			SeguroView s = new SeguroView(nombre, tipo, Float.parseFloat(tarifa));
			Integer i = ControladorPrincipal.getInstance().agregarSeguro(Integer.parseInt(id), s);
			forwardGenerico(request, response, "Compania de seguros agregada exitosamente con id: " + i + ".");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			forwardGenerico(request, response, e.getMessage());
		}
	}
}
