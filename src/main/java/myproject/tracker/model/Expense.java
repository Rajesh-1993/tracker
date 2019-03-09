package myproject.tracker.model;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class Expense {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int expenseId;

	private String expenseName;

	private double amount;


	private Date createdDate;

	private String comments;



	private int userId;



	public Expense() {
		// TODO Auto-generated constructor stub
	}


	public Expense(int expenseId, String expenseName, double amount, Date createdDate, String comments) {
		super();
		this.expenseId = expenseId;
		this.expenseName = expenseName;
		this.amount = amount;
		this.createdDate = createdDate;
		this.comments = comments;
	}


	public int getExpenseId() {
		return expenseId;
	}


	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}


	public String getExpenseName() {
		return expenseName;
	}


	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public int getuserId() {
		return userId;
	}


	public void setuserId(int userId) {
		this.userId = userId;
	}












}