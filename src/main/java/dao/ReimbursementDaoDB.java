package dao;

import java.util.ArrayList;
import java.util.List;



import org.hibernate.Session;
import org.hibernate.Transaction;

import models.Reimbursement;
import models.ReimbursementStatus;
import models.ReimbursementType;
import models.User;
import utils.HibernateUtil;

public class ReimbursementDaoDB implements ReimbursementDao {
	public ReimbursementDaoDB() {
		
	}

	@Override
	public void createReimbursement(Reimbursement r) {
		// TODO Auto-generated method stub
		  Session ses = HibernateUtil.getSession();
          Transaction tx = ses.beginTransaction();
          ses.save(r);
          tx.commit();
         
	}
	/*
	@Override
	public ReimbursementStatus getReimbursementStatus(int id) {
		Session ses = HibernateUtil.getSession();
		System.out.println("This method doesn't work");
		ReimbursementStatus rs = (ReimbursementStatus) ses.createQuery("select * from reimbursement_status where ");
		return rs;
	}*/
	@Override
	public List<Reimbursement> selectAllReimbursements() {
		// TODO Auto-generated method stub
		 Session ses = HibernateUtil.getSession();
		 System.out.println("SelectAllReimbursements before Query");
         List<Reimbursement> l = ses.createQuery("from Reimbursement", Reimbursement.class).list();
         System.out.println("SelectAllReimbursements after Query");
         return l;
	}

	@Override
	public List<Reimbursement> selectUserReimbursement(int id) {
		// TODO Auto-generated method stub
		Session ses = HibernateUtil.getSession();
		System.out.println("SelectUserReimbursements before Query");
        List<Reimbursement> rList = ses.createQuery("from Reimbursement where employee_id="+ id, Reimbursement.class).list();
        System.out.println("SelectUserReimbursements after Query");
        return rList;
	}

	@Override
	public void updateReimbursement(Reimbursement rm) {
		// TODO Auto-generated method stub
		   Session ses = HibernateUtil.getSession();
           Transaction tx = ses.beginTransaction();
           ses.merge(rm);
           tx.commit();
		
	}
	@Override
	public List<Reimbursement> getAllReimbursements() {
		// TODO Auto-generated method stub
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> rList = ses.createQuery("from Reimbursement",Reimbursement.class).list();
		return rList;
	}

	@Override
	public List<Reimbursement> getAllPendingReimbursements() {
		// TODO Auto-generated method stub
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> rList = ses.createQuery("from Reimbursement where re_status_id = 2", Reimbursement.class).list();
		return rList;
	}

	@Override
	public List<Reimbursement> getAllApprovedReimbursements() {
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> rList = ses.createQuery("from Reimbursement where re_status_id = 0", Reimbursement.class).list();
		return rList;
	}

	@Override
	public List<Reimbursement> getAllDeniedReimbursements() {
		// TODO Auto-generated method stub
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> rList = ses.createQuery("from Reimbursement where re_status_id = 1", Reimbursement.class).list();
		return rList;
	}

	@Override
	public List<Reimbursement> getAllPendingReimbursementsForUser(User u) {
		// TODO Auto-generated method stub
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> rList = ses.createQuery("from Reimbursement where re_status_id = 2", Reimbursement.class).list();
		List<Reimbursement> result = new ArrayList<Reimbursement>();
		for(Reimbursement curr:rList) {
			if(curr.getEmployee()==u) {
				result.add(curr);
			}
		}
		return result;
	}

	@Override
	public List<Reimbursement> getAllApprovedReimbursementsForUser(User u) {
		// TODO Auto-generated method stub
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> rList = ses.createQuery("from Reimbursement where re_status_id = 0", Reimbursement.class).list();
		List<Reimbursement> result = new ArrayList<Reimbursement>();
		for(Reimbursement curr:rList) {
			if(curr.getEmployee()==u) {
				result.add(curr);
			}
		}
		return result;
	}

	@Override
	public List<Reimbursement> getAllDeniedReimbursementsForUser(User u) {
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> rList = ses.createQuery("from Reimbursement where re_status_id = 3", Reimbursement.class).list();
		List<Reimbursement> result = new ArrayList<Reimbursement>();
		for(Reimbursement curr:rList) {
			if(curr.getEmployee()==u) {
				result.add(curr);
			}
		}
		return result;
	}
	@Override
	public void createReimbursementType(ReimbursementType t) {
		// TODO Auto-generated method stub
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.save(t);
		tx.commit();
		
	}

	@Override
	public void createReimbursementStatus(ReimbursementStatus t) {
		// TODO Auto-generated method stub
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.save(t);
		tx.commit();
	}

	@Override
	public Reimbursement getReimbursementById(int id) {
		// TODO Auto-generated method stub
		Session ses = HibernateUtil.getSession();
		Reimbursement r = ses.get(Reimbursement.class, id);
		return r;
	}

}
