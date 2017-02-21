package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import model.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {
	private static final String COLLECTION = "customers";
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void create(Customer customer) {
		this.mongoTemplate.insert(customer, COLLECTION);
	}

	@Override
	public Customer findCustomerByUsername(String username) {
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(username));
		return this.mongoTemplate.findOne(query, Customer.class, COLLECTION);
	}

	@Override
	public List<Customer> findAllCustomers() {
		return this.mongoTemplate.findAll(Customer.class, COLLECTION);
	}
}
