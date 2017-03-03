package dao;

import java.util.List;

import model.Customer;

public interface CustomerDao {
	public boolean create(Customer customer);
	public Customer findCustomerByUsername(String username);
	public List<Customer> findAllCustomers();
}
