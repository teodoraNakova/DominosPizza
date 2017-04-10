package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import model_dao.ProductDAO;

/**
 * Servlet implementation class menuServlet
 */
@WebServlet("/menu")
public class menuServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ehooooooooooooooooooo");
		List<String> categories = null;
		try {
			categories = new ArrayList<String>(ProductDAO.getInstance().getAllProducts().keySet());
		} catch (SQLException e) {
			System.out.println("MenuServlet problem");
			e.printStackTrace();
		}
		request.getSession().setAttribute("categories", categories);
		System.out.println(categories);
		request.getRequestDispatcher("menu.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("why are you clicking on me");
		System.out.println(request.getParameter("category"));
		String category = (String) request.getParameter("category");
		List<Product> products = null;
		try {
			products = ProductDAO.getInstance().getAllProducts().get(category);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
	    System.out.println(products);
		response.getWriter().append(products.toString());

		
		
	}

}
