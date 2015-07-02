package svl.cargas;

import impl.cargas.Carga;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.CargaDAO;
import rmi.delegate.BusinessDelegate;
import views.cargas.CargaView;

import com.google.gson.Gson;

/**
 * Servlet implementation class SeguimientoCarga
 */
@WebServlet("/SeguimientoCarga")
public class SeguimientoCarga extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SeguimientoCarga() {
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
		String id = request.getParameter("id_carga");

		// Obtiene la Carga
		List<Carga> cargas = CargaDAO.getInstance().getAll();
		List<CargaView> cviews = new ArrayList<CargaView>();
		for(Carga c : cargas)
			cviews.add(c.getView());

		// Genera json
		Gson gson = new Gson();
		String json = gson.toJson(cviews);
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
