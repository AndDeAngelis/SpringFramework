package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dao.CustomerDao;
import model.Customer;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private CustomerDao customerDao;

	@Override
	public Customer loadUserByUsername(String username) throws UsernameNotFoundException {
		return customerDao.findCustomerByUsername(username);
	}
}
