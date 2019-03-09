package myproject.tracker.model;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Cacheable(false)
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	private String user;







	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int userId, String user) {
		super();
		this.userId = userId;
		this.user = user;
	}

	public int getuserId() {
		return userId;
	}

	public void setuserId(int userId) {
		this.userId = userId;
	}

	public String getuser() {
		return user;
	}

	public void setuser(String user) {
		this.user = user;
	}


}
