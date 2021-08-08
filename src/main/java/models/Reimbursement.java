package models;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import enums.ReibursementType;


@Entity
@Table(name="Reimbursement")
public class Reimbursement {
	@Id
	@Column(name="reimbursement_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name ="reimb_amount",nullable = false)
	private double reimb_Amount;
	
	@Column(name ="submitted", nullable = false)
	private Timestamp submitted;
	
	@Column(name ="resolved")
	private Timestamp resolved;
	
	@Column(name ="description",nullable = false)
	private String description;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="re_status_id")
	private ReimbursementStatus rStatus;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="re_type_id")
	private ReimbursementType type;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id")
	private User employee;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="manager_id")
	private User manager;
	
	public Reimbursement() {
		
	}
	
	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", reimb_Amount=" + reimb_Amount + ", submitted=" + submitted + ", resolved="
				+ resolved + ", description=" + description + ", rStatus=" + rStatus + ", type=" + type + ", employee="
				+ employee + ", manager=" + manager + "]";
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public Reimbursement(double reimb_Amount, Timestamp submitted, String description, ReimbursementStatus rStatus,
			ReimbursementType type, User employee) {
		super();
		this.reimb_Amount = reimb_Amount;
		this.submitted = submitted;
		this.description = description;
		this.rStatus = rStatus;
		this.type = type;
		this.employee = employee;
	}

	public Reimbursement(double reimb_Amount, Timestamp submitted, Timestamp resolved, String description,
			ReimbursementStatus rStatus, ReimbursementType type, User employee, User manager) {
		super();
		this.reimb_Amount = reimb_Amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.rStatus = rStatus;
		this.type = type;
		this.employee = employee;
		this.manager = manager;
	}

	public Reimbursement(int id, double reimb_Amount, Timestamp submitted, Timestamp resolved, String description,
			ReimbursementStatus rStatus, ReimbursementType type, User employee, User manager) {
		super();
		this.id = id;
		this.reimb_Amount = reimb_Amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.rStatus = rStatus;
		this.type = type;
		this.employee = employee;
		this.manager = manager;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getReimb_Amount() {
		return reimb_Amount;
	}

	public void setReimb_Amount(double reimb_Amount) {
		this.reimb_Amount = reimb_Amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public ReimbursementStatus getrStatus() {
		return rStatus;
	}

	public void setrStatus(ReimbursementStatus rStatus) {
		this.rStatus = rStatus;
	}

	public ReimbursementType getType() {
		return type;
	}

	public void setType(ReimbursementType type) {
		this.type = type;
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}
	
	
}
