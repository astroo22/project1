package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import models.UserRoles;
import utils.HibernateUtil;

public class UserRoleDaoDB {
	public UserRoleDaoDB() {
		
	}
	public void insert(UserRoles role) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.save(role);
		tx.commit();
	}
}
