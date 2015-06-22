package svl.clientes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.ControladorPrincipal;
import svl.GenericHttpServlet;
import views.clientes.ReceptorView;
import views.misc.CoordenadaView;
import views.misc.UbicacionView;

@WebServlet("/jsp/Clientes/AgregarReceptor")
public class AgregarReceptor extends GenericHttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String dni = request.getParameter("dni");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String pais = request.getParameter("pais");
		String provincia = request.getParameter("provincia");
		String ciudad = request.getParameter("ciudad");
		String calle = request.getParameter("calle");
		String altura = request.getParameter("altura");
		String piso = request.getParameter("piso");
		String departamento = request.getParameter("departamento");
		String latitud = request.getParameter("latitud");
		String longitud = request.getParameter("longitud");
		try {
			CoordenadaView c = new CoordenadaView(Float.parseFloat(latitud), Float.parseFloat(longitud));
			UbicacionView u = new UbicacionView(pais, provincia, ciudad, calle, altura, piso, departamento, c);
			ReceptorView r = new ReceptorView(dni, nombre, apellido, u);
			ControladorPrincipal.getInstance().getAdministradorClientes().agregarReceptor(Integer.parseInt(id), r);
			forwardGenerico(request, response, "Receptor agregado exitosamente.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			forwardGenerico(request, response, e.getMessage());
		}
	}
}
