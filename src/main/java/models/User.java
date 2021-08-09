package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import enums.URoles;
@Entity
@Table(name="users")
@JsonIgnoreProperties(value= {"hibernateLazyInitializer", "handler"})
public class User{
	@Id
	@Column(name="user_id", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="first_name",nullable=false)
	private String firstName;
	@Column(name="last_name", nullable = false)
	private String lastName;
	@Column(name="username",nullable=false,unique=true)
	private String username;
	@Column(name="email", nullable=false, unique=true)
	private String email;
	@Column(name="password",nullable=false)
	private String password;
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name ="user_role")
	private UserRoles user_role;
	@OneToMany(mappedBy="employee",fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Reimbursement> employees = new ArrayList<Reimbursement>();
	
	@OneToMany(mappedBy="manager",fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Reimbursement> manager = new ArrayList<Reimbursement>();
	public User() {
		//posts = new ArrayList<Post>();
	}

	@Override
	public String toString() {
		return  "\n"+"Id: " + id + "\n" + "First Name: " + firstName + "\n" + "Last Name: " + lastName + "\n" + "Username: " + username
				+ "\n" + "Email: " + email + "\n" + "Password: " + password + "\n"+ "Role: " + user_role + "\n";
	}


	public User(int id, String firstName, String lastName, String email, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = firstName + lastName + (new Random().nextInt(9000) + 1000);
		this.email = email;
		this.password = password;
		//this.posts = new ArrayList<Post>();
	}
	/*
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", email=" + email + ", password=" + password + ", user_role=" + user_role + ", employees="
				+ employees + ", manager=" + manager + "]";
	}
	*/
	public UserRoles getUser_role() {
		return user_role;
	}

	public void setUser_role(UserRoles user_role) {
		this.user_role = user_role;
	}

	//Used to send user info to the database because the db auto generates the id
	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = firstName + lastName + (new Random().nextInt(9000) + 1000);
		this.email = email;
		this.password = password;
		//this.posts = new ArrayList<Post>();
	}
	
	//User to get user info from the database
	public User(int id, String firstName, String lastName, String username, String email, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		//this.posts = new ArrayList<Post>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public User(String firstName, String lastName,  String email, String password,
			UserRoles user_role) {
		super();
		this.id = new Random().nextInt(900000000) + 100000000;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = firstName + lastName + (new Random().nextInt(9000) + 1000);
		this.email = email;
		this.password = password;
		this.user_role = user_role;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
