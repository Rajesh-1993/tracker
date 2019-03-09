package myproject.tracker.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import myproject.tracker.model.Expense;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Integer> {

	@Query("SELECT e.expenseName, e.amount, e.createdDate, e.comments, e.expenseId, c.user FROM Expense e, User c WHERE c.userId = e.userId AND e.createdDate BETWEEN :fromDate AND :tillDate")
	public List<Object[]> getExpenseByDate(@Param("fromDate") Date fromDate, @Param("tillDate") Date tillDate);

	@Query("SELECT e.expenseName, e.amount, e.createdDate, e.comments, e.expenseId, c.user FROM Expense e, User c WHERE c.userId = e.userId AND e.userId = :userId")
	public List<Object[]> getExpenseByUser(@Param("userId") int userId);

	@Query("SELECT e.expenseName, e.amount, e.createdDate, e.comments, e.expenseId, c.user FROM Expense e, User c WHERE c.userId = e.userId AND e.userId = :userId AND e.createdDate BETWEEN :fromDate AND :tillDate")
	public List<Object[]> getExpenseByUserAndDate(@Param("fromDate") Date fromDate, @Param("tillDate") Date tillDate, @Param("userId") int userId);



}
