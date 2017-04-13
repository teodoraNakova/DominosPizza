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
import javax.swing.plaf.synth.SynthSpinnerUI;

import model.Product;
import model_dao.ProductDAO;


@WebServlet("/productServlet")
public class productServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String product = (String) request.getParameter("product");
		String category = (String) request.getParameter("category");
		Product pro = null;
		try {
			ArrayList<Product> products = ProductDAO.getInstance().getAllProducts().get(category);
			for (Product p : products) {
				if(p.getName().equals(product)){
					pro = p;
					break;
				}
			}
			request.getSession().setAttribute("product", pro);
			request.getSession().setAttribute("subproducts", ProductDAO.getInstance().getAllProducts().get("Toppings"));
		} catch (SQLException e) {
			System.out.println("fsafa");
		}
		request.getRequestDispatcher("single-post.jsp").forward(request, response);
	}

}
