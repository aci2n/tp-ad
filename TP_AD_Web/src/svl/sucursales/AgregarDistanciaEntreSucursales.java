package svl.sucursales;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svl.GenericHttpServlet;
import views.sucursales.DistanciaEntreSucursalesView;

@WebServlet("/jsp/Sucursales/AgregarDistanciaEntreSucursales")
public class AgregarDistanciaEntreSucursales extends GenericHttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idA = request.getParameter("idA");
		String idB = request.getParameter("idB");
		String km = request.getParameter("km");
		String horas = request.getParameter("horas");
		String costo = request.getParameter("costo");

		try {
			DistanciaEntreSucursalesView d = new DistanciaEntreSucursalesView(Integer.parseInt(idA), Integer.parseInt(idB), Float.parseFloat(km),
					Float.parseFloat(horas), Float.parseFloat(costo));
			delegate.getInterfaz().agregarDistanciaEntreSucursales(d);
			forwardGenerico(request, response, "Distancia entre sucursales agregada correctamente.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			forwardGenerico(request, response, e.getMessage());
		}
	}
}
