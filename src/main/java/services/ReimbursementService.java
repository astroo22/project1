package services;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.ReimbursementDao;
import enums.ReibursementType;
import enums.URoles;
import models.Reimbursement;
import models.ReimbursementStatus;
import models.ReimbursementType;
import models.User;
import models.UserRoles;

public class ReimbursementService {
	private ReimbursementDao rDao;
	public ReimbursementService(ReimbursementDao r) {
		this.rDao = r;
	}
	public void addReimbursement(String reimtype, double amount,Timestamp ts,String description, ReimbursementStatus status, User employee) {
		System.out.println(reimtype);
		 
		if(reimtype.equals("LODGING")) {
			System.out.println("Please no updates to thing");
			ReimbursementType t = new ReimbursementType(1, ReibursementType.LODGING);
			Reimbursement r = new Reimbursement(amount,ts,description,status,t,employee);
			System.out.println(r);
			rDao.createReimbursement(r);
		}else if(reimtype.equals("TRAVEL")) {
			System.out.println("Please no updates to thing");
			ReimbursementType t = new ReimbursementType(0, ReibursementType.TRAVEL);
			Reimbursement r = new Reimbursement(amount,ts,description,status,t,employee);
			System.out.println(r);
			rDao.createReimbursement(r);
		}else if(reimtype.equals("FOOD")) {
			System.out.println("Please no updates to thing");
			ReimbursementType t = new ReimbursementType(2, ReibursementType.FOOD);
			Reimbursement r = new Reimbursement(amount,ts,description,status,t,employee);
			System.out.println(r);
			rDao.createReimbursement(r);
		}else if(reimtype.equals("OTHER")) {
			System.out.println("Please no updates to thing");
			ReimbursementType t = new ReimbursementType(3, ReibursementType.OTHER);
			Reimbursement r = new Reimbursement(amount,ts,description,status,t,employee);
			System.out.println(r);
			rDao.createReimbursement(r);
		}
	}
	public List<Reimbursement> getAllReimbursementsForUser(User u){
		List<Reimbursement> results = new ArrayList<Reimbursement>();
		List<Reimbursement> penL = rDao.getAllPendingReimbursementsForUser(u);
		List<Reimbursement> denL = rDao.getAllDeniedReimbursementsForUser(u);
		List<Reimbursement> appL = rDao.getAllApprovedReimbursementsForUser(u);
		for(Reimbursement r:penL) {
			results.add(r);
		}
		for(Reimbursement r:denL) {
			results.add(r);
		}
		for(Reimbursement r:appL) {
			results.add(r);
		}
		return results;
	}
	public void updateReimbursement(int id, double amount, Timestamp timestamp, Timestamp ts, String description,
			ReimbursementStatus status, ReimbursementType type,User employe, User manager) {
		Reimbursement r = new Reimbursement(id,amount,timestamp, ts,description,status,type,employe,manager);
		rDao.updateReimbursement(r);
	}
	
	public List<Reimbursement> getAllReimbursements(){
		return rDao.getAllReimbursements();
	}
	public List<Reimbursement> getAllDeniedReimbursements(){
		return rDao.getAllDeniedReimbursements();
	}
	public List<Reimbursement> getAllPendingReimbursements(){
		return rDao.getAllPendingReimbursements();
	}
	public List<Reimbursement> getAllApprovedReimbursements(){
		return rDao.getAllApprovedReimbursements();
	}
	

}
