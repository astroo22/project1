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
			case "/project1/api/getAllPending":
				ReimbursementController.getAllPendingReimbursements(req, res);
				break;
			case "/project1/api/getAllApproved":
				ReimbursementController.getAllReimbursements(req, res);
				break;
			case "/project1/api/getAllDenied":
				ReimbursementController.getAllDeniedReimbursements(req, res);
				break;
			case "/project1/api/getAllReimbursements":
				ReimbursementController.getAllReimbursements(req, res);
				break;
			case "/project1/api/getAllByUser":
				ReimbursementController.getAllByUser(req, res);
				break;
			case "/project1/api/getAllReimbursementsByUser":
				ReimbursementController.getAllByUser(req, res);
				break;
			case "/project1/api/getAllApprovedReimbursementsByUser":
				ReimbursementController.getAllApprovedReimbursementsByUser(req, res);
				break;
			case "/project1/api/getAllDeniedReimbursementsByUser":
				ReimbursementController.getAllDeniedReimbursementsByUser(req, res);
				break;
			case "/project1/api/getAllPendingReimbursementsByUser":
				ReimbursementController.getAllPendingReimbursementsByUser(req, res);
				break;
			case "/project1/api/approveReimbursement":
				ReimbursementController.approveReimbursement(req, res);
				break;
			case "/project1/api/denyReimbursement":
				ReimbursementController.denyReimbursement(req, res);
				break;
		}
	}
}
