package models;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import enums.URoles;

@Entity
@Table(name="user_roles")
public class UserRoles{

    @Id
    @Column(name="user_id")
    private int id;
    
    @Column(name="user_role")
    @Enumerated(EnumType.STRING)
    private URoles user_role;

    public UserRoles() {
    }

    public UserRoles(int id, URoles userRole) {
        super();
        this.id = id;
        this.user_role = userRole;
    }

	public URoles getUser_role() {
		return user_role;
	}

	public void setUser_role(URoles user_role) {
		this.user_role = user_role;
	}

	@Override
	public String toString() {
		return "UserRoles [user_role=" + user_role + "]";
	}
}
	

