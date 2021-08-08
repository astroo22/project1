package controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import dao.UserDao;

import dao.UserDaoHibernate;
import models.User;
import services.UserService;

public class LoginController {
	private static UserDaoHibernate uDao = new UserDaoHibernate();
	private static UserService uServ = new UserService(uDao);
	
	public static void login(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		//process login attempt
		//to read the data from a Json is a bit more complicated than converting a java object to json
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = req.getReader();
		
		
		//breaking up the JSON string object into separate lines to use later
		String line;
		while((line= reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
			
		}
		//we converted the string builder into a string which the ObjectMapper can read from
		
		String data = buffer.toString();
		System.out.println(data);

		ObjectMapper mapper = new ObjectMapper();
		//the object mpapper is capable of parsing the data out of the string that we created from the request
		JsonNode parsedObj = mapper.readTree(data);
		
		String username = parsedObj.get("username").asText();
		String password = parsedObj.get("password").asText();
		
		try {
			System.out.println("In the login attempt.");
			System.out.println(username);
			System.out.println(password);
			User u = uServ.signIn(username, password);
			System.out.println(u);
			String role = u.getUser_role().getUser_role().name();
			//getRole().getU_role().name();
			//below here we would want to save the users info in a session
			req.getSession().setAttribute("id", u.getId());
			req.getSession().setAttribute("role", role);
			res.setStatus(HttpServletResponse.SC_OK);
			res.addHeader("Access-Control-Allow-Origin", "*");
            res.setHeader("Access-Control-Allow-Methods", "POST");
            ObjectNode user = mapper.createObjectNode();
            System.out.println(role);
            user.put("id", u.getId());
            user.put("role", role);
			res.getWriter().write(new ObjectMapper().writeValueAsString(user));
		}catch(Exception e) {
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			res.getWriter().println("username or password incorrect you fool");
		}
		
	}
}
/* try {
            System.out.println("In The Login Handler");
            User u = uServ.signIn(username, password);
            System.out.println(u);
            String role = u.getRole().getU_role().name();
            req.getSession().setAttribute("role", role);

            req.getSession().setAttribute("id", u.getId());
            res.setStatus(HttpServletResponse.SC_OK);
            res.addHeader("Access-Control-Allow-Origin", "*");
            res.setHeader("Access-Control-Allow-Methods", "POST");
            ObjectNode user = mapper.createObjectNode();

            user.put("id", u.getId());
            user.put("role", role);
            res.getWriter().write(new ObjectMapper().writeValueAsString(user));
        }*/
