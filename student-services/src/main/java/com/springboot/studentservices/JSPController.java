package com.springboot.studentservices;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springboot.studentservices.entities.Companies;
import com.springboot.studentservices.entities.Stock;
import com.springboot.studentservices.entities.User;
import com.springboot.studentservices.repositories.CompaniesRepository;
import com.springboot.studentservices.repositories.RoleRepository;
import com.springboot.studentservices.repositories.StockRepository;
import com.springboot.studentservices.repositories.UserRepository;
import com.springboot.studentservices.userservices.SecurityService;
import com.springboot.studentservices.userservices.UserService;
import com.springboot.studentservices.userservices.UserValidator;

@Controller
public class JSPController {
	
		private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		// Create repositories for our user entered data
		@Autowired
		private CompaniesRepository companyRepo;
		
		@Autowired
		private StockRepository stockRepo;
		
		@Autowired
		private UserRepository userRepo;
		
		@Autowired
		private RoleRepository roleRepo;

		@RequestMapping("/")
		public String welcomePage(Map<String, Object> model) {
			return "welcome";
		}
		
		@RequestMapping(value="/signup",method=RequestMethod.GET)
		public String signupPage(ModelMap model) {
			model.addAttribute("companies", new Companies());
			return "signup";
		}
		
		@RequestMapping("/admin") 
		public String adminPage(ModelMap model) {
			model.addAttribute("companiesInfo",companyRepo.findAll());
			return "admin";
		}
		
		 	@Autowired
		    private UserService userService;

		    @Autowired
		    private SecurityService securityService;

		    @Autowired
		    private UserValidator userValidator;

		    @RequestMapping(value = "/registration", method = RequestMethod.GET)
		    public String registration(Model model) {
		        model.addAttribute("userForm", new User());
		        return "registration";
		    }

		    @RequestMapping(value = "/registration", method = RequestMethod.POST)
		    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		        userValidator.validate(userForm, bindingResult);

		        if (bindingResult.hasErrors()) {
		            return "registration";
		        }

		        userService.save(userForm);

		        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

		        return "redirect:/welcome";
		    }

		    @RequestMapping(value = "/login", method = RequestMethod.GET)
		    public String login(Model model, String error, String logout) {
		        if (error != null)
		            model.addAttribute("error", "Your username and password is invalid.");

		        if (logout != null)
		            model.addAttribute("message", "You have been logged out successfully.");

		        return "login";
		    }

		    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
		    public String welcome(Model model) {
		        return "welcome";
		    }
	
		
		@RequestMapping(value="/addCompany",method=RequestMethod.POST)
		public String submit(@Valid @ModelAttribute("companies") Companies company,BindingResult result,ModelMap model) {
			if (result.hasErrors()) {
				model.addAttribute("status", "<span class='text-capitalize text-danger mt-2'>Registration failed due to missing/insufficient information! Check your user data and try again!</span>");
				return "signup";
			}
			// Create a new user 
			User user = new User();
	        // Set password to its hashed equivalent
	        // for storage in the database
			String encPass = encoder.encode(company.getPassword());
	        company.setPassword(encPass);
	        // This is supposed to be our foreign key to the Users table...
	        company.setUser(user);
	        user.setPassword(encPass);
	        user.setUsername(company.getEmail());
	        userRepo.save(user);
	        companyRepo.save(company);
	        model.addAttribute("status","<span class='text-capitalize text-success mt-2'>Registration successful!</span>");
	        // Used to reset the form 
	        model.addAttribute("companies",new Companies());
			return "signup";
		}
		
		@RequestMapping(value="/addStock",method=RequestMethod.GET)
		public String addStockPage(Model model) {
			model.addAttribute("stock",new Stock());
			return "addStock";
		}
		
		
		@RequestMapping(value="/addStockResult",method=RequestMethod.POST)
		public String submit(@Valid @ModelAttribute("stock") Stock stock,BindingResult result,ModelMap model) {
			if (result.hasErrors()) {
				model.addAttribute("status", "<span class='text-capitalize text-danger mt-2'>Registration failed due to missing/insufficient information! Check your user data and try again!</span>");
				return "addStock";
			}
	        // Set password to its hashed equivalent
	        // for storage in the database
	        stockRepo.save(stock);
	        model.addAttribute("status","<span class='text-capitalize text-success mt-2'>Registration successful!</span>");
	        // Used to reset the form 
	        model.addAttribute("stock",new Stock());
			return "addStock";
		}
		

}
