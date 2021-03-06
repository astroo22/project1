package driver;

import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import org.apache.log4j.Logger;

import controllers.ReimbursementController;
import dao.ReimbursementDaoDB;
import dao.StatusDao;
import dao.TypeDao;
import dao.UserDaoHibernate;
import dao.UserRoleDaoDB;
import enums.URoles;
import models.Reimbursement;
import models.ReimbursementStatus;
import models.ReimbursementType;
import models.User;
import models.UserRoles;
import services.UserService;
import enums.RStatus;
import enums.ReibursementType;

import java.io.*;
import java.io.IOException;
import java.util.*;

public class Main {

	static Logger log = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		UserDaoHibernate uDao= new UserDaoHibernate();
		UserService uServ = new UserService(uDao);
		UserRoleDaoDB urDao = new UserRoleDaoDB();
		TypeDao tDao = new TypeDao();
		StatusDao sDao = new StatusDao();
		
		ReimbursementDaoDB rDao = new ReimbursementDaoDB();
		//ReimbursementService rServ = new ReimService(rDao);
		UserRoles E = new UserRoles(1,URoles.EMPLOYEE);
		 urDao.insert(E);
		 UserRoles M = new UserRoles(2, URoles.MANAGER);
		 urDao.insert(M);
		 ReimbursementType t = new ReimbursementType(0, ReibursementType.TRAVEL);
		 ReimbursementType t1 = new ReimbursementType(1, ReibursementType.LODGING);
		 ReimbursementType t2 = new ReimbursementType(2, ReibursementType.FOOD);
		 ReimbursementType t3 = new ReimbursementType(3, ReibursementType.OTHER);
		 User u = new User("jonathan","starkey","js@gmail.com","a",M);
		 User u2 = new User("j","s","j@mail.com","password",E);
		 User u3 = new User("t","s","t@est.com","password",E);
		 User u4 = new User("a","s","a@s.com","pass",M);
		 ReimbursementStatus rs = new ReimbursementStatus(0,RStatus.APPROVED);
		 ReimbursementStatus rs1 = new ReimbursementStatus(1,RStatus.DENIED);
		 ReimbursementStatus rs2 = new ReimbursementStatus(2,RStatus.PENDING);
		 Timestamp ts = Timestamp.from(Instant.now());
		 Timestamp ts2 = Timestamp.from(Instant.now());
		 User e1 = uServ.getUserById(2);
		 User m = uServ.getUserById(4);
		 Reimbursement r = new Reimbursement(563,ts,ts2,"Something",rs,t,u2,u4);
		 Reimbursement r2 = new Reimbursement(231,ts,ts2,"Somethingelse",rs,t,u2,u4);
		 Reimbursement r3 = new Reimbursement(5462,ts,ts2,"Somethingdied",rs1,t2,u2,u4);
		// ReimbursementController.getAllReimbursements(null, null);
		 
		 
		 Reimbursement r4 = new Reimbursement(100,ts,"description super good",rs2,t,u2);
		 Reimbursement r5 = new Reimbursement(50,ts,"Even better super good description",rs2,t1,u2);
		 
		 
		 
		 try {
			 
			uDao.createUser(u);
			uDao.createUser(u2);
			uDao.createUser(u3);
			uDao.createUser(u4);
			rDao.createReimbursementType(t);
			rDao.createReimbursementType(t1);
			rDao.createReimbursementType(t2);
			rDao.createReimbursementType(t3);
			rDao.createReimbursementStatus(rs);
			rDao.createReimbursementStatus(rs1);
			rDao.createReimbursementStatus(rs2);
			rDao.createReimbursement(r);
			rDao.createReimbursement(r2);
			rDao.createReimbursement(r3);
			rDao.createReimbursement(r4);
			rDao.createReimbursement(r5);
			sDao.insert(rs);
			sDao.insert(rs1);
			sDao.insert(rs2);
			tDao.insert(t);
			tDao.insert(t1);
			tDao.insert(t2);
			tDao.insert(t3);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println("hit3");
		 //User u1 = uServ.signUp("j","s","js1@gmail.com","a");
		 //User u2 = uServ.signUp("rick","sanchez","rick@mail.com","a");
		 //User u3 = uServ.signUp("morty","sanchez","morty@mail.com","a");
		 //User u4 = uServ.signUp("sam","sanchez","sam@mail.com","a");
		 //String employee = "EMPLOYEE";
		 
		 
		 
		 



		//System.out.println("Are you a employee or manager?");
		
		
		
	}
}
/*
 * case "create": // u = uServ.grabInfo();
 * System.out.print("Please enter you first name: "); String first =
 * scan.nextLine(); System.out.println("Please enter your last name: "); String
 * last = scan.nextLine(); System.out.println("Please enter your email: ");
 * String email = scan.nextLine();
 * System.out.println("Please enter a password: "); password = scan.nextLine();
 * //u = new User(first, last, email, password); try {
 * log.debug("Attempted signup first: " + first + " Last: " + last + " email: "
 * + email); //u = uServ.signUp(u.getFirstName(), u.getLastName(), u.getEmail(),
 * u.getPassword()); log.debug("signup succeeded. first: " + first + " Last: " +
 * last + " email: " + email);
 * //System.out.println("You may now log in with the username: " +
 * u.getUsername()); loggedIn = true; break; } catch (Exception e) {
 * System.out.println("Sorry, we could not process your request");
 * System.out.println("Please try again later"); // done = true; } break;
 * default: System.out.println("didn't recognize or scan error who knows?");
 * System.out.println("input read: " + input.toLowerCase()); break; } break;
 */
