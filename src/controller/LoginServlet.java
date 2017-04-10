package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

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
			HttpSession session = req.getSession();
			Form form = new Form();
			User user = UserDAO.getInstance().findByEmail(email);
			
			if(user != null && !user.getIsVerified()) {
				LocalDateTime expireTime = user.getRegistrationTime().plusHours(1);
				LocalDateTime now = LocalDateTime.now();
				if(now.isAfter(expireTime)) {
					user.setIsVerified();
				} else {
					//redirect somewhere?
					//return
				}
			}
			
			boolean validLogin = UserDAO.getInstance().validLogin(user, email, password);	
			System.out.println(validLogin);
			
			if(validLogin) {
				session.setAttribute("user", user);
				session.setAttribute("logged", true);
				session.setMaxInactiveInterval(15*60); // 15 minutes
				//resp.sendRedirect(to main page);
			} else {
				//stay on the same page, keep the correct data
				form.addError(form.new Error("email", "Invalid email or password"));
				session.setAttribute("form", form);
			}
		} catch (Exception e) {
				System.out.println(e.getMessage());
				e.getStackTrace();
				RequestDispatcher rd = req.getRequestDispatcher("error500.html");
				rd.forward(req, resp);
		}
	}
}
