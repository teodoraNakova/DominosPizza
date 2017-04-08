package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import validation.Form;
import model.User;
import model_dao.UserDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {		
		try{
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			HttpSession session = null;
			String error = "";	
			User user = UserDAO.getInstance().findByEmail(email);
			boolean validLogin = UserDAO.getInstance().validLogin(user, email, password);
			Form form = new Form();
			System.out.println(validLogin);
			if(validLogin) {
				session = req.getSession();
				//user id?
				session.setAttribute("logged", true);
				session.setMaxInactiveInterval(15*60); // 15 minutes
				session.setAttribute("user", user);
				//resp.sendRedirect(to main page);
			} else {
				//stay on the same page, keep the correct data
				//form.addError(new Form.Error("email", "Invalid email or password"));
			}
		} catch (Exception e) {
				System.out.println(e.getMessage());
				e.getStackTrace();
				RequestDispatcher rd = req.getRequestDispatcher("error500.html");
				rd.forward(req, resp);
		}
	}
}