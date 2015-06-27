package svl.viajes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svl.GenericHttpServlet;
import views.viajes.CompaniaSeguroView;
import controllers.ControladorPrincipal;

@WebServlet("/jsp/Viajes/AltaCompaniaSeguro")
public class AltaCompaniaSeguro extends GenericHttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cuit = request.getParameter("cuil");
		String nombre = request.getParameter("nombre");

		try {
			CompaniaSeguroView c = new CompaniaSeguroView(cuit, nombre);
			Integer i = ControladorPrincipal.getInstance().altaCompaniaSeguro(c);
			forwardGenerico(request, response, "Compania de seguros agregada exitosamente con id: " + i + ".");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			forwardGenerico(request, response, e.getMessage());
		}
	}
}
