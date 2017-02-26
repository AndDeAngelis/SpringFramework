package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping("/user/catalogue")
	public ModelAndView showCatalogue() {
		List<Product> products = new ArrayList<>();
		products = productDao.findAllProducts();
		
		Customer customer = new Customer();
		
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof Customer) {
			customer = (Customer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		
		return new ModelAndView("user/catalogue", "products", products).addObject("customer", customer);
	}

	@RequestMapping("/user/catalogue/{category}")
	public ModelAndView showCategorySection(@PathVariable String category) {
		List<Product> products = new ArrayList<>();
		products = productDao.findProductsByCategory(category);
		
		Customer customer = new Customer();
		
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof Customer) {
			customer = (Customer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		
		return new ModelAndView("user/category", "products", products).addObject("customer", customer);
	}
	
	@RequestMapping("/user/catalogue/{category}/{id}")
	public ModelAndView showProductDetails(@PathVariable String category, @PathVariable String id) {
		Product product = productDao.findProductById(id);
		
		Customer customer = new Customer();
		
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof Customer) {
			customer = (Customer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		
		return new ModelAndView("user/productDetails", "product", product).addObject("customer", customer);
	}
	
	@RequestMapping("/user/profile")
	public ModelAndView showCustomerDetails() {
		Customer customer = new Customer();
		
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof Customer) {
			customer = (Customer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}		
		
		return new ModelAndView("user/userDetails", "customer", customer);
	}
}
