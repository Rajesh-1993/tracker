package myproject.tracker.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myproject.tracker.model.Expense;
import myproject.tracker.repository.ExpenseRepository;
import myproject.tracker.repository.UserRepository;


@Service
public class ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;

	@Autowired
	private UserRepository userRepository;

	public void addexpense(Expense expense){
		expenseRepository.save(expense);
	}

	public List<Object[]> getbydate(Date fromDate, Date tillDate) {
		return expenseRepository.getExpenseByDate(fromDate, tillDate);

	}

	public List<Object[]> getbyUser(String user) {
		int userId = userRepository.getIdForuser(user);

		return expenseRepository.getExpenseByUser(userId);

	}
	public List<Object[]> getByUserAndDate(Date fromDate, Date tillDate, String user){
		int userId = userRepository.getIdForuser(user);
		return expenseRepository.getExpenseByUserAndDate(fromDate, tillDate, userId);
	}

	public Expense getExpenseById(int expenseId){
		return expenseRepository.findOne(expenseId);
	}

	public void deleteExpenses(int[] ids){
		for (int i : ids) {
			expenseRepository.delete(i);
		}
	}

}
