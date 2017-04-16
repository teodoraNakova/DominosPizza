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
			ArrayList<Product> sub = new ArrayList<>();
			ArrayList<Product> temp = new ArrayList<>();
			temp.addAll(ProductDAO.getInstance().getAllProducts().get("Toppings"));
			temp.addAll(ProductDAO.getInstance().getAllProducts().get("Crusts"));
			temp.addAll(ProductDAO.getInstance().getAllProducts().get("Size"));
			
			for (String subpro : pro.getSubproducts()) {
				for (Product product2 : temp) {
					if(!subpro.equals(product2.getName()) && !sub.contains(product2)){
						sub.add(product2);
					}
				}
			}

			ArrayList<Product> temp2 = new ArrayList<>();
			for (Product subpro : sub) {
				if(!pro.getSubproducts().contains(subpro.getName())){
					temp2.add(subpro);
				}
			}
			request.getSession().setAttribute("subproducts", temp);
		} catch (SQLException e) {
			System.out.println("fsafa");
		}
		request.getRequestDispatcher("single-post.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] subproducts = req.getParameterValues("subproduct");
		for (String string : subproducts) {
			System.out.println(string);
		}
	}

}
