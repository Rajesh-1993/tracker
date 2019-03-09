package myproject.tracker.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import myproject.tracker.model.Expense;
import myproject.tracker.model.User;
import myproject.tracker.service.ExpenseService;
import myproject.tracker.service.UserService;

@Controller
public class TrackerController {


	@Autowired
	private ExpenseService expenseService;

	@Autowired
	private UserService userService;





	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setDisallowedFields(new String[] { "user" });
		binder.setDisallowedFields(new String[] { "image" });
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, "createdDate", new CustomDateEditor(format, false));
	}

	@RequestMapping(value = "/tracker", method = RequestMethod.GET)
	public String trackerPage(Model model,HttpServletResponse response) throws IOException {

		List<String> users = userService.getOnlyUsers();
		//pieChart.createAndSavePieChart(pieData);

		model.addAttribute("users", users);
		return "tracker";
	}

	@RequestMapping(value = "/dateTracker", method = RequestMethod.POST)
	public String trackViaDate(Model model, @RequestParam("fromDate") String fromDate,
			@RequestParam("tillDate") String tillDate, HttpServletResponse response) throws ParseException, IOException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date fDate;
		Date tDate;
			
		double total = 0;
		
		 fDate = fromDate ==null?new Date():format.parse(fromDate);
		 tDate = tillDate ==null?new Date():format.parse(tillDate);
		List<Object[]> object = expenseService.getbydate(fDate, tDate);





		for (int i=0; i<object.size(); i++){
			Object[] row = object.get(i);
			double one = (double) row[1];
			total = total +one;
		}


		model.addAttribute("total", total);
		model.addAttribute("from", fDate);
		model.addAttribute("to", tDate);
		model.addAttribute("expense", object);
		return "dataFromDate";
	}

	@RequestMapping(value = "/userTracker", method = RequestMethod.POST)
	public String trackViauser(Model model, @RequestParam("trackuser") String user) throws IOException {
		double total = 0;
		List<Object[]> expenses = expenseService.getbyUser(user);

		for (int i=0; i<expenses.size(); i++){
			Object[] row = expenses.get(i);
			double one = (double) row[1];
			total = total +one;
		}





		model.addAttribute("total", total);
		model.addAttribute("user", user);
		model.addAttribute("expense", expenses);
		return "dataFromUser";
	}

	@RequestMapping(value = "/dateAnduserTracker", method = RequestMethod.POST)
	public String trackViaDateAnduser(Model model, @RequestParam("trackuser") String user,
			@RequestParam("fromDate") String fromDate, @RequestParam("tillDate") String tillDate)
					throws ParseException, IOException {
		double total = 0;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date fDate;
		Date tDate;
		if(tillDate ==null)
			 fDate = new Date();
		else if( fromDate==null )
			 tDate = new Date();
		 fDate = fromDate ==null?new Date():format.parse(fromDate);
		 tDate = tillDate ==null?new Date():format.parse(tillDate);
		List<Object[]> cdexpense = expenseService.getByUserAndDate(fDate, tDate, user);
		for (int i=0; i<cdexpense.size(); i++){
			Object[] row = cdexpense.get(i);
			double one = (double) row[1];
			total = total +one;
		}


		model.addAttribute("expense", cdexpense);
		model.addAttribute("total", total);
		model.addAttribute("from", fDate);
		model.addAttribute("to", tDate);
		model.addAttribute("user", user);
		return "DataFromuserAndDate";
	}

	@RequestMapping(value = "/deleteExpenseFromUser", method = RequestMethod.POST)
	public String deleteExpenseuser(Model model, @RequestParam("checkboxgroup") String[] expenseIds) {
		int[] result = new int[expenseIds.length];
		for (int i = 0; i < expenseIds.length; i++) {
			result[i] = Integer.parseInt(expenseIds[i]);
		}
		expenseService.deleteExpenses(result);
		return "redirect:/tracker";
	}

	@RequestMapping(value = "/deleteExpenseFromDate", method = RequestMethod.POST)
	public String deleteExpenseDate(Model model, @RequestParam("checkboxgroup") String[] expenseIds) {
		int[] result = new int[expenseIds.length];
		for (int i = 0; i < expenseIds.length; i++) {
			result[i] = Integer.parseInt(expenseIds[i]);
		}
		expenseService.deleteExpenses(result);
		return "redirect:/tracker";
	}




	@RequestMapping(value = "/edit/{expenseId}", method = RequestMethod.GET)
	public String EditController(@PathVariable("expenseId") String expId, Model model) {
		int expenseId = Integer.parseInt(expId);
		Expense expense = expenseService.getExpenseById(expenseId);
		User user = userService.getuserById(expense.getuserId());
		List<String> users = userService.getOnlyUsers();
		model.addAttribute("expense", expense);
		model.addAttribute("users", users);
		model.addAttribute("Stringuser", user.getuser());

		return "edit";
	}

	@RequestMapping(value = "/UpdateExpenseSubmission/{expenseId}", method = RequestMethod.POST)
	public String UpdateExpenseController(@PathVariable("expenseId") String expId, Model model,
			@ModelAttribute Expense expense, @RequestParam("user") String user,
			BindingResult result) throws IOException {

		if(result.hasErrors()){
			int expenseId = Integer.parseInt(expId);
			return "redirect:/edit/"+expenseId;
		}

		else{
			int userId = userService.getuserId(user);
			expense.setuserId(userId);
			expenseService.addexpense(expense);
			return "redirect:/tracker";
		}
	}}
