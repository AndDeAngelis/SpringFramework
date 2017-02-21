package controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import model.Customer;

@Controller
public class CustomerController {

	@RequestMapping("/user")
	public ModelAndView homePage() {
		Customer customer = new Customer();
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof Customer) {
			customer = (Customer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		return new ModelAndView("user/home", "customer", customer);
	}
}
