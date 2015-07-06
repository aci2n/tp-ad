package svl.cargas;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rmi.delegate.BusinessDelegate;
import views.cargas.SeguimientoCargaView;

import com.google.gson.Gson;

/**
 * Servlet implementation class SeguimientoCarga
 */
@WebServlet("/SeguimientoCargas")
public class SeguimientoCargas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SeguimientoCargas() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Obtiene el Id del input
			Integer id = Integer.parseInt(request.getParameter("id_carga"));

			// TODO
			// Ver lo de seguimiento de cargas

			// Obtiene la Carga
			List<SeguimientoCargaView> seguimientoCargas = BusinessDelegate.getInstance().getInterfaz().getSeguimientosCarga(id);

			// Genera json
			Gson gson = new Gson();
			String json = gson.toJson(seguimientoCargas);
			System.out.println(json);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
