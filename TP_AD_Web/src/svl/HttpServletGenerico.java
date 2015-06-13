package svl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class HttpServletGenerico extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HttpServletGenerico() {

	}

	protected abstract void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;

	protected void manejarError(HttpServletRequest request,
			HttpServletResponse response, String mensaje)
			throws ServletException, IOException {
		request.setAttribute("error", mensaje);
		request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(
				request, response);
	}
	
	protected void manejarExito(HttpServletRequest request,
			HttpServletResponse response, String mensaje)
			throws ServletException, IOException {
		request.setAttribute("mensaje", mensaje);
		request.getRequestDispatcher("/WEB-INF/OK.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ayy lmao
		doGet(request, response);
	}

}
