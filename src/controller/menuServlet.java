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

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import model.Product;
import model_dao.ProductDAO;

/**
 * Servlet implementation class menuServlet
 */
@WebServlet("/menu")
public class menuServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request);
		System.out.println(request.getParameter("category"));
		String category = (String) request.getParameter("category");
		List<Product> products = null;
		try {
			products = ProductDAO.getInstance().getAllProducts().get(category);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str = new Gson().toJson(products);
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(str);
		
		
	}

}
