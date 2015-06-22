package svl.sucursales;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svl.GenericHttpServlet;
import views.misc.CoordenadaView;
import views.misc.UbicacionView;
import views.sucursales.SucursalView;
import controllers.ControladorPrincipal;

@WebServlet("/jsp/Sucursales/AltaSucursal")
public class AltaSucursal extends GenericHttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
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
			SucursalView s = new SucursalView(nombre, u);
			Integer id = ControladorPrincipal.getInstance().getAdministradorSucursales().altaSucursal(s);
			forwardGenerico(request, response, "Sucursal agregada exitosamente con id: " + id + ".");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			forwardGenerico(request, response, e.getMessage());
		}
	}
}
