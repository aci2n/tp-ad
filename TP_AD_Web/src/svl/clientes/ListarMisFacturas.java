package svl.clientes;

import impl.cargas.Carga;
import impl.cobranzas.Factura;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.FacturaDAO;
import views.clientes.FacturaView;

import com.google.gson.Gson;

/**
 * Servlet implementation class ListarMisFacturas
 */
@WebServlet("/ListarMisFacturas")
public class ListarMisFacturas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarMisFacturas() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Obtiene el Id del input
		Integer id = Integer.parseInt(request.getParameter("id_cliente"));

		// Obtiene la Carga
		List<Factura> facturas = FacturaDAO.getInstance().getByCliente(id);
		List<FacturaView> fViews = new ArrayList<FacturaView>();
		for (Factura f : facturas)
			fViews.add(f.getView());

		// Genera json
		Gson gson = new Gson();
		String json = gson.toJson(fViews);
		System.out.println(json);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
