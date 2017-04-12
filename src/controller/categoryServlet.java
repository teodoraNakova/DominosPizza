package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import model_dao.ProductDAO;

/**
 * Servlet implementation class categoryServlet
 */
@WebServlet("/category")
public class categoryServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ehooooooooooooooooooo");
		List<String> categories = null;
		List<Product> products = new ArrayList<>();
		try {
			categories = new ArrayList<String>(ProductDAO.getInstance().getAllProducts().keySet());
			for (String category : categories) {
				products.addAll(ProductDAO.getInstance().getAllProducts().get(category));
			}
		} catch (SQLException e) {
			System.out.println("MenuServlet problem");
			e.printStackTrace();
		}
		request.getSession().setAttribute("products", products);
		request.getSession().setAttribute("categories", categories);
		System.out.println(categories);
		request.getRequestDispatcher("products.jsp").forward(request, response);
	}


}
