package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import enums.ReibursementType;
@Entity
@Table(name="re_type")
public class ReimbursementType {
	 @Id
	 @Column(name="re_type_id")
	 private int id;
	 
	 @Column(name="re_type")
	 @Enumerated(EnumType.STRING)
	 private ReibursementType type;

	public ReimbursementType(int id, ReibursementType type) {
		super();
		this.id = id;
		this.type = type;
	}
	public ReimbursementType() {
		
	}
	@Override
	public String toString() {
		return "" + type;
	}
	
 
}
