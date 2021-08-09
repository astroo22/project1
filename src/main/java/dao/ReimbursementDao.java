package dao;

import java.util.List;

import models.Reimbursement;
import models.ReimbursementStatus;
import models.ReimbursementType;
import models.User;

public interface ReimbursementDao {
	public void createReimbursement(Reimbursement r);
	List<Reimbursement> selectAllReimbursements();
	public List<Reimbursement> selectUserReimbursement(int id);
	public void updateReimbursement(Reimbursement rm);
	public List<Reimbursement> getAllReimbursements();
	public List<Reimbursement> getAllPendingReimbursements();
	public List<Reimbursement> getAllApprovedReimbursements();
	public List<Reimbursement> getAllDeniedReimbursements();
	public List<Reimbursement> getAllPendingReimbursementsForUser(User u);
	public List<Reimbursement> getAllApprovedReimbursementsForUser(User u);
	public List<Reimbursement> getAllDeniedReimbursementsForUser(User u);
	public void createReimbursementType(ReimbursementType t);
	public void createReimbursementStatus(ReimbursementStatus t);
	public Reimbursement getReimbursementById(int id);
	//public ReimbursementStatus getReimbursementStatus(int id);
	
	
}
