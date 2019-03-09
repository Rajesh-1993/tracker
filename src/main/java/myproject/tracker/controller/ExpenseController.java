package myproject.tracker.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import myproject.tracker.model.Expense;
import myproject.tracker.model.User;
import myproject.tracker.service.ExpenseService;
import myproject.tracker.service.UserService;

@Controller
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@Autowired
	private UserService userService;

	@Autowired
	public EntityManager entityManager;

	@Value("${address}")
	private String address;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setDisallowedFields(new String[] { "user" });
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, "createdDate", new CustomDateEditor(format, false));
	}

	@RequestMapping(value = "/expense", method = RequestMethod.GET)
	public String hello(Model model) {

		List<String> users = userService.getOnlyUsers();
		model.addAttribute("users", users);
		String addressAddEmployee = address+"trackEmployee";
		String addressTracker = address+"tracker";
		model.addAttribute("empaddress", addressAddEmployee);
		model.addAttribute("address", addressTracker);
		return "heloo";
	}

	@RequestMapping(value = "/addUsers", method = RequestMethod.POST)
	public String addCategories(@RequestParam("adduser") String newUser) {
		User user = new User();
		user.setuser(newUser);
		userService.adduser(user);

		return "redirect:/expense";
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public String deleteuser(@RequestParam("deleteuser") String user) {
		userService.deleteuser(user);
		return "redirect:/expense";
	}

	@RequestMapping(value = "/expenseSubmission", method = RequestMethod.POST)
	public String greetingSubmit( @ModelAttribute Expense expense, @RequestParam("user") String user,
			BindingResult result, Model model ) throws IOException {

		if (result.hasErrors()) {
			return "heloo";
		}
		else{
			int userId = userService.getuserId(user);
			expense.setuserId(userId);
			expenseService.addexpense(expense);
		
			model.addAttribute("Saved", "Expense Saved");
			return "redirect:/expense";
		}
	}
}
