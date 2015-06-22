package svl.vehiculos;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.ControladorPrincipal;
import svl.GenericHttpServlet;

@WebServlet("/jsp/Vehiculos/AltaProveedor")
public class AltaProveedor extends GenericHttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cuit = request.getParameter("cuit");
		String nombre = request.getParameter("nombre");		
		try {
			Integer id = ControladorPrincipal.getInstance().getAdministradorVehiculos().altaProveedor(cuit, nombre);
			forwardGenerico(request, response, "Proveedor agregado exitosamente con id: " + id + ".");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			forwardGenerico(request, response, e.getMessage());
		}
	}
}
