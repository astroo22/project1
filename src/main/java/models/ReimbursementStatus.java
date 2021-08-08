package models;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import enums.RStatus;

@Entity
@Table(name="Reimbursement_status")
public class ReimbursementStatus {
	@Id
	@Column(name = "re_status_id")
	private int id;

	
	@Enumerated(EnumType.STRING)
	@Column(name ="re_status")
	private RStatus reimbursement_status;
	
	public ReimbursementStatus() {
	    }

	public ReimbursementStatus(int id, RStatus reibursement_status) {
	        super();
	        this.id = id;
	        this.reimbursement_status = reibursement_status;
	}
}
/*private enum reimbursement_status{
SUBMITTED,
APPROVED,
DENIED,
PENDING
};*/
