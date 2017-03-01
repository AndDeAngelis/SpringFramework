package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.CustomerDao;
import model.Customer;

@Controller
public class AcmeController {
	@Autowired
	private CustomerDao customerDao;
	
	@RequestMapping(value = {"/", "/index"})
	public ModelAndView welcome() {
		return new ModelAndView("index");
	}
	
	@RequestMapping("/login")
	public ModelAndView loginForm() {
		Customer customer = new Customer();
		return new ModelAndView("login", "customer", customer);
	}
	
	@RequestMapping("/registration")
	public ModelAndView registration(@ModelAttribute("customer") Customer customer) {
		customer.setRole("ROLE_USER");
		customerDao.create(customer);
		
		String message = "Registrazione completata!";
		return new ModelAndView("user/home", "message", message).addObject("customer", customer);
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView doLogout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		return new ModelAndView("index");
	}
}
