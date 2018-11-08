package pl.justynababinska.weekop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.justynababinska.weekop.model.User;
import pl.justynababinska.weekop.service.DiscoveryService;

@WebServlet("/add")
public class DiscoveryAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getUserPrincipal() != null) {
			request.getRequestDispatcher("WEB-INF/new.jsp").forward(request, response);
		} else {
			response.sendError(403);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("inputTitle");
		String url = request.getParameter("inputUrl");
		String description = request.getParameter("inputDescription");
		User authenticatedUser = (User) request.getSession().getAttribute("user");
		if (request.getUserPrincipal() != null) {
			DiscoveryService discoveryService = new DiscoveryService();
			discoveryService.addDiscovery(title, description, url, authenticatedUser);
			response.sendRedirect(request.getContextPath() + "/");
		} else {
			response.sendError(403);
		}
	}

}
