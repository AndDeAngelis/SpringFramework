package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.ProductDao;
import model.Customer;
import model.Product;

@Controller
public class CustomerController {
	@Autowired
	private ProductDao productDao;

	@RequestMapping("/user")
	public ModelAndView homePage() {
		Customer customer = new Customer();
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof Customer) {
			customer = (Customer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		return new ModelAndView("user/home", "customer", customer);
	}
	
	public ModelAndView showCatalogue() {
		List<Product> products = new ArrayList<>();
		products = productDao.findAllProducts();
		
		return new ModelAndView("user/catalogue", "products", products);
	}
}
