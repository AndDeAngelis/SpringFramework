package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dao.ProductDao;
import model.Customer;
import model.Product;

@Controller
public class AdminController {
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping("/admin")
	public ModelAndView homeAdmin() {
		Customer customer = new Customer();
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof Customer) {
			customer = (Customer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		return new ModelAndView("admin/homeAdmin", "customer", customer);
	}
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public ModelAndView addProduct() {
		Product product = new Product();
		return new ModelAndView("admin/addProduct", "product", product);
	}
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public ModelAndView showNewProduct(@ModelAttribute("product") Product product) {
		productDao.create(product);
		return new ModelAndView("admin/addedProduct", "product", product);
	}
}
