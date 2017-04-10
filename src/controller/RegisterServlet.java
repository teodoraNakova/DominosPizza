package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import validation.EmailSender;
import validation.Form;
import model.User;
import model_dao.UserDAO;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			// get parameters
			String firstName = req.getParameter("first name");
			String lastName = req.getParameter("last name");
			String password = req.getParameter("password");
			String confirmPassword = req.getParameter("confirm password");
			String email = req.getParameter("email");
			Form form = new Form();
			HttpSession session = null;
			
			if (UserDAO.getInstance().findByEmail(email) == null) {
				// validate parameters
				boolean validEmail = validateEmail(email);
				if(!validEmail) {
					form.addError(form.new Error("email", "Invalid email"));
					session.setAttribute("form", form);	
					return;
				}
				boolean validPassword = validatePassword(password);
				if(!validPassword){
					form.addError(form.new Error("password", "Incorrect password"));
					session.setAttribute("form", form);
					return;
				}
				if(!password.equals(confirmPassword)) {
					form.addError(form.new Error("confirm password", "Confirm password error"));
					session.setAttribute("form", form);
					return;
				}
				// if the data is not valid //if the data is valid
				if (validEmail && validPassword && password.equals(confirmPassword)) {
					User u = new User(firstName, lastName, email, password);
					EmailSender.sendValidationEmail("dominos.pizza.itt@gmail.com",
							"Dominos pizza verification code", "Please click on the following link: ");
					//TODO add link
					UserDAO.registeredUsers.put(u.getEmail(), u);
					//TODO redirect to login page
					//resp.sendRedirect("login.jsp");
				} else {
					//TODO stay on same page, keep the right data and ask the user to fill the form again
				}
			} else {
				form.addError(form.new Error("email", "This email is already registered"));
				session.setAttribute("form", form);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			//TODO redirect to error page
			RequestDispatcher rd = req.getRequestDispatcher("error500.html");
			rd.forward(req, resp);
		}
	}

	private boolean validateEmail(String email) {
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
				"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		boolean isTaken = false;
		try {
			isTaken = UserDAO.getInstance().isEmailFree(email);
		} catch (SQLException e) {
			System.out.println("Problem validating email.");
		}
		return matcher.find() && isTaken;
	}

	private boolean validatePassword(String password) {
		Pattern VALID_PASSWORD_REGEX = Pattern
				.compile("(?=.*[0-9])(?=.*[A-Z])(?=\\S+$).{8,}");
		// at least one digit,at least one upper case letter, at least 8
		// characters, no whitespaces
		Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
		return matcher.find();
	}
}
