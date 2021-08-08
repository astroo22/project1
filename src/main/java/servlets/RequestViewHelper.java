package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import controllers.LoginViewController;
public class RequestViewHelper {
	public static String process(HttpServletRequest req) throws ServletException, IOException{
		System.out.println("in the request helper, with the URI: " + req.getRequestURI());
		switch(req.getRequestURI()) {
		case "/project1/login":
			return LoginViewController.fetchLoginPage(req);
		}
		return "/project1/login";
	}
}
