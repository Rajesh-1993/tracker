package myproject.tracker.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import myproject.tracker.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {



	@Query("select c.user from User c")
	public List<String> getOnlyUsers();

	@Query("select c.userId from User c where c.user = :user")
	public int getIdForuser(@Param("user") String user);


	public User findByuser(String user);

	
}
