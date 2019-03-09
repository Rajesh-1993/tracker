package myproject.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import myproject.tracker.model.User;
import myproject.tracker.service.UserService;

@RestController
@RequestMapping("/rest")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, value = "/user")
	public void adduser(@RequestBody User user){
		userService.adduser(user);
	}

	@RequestMapping(method = RequestMethod.GET, value ="/user")
	public List<User> getuser(){
		return userService.getuser();
	}

	@RequestMapping(method = RequestMethod.GET, value ="/userList")
	public List<String> getOneuser(){
		return userService.getOnlyUsers();
	}
}
