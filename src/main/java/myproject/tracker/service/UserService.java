package myproject.tracker.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myproject.tracker.model.User;
import myproject.tracker.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	public UserRepository userRepository;

	@Autowired

	public List<String> getOnlyUsers(){

		return userRepository.getOnlyUsers();
	}

	public List<User> getuser(){
		List<User> users = new ArrayList<User>();
		userRepository.findAll()
		.forEach(users::add);
		return users;
	}

	public void adduser(User user){
		userRepository.save(user);
	}

	public int getuserId(String user){
		return userRepository.getIdForuser(user);
	}

	public void deleteuser(String user){

		User deleteuser = userRepository.findByuser(user);
		if(deleteuser==null){

		}
		userRepository.delete(deleteuser.getuserId());
	}

	public int saveOrgetuser(String user){
		int id=0;
		user.trim();

		/*id = userRepository.getIdForuser(user);
		if(id==0){
			user obj = new user();
			obj.setuser(user);
			adduser(obj);
			return userRepository.getIdForuser(user);
		}
		else{
			return id;
		}*/

		List<String> users = getOnlyUsers();
		if(users.contains(user)){
			id = userRepository.getIdForuser(user);
			return id;
		}
		else{
			User obj = new User();
			obj.setuser(user);
			adduser(obj);
			return userRepository.getIdForuser(user);
		}
	}

	public User getuserById(int id){
		return userRepository.findOne(id);
	}

}
