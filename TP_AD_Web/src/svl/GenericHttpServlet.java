package svl;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class GenericHttpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GenericHttpServlet() {
	}

	protected abstract void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	protected void forwardGenerico(HttpServletRequest request, HttpServletResponse response, String mensaje) throws ServletException, IOException {
		request.setAttribute("mensaje", mensaje);
		request.getRequestDispatcher("/jsp/Misc/GenericForward.jsp").forward(request, response);
	}
	
	protected void forwardJsp(HttpServletRequest request, HttpServletResponse response, String path, Map<String, Object> params) throws ServletException, IOException {
		for (String key : params.keySet()) {
			request.setAttribute(key, params.get(key));
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ayy lmao
		doGet(request, response);
	}
}
