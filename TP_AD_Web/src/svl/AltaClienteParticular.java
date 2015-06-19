package svl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.controllers.ControladorPrincipal;

@WebServlet("/AltaClienteParticular")
public class AltaClienteParticular extends GenericHttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String dni = request.getParameter("dni");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");

		try {
			ControladorPrincipal.getInstance().getAdministradorClientes()
					.altaClienteParticular(dni, nombre, apellido);
			forwardGenerico(request, response, "Cliente agregado exitosamente.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			forwardGenerico(request, response, e.getMessage());
		}
	}
}
