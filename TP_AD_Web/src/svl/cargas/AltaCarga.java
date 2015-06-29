package svl.cargas;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svl.GenericHttpServlet;

@WebServlet("/jsp/Clientes/AgregarReceptor")
public class AltaCarga extends GenericHttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8589610416039812469L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String tipoCarga = request.getParameter("tipoCarga");
		String cuit = request.getParameter("cuit");
		String productos = request.getParameter("productos");
		String idSucursalOrigen = request.getParameter("idSucursalOrigen");
		String fechaMaxEntrega = request.getParameter("fechaMaxEntrega");
		String manifiesto = request.getParameter("manifiesto");
		String retira = request.getParameter("retira");
		String idSucursalDestino = request.getParameter("idSucursalDestino");
		String pais = request.getParameter("pais");
		String provincia = request.getParameter("provincia");
		String ciudad = request.getParameter("ciudad");
		String calle = request.getParameter("calle");
		String altura = request.getParameter("altura");
		String departamento = request.getParameter("departamento");
		String piso = request.getParameter("piso");
		String longitud = request.getParameter("longitud");
		String latitud = request.getParameter("latitud");		
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
