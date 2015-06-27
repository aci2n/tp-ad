package svl.clientes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svl.GenericHttpServlet;
import views.clientes.ParticularView;
import controllers.ControladorPrincipal;

/**
 * Servlet implementation class ListarClientes
 */
@WebServlet("/ListarClientes")
public class ListarClientes extends GenericHttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarClientes() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<ParticularView> clientes = ControladorPrincipal.getInstance().obtenerClientesParticulares();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clientes", clientes);
		
		forwardJsp(request, response, "/jsp/Clientes/ListarClientes.jsp", params);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
		
	}

}
