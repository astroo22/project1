package servlets;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import controllers.LoginController;
import controllers.LogoutController;
import controllers.ProjectViewController;
import controllers.ReimbursementController;
import controllers.SessionController;
public class ServletJSONHelper {
	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		System.out.println("In the ServletJSONHelper with URI: " + req.getRequestURI());
		switch(req.getRequestURI()) {
			case "/project1/api/login":
				LoginController.login(req, res);
				break;
			case "/project1/api/EAcc":
				//ProjectViewController.fetchProject1Page(req);
				break;
			case "/project1/api/ELanding":
				break;
			case "/project1/api/getSession":
				SessionController.getSession(req, res);
				break;
			case "/project1/api/EPending":
				break;
			case "/project1/api/EReimb":
				ReimbursementController.addReimbursements(req, res);
				break;
			case "/project1/api/logout":
				LogoutController.logout(req, res);
				break;
			case "/project1/api/getUser":
				ReimbursementController.getUserById(req, res);
				break;
		}
	}
}
