package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import dao.ReimbursementDao;
import dao.ReimbursementDaoDB;
import dao.UserDaoHibernate;
import dao.UserRoleDaoDB;
import enums.RStatus;
import enums.ReibursementType;
import models.Reimbursement;
import models.ReimbursementStatus;
import models.ReimbursementType;
import models.User;
import services.ReimbursementService;
import services.UserService;

public class ReimbursementController {
	private static UserDaoHibernate uDao= new UserDaoHibernate();
	private static UserService uServ = new UserService(uDao);
	private static UserRoleDaoDB urDao = new UserRoleDaoDB();
	private static ReimbursementDao rDao = new ReimbursementDaoDB();
	private static ReimbursementService rServ = new ReimbursementService(rDao);
	
	public static void getAllByUser(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
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
		String userName = parsedObj.get("username").asText();
		User u = uServ.getUserByUsername(userName);
		List<Reimbursement> rl = rServ.getAllReimbursementsForUser(u);
		System.out.println("In getAllByUser");
		System.out.println(rl);
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "GET");
		res.getWriter().write(new ObjectMapper().writeValueAsString(rl));
	}
	
	public static void addReimbursements(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		System.out.println("addReimbursements");
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = req.getReader();
		//breaking up the JSON string object into separate lines to use later
		String line;
		while((line= reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		System.out.println("after while");
		//we converted the string builder into a string which the ObjectMapper can read from
		String data = buffer.toString();
		System.out.println(data);
		System.out.println("idk");
		ObjectMapper mapper = new ObjectMapper();
		//the object mpapper is capable of parsing the data out of the string that we created from the request
		JsonNode parsedObj = mapper.readTree(data);
		
		
		String retype = parsedObj.get("type").asText();
	
		System.out.println("retype var in addReimbursement");
		System.out.println(retype);
		double amount = Double.parseDouble(parsedObj.get("amount").asText());
		//wanted to use timestamp?
		Timestamp submittedTS = Timestamp.from(Instant.now());
		String description = parsedObj.get("description").asText();
		int userNameID = parsedObj.get("userID").asInt();
		User creator = uServ.getUserById(userNameID);
		System.out.println("userv in addReimbursement");
		ReimbursementStatus status = new ReimbursementStatus(2,RStatus.PENDING);
		rServ.addReimbursement(retype,amount,submittedTS,description,status,creator);
		System.out.println("rserv in addReimbursement");
		ObjectNode ret = mapper.createObjectNode();
		ret.put("message",  "Successfully submitted a new request maybe who knows i dont");
		res.getWriter().write(new ObjectMapper().writeValueAsString(ret));
		
	}
	public static void approveReimbursement(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line;
		while((line= reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		String data = buffer.toString();
		System.out.println(data);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode parsedObj = mapper.readTree(data);
		
		Timestamp ts = Timestamp.from(Instant.now());
		//reimbursement id
		int rid = Integer.parseInt(parsedObj.get("r_id").asText());
		Reimbursement r = rDao.getReimbursementById(rid);
		int managerId = Integer.parseInt(parsedObj.get("manager_id").asText());
		User manager = uDao.getUserById(managerId);
		ReimbursementStatus status = new ReimbursementStatus(2,RStatus.APPROVED);
		ObjectNode ret = mapper.createObjectNode();
		ret.put("message",  "Successfully approved a request maybe who knows i dont");
		rServ.updateReimbursement(r.getId(), r.getReimb_Amount(), r.getSubmitted(), ts,
				r.getDescription(), status, r.getType(), r.getEmployee(), manager);
		res.getWriter().write(new ObjectMapper().writeValueAsString(ret));
		
	}
	public static void denyReimbursement(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line;
		while((line= reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		String data = buffer.toString();
		System.out.println(data);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode parsedObj = mapper.readTree(data);
		
		Timestamp ts = Timestamp.from(Instant.now());
		//reimbursement id
		int rid = Integer.parseInt(parsedObj.get("r_id").asText());
		Reimbursement r = rDao.getReimbursementById(rid);
		int managerId = Integer.parseInt(parsedObj.get("manager_id").asText());
		User manager = uDao.getUserById(managerId);
		ReimbursementStatus status = new ReimbursementStatus(2,RStatus.DENIED);
		ObjectNode ret = mapper.createObjectNode();
		ret.put("message",  "Successfully approved a DENYING request maybe idk i copy pastad this code from above");
		rServ.updateReimbursement(r.getId(), r.getReimb_Amount(), r.getSubmitted(), ts,
				r.getDescription(), status, r.getType(), r.getEmployee(), manager);
		res.getWriter().write(new ObjectMapper().writeValueAsString(ret));
	}
	public static void getUserById(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		int id = Integer.parseInt(req.getSession().getAttribute("id").toString());
		User u = uServ.getUserById(id);
		System.out.println(u);
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "GET");
		res.getWriter().write(new ObjectMapper().writeValueAsString(u));
	}

	
	
	
}

