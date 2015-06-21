package svl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class GenericHttpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GenericHttpServlet() {

	}

	protected abstract void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;

	protected void forwardGenerico(HttpServletRequest request,
			HttpServletResponse response, String mensaje)
			throws ServletException, IOException {
		request.setAttribute("mensaje", mensaje);
		request.getRequestDispatcher("/WEB-INF/GenericForward.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ayy lmao
		doGet(request, response);
	}

}